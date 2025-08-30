import re
from dataclasses import dataclass
@dataclass(frozen=True)
class ForeignKey:
    header_field:str
    def reference_table(self) -> str:
        foreign_key_part = self.header_field.split('(')[1]
        return foreign_key_part.split('.')[0]
    def reference(self) -> str:
        foreign_key_part = self.header_field.split('(')[1]
        return foreign_key_part[0:-1]
    def reference_column(self) -> str:
        foreign_key_part = self.header_field.split('(')[1]
        return foreign_key_part.split('.')[1][:-1]

def table_name(header_column:str) -> str:
    full_table_name = header_column.split('.')[0]
    return full_table_name[1:] if full_table_name.startswith("*") else full_table_name

def table_column_name(header_column:str) -> str:
    column_name_part = header_column.split('.')[1]
    result = column_name_part.split('(')[0].strip() if ' (' in column_name_part else column_name_part
    return result.split(' ')[0] if result.endswith('#') else result

def is_foreign_key(header_column:str) -> bool:
    return re.fullmatch(r'^[^()]+ \([^()]+\)$', header_column)

def reference_column(reference_header_column:str) -> str:
    """
    :param reference_header_column: column in the header. NOTE that the column must be a foreign key
    :return:
    """
    foreign_key_part = reference_header_column.split('(')[1]
    return foreign_key_part.split('.')[1][:-1]