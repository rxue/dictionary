const axios = require('axios');
const https = require('https');
const endpointURL = 'http://localhost:8080/dictionary-rest-api/rest/glossary'

function getHeaders() {
  return {
    headers: {"Content-Type": "application/json"}
  };
}
const failureMessage = "POST: ";
const errorCaseMessagePrefix = failureMessage + "error case: ";
test(errorCaseMessagePrefix + "empty input", async () => {
    const requestURL = endpointURL;
    const inputData = {

    }
    const resp = await axios.post(requestURL, inputData, getHeaders())
      .catch(error => {
        const resp = error.response;
        expect(resp.status).toBe(400);
        const receivedParamViolations = resp.data.parameterViolations;
        receivedParamViolations.sort((legft, right) => legft.path.localeCompare(right.path));
        const expectedViolations = [
          {
            constraintType : "PARAMETER",
            message : "must not be empty",
            path : "createExplanationsByLexicalItemDTO.arg0.explanations",
            value : ""
          },
          {
            constraintType : "PARAMETER",
            message : "must not be null",
            path : "createExplanationsByLexicalItemDTO.arg0.lexicalItemDTO",
            value : ""
          }
        ];
        expect(receivedParamViolations).toEqual(expectedViolations);
      });
    expect(resp).toBeUndefined();

});

test(errorCaseMessagePrefix + "with lexicalItem but without explanations", async () => {
  const requestURL = endpointURL;
  const inputData = {
    lexicalItemDTO : {
      languageTag : "en",
      value : "test"
    }

  }
  const resp = await axios.post(requestURL, inputData, getHeaders())
    .catch(error => {
      const resp = error.response;
      expect(resp.status).toBe(400);
      const receivedParamViolations = resp.data.parameterViolations;
      receivedParamViolations.sort((legft, right) => legft.path.localeCompare(right.path));
      const expectedViolations = [
        {
          constraintType : "PARAMETER",
          message : "must not be empty",
          path : "createExplanationsByLexicalItemDTO.arg0.explanations",
          value : ""
        }
      ];
      expect(receivedParamViolations).toEqual(expectedViolations);
    });
    expect(resp).toBeUndefined();
});

test(failureMessage + "base case", async () => {
  const requestURL = endpointURL;
  const inputData = {
    lexicalItemDTO : {
      languageTag : "en",
      value : "test c"
    },
    explanations : [
      {
        languageTag : "zh-CN",
        definition : "ceshi",
        partOfSpeech : "N"
      }
    ]

  }
  const resp = await axios.post(requestURL, inputData, getHeaders())
    .catch(error => {
      expect(error).toBeUndefined();
    });
  const responseData = resp.data;
  console.log(responseData);
  expect(responseData.lexicalItemDTO.id).toBeDefined();  
});