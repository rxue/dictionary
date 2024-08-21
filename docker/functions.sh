env_file_name=${1}
echo "env file: "${env_file_name}
trap 'unset $(cat '${env_file_name}' |cut -d '=' -f 1 |xargs)' EXIT
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
