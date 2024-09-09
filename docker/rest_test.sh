#!/bin/bash
source functions.sh ci_test.env
# 1. start database (Docker container) and create users
source web/start_mariadb.sh
# 2. build schema and insert test data by running jpa project's main method
mvn -B -f ../jpa exec:java -Dexec.mainClass="rx.dictionary.jpa.Main"
# 3. build REST API application and then deploy to Wildfly server
source web/deploy_2_wildfly.sh
npm --prefix=../web/ci-test/node install
npm --prefix=../web/ci-test/node test
