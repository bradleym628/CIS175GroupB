
import static com.oracle.wls.shaded.org.apache.xpath.XPath.SELECT;
import group3.data.DBUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {
    public static void main(String[] arg) {

        // This class was built to try and debug general database connectivity errors
        // It uses prewritten SQL commands to display output to the console and prevent SQL injection
        try (Connection conn = DBUtil.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the Database!");
                
                Statement stmt = conn.createStatement();
                
                String query = "SELECT * FROM products";
                ResultSet rs = stmt.executeQuery(query);
                
                while (rs.next()) {
                    String productCode = rs.getString("code");
                    String productDescription = rs.getString("description");
                    double productPrice = rs.getDouble("price");
                    
                    System.out.println("Code: " + productCode);
                    System.out.println("Description: " + productDescription);
                    System.out.println("Price: $" + productPrice);
                    System.out.println("---------------------------------");
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
}
