package cybersoft.java18.backend.guessnumber.repository;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface JdbcExecute<T> {
    T processQuery(Connection connection) throws SQLException;
}