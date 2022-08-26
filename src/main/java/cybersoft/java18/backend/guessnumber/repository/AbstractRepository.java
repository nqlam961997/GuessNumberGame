package cybersoft.java18.backend.guessnumber.repository;

import cybersoft.java18.backend.guessnumber.exception.DatabaseNotFoundException;
import cybersoft.java18.backend.guessnumber.jdbc.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AbstractRepository<T> {
    public List<T> executeQuery(JdbcExecute<List<T>> processor) {
        try (Connection connection = MySqlConnection.getConnection()) {
            return processor.processQuery(connection);
        } catch (SQLException e) {
            throw new DatabaseNotFoundException(e.getMessage());
        }
    }

    public T executeQuerySingle(JdbcExecute<T> processor) {
        try (Connection connection = MySqlConnection.getConnection()) {
            return (T) processor.processQuery(connection);
        } catch (SQLException e) {
            throw new DatabaseNotFoundException(e.getMessage());
        }
    }

    public int executeUpdate(JdbcExecute<Integer> processor) {
        try (Connection connection = MySqlConnection.getConnection()) {
            return processor.processQuery(connection);
        } catch (SQLException e) {
            throw new DatabaseNotFoundException(e.getMessage());
        }
    }

}
