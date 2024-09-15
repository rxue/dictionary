# Download mariadb JDBC client for the deployed application in Wildfly server to use
jarURL="https://repo1.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/3.2.0/mariadb-java-client-3.2.0.jar"
jarName=`basename ${jarURL}`
curl -o ../web/docker_config/${jarName} $jarURL
mvn -B -f ../web/java --projects rest-api --also-make clean package
if [[ 0 -eq $? ]]; then
  docker compose build --build-arg portAssignment="-Djboss.http.port=${REST_API_PORT}" rest-api
  docker compose up -d rest-api
  waitBySleep 4 'docker logs dictionary-rest-api |grep "Admin console listening on http://"'
  if [[ `docker logs dictionary-rest-api |grep "ERROR"` ]]; then
    docker logs dictionary-rest-api
    echo
    echo -e "\tERROR ENCOUNTERED! Failure: Wildfly started with ERROR"
  fi
else
  echo "ERROR: Failure: mvn package, script terminating"
fi
