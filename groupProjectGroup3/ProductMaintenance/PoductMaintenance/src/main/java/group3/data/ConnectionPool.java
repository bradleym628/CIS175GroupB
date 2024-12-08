package group3.data;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// A connection pool class, in another attempt to connect to the database. 
public class ConnectionPool {
    
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        InitialContext ic = new InitialContext();
        dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/products");
    } catch (ClassNotFoundException e) {
        System.out.println("MySQL JDBC Driver not found: " + e);
    } catch (NamingException e) {
        System.out.println("NamingException: " + e);
    }
}
    
    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool= new ConnectionPool();
        }
        return pool;
    }
    
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
