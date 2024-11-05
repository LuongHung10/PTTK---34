package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DAO410 {
    private Connection410 connection410 = Connection410.getInstance();

    public DAO410() {
    }
    
    public Connection getConnection() throws SQLException {
        return connection410.getConnection();
    }
}
