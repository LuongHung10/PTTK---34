package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection410 {
    private static final Connection410 connection410 = new Connection410();
    private Connection connection;

    private Connection410() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopman?useSSL=false", "root", "123456789");
            System.out.println("Connection established: " + connection);
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL JDBC Driver not found.");
        } catch (SQLException ex) {
            System.out.println("Error establishing connection: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
        }
    }

    public static Connection410 getInstance() {
        return connection410;
    }
    
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Failed to establish a database connection.");
        }
        return connection;
    }
}
