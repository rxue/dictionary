package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;
import rx.Util;
import rx.transaction.jdbc.PreparedStatementExecutor;
import rx.transaction.UserTransactionExecutor;

import java.util.Collections;
import java.util.function.Consumer;

public abstract class AbstractDatabaseConfiguration {
    protected static EntityManagerFactory entityManagerFactory;
    private static MariaDBContainer<?> db;
    protected static PreparedStatementExecutor preparedStatementExecutor;
    protected static UserTransactionExecutor userTransactionExecutor;

    @BeforeAll
    protected static void init() {
        db = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.5.8"));
        System.setProperty("port", "3307");
        db.setPortBindings(Collections.singletonList(Util.getPortNumber() + ":3306"));
        db.start();
        entityManagerFactory = Persistence.createEntityManagerFactory("dictionary-mariadb-test");
        preparedStatementExecutor = new PreparedStatementExecutor(entityManagerFactory);
        userTransactionExecutor = new UserTransactionExecutor(entityManagerFactory);
    }


    public void execute(Consumer<EntityManager> operations) {
        userTransactionExecutor.execute(operations);
    }

    @AfterAll
    protected static void destroy() {
        entityManagerFactory.close();
        db.stop();
    }

}
