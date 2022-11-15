FROM jboss/wildfly:latest
RUN wildfly/bin/add-user.sh -u admin -p admin --silent
#ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-Djboss.http.port=8080", "-Djboss.management.http.port=9998", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
