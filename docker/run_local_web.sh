docker-compose up -d mariadb
sleep 5
docker exec mariadb mysql -u root -ptest -e "CREATE DATABASE dictionary;"
docker exec mariadb mysql -u root -ptest -e "GRANT ALL PRIVILEGES ON dictionary.* TO 'root'@'%' WITH GRANT OPTION;"
jarURL="https://repo1.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/3.2.0/mariadb-java-client-3.2.0.jar"
jarName=`basename ${jarURL}`
curl -o ../web/docker_config/${jarName} $jarURL
mvn -f ../web/java/pom.xml clean package
docker-compose up -d --build web
docker-compose logs -f web

