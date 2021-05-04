package main.data.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnectionDAO {
    Connection openConnection() throws SQLException;
    Connection getConnection();
    void closeConnection();
}
