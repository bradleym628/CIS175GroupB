<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <c:if test="${empty products}">
                        <tr>
                            <td colspan="4">No products available.</td>
                        </tr>
                    </c:if>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.code}</td>
                            <td>${product.description}</td>
                            <td>${product.priceCurrencyFormat}</td>
                            <td>
                                <a href="products?action=edit&code=${product.code}" class ="button small">Edit</a>
                                <a href="products?action=delete&code=${product.code}" class="button small delete">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- <a href="addProduct.jsp" class="button">Add Products</a>  -->
            <a href="products?action=add&code="${product.code} class="button">Add New Product</a>
        </div>
    </body>
</html>
