import unittest
from backoffice.headercolumnparser import ForeignKey


class ForeignKeyTest(unittest.TestCase):
    def setUp(self) -> None:
        self.foreign_key = ForeignKey('*explanation.lexical_item_id (lexical_item.id)')
    def test_reference_table(self):
        self.assertEqual('lexical_item', self.foreign_key.reference_table())
    def test_reference_column(self):
        self.assertEqual('id', self.foreign_key.reference_column())

class HeaderParserTest(unittest.TestCase):
    def test_table_name(self):
        from backoffice.headercolumnparser import table_name
        header_column = 'explanation.definition'
        self.assertEqual('explanation', table_name(header_column))

    def test_table_name_when_given_column_is_composite_key(self):
        from backoffice.headercolumnparser import table_name
        header_column = '*explanation.partofspeech'
        self.assertEqual('explanation', table_name(header_column))

    def test_table_column_name_in_case_of_foreign_key(self):
        from backoffice.headercolumnparser import table_column_name
        header_column = 'explanation.lexical_item_id (lexical_item.id)'
        self.assertEqual('lexical_item_id', table_column_name(header_column))
    def test_table_column_name_in_case_of_number(self):
        from backoffice.headercolumnparser import table_column_name
        header_column = 'explanation.serialnumber #'
        self.assertEqual('serialnumber', table_column_name(header_column))

    def test_is_foreign_key_True(self):
        from backoffice.headercolumnparser import is_foreign_key
        header_column = '*explanation.lexical_item_id (lexical_item.id)'
        self.assertTrue(is_foreign_key(header_column))

    def test_is_foreign_key_False(self):
        from backoffice.headercolumnparser import is_foreign_key
        header_column = 'explanation.definition'
        self.assertFalse(is_foreign_key(header_column))
