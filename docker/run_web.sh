#Validate: port 80 is not in use
netstat -tulpn |grep :80
if [ `echo $?` -eq 0 ]; then
  echo 'NOTE!!! port 80 in use, so running of this docker container might fail if the port in use is not this existing Docker container'
  sleep 5
fi


mvn -f ../web/pom.xml clean package
docker-compose up -d --build web
docker-compose logs -f web

