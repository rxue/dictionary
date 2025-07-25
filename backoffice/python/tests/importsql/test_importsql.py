import unittest
from backoffice.importsql import PostgreSQLGenerator

class ImportSQLGeneratorTest(unittest.TestCase):
    isg = PostgreSQLGenerator()
    def test_to_sql(self):
        self.assertEqual(1,1)