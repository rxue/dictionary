import csv
import unittest

from backoffice.headerparser import ForeignKey


class CSVRowConverterUtilityFunctionsTest(unittest.TestCase):
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

    def test__to_using_on_clause_without_foreign_key(self):
        from backoffice.csvrowconverter import _to_using_on_clause
        key_value_pairs = {'*lexical_item.language':'en_US', '*lexical_item.value':'test'}
        expected_using_clause = ("USING (SELECT 'en_US' AS language,'test' AS value) AS u",
                                 "ON lexical_item.language = u.language AND lexical_item.value = u.value")
        self.assertEqual(expected_using_clause, _to_using_on_clause(key_value_pairs, 'merged_', 'u'))
    def test__to_using_on_clause_with_foreign_key(self):
        from backoffice.csvrowconverter import _to_using_on_clause
        key_value_pairs = {'*explanation.lexical_item_id (lexical_item.id)':'', '*explanation.language':'en_US', '*explanation.partofspeech':'N', '*explanation.serialnumber':'1', 'explanation.definition':'test'}
        expected_using_clause = ("USING (SELECT id AS lexical_item_id,'en_US' AS language,'N' AS partofspeech,'1' AS serialnumber FROM merged_lexical_item) AS u",
                                 "ON explanation.lexical_item_id = u.lexical_item_id AND explanation.language = u.language AND explanation.partofspeech = u.partofspeech AND explanation.serialnumber = u.serialnumber")
        self.assertEqual(expected_using_clause, _to_using_on_clause(key_value_pairs, 'merged_', 'u'))

    def test__to_merge_statement(self):
        from backoffice.csvrowconverter import _to_merge_statement
        key_value_pairs = {
            '*explanation.lexical_item_id (lexical_item.id)': '',
            '*explanation.language': 'en',
            '*explanation.partofspeech': 'N',
            '*explanation.serialnumber': '1',
            'explanation.definition': 'test',
            'explanation.last_update_time': '2025-08-15 00:00:00+00'}
        expected_statement = ["MERGE INTO explanation",
                              "USING (SELECT id AS lexical_item_id,'en' AS language,'N' AS partofspeech,'1' AS serialnumber FROM merged_lexical_item) AS u",
                              "    ON explanation.lexical_item_id = u.lexical_item_id AND explanation.language = u.language AND explanation.partofspeech = u.partofspeech AND explanation.serialnumber = u.serialnumber",
                              "WHEN MATCHED THEN",
                              "    UPDATE SET definition = 'test',last_update_time = '2025-08-15 00:00:00+00'",
                              "WHEN NOT MATCHED THEN",
                              "    INSERT (lexical_item_id,language,partofspeech,serialnumber,definition,last_update_time) VALUES(merged_lexical_item.id,'en','N','1','test','2025-08-15 00:00:00+00')"]
        self.assertEqual(expected_statement, _to_merge_statement(key_value_pairs, 'merged_'))

class RowConverterTest(unittest.TestCase):
    def test_convert(self):
        from backoffice.csvrowconverter import RowConverter
        row_converter = RowConverter()
        with open("tests/data/one_record.csv", "r", encoding="utf-8") as f:
            reader = csv.DictReader(f)
            for row in reader:
                result = row_converter.convert(row)
                expected = None
                with open("tests/data/expected_sql_statement", "r", encoding="utf-8") as text_file:
                    expected = text_file.read()
                self.assertEqual(expected, result)
