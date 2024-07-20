# Download mariadb JDBC client for the deployed application in Wildfly server to use
jarURL="https://repo1.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/3.2.0/mariadb-java-client-3.2.0.jar"
jarName=`basename ${jarURL}`
curl -o ../web/docker_config/${jarName} $jarURL
mvn -B -f ../web/java --projects rest-api --also-make clean package
d-Djboss.http.port=ocker compose build --build-arg portAssignment="-Djboss.http.port=${REST_API_PORT}" rest-api
docker compose up -d rest-api
waitBySleep 'docker logs dictionary-rest-api |grep "Admin console listening on http://"'
npm --prefix=../web/ci-test/node test
