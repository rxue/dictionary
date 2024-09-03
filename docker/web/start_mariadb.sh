#!/bin/bash
source functions.sh ci_test.env
echo "Run in environment "$ENV
docker compose down -v mariadb
docker compose up -d --remove-orphans mariadb
waitBySleep 3 'docker logs '${DB_CONTAINER_NAME}' 2>&1 |grep "port: [0-9]\{4\}  mariadb.org binary distribution"'
docker exec ${DB_CONTAINER_NAME} mysql -u root -ptest -e "CREATE DATABASE IF NOT EXISTS dictionary;"
docker exec ${DB_CONTAINER_NAME} mysql -u root -ptest -e "GRANT ALL PRIVILEGES ON dictionary.* TO 'root'@'%' WITH GRANT OPTION;"
uberJarURL=https://repo1.maven.org/maven2/io/github/rxue/dictionary-jpa/1.6/dictionary-jpa-1.6-uber.jar
curl -O $uberJarURL
jarName=`basename $uberJarURL`
java -Djakarta.persistence.schema-generation.database.action=create -Djakarta.persistence.sql-load-script-source=web/import_mariadb.sql -jar $jarName
