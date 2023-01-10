import generate_sql_h2
import unittest

class TestGenerateSQL(unittest.TestCase):
    def test_getFieldValue_nextval(self):
        input='NEXTVAL(seq)'
        self.assertEquals(generate_sql_h2._getFieldValue(input), 'NEXT VALUE FOR seq')
    def test_getFieldValue_date(self):
        input='2021-01-02'
        self.assertEquals(generate_sql_h2._getFieldValue(input), 'PARSEDATETIME(\'2021-01-02\',\'yyyy-MM-dd\')')
    def test_getFieldValue_currval(self):
        input='LASTVAL(seq)'
        self.assertEquals(generate_sql_h2._getFieldValue(input), 'seq.CURRVAL')
    def test_getFieldValue_normalString(self):
        input='value'
        self.assertEquals(generate_sql_h2._getFieldValue(input), '\'value\'')
        
    def testGetInsertScript(self):
        row={'lexical_item.id':'NEXTVAL(item_id_seq)', 'lexical_item.createdate':'2023-01-09', 'lexical_item.language':'en'}
        self.assertEquals(generate_sql_h2._getInsertScript(row), 'INSERT INTO lexical_item (id, createdate, language) VALUES (NEXT VALUE FOR item_id_seq,PARSEDATETIME(\'2023-01-09\',\'yyyy-MM-dd\'),\'en\');')

if __name__ == '__main__':
   unittest.main()