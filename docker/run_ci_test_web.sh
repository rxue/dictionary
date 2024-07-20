#!/bin/bash
source functions.sh ci_test.env
# 1. start database (Docker container) and create users
source web/start_mariadb.sh
# 2. build schema and insert test data by running jpa project's main method
mvn -B clean -f ../jpa install -DskipTests
mvn -B -f ../jpa exec:java -Dexec.mainClass="rx.dictionary.jpa.Main"
# 3. build rest-api app war and deploy it to Wildfly
# Component test
source web/deploy_2_wildfly.sh
npm --prefix=../web/ci-test/node test
#sleep 10
#trap "docker-compose kill web-test" RETURN
#mvn -f ../jpa clean verify
#mvn -f ../web/java clean verify
#mvn -f ../jpa clean install -DskiptTests -DrunMainPhase=install
##exit_code=$?
#if [ ${exit_code} -ne 0 ];
#then
#  echo "Component test failed, and thus exit"
#  return ${exit_code}
#fi
# UAT
# echo "Start UAT"
# docker cp ../web/java/web-project/target/web-project.war dictionary-web-test:/opt/jboss/wildfly/standalone/deployments/
# sleep 10
# python3 -m unittest discover -s ../web/ci-test/uat
# exit_code=$?
# return ${exit_code}


