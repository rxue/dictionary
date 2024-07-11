#!/bin/bash
# Component test
docker compose up -d --build web-test
sleep 10
trap "docker-compose kill web-test" RETURN
mvn -f ../jpa clean verify
mvn -f ../web/java clean verify
exit_code=$?
if [ ${exit_code} -ne 0 ];
then
  echo "Component test failed, and thus exit"
  return ${exit_code}
fi
# UAT
# echo "Start UAT"
# docker cp ../web/java/web-project/target/web-project.war dictionary-web-test:/opt/jboss/wildfly/standalone/deployments/
# sleep 10
# python3 -m unittest discover -s ../web/ci-test/uat
# exit_code=$?
# return ${exit_code}


