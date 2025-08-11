from backoffice.headerparser import *


def _divide(row:dict[str,str], foreign_keys:list[ForeignKey]) -> list[tuple[dict,str]]:
    """
    divide the given row input into a multiple parts, each of which is input to one relational table
    :param row:
    :return:
    """
    current_table = {}
    result_list = []
    previous_table_name = None
    for c,v in row.items():
        current_table_name = get_table_name(c)
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

def _to_using_clause(single_table_record:dict, alias:str) -> str:
        def to_as_clause(column:str, value:str):
            if is_foreign_key(column):
                foreign_key = ForeignKey(column)
                return 'merged_' + foreign_key.reference() + " AS " + table_column_name(column)
            return _quote(value) + " AS " + table_column_name(column)
        as_list = [to_as_clause(c, v) for c, v in single_table_record.items() if c.startswith('*')]
        return "USING (SELECT " + ','.join(as_list) + ") AS " + alias

def _get_unique_columns(single_table_record:dict) -> list[str]:
    return [table_column_name(c) for c, v in single_table_record.items() if c.startswith("*")]

def _on_clause(single_table_record:dict, table_name:str) -> str:
    on_condition_list = [ table_name + "." + c + " = u." + c for c in _get_unique_columns(single_table_record)]
    return 'ON ' + ' AND '.join(on_condition_list)

def _to_update_clause(single_table_record: dict[str,str]) -> str:
    set_list = [table_column_name(c) + " = " + _quote(v) for c, v in single_table_record.items() if not is_foreign_key(c)]
    return "UPDATE SET " + ','.join(set_list)

def _to_insert_clause(single_table_record: dict[str,str]) -> str:
    def get_columns() -> str:
        column_list = [table_column_name(c) for c in single_table_record.keys()]
        return ','.join(column_list)
    def get_value(column:str,value:str) -> str:
        if is_foreign_key(column):
            foreign_key = ForeignKey(column)
            return 'merged_' + foreign_key.reference()
        return _quote(value)
    value_list = [get_value(c,v) for c,v in single_table_record.items()]
    values = ','.join(value_list)
    return "INSERT INTO (" + get_columns() + ") VALUES(" + values + ")"


def _to_merge_statement(single_table_row_record:dict, returning_value:str=None) -> str:
    statement_rows = []
    table_name = get_table_name(next(iter(single_table_row_record)))
    statement_rows.append("MERGE INTO " + table_name)
    statement_rows.append(_to_using_clause(single_table_row_record, 'u'))
    statement_rows.append("    " + _on_clause(single_table_row_record, table_name))
    statement_rows.append("WHEN MATCHED THEN")
    statement_rows.append("    " + _to_update_clause(single_table_row_record))
    if returning_value is not None:
        statement_rows.append("    RETURNING " + returning_value)
    statement_rows.append("WHEN NOT MATCHED THEN")
    statement_rows.append("    " + _to_insert_clause(single_table_row_record))
    if returning_value is not None:
        statement_rows.append("    RETURNING " + returning_value)
    return '\n'.join(statement_rows) + ';'


class RowParser:
    def __init__(self, row:dict[str,str], foreign_keys:list[ForeignKey]):
        self.row = row
        self.foreign_keys = foreign_keys
    def parse_row(self) -> list[str]:
        """
        :param row:
        :return: list of SQL merge statements. NOTE that merge statements corresponding to one csv row have dependencies on each other
        """
        divided_row_input_list = _divide(self.row, self.foreign_keys)

