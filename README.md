# Design flaw of using JPA
## This flaw was discovered when trying to resolve a bug
### How to reproduce the bug
1. open the `update.xhtml` front-end page
2. search for a word, e.g. `take` from `English` to `简体中文`
3. update the 2nd explanation by changing the word of speech from `vt` to `vi` merely for testing purpose
4. click the `update` button
5. open the `search.xhtml` page
6. search for the word, `take` in the `search.xhtml` page

Expected Result: there should be an explanation with word of speech `vi`

Actual Result: there is no explanation with word of speech `vi`
