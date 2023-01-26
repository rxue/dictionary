sed 's/${PORT}/8080/g' templates/web/Dockerfile.template > ../web/Dockerfile
docker-compose build web
