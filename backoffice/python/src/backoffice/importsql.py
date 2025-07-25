from abc import ABC, abstractmethod
class SQLGenerator(ABC):
    def __init__(self):
        pass
    @abstractmethod
    def to_insert_statement(self, single_table_record:dict) -> str:
        pass

class PostgreSQLGenerator(SQLGenerator):
    def to_insert_statement(self, single_table_record:dict) -> str:
        pass

