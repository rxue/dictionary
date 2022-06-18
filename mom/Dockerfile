FROM jboss/wildfly:latest
COPY docker_config/standalone-full.xml /opt/jboss/wildfly/standalone/configuration/
RUN /opt/jboss/wildfly/bin/add-user.sh -u admin -p admin --silent
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-full.xml", "-Djboss.http.port=8081", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
