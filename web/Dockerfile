FROM jboss/wildfly
COPY target/dictionary.war /opt/jboss/wildfly/standalone/deployments/
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-Djboss.http.port=80", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
