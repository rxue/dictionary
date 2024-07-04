package rx.transaction.jdbc;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * In light of org.hibernate.jdbc.ReturningWork
 * @param <T> generaic type of return data
 */
@FunctionalInterface
public interface WorkFrom<T> {
    void execute(T jdbcObj) throws SQLException;
}
