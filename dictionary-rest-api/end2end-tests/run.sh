#docker run -d \
#  --name my-postgres \
#  -e POSTGRES_USER=test \
#  -e POSTGRES_PASSWORD=test \
#  -e POSTGRES_DB=testdb \
#  -p 15:5432 \
#  postgres:17 &
#sleep 20
mvn -f ../pom.xml quarkus:dev -Dquarkus.telemetry=true &
pid=$!
sleep 20
npm install
npm test
kill -9 $pid

