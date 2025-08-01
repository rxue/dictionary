"""
Args:t
    single_table_record (dict): NOTE that single table record means that the prefix of each key in this dict must be the same
"""
def get_table_name(single_table_record:dict) -> str:
    anykey = next(iter(single_table_record))
    table_name = anykey.split('.')[0]
    return table_name[1:] if table_name[0] == "*" else table_name

"""
Returns:
    columns concatenated by "," so that it can be used directly as part of SQL insert statement 
"""
def _column_name(header_column_name:str):
    return header_column_name.split('.')[1]
def _quote(value:str):
    if value == 'CURRENT_TIME':
        return value
    return "'" + value + "'"
def _get_unique_columns(single_table_record:dict) -> list[str]:
    return [_column_name(c) for c, v in single_table_record.items() if c.startswith("*")]
def get_columns(single_table_record:dict) -> str:
    column_list = [_column_name(c) for c in single_table_record.keys()]
    return ','.join(column_list)

def get_values(single_table_record:dict) -> str:
    value_list = [_quote(c) for c in single_table_record.values()]
    return ','.join(value_list)

def _on_clause(single_table_record:dict) -> str:
    table_name = get_table_name(single_table_record)
    on_condition_list = [ table_name + "." + c + " = u." + c for c in _get_unique_columns(single_table_record)]
    return 'ON ' + ' AND '.join(on_condition_list)

def _to_insert_statement(single_table_record:dict) -> str:
        values = get_values(single_table_record)
        return "INSERT INTO (" + get_columns(single_table_record) + ") VALUES(" + values + ")"

def _form_unique_tuple_query(single_table_record:dict) -> str:
        as_list = [_quote(v) + " AS " + _column_name(c) for c, v in single_table_record.items()]
        return "SELECT " + ','.join(as_list)

def _to_update_statement(single_table_record:dict) -> str:
        set_list = [ _column_name(c) + " = " + _quote(v) for c, v in single_table_record.items()]
        return "UPDATE SET " + ','.join(set_list)

def to_merge_statement(single_table_record:dict) -> str:
    statement_rows = []
    statement_rows.append("MERGE INTO " + get_table_name(single_table_record))
    statement_rows.append("USING (" + _form_unique_tuple_query(single_table_record) + ") AS u")
    statement_rows.append("    " + _on_clause(single_table_record))
    statement_rows.append("WHEN MATCHED THEN")
    statement_rows.append("    " + _to_update_statement(single_table_record))
    statement_rows.append("WHEN NOT MATCHED THEN")
    statement_rows.append("    " + _to_insert_statement(single_table_record))
    return '\n'.join(statement_rows) + ';'





