export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
mvn -f ../backoffice/pom.xml clean package
docker-compose up -d --build backoffice
docker-compose logs -f backoffice

