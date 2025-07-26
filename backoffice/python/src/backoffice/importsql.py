from abc import ABC, abstractmethod
class SQLGenerator(ABC):
    def __init__(self):
        pass
    @abstractmethod
    def to_insert_statement(self, single_table_record:dict) -> str:
        pass

"""
Args:
    single_table_record (dict): NOTE that single table record means that the prefix of each key in this dict must be the same
"""
def get_table_name(single_table_record:dict) -> str:
    anykey = next(iter(single_table_record))
    table_name = anykey.split('.')[0]
    return table_name

"""
Returns:
    columns concatenated by "," so that it can be used directly as part of SQL insert statement 
"""
def get_columns(single_table_record:dict) -> str:
    return ','.join(single_table_record.keys())

class PostgreSQLGenerator(SQLGenerator):
    def to_insert_statement(self, single_table_record:dict) -> str:
        table_name = get_table_name(single_table_record)
        return "INSERT INTO " + table_name + '(' + get_columns(single_table_record) + ')'


