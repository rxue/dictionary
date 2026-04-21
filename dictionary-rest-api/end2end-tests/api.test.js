const axios = require('axios');
const url = 'http://localhost:8080';
const config = {
  headers: {
    'Content-Type': 'application/json'
  }
};

describe('Error case', () => {
  it('get English explanation of English word, test', async () => {
    try {
      const response = await axios.get(url + "/lexical-items/test;language=en/explanations?language=en", config);
      expect(response.status).toBe(200);
      expect(response.data).not.toBeNull();
    } catch(error) {
      throw new Error('Never expected to throw error: ' + error.message);
    }
  });
});


