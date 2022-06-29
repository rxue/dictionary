FROM quay.io/wildfly/wildfly:latest
COPY docker_config/standalone-full.xml /opt/jboss/wildfly/standalone/configuration/
RUN /opt/jboss/wildfly/bin/add-user.sh -u admin -p admin --silent
RUN /opt/jboss/wildfly/bin/add-user.sh -a -u jmsuser -p jmspassword --group guest --silent
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-full.xml", "-Djboss.http.port=8081", "-b", "172.22.0.2", "-bmanagement", "0.0.0.0"]
