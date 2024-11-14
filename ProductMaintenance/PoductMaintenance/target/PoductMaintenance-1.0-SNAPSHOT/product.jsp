<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <form action="products" method="POST">
                <input type="hidden" name="action" value="${action}">
                <label for="code">Product Code:</label>
                <input type="text" id="code" name="code" value="${product.code}" required ${action == 'edit' ? 'readonly' : ''}>

                <label for="description">Description:</label>
                <input type="text" id="description" name="description" value="${product.description}" required>

                <label for="price">Price:</label>
                <input type="number" step="0.01" id="price" name="price" value="${product.price}" required>

                <button type="submit" class="button">${action == 'edit' ? 'Update Product' : 'Add Product'}</button>
            </form>
        </div>
    </body>
</html>