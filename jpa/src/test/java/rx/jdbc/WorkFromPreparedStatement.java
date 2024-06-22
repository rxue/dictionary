package rx.jdbc;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * In light of org.hibernate.jdbc.ReturningWork
 * @param <T> generaic type of return data
 */
@FunctionalInterface
public interface WorkFromPreparedStatement {
    void execute(PreparedStatement preparedStatement) throws SQLException;
}
