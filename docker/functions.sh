env_file_name=${1}
echo "env file: "${env_file_name}
trap 'unset $(cat '${env_file_name}' |cut -d '=' -f 1 |xargs)' EXIT
restartRestAPI() {
  mvn -f ../dictionary-mod-jpa/pom.xml clean package -Dquarkus.package.jar.type=legacy-jar
  if [ $? -ne 0 ]; then
    echo "Project Build Failure :("
    return 1
  fi
  docker compose up -d --build rest-api
}
waitBySleep() {
  local seconds=${1}
  while true; do
    if [[ -n $(eval "$2") ]]; then
      echo "GREAT! got the needed terminate output"
      break
    else
      echo "PROCESS - ${2} - IS NOT READY, GOING TO SLEEP"
      sleep $seconds
    fi 
  done
}
set -a
source ${env_file_name}
set +a
