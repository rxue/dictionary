import unittest
from backoffice.importsql import PostgreSQLGenerator

class ImportSQLGeneratorTest(unittest.TestCase):
    def test_to_sql_in_postgres(self):
        sqlGenerator = PostgreSQLGenerator()
        key_value_pairs = {'lexical_item.language':'en_US', 'lexical_item.value':'test'}
        self.assertEqual("INSERT INTO lexical_item(language, value) VALUES('en_US', 'test')", sqlGenerator.to_insert_statement(key_value_pairs))