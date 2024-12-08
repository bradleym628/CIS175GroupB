package group3.data;

import group3.business.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// A class built in an attempt to connect to the database and run input and output tasks for the servlet

public class ProductIODB {
    
    public static List<Product> selectProductsDB() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        String query = "SELECT * from Checkout";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setCode(rs.getString("Code"));
                product.setDescription(rs.getString("Descritpion"));
                product.setPrice(rs.getDouble("Price"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil2.closeResultSet(rs);
            DBUtil2.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        return products;
    }
    
}
