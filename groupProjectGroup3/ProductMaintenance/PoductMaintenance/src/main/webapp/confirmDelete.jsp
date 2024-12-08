<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <h1>Are you sure you want to delete this product?</h1>
            <!-- A form who's submission will run the doPost method of the servlet and delete an object from the database -->
            <form action="products" method="POST">
                <input type="hidden" name="action" value="${action}">
                <input type="hidden" id="code" name="code" value="${product.code}">
                <input type="hidden" id="description" name="description" value="${product.description}">
                <input type="hidden" id="price" name="price" value="${product.price}">
                
                <div class="buttons">
                    <button type="submit" class="button">Yes</button>
                    <a href="products" class="button">No</a>
                </div>
            </form> 
        </div>
    </body>
</html>
