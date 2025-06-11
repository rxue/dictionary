mvn -f ../pom.xml quarkus:dev &
pid=$!
sleep 20
npm test
kill -9 $pid

