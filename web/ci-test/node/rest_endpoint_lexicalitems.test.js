const axios = require('axios');
const https = require('https');
const endpointURL = 'http://localhost/dictionary/rest'

function getHeaders() {
  return {
    headers: {"Content-Type": "application/json"}
  };
}

test("add one lexical item and ", async () => {
    const newLexicalItem = {
        language: 'en',
        value: 'test key'
      };
    const lexicalItemsURL = endpointURL + "/lexicalitems";
    const resp = await axios.post(lexicalItemsURL, newLexicalItem, getHeaders());
    expect(resp.status).toBe(201);
});