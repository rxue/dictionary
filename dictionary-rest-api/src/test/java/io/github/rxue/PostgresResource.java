package io.github.rxue;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

@QuarkusTestResource(PostgresResource.class)
public class PostgresResource implements QuarkusTestResourceLifecycleManager {

    private PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @Override
    public Map<String, String> start() {
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
