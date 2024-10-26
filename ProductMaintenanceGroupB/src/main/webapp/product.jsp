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
        <h1>Product Information</h1>
        <form action="${pageContext.request.contextPath}/saveProduct" method="POST">
            <label for="code">Product Code:</label>
            <input type="text" id="code" name="code" required>

            <label for="description">Description:</label>
            <input type="text" id="description" name="description" required>

            <label for="price">Price:</label>
            <input type="number" step="0.01" id="price" name="price" required>

            <button type="submit" class="button">Update Product</button>
        </form>
    </div>
</body>
</html>
