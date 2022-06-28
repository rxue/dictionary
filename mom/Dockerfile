FROM quay.io/wildfly/wildfly:latest
RUN /opt/jboss/wildfly/bin/add-user.sh -u admin -p admin --silent
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-Djboss.http.port=8081", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
