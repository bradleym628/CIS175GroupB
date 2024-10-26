<%-- 
    Document   : products
    Created on : Oct 26, 2024, 12:38:36 AM
    Author     : 27717
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Products</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <div class="container">
            <%-- Include database connection --%>
            <%@ include file="dbConnection.jsp" %>

            <h1>Products</h1>

            <%-- Display success/error messages if any --%>
            <c:if test="${not empty param.message}">
                <div class="alert alert-success">
                    ${param.message}
                </div>
            </c:if>
            <c:if test="${not empty param.error}">
                <div class="alert alert-error">
                    ${param.error}
                </div>
            </c:if>

            <%-- Search form --%>
            <div class="search-container">
                <form action="products.jsp" method="GET" class="search-form">
                    <input type="text" 
                           name="search" 
                           placeholder="Search products..."
                           value="${param.search}">
                    <button type="submit" class="button">Search</button>
                </form>
            </div>

            <%
                // Database query to fetch products
                java.util.List<java.util.Map<String, Object>> products = new java.util.ArrayList<>();

                try {
                    String searchTerm = request.getParameter("search");
                    String query = "SELECT * FROM products";
                    if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                        query += " WHERE code LIKE ? OR description LIKE ?";
                        // Add database code to execute search query
                    } else {
                        // Add database code to execute regular query
                    }

                    // Simulate some product data for demonstration
                    java.util.Map<String, Object> product = new java.util.HashMap<>();
                    product.put("id", "1");
                    product.put("code", "8601");
                    product.put("description", "86 (the band) - True Life Songs and Pictures");
                    product.put("price", 14.95);
                    products.add(product);

                    request.setAttribute("products", products);
                } catch (Exception e) {
                    request.setAttribute("error", "Error loading products: " + e.getMessage());
                }
            %>

            <table>
                <thead>
                    <tr>
                        <th>Code</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty products}">
                            <tr>
                                <td colspan="4" class="text-center">No products found</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="product" items="${products}">
                                <tr>
                                    <td>${product.code}</td>
                                    <td>${product.description}</td>
                                    <td>
                                        <fmt:formatNumber value="${product.price}" 
                                                          type="currency" 
                                                          currencySymbol="$"/>
                                    </td>
                                    <td class="actions">
                                        <a href="productForm.jsp?action=edit&productId=${product.id}" 
                                           class="button small">Edit</a>
                                        <a href="confirmDelete.jsp?productId=${product.id}" 
                                           class="button small delete"
                                           onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <div class="button-container">
                <a href="productForm.jsp?action=add" class="button">Add New Product</a>
            </div>
        </div>

        <%-- Optional: Add sorting functionality --%>
        <script>
            function sortTable(columnIndex) {
                const table = document.querySelector('table');
                const tbody = table.querySelector('tbody');
                const rows = Array.from(tbody.querySelectorAll('tr'));

                rows.sort((a, b) => {
                    const aValue = a.cells[columnIndex].textContent;
                    const bValue = b.cells[columnIndex].textContent;

                    if (columnIndex === 2) { // Price column
                        return parseFloat(aValue.replace('$', '')) - parseFloat(bValue.replace('$', ''));
                    }
                    return aValue.localeCompare(bValue);
                });

                rows.forEach(row => tbody.appendChild(row));
            }
        </script>
    </body>
</html>
