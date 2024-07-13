trap 'unset $(cat ci_test.env |cut -d '=' -f 1 |xargs)' RETURN
source web/functions.sh
export $(cat ci_test.env | xargs)
echo "Run in environment "$ENV
docker compose down -v mariadb
docker compose up -d --remove-orphans mariadb
waitBySleep 'docker logs mariadb-'${ENV}' 2>&1 |grep "port: [0-9]\{4\}  mariadb.org binary distribution"'
docker exec mariadb-${ENV} mysql -u root -ptest -e "CREATE DATABASE IF NOT EXISTS dictionary;"
docker exec mariadb-${ENV} mysql -u root -ptest -e "GRANT ALL PRIVILEGES ON dictionary.* TO 'root'@'%' WITH GRANT OPTION;"
