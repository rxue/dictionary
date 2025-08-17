import csv
from typing import NamedTuple
from backoffice.headerparser import ForeignKey

class TableMetadata(NamedTuple):
    header_fields:list[str]
    referenced_primary_key:str=None
def to_sql_statements(csv_file_path:str) -> list[str]:
    """
    Given a csv file, parse it to a list of SQL elements to insert/update data into database
    :param csv_file_path:
    :return: list of SQL elements
    """
    with open(csv_file_path, mode='r', encoding='utf-8') as file:
        from backoffice.headerparser import is_foreign_key
        reader = csv.DictReader(file)
        foreign_keys = [ForeignKey(f) for f in reader.fieldnames if is_foreign_key(f)]
        result = []
        # Process each row
        pass
