FROM quay.io/wildfly/wildfly:latest
COPY target/backoffice.war /opt/jboss/wildfly/standalone/deployments/
COPY docker_config/standalone-full.xml /opt/jboss/wildfly/standalone/configuration/
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-full.xml", "-Djboss.http.port=8082", "-b", "backoffice", "-bmanagement", "0.0.0.0"]
