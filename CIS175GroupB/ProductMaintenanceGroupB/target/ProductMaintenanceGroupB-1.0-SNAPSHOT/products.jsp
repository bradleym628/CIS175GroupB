<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <td>${product.code}</td>
                        <td>${product.description}</td>
                        <td>$${product.price}</td>
                        <td>
                            <a href="product.jsp" class="button small">Edit</a>
                            <a href="confirmDelete.jsp" class="button small delete">Delete</a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <a href="product.jsp" class="button">Add New Product</a>
        </div>
    </body>
</html>
