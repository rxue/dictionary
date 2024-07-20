env_file_name=${1}
echo "env file: "${env_file_name}
trap 'unset $(cat '${env_file_name}' |cut -d '=' -f 1 |xargs)' EXIT
waitBySleep() {
  while true; do
    if [[ -n $(eval "$1") ]]; then
      echo "GREAT! got the needed terminate output"
      break
    else
      echo "PROCESS - ${1} - IS NOT READY, GOING TO SLEEP"
      sleep 1
    fi 
  done
}
set -a
source ${env_file_name}§
set +a
