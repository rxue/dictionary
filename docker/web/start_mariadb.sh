#!/bin/bash
source functions.sh ci_test.env
echo "Run in environment "$ENV
docker compose down -v mariadb
docker compose up -d --remove-orphans mariadb
waitBySleep 'docker logs '${DB_CONTAINER_NAME}' 2>&1 |grep "port: [0-9]\{4\}  mariadb.org binary distribution"'
docker exec ${DB_CONTAINER_NAME} mysql -u root -ptest -e "CREATE DATABASE IF NOT EXISTS dictionary;"
docker exec ${DB_CONTAINER_NAME} mysql -u root -ptest -e "GRANT ALL PRIVILEGES ON dictionary.* TO 'root'@'%' WITH GRANT OPTION;"
