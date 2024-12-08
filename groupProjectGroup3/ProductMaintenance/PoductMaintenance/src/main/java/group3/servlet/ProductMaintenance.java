package group3.servlet;

import group3.business.Product;
import group3.business.ProductIO;
import group3.data.ProductIODB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;



public class ProductMaintenance extends HttpServlet {

    // An initialization method to set the filepath for upon the first execution of the servlet.
    @Override
    public void init() throws ServletException {
        String filePath = getServletConfig().getInitParameter("filePath");
        ProductIO.init(getServletContext().getRealPath(filePath));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // A method to debug database connection, Which I never got to work correctly
        // The method attempts to create a list of products and set them as a session attribute. 
        // It then attempts to output the information to the DOM of an HTTP page for developer debuggin
//        List<Product> products = ProductIODB.selectProductsDB();
//        request.setAttribute("products", products);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
//        dispatcher.forward(request, response);
//    }
        
//        response.setContentType("text/html;charset-UTF-8");
//        
//        try (PrintWriter out = response.getWriter()) {
//            out.println("<html><body>");
//            out.println("<h2>Product List</h2>");
//            out.println("<table border='1'><tr><th>Code</th><th>Description</th><th>Price</th></tr>");
//            
//            //String query = "SELECT * FROM products";
//            try (Connection conn = DBUtil.getConnection()) {
//                
//                Statement stmt = conn.createStatement();
//                
//                String query = "SELECT * FROM products";
//                ResultSet rs = stmt.executeQuery(query);
//                
//                while (rs.next()) {
//                    String code = rs.getString("code");
//                    String description = rs.getString("description");
//                    double price = rs.getDouble("price");
//                    
//                    out.println("<tr>");
//                    out.printf("<td>%s</td><td>%s</td><td>$%f.2f</td>", code, description, price);
//                    out.println("</tr>");
//                }
//            } catch (Exception e) {
//                e.printStackTrace(out);
//                out.println("<p style='color:red;'>Failed to retrieve data. Check database connection.</p>");
//            }
//            
//            out.println("</table>");
//            out.println("</body></html>");
//            
//        }
//    }
        

        // This section pulls information out of a .txt file that is then used to create a table of products
        // and provides the user the option to add, or edit files. 
        String action = request.getParameter("action");
        String code = request.getParameter("code");

        if ("edit".equals(action) && code != null) {
            Product product = ProductIO.selectProduct(code);
            request.setAttribute("product", product);
            request.setAttribute("action", "edit");
            RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
            dispatcher.forward(request, response);
        } else if ("delete".equals(action) && code != null) {
            Product product = ProductIO.selectProduct(code);
            request.setAttribute("product", product);
            request.setAttribute("action", "delete");
            RequestDispatcher dispatcher = request.getRequestDispatcher("confirmDelete.jsp");
            dispatcher.forward(request, response);
        } else if ("add".equals(action)) {  
            Product product = ProductIO.selectProduct(code);
            request.setAttribute("product", product);
            request.setAttribute("action", "add");
            RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
            dispatcher.forward(request, response);
        } else {
            List<Product> products = ProductIO.selectProducts();
            request.setAttribute("products", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
            dispatcher.forward(request, response);
        }
    }

    // The doPost method adds and edits information it takes from the form, and adds it to the products.txt file.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String priceString = request.getParameter("price");

        double price = Double.parseDouble(priceString);
        Product product = new Product();
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);

        if ("add".equals(action)) {
            ProductIO.insertProduct(product);
        } else if ("edit".equals(action)) {
            ProductIO.updateProduct(product);
        } else if ("delete".equals(action)) {
            ProductIO.deleteProduct(product);
        }

        response.sendRedirect("products");  // Redirect to the product list page after any action
    }
}
