from backoffice.headercolumnparser import *

RETURNING = "RETURNING"

INDENTATION_SPACES = "    "
def _divide(row:dict[str,str], foreign_keys:list[ForeignKey]) -> list[tuple[dict[str,str],str]]:
    """
    divide the given row input into a multiple parts, each of which is input to one relational table
    :param row:
    :return:
    """
    current_table = {}
    result_list = []
    previous_table_name = None
    for c,v in row.items():
        current_table_name = table_name(c)
        if current_table_name != previous_table_name and len(current_table) > 0:
            returning_columns = [f.reference_column() for f in foreign_keys if f.reference_table() == previous_table_name]
            result_list.append((current_table, None if len(returning_columns) == 0 else returning_columns[0]))
            current_table = {}
        current_table[c] = v
        previous_table_name = current_table_name
    result_list.append((current_table, None))
    return result_list

def _quote(value:str):
    if value == 'CURRENT_TIME':
        return value
    return "'" + value + "'"

def _table_name(single_table_record: dict[str,str]) -> str:
    return table_name(next(iter(single_table_record)))

def _is_composite_key(header_column: str) -> bool:
    return header_column.startswith("*")

def _to_using_on_clause(single_table_record:dict, cte_prefix:str, alias:str) -> tuple[str,str]:
    """
    :param single_table_record:
    :param alias:
    :return: USING ..., ON ...
    """
    def _select_clause() -> str:
        def _as_clause(column:str, value:str) -> str:
            if is_foreign_key(column):
                foreign_key = ForeignKey(column)
                return cte_prefix + foreign_key.reference() + ' AS ' + table_column_name(column)
            elif column.endswith('#'):
                return value + ' AS ' + table_column_name(column)
            else:
                return _quote(value) + ' AS ' + table_column_name(column)
        as_list = [ _as_clause(c,v) for c,v in single_table_record.items() if _is_composite_key(c)]
        foreign_keys = [ForeignKey(c) for c, v in single_table_record.items() if is_foreign_key(c)]
        if len(foreign_keys) > 0:
            reference_tables = [cte_prefix + f.reference_table() for f in foreign_keys]
            return "SELECT " + ','.join(as_list) + ' FROM ' + ','.join(reference_tables)
        return "SELECT " + ','.join(as_list)
    def _on_condition() -> str:
        table_name = _table_name(single_table_record)
        condition = [table_name + '.' + table_column_name(c) + " = u." + table_column_name(c) for c,v in single_table_record.items() if _is_composite_key(c)]
        return ' AND '.join(condition)
    return "USING (" + _select_clause() + ") AS " + alias, 'ON ' + _on_condition()

def _to_insert_clause(single_table_record: dict[str,str], reference_cte_prefix:str) -> str:
    def get_columns() -> str:
        column_list = [table_column_name(c) for c in single_table_record.keys()]
        return ','.join(column_list)
    def get_value(column:str,value:str) -> str:
        if is_foreign_key(column):
            return table_column_name(column)
        return _quote(value)
    value_list = [get_value(c,v) for c,v in single_table_record.items()]
    values = ','.join(value_list)
    return "INSERT (" + get_columns() + ") VALUES(" + values + ")"


def _to_merge_statement(single_table_row_record:dict, reference_cte_prefix:str, returning_column:str=None) -> list[str]:
    statement_rows = []
    table_name = _table_name(single_table_row_record)
    statement_rows.append("MERGE INTO " + table_name)
    using_on_clause = _to_using_on_clause(single_table_row_record, reference_cte_prefix, 'u')
    statement_rows.append(using_on_clause[0])

    statement_rows.append(INDENTATION_SPACES + using_on_clause[1])
    def _to_update_clause(single_table_record: dict[str, str]) -> str:
        set_list = [table_column_name(c) + " = " + _quote(v) for c, v in single_table_record.items() if
                    not _is_composite_key(c)]
        if not set_list:
            return None
        return "UPDATE SET " + ','.join(set_list)

    update_clause = _to_update_clause(single_table_row_record)
    if update_clause is not None:
        statement_rows.append("WHEN MATCHED THEN")
        statement_rows.append(INDENTATION_SPACES + update_clause)
    statement_rows.append("WHEN NOT MATCHED THEN")
    statement_rows.append(INDENTATION_SPACES + _to_insert_clause(single_table_row_record, reference_cte_prefix))
    if returning_column is not None:
        statement_rows.append("RETURNING " + returning_column)
    return statement_rows


class RowConverter:
    def convert(self, row:dict[str,str], foreign_keys:list[ForeignKey]=None) -> str:
        """
        :param row:
        :param foreign_keys: optional
        :return: combination of several merge statements. NOTE that statements corresponding to one csv row have dependencies on each other
        """
        if foreign_keys is None:
            foreign_keys = [ForeignKey(c) for c, v in row.items() if is_foreign_key(c)]
        single_table_record_list = _divide(row, foreign_keys)
        reference_table_prefix = 'merged_'
        merge_statement_list = []
        for single_table_input in single_table_record_list:
            returning_column = single_table_input[1]
            merge_statement_list.append(_to_merge_statement(single_table_input[0], reference_table_prefix, returning_column))
        def _decorate(merge_stmt:list[str], idx:int, next_is_merge:bool | None = None) -> list[str]:
            def merge_table_name() -> str:
                return merge_stmt[0].split(" ")[2]
            result = []
            if idx == 0 and RETURNING in merge_stmt[-1]:
                result.append("WITH " + reference_table_prefix + merge_table_name() + " AS (")
            elif RETURNING in merge_stmt[-1]:
                result.append(reference_table_prefix + merge_table_name() + " AS (")
            for line in merge_stmt:
                if RETURNING in merge_stmt[-1]:
                    result.append(INDENTATION_SPACES + line)
                else:
                    result.append(line)
            if RETURNING in merge_stmt[-1]:
                result[-1] += ")"
                if next_is_merge:
                    pass
                else:
                    result[-1] += ","
            return result
        index = 0
        result_lines = []
        for merge_statement in merge_statement_list:
            next_is_merge = None
            if index < len(merge_statement_list)-1:
                next_is_merge = not RETURNING in merge_statement_list[index+1][-1]
            result_lines.extend(_decorate(merge_statement, index, next_is_merge))
            index += 1
        return '\n'.join(result_lines) + ";"




