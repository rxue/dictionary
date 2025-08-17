import unittest

from backoffice.headerparser import ForeignKey


class CSVRowParserTest(unittest.TestCase):
    def test__divide(self):
        from backoffice.csvrowconverter import _divide
        foreign_key_column = '*explanation.lexical_item_id (lexical_item.id)'
        row_input = {
            '*lexical_item.language':'en',
            '*lexical_item.value':'test',
            'lexical_item.last_update_time':'2025-08-09',
            foreign_key_column:'',
            '*explanation.language':'zh_CN',
            '*explanation.partofspeech':'N',
            '*explanation.serial_number': '1',
            'explanation.definition':'pin yin'}
        foreign_keys = [ForeignKey(foreign_key_column)]
        expected_result1 = ({
            '*lexical_item.language': 'en',
            '*lexical_item.value': 'test',
            'lexical_item.last_update_time': '2025-08-09'}, 'id')
        expected_result2 = ({
            foreign_key_column: '',
            '*explanation.language': 'zh_CN',
            '*explanation.partofspeech': 'N',
            '*explanation.serial_number': '1',
            'explanation.definition': 'pin yin'}, None)
        self.assertEqual([expected_result1, expected_result2], _divide(row_input, foreign_keys))

    def test__to_using_clause_without_foreign_key(self):
        from backoffice.csvrowconverter import _to_using_clause
        key_value_pairs = {'*lexical_item.language':'en_US', '*lexical_item.value':'test', 'explanation.definition':'test'}
        self.assertEqual("USING (SELECT 'en_US' AS language,'test' AS value) AS u", _to_using_clause(key_value_pairs, 'u'))
    def test__to_using_clause_with_foreign_key(self):
        from backoffice.csvrowconverter import _to_using_clause
        key_value_pairs = {'*explanation.lexical_item_id (lexical_item.id)':'', '*explanation.language':'en_US', '*explanation.partofspeech':'N', '*explanation.serial_number':'1', 'explanation.definition':'test'}
        self.assertEqual("USING (SELECT merged_lexical_item.id AS lexical_item_id,'en_US' AS language,'N' AS partofspeech,'1' AS serial_number) AS u", _to_using_clause(key_value_pairs, 'u'))

    def test__on_clause(self):
        from backoffice.csvrowconverter import _on_clause
        key_value_pairs = {'*lexical_item.language': 'en_US', '*lexical_item.value': 'test'}
        self.assertEqual("ON lexical_item.language = u.language AND lexical_item.value = u.value", _on_clause(key_value_pairs, 'lexical_item'))

    def test__to_merge_statement(self):
        from backoffice.csvrowconverter import _to_merge_statement
        key_value_pairs = {
            '*explanation.lexical_item_id (lexical_item.id)': '',
            '*explanation.partofspeech': 'N',
            '*explanation.serial_number': '1',
            'explanation.definition': 'test'}
        expected_statement = """MERGE INTO explanation
USING (SELECT merged_lexical_item.id AS lexical_item_id,'N' AS partofspeech,'1' AS serial_number) AS u
    ON explanation.lexical_item_id = u.lexical_item_id AND explanation.partofspeech = u.partofspeech AND explanation.serial_number = u.serial_number
WHEN MATCHED THEN
    UPDATE SET partofspeech = 'N',serial_number = '1',definition = 'test'
WHEN NOT MATCHED THEN
    INSERT INTO (lexical_item_id,partofspeech,serial_number,definition) VALUES(merged_ lexical_item.id,'N','1','test');"""
        self.assertEqual(expected_statement, _to_merge_statement(key_value_pairs))
