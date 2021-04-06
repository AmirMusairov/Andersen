<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Bucket</title>
</head>
<body>
<h1>Products</h1>
<p><a href="/main">menu</a></p>

<table>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Group</th>
        <th>Count</th>
        <th>Delete from bucket</th>
    </tr>
    <c:forEach var="pair" items="${products}">
        <tr>
            <td>${pair.key.name}</td>
            <td>${pair.key.price}</td>
            <td>${pair.key.group}</td>
            <td>${pair.value}</td>
            <td><a href="/bucket/delete-product/${pair.key.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>