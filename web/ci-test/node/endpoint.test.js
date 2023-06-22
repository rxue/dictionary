const axios = require('axios');
const https = require('https');

const endpointURL = 'http://localhost/dictionary/search.xhtml'
test("with all parameters", async () => {
    const result = await axios.get(endpointURL + '?lang=en&word=crux&explain_in_lang=zg-CN', {});
    expect(result.status).toBe(200);
});
test("without any parameters", async () => {
    const result = await axios.get(endpointURL, {});
    expect(result.status).toBe(200);
});
