waitBySleep() {
  while true; do
    if [[ -n $(eval $1) ]]; then
      echo "GREAT! got the needed terminate output"
      break
    else
      echo "WIDFLY NOT READY, GOING TO SLEEP"
      sleep 1
    fi 
  done
}
