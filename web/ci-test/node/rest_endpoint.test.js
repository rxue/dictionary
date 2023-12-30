const axios = require('axios');
const https = require('https');
const endpointURL = 'http://localhost/dictionary/rest'

function getHeaders(username, password) {
  // Encoding credentials in base64
  base64Credentials = Buffer.from(`${username}:${password}`).toString('base64');
  return {
    headers: {Authorization: `Basic ${base64Credentials}`}
  };
}

test("add 6 explanations to one lexical item and result with get", async () => {
    const newExplanations = {

      lexicalItem: {
        language: 'en',
        value: 'test key'
      },
      explanationLanguage: 'en',
      explanations: [
        {
          partOfSpeech: 'VI',
          explanation: '1'
        },
        {
          partOfSpeech: 'VT',
          explanation: '2'
        },
        {
          partOfSpeech: 'VT',
          explanation: '3'
        },
        {
          partOfSpeech: 'VT',
          explanation: '4'
        },
        {
          partOfSpeech: 'VT',
          explanation: '5'
        },
        {
          partOfSpeech: 'VT',
          explanation: '6'
        }
      ]
    };
    const vocabulariesURL = endpointURL + "/vocabularies";
    const resp = await axios.post(vocabulariesURL, newExplanations, getHeaders('admin','admin'));
    expect(resp.status).toBe(201);
    console.log("Going to test GET on the created resource");
    const getLocationURL = resp.headers.location;
    const responseOfGet = await axios.get(getLocationURL, getHeaders('user','user'));
    expect(responseOfGet.status).toBe(200);
    expect(responseOfGet.data).toEqual(newExplanations);
});
/*
test("add 2 explanations to one lexical item and result with get", async () => {
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
    console.log("Going to test GET on the created resource");
    const getLocationURL = resp.headers.location;
    const responseOfGet = await axios.get(getLocationURL);
    expect(responseOfGet.status).toBe(200);
    expect(responseOfGet.data).toEqual(newExplanations);
});
test("add 2 explanations to one lexical item and update them", async () => {
    const explanations = {
      lexicalItem: {
        language: 'en',
        value: 'test 2'
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
    const resp = await axios.post(vocabulariesURL, explanations);
    expect(resp.status).toBe(201);
   
    console.log("Going to test update the created resource with put");
    
    const getLocationURL = resp.headers.location;
    const newExplanationsArray = [
        {
          partOfSpeech: 'VI',
          explanation: 'aa'
        },
        {
          partOfSpeech: 'VT',
          explanation: 'bb'
        }
      ];
    const newExplanations = {};
    newExplanations.explanations = newExplanationsArray;
    const responseOfGet = await axios.put(getLocationURL, newExplanations);
    expect(responseOfGet.status).toBe(200);
    const expectedExplanations = {
      lexicalItem: {
        language: 'en',
        value: 'test 2'
      },
      explanationLanguage: 'en',
    };
    expectedExplanations.explanations = newExplanationsArray;
    expect(responseOfGet.data).toEqual(expectedExplanations);
});
test("delete explanations", async () => {
    //the word - take - has to exist before running this test
    const vocabulariesURL = endpointURL + "/vocabularies/en;explanation_language=en/take";
    const resp = await axios.delete(vocabulariesURL);
    expect(resp.status).toBe(204);
});
*/