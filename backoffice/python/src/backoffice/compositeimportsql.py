from backoffice.headerparser import is_foreign_key
from backoffice.headerparser import ForeignKey

def get_header_columns(multi_tables_record:dict) -> list[str]:
    return [c for c,v in multi_tables_record.items()]
def _get_foreign_keys(multi_table_columns:list[str]) -> list[ForeignKey]:
    return [ForeignKey(c) for c in multi_table_columns if is_foreign_key(c)]
"""
    Given a csv row corresponding to multiple relational tables, convert it to multiple SQL statements
"""
def to_sql_statements(multiple_tables_record:dict) -> list[str]:
    pass