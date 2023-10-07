if [[ -n $(docker ps |grep mariadb:) ]]; then
  # reference: https://www.baeldung.com/ops/docker-stop-vs-kill#:~:text=When%20we%20run%20the%20docker,a%20stopped%20or%20paused%20state.
  docker stop mariadb
  docker rm mariadb
fi
docker-compose up -d --remove-orphans mariadb
sleep 15
docker exec mariadb mysql -u root -ptest -e "CREATE DATABASE IF NOT EXISTS dictionary;"
docker exec mariadb mysql -u root -ptest -e "GRANT ALL PRIVILEGES ON dictionary.* TO 'root'@'%' WITH GRANT OPTION;"

