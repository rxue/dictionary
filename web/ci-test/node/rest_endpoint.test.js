const axios = require('axios');
const https = require('https');

const endpointURL = 'http://localhost/dictionary/rest'
test("add 2 explanations", async () => {
    const newExplanation = {
      lexicalItem: {
        language: 'en',
        value: 'test'
      },
      explanationLanguage: 'en',
      partOfSpeech: 'N',
      explanation: 'test'
    };
    const result = await axios.post(endpointURL + "/explanations", newExplanation);
    expect(result.status).toBe(200);

    const newExplanation2 = {
      lexicalItem: {
        language: 'en',
        value: 'test'
      },
      explanationLanguage: 'en',
      partOfSpeech: 'N',
      explanation: 'test2'
    };
    const result2 = await axios.post(endpointURL + "/explanations", newExplanation2);
    expect(result2.status).toBe(200);
});
test("get explanation", async () => {
    const result = await axios.get(endpointURL + "/vocabularies/en/test?explainLanguage=en", {});
    expect(result.status).toBe(200);
});
