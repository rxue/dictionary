mvn -f ../web/pom.xml clean package
if [ ! -d "../web/docker_config" ]; then
  mkdir ../web/docker_config
  cp templates/standalone.xml ../web/docker_config
fi
docker-compose up -d --build web
docker-compose logs -f web

