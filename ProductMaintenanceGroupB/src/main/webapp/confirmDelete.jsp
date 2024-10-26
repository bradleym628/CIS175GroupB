<%-- 
    Document   : confirmDelete
    Created on : Oct 26, 2024, 12:33:22 AM
    Author     : Patrick Matshumba
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Confirm Delete</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <div class="container">
            <%-- Get product ID from request parameter --%>
            <% String productId = request.getParameter("productId"); %>

            <h1>Are you sure you want to delete this product?</h1>

            <%-- Display product details if available --%>
            <% if (productId != null && !productId.isEmpty()) {%>
            <p>Product ID: <%= productId%></p>
            <% }%>

            <div class="buttons">
                <%-- Updated links to use proper JSP paths and include product ID --%>
                <a href="deleteProduct.jsp?productId=<%= productId%>&confirm=yes" class="button">Yes</a>
                <a href="products.jsp" class="button">No</a>
            </div>
        </div>
    </body>
</html>
