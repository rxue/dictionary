package rx.transaction.jdbc;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementExecutor {
    private static final String SQL_EXCEPTION_ERROR_MESSAGE = SQLException.class + " thrown when executing operations on PreparedStatement, program terminates now!";
    private final EntityManagerFactory entityManagerFactory;
    public PreparedStatementExecutor(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public <T> T executeAndReturn(String sql, ReturningWorkFromPreparedStatement<T> operations) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Session session = em.unwrap(Session.class);
            return session.doReturningWork(conn -> {
                System.out.println("DEBUG::Isolation Level of the current JDBC connection is " + conn.getTransactionIsolation());
                try (PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    return operations.execute(statement);
                } catch (SQLException e) {
                    throw new IllegalStateException(SQL_EXCEPTION_ERROR_MESSAGE, e);
                }
            });
        }
    }

    public void execute(String sql, WorkFrom<PreparedStatement> operations) {
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
}