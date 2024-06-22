package rx.dictionary.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;
import rx.Util;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractDatabaseConfiguration {
    protected static EntityManagerFactory entityManagerFactory;
    private static MariaDBContainer<?> db;
    @BeforeAll
    protected static void init() {
        db = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.5.8"));
        System.setProperty("port", "3307");
        db.setPortBindings(Collections.singletonList(Util.getPortNumber() + ":3306"));
        db.start();
        entityManagerFactory = Persistence.createEntityManagerFactory("dictionary-mariadb-test");
    }

    @AfterAll
    protected static void destroy() {
        entityManagerFactory.close();
        db.stop();
    }

}
