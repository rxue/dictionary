const axios = require('axios');
const https = require('https');

const endpointURL = 'http://localhost/dictionary/rest'
test("add 2 explanations", async () => {
    const newExplanations = {
      lexicalItem: {
        language: 'en',
        value: 'test key'
      },
      explanationLanguage: 'en',
      explanations: [
        {
          partOfSpeech: 'VI',
          explanation: 'a'
        },
        {
          partOfSpeech: 'VT',
          explanation: 'b'
        }
      ]
    };
    const vocabulariesURL = endpointURL + "/vocabularies";
    const resp = await axios.post(vocabulariesURL, newExplanations);
    expect(resp.status).toBe(201);
    console.log(resp.headers)
    const responseOfGet = await axios.get(vocabulariesURL + "/en;explanation_language=en/test key");
    expect(responseOfGet.status).toBe(200);
    expect(responseOfGet.data.explanations.length).toBeGreaterThan(0);

    
/*
    const newExplanation2 = {
      lexicalItem: {
        language: 'en',
        value: 'test key'
      },
      explanationLanguage: 'en',
      partOfSpeech: 'N',
      explanation: 'test2'
    };
    const result2 = await axios.post(endpointURL + "/explanations", newExplanation2);
    expect(result2.status).toBe(201);
    */
});
/*
test("get explanation", async () => {
    const result = await axios.get(endpointURL + "/vocabularies/en/test?explainLanguage=en", {});
    expect(result.status).toBe(200);
});
*/
