<%-- 
    Document   : product
    Created on : Oct 26, 2024, 12:36:21 AM
    Author     : Patrick Matshumba
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add/Edit Product</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <div class="container">
            <%-- Get product details if editing existing product --%>
            <%
                String action = request.getParameter("action");
                String productId = request.getParameter("productId");
                String code = request.getParameter("code");
                String description = request.getParameter("description");
                String price = request.getParameter("price");
                boolean isEdit = "edit".equals(action) && productId != null;
            %>

            <h1><%= isEdit ? "Edit" : "Add"%> Product</h1>

            <%-- Display any error messages --%>
            <%
                String error = request.getParameter("error");
                if (error != null && !error.isEmpty()) {
            %>
            <div class="error-message">
                <p><%= error%></p>
            </div>
            <% } %>

            <form action="saveProduct.jsp" method="POST" onsubmit="return validateForm()">
                <%-- Hidden field for edit mode --%>
                <% if (isEdit) {%>
                <input type="hidden" name="productId" value="<%= productId%>">
                <% }%>

                <label for="code">Product Code:</label>
                <input type="text" 
                       id="code" 
                       name="code" 
                       required
                       value="<%= code != null ? code : ""%>"
                       pattern="[A-Za-z0-9-]+"
                       title="Product code can only contain letters, numbers, and hyphens">

                <label for="description">Description:</label>
                <input type="text" 
                       id="description" 
                       name="description" 
                       required
                       maxlength="100"
                       value="<%= description != null ? description : ""%>">

                <label for="price">Price:</label>
                <input type="number" 
                       step="0.01" 
                       id="price" 
                       name="price" 
                       required
                       min="0.01"
                       value="<%= price != null ? price : ""%>">

                <div class="button-group">
                    <button type="submit" class="button">
                        <%= isEdit ? "Update" : "Add"%> Product
                    </button>
                    <a href="products.jsp" class="button secondary">Cancel</a>
                </div>
            </form>
        </div>

        <%-- Add client-side validation --%>
        <script>
            function validateForm() {
                const code = document.getElementById('code').value;
                const description = document.getElementById('description').value;
                const price = document.getElementById('price').value;

                let isValid = true;
                let errorMessage = '';

                // Validate product code
                if (!/^[A-Za-z0-9-]+$/.test(code)) {
                    errorMessage += 'Product code can only contain letters, numbers, and hyphens.\n';
                    isValid = false;
                }

                // Validate description
                if (description.length > 100) {
                    errorMessage += 'Description must be less than 100 characters.\n';
                    isValid = false;
                }

                // Validate price
                if (parseFloat(price) <= 0) {
                    errorMessage += 'Price must be greater than 0.\n';
                    isValid = false;
                }

                if (!isValid) {
                    alert(errorMessage);
                }

                return isValid;
            }
        </script>
    </body>
</html>
