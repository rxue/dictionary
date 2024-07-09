FROM quay.io/wildfly/wildfly:27.0.0.Final-jdk17
ENV WILDFLY_DIR=/opt/jboss/wildfly
ENV MARIADB_MODULE_DIR=$WILDFLY_DIR/modules/system/layers/base/org/mariadb/jdbc/main
RUN mkdir -p $MARIADB_MODULE_DIR
COPY docker_config/mariadb-ds_module.xml $MARIADB_MODULE_DIR/module.xml
COPY docker_config/*.jar $MARIADB_MODULE_DIR/
COPY docker_config/standalone.xml $WILDFLY_DIR/standalone/configuration/
COPY java/rest-api/target/dictionary.war $WILDFLY_DIR/standalone/deployments/
COPY docker_config/standalone.conf $WILDFLY_DIR/bin/
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-Djboss.http.port=80", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
