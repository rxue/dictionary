package io.github.rxue;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

@QuarkusTestResource(PostgresResource.class)
public class PostgresResource implements QuarkusTestResourceLifecycleManager {

    private PostgreSQLContainer<?> postgres;

    @Override
    public Map<String, String> start() {
        Config config = ConfigProvider.getConfig();

        final String dbName = config.getOptionalValue("quarkus.datasource.database", String.class).orElse("defaulttestdb");
        final String username = config.getOptionalValue("quarkus.datasource.username", String.class).orElse("defaultuser");
        final String password = config.getOptionalValue("quarkus.datasource.password", String.class).orElse("defaultpassword");
        postgres = new PostgreSQLContainer<>("postgres:15")
                        .withDatabaseName(dbName)
                        .withUsername(username)
                        .withPassword(password);
        postgres.start();
        Map<String, String> props = new HashMap<>();
        props.put("quarkus.datasource.jdbc.url", postgres.getJdbcUrl());
        props.put("quarkus.datasource.username", postgres.getUsername());
        props.put("quarkus.datasource.password", postgres.getPassword());
        props.put("quarkus.datasource.db-kind", "postgresql");
        return props;
    }

    @Override
    public void stop() {
        postgres.stop();
    }
}
