docker compose down -v mariadb
docker compose up -d --remove-orphans mariadb
source web/functions.sh
waitBySleep 'docker-compose logs mariadb |grep "mariadb.org binary distribution"'
sleep 5
docker exec mariadb mysql -u root -ptest -e "CREATE DATABASE IF NOT EXISTS dictionary;"
docker exec mariadb mysql -u root -ptest -e "GRANT ALL PRIVILEGES ON dictionary.* TO 'root'@'%' WITH GRANT OPTION;"
mvn -f ../jpa/pom.xml -PbuildJar clean install

