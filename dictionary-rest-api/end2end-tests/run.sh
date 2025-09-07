mvn -f ../pom.xml quarkus:dev &
pid=$!
sleep 20
npm install
npm test
kill -9 $pid

