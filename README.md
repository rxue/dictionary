# Requirement
* System: Ubuntu
* `openjdk version "11.0.16"` is needed to be installed to Ubuntu
* *Docker* is needed to be installed to Ubuntu

# How to start through Terminal
1. `cd` to `docker` directory
2. run `. web/start_mariadb.sh` and then wait till database is ready
3. run `. web/deploy_2_wildfly.sh`

Test REST API endpoint:

http://localhost/dictionary/rest/lexicalitems/1

# Ubiquitous Language
## Reference
https://en.wikipedia.org/wiki/Lexical_item

Critical question on definition of *lexical item* : could one lexical item have multiple meanings
Answer: yes

> According to Crystal (1980: 274), *polysemy* is a term used in semantic analysis to refer to a lexical item which has a range of different meanings.

Reference: https://www.iasj.net/iasj/download/5784a6a9435b5c81

