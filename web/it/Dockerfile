FROM jboss/wildfly:latest
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-Djboss.http.port=8080", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
