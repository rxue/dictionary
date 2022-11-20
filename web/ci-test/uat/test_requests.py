import unittest
import requests

class TestUpdatePage(unittest.TestCase):
    def test_search(self):
        response=requests.get("http://localhost:8080/dictionary/en/zh-CN/search.xhtml")
        self.assertEqual(200, response.status_code)

    def test_add(self):
        response=requests.get("http://localhost:8080/dictionary/private/add.xhtml")
        self.assertEqual(200, response.status_code)

    def test_update(self):
        response=requests.get("http://localhost:8080/dictionary/private/update.xhtml")
        self.assertEqual(200, response.status_code)
