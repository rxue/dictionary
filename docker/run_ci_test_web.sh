# Component test
docker-compose up -d --build web-test-component
mvn -f ../web/pom.xml clean verify -Parq-wildfly-remote
docker-compose kill web-test-component
# UAT
docker-compose up -d --build web-test-uat
sleep 10
python3 -m unittest discover -s ../web/ci-test/uat
docker-compose kill web-test-uat


