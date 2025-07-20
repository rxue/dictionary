import generate_sql_h2
import unittest

class TestGenerateSQL(unittest.TestCase):
    def test_getFieldValue_nextval(self):
        self.assertEqual(
            generate_sql_h2._getFieldValue('NEXTVAL(seq)'), 
            'NEXT VALUE FOR seq')
    def test_getFieldValue_date(self):
        self.assertEqual(
            generate_sql_h2._getFieldValue('2021-01-02'),
            "PARSEDATETIME('2021-01-02','yyyy-MM-dd')")
    def test_getFieldValue_currval(self):
        self.assertEqual(
            generate_sql_h2._getFieldValue('LASTVAL(seq)'), 
            'seq.CURRVAL')
    def test_getFieldValue_normalString(self):
        self.assertEqual(
            generate_sql_h2._getFieldValue('value'), 
            "'value'")
      
    def testGetInsertScript(self):
        row={'lexical_item.id':'NEXTVAL(item_id_seq)', 'lexical_item.createdate':'2023-01-09', 'lexical_item.language':'en'}
        self.assertEqual(
            generate_sql_h2._getInsertScript(row), 
            "INSERT INTO lexical_item (id, createdate, language) VALUES (NEXT VALUE FOR item_id_seq,PARSEDATETIME('2023-01-09','yyyy-MM-dd'),'en');"
            )

if __name__ == '__main__':
   unittest.main()