package rx.dictionary.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;
import rx.Util;
import rx.jdbc.ReturningWorkFromPreparedStatement;
import rx.jdbc.WorkFromPreparedStatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractDatabaseConfiguration {
    private static final String SQL_EXCEPTION_ERROR_MESSAGE = SQLException.class + " thrown when executing operations on PreparedStatement, program terminates now!";
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
    protected static <T> T executeTransactionWithReturnValue(String sql, ReturningWorkFromPreparedStatement<T> operations) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Session session = em.unwrap(Session.class);
            return session.doReturningWork(conn -> {
                try (PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    return operations.execute(statement);
                } catch (SQLException e) {
                    throw new IllegalStateException(SQL_EXCEPTION_ERROR_MESSAGE, e);
                }
            });
        }
    }
    protected static void executeTransaction(String sql, WorkFromPreparedStatement operations) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Session session = em.unwrap(Session.class);
            session.doWork(conn -> {
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    operations.execute(statement);
                } catch (SQLException e) {
                    throw new IllegalStateException(SQL_EXCEPTION_ERROR_MESSAGE, e);
                }
            });
        }
    }

    @AfterAll
    protected static void destroy() {
        entityManagerFactory.close();
        db.stop();
    }

}
