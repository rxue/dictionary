# Component test
docker-compose up -d --build web-test
sleep 10
mvn -f ../web/pom.xml clean verify -Parq-wildfly-remote
exit_code=$?
if [ ${exit_code} -ne 0 ];
then
  docker-compose kill web-test
  return ${exit_code}
fi
# UAT
docker cp ../web/target/dictionary.war dictionary-web-test:/opt/jboss/wildfly/standalone/deployments/
sleep 10
python3 -m unittest discover -s ../web/ci-test/uat
exit_code=$?
docker-compose kill web-test
return ${exit_code}


