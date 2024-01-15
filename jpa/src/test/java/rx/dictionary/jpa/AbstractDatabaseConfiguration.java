package rx.dictionary.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

public abstract class AbstractDatabaseConfiguration {
    protected static EntityManagerFactory entityManagerFactory;
    private static MariaDBContainer<?> db;
    @BeforeAll
    protected static void init() {
        db = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.5.8"));
        db.setPortBindings(Collections.singletonList("3307:3306"));
        db.start();
        entityManagerFactory = Persistence.createEntityManagerFactory("dictionary-mariadb-test");
    }

    @AfterAll
    protected static void destroy() {
        entityManagerFactory.close();
        db.stop();
    }
}
