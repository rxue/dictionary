FROM jboss/wildfly:latest
COPY target/dictionary.war /opt/jboss/wildfly/standalone/deployments/
COPY docker_config/standalone.xml /opt/jboss/wildfly/standalone/configuration/
COPY docker_config/bin/standalone.conf /opt/jboss/wildfly/bin/
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-Djboss.http.port=80", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
