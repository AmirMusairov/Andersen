<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Warehouse</title>
</head>
<body>
<h1>Products</h1>

<table>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Group</th>
        <th>Count</th>
        <th>Add to bucket</th>
    </tr>
    <c:forEach var="pair" items="${products}">
        <tr>
            <td>${pair.key.name}</td>
            <td>${pair.key.price}</td>
            <td>${pair.key.group}</td>
            <td>${pair.value}</td>
            <td><a href="/add-product?productId=${pair.key.id}">add</a></td>
        </tr>
    </c:forEach>
</table>

<p><a href="/main">menu</a></p>
</body>
</html>