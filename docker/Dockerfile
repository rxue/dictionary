FROM jboss/wildfly:latest
COPY java/dictionary-web-project/target/dictionary.war /opt/jboss/wildfly/standalone/deployments/
COPY docker_config/standalone.conf /opt/jboss/wildfly/bin/
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-Djboss.http.port=8080", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
