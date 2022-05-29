mvn -f ../web/pom.xml clean package
docker-compose up -d --build web
docker-compose logs -f web

