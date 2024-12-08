package group3.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// A database utility class that establishes the database location the username and password for the database
public class DBUtil {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/products";
    private static final String USER = "root";
    private static final String PASSWORD = "sesame";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
