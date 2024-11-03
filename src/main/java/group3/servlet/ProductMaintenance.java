package group3.servlet;

import group3.business.Product;
import group3.business.ProductIO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductMaintenance extends HttpServlet {

    @Override
    public void init() throws ServletException {
        String filePath = getServletConfig().getInitParameter("filePath");
        ProductIO.init(getServletContext().getRealPath(filePath));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
