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
        <h1>Products</h1>
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
                <tr>
                    <td>8601</td>
                    <td>86 (the band) - True Life Songs and Pictures</td>
                    <td><fmt:formatNumber value="14.95" type="currency" currencySymbol="$" /></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/product.html" class="button small">Edit</a>
                        <a href="${pageContext.request.contextPath}/confirmDelete.html" class="button small delete" onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
                    </td>
                </tr>
                <!-- More products can be added here -->
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/product.html" class="button">Add New Product</a>
    </div>
</body>
</html>