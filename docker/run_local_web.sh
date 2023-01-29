sed 's/${PORT}/80/g' templates/web/Dockerfile.template > ../web/Dockerfile
mvn -f ../web/java/pom.xml clean package
docker-compose up -d --build web
docker-compose logs -f web

