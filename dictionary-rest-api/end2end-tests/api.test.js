const axios = require('axios');
const url = 'http://localhost:8080';
const config = {
  headers: {
    'Content-Type': 'application/json'
  }
};

describe('Error case', () => {
  it('get get Simplified Chinese explanation of US English word, curate', async () => {
    try {
      const response = await axios.get(url + "/lexical-items/curate;language=en-US/explanations?language=zh-CN", config);
      expect(response.status).toBe(200);
      expect(response.data).not.toBeNull();
    } catch(error) {
      fail('Never expected to throw error', error.message);
    }
  });
});


