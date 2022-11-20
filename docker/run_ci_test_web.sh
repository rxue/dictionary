#!/bin/bash
# Component test
docker-compose up -d --build web-test
sleep 10
trap "docker-compose kill web-test" RETURN
mvn -f ../web/pom.xml clean verify -Parq-wildfly-remote
exit_code=$?
if [ ${exit_code} -ne 0 ];
then
  echo "Component test failed, and thus exit"
  return ${exit_code}
fi
# UAT
echo "Start UAT"
docker cp ../web/target/dictionary.war dictionary-web-test:/opt/jboss/wildfly/standalone/deployments/
sleep 10
python3 -m unittest discover -s ../web/ci-test/uat
exit_code=$?
return ${exit_code}


