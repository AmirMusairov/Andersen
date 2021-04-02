<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Order History</title>
</head>
<body>
<h1>Order History</h1>

<table>
    <tr>
        <th>Id</th>
        <th>Created at</th>
        <th>Total</th>
        <th>Accepted</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.createdAt}</td>
            <td>${order.total}</td>
            <td>${order.accepted}</td>
        </tr>
    </c:forEach>
</table>

<p><a href="/main">menu</a></p>
</body>
</html>