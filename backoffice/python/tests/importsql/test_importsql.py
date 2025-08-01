import unittest
from backoffice.importsql import *

class ImportSQLTest(unittest.TestCase):

    def test__form_unique_tuple_query(self):
        from backoffice.importsql import _form_unique_tuple_query
        key_value_pairs = {'*lexical_item.language':'en_US', '*lexical_item.value':'test'}
        self.assertEqual("SELECT 'en_US' AS language,'test' AS value", _form_unique_tuple_query(key_value_pairs))

    def test__on_clause(self):
        from backoffice.importsql import _on_clause
        key_value_pairs = {'*lexical_item.language': 'en_US', '*lexical_item.value': 'test'}
        self.assertEqual("ON lexical_item.language = u.language AND lexical_item.value = u.value", _on_clause(key_value_pairs))

    def test__to_update_statement(self):
        from backoffice.importsql import _to_update_statement
        key_value_pairs = {'*lexical_item.language': 'en_US', '*lexical_item.value': 'test'}
        self.assertEqual("UPDATE SET language = 'en_US',value = 'test'", _to_update_statement(key_value_pairs))

    def test__to_sql_in_postgres(self):
        from backoffice.importsql import _to_insert_statement
        key_value_pairs = {'*lexical_item.language':'en_US', '*lexical_item.value':'test', '*lexical_time.last_update_time':'CURRENT_TIME'}
        self.assertEqual("INSERT INTO (language,value,last_update_time) VALUES('en_US','test',CURRENT_TIME)", _to_insert_statement(key_value_pairs))

    def test_to_merge_statement(self):
        key_value_pairs = {'*lexical_item.language': 'en_US', '*lexical_item.value': 'test'}
        expected_statement = """MERGE INTO lexical_item
USING (SELECT 'en_US' AS language,'test' AS value) AS u
    ON lexical_item.language = u.language AND lexical_item.value = u.value
WHEN MATCHED THEN
    UPDATE SET language = 'en_US',value = 'test'
WHEN NOT MATCHED THEN
    INSERT INTO (language,value) VALUES('en_US','test');"""
        self.assertEqual(to_merge_statement(key_value_pairs), expected_statement)