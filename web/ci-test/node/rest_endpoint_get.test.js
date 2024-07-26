const axios = require('axios');
const https = require('https');
const endpointURL = 'http://localhost:8080/dictionary-rest-api/rest/glossary'

function getHeaders() {
  return {
    headers: {"Content-Type": "application/json"}
  };
}

test("base GET request ", async () => {
    const requestURL = endpointURL + "/en";
    const resp = await axios.get(requestURL, getHeaders());
    const expectedResponseData = {
      language: "en",
      explanationLanguage: "en",
      value : "dry",
      definitions: {
        "ADJ" : ["dull"]
      }
    };
    expect(resp.status).toBe(200);
    expect(resp.data).toEqual(expectedResponseData);

});