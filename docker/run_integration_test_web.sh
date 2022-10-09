docker-compose up -d --build web-it
mvn -f ../web/pom.xml clean verify -Parq-wildfly-remote
docker-compose kill web-it

