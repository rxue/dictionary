const axios = require('axios');
const https = require('https');

const endpointURL = 'http://localhost/dictionary/search.xhtml'
test("with valid access token", async () => {
    const result = await axios.get(endpointURL + '?lang=en&word=crux&explain_in_lang=zg-CN', {});
    expect(result.status).toBe(200);
});
