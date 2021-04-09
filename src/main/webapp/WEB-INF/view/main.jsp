<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Main Menu</title>
</head>
<body>
<h1>Hello Servlet</h1>
<p>Welcome : ${name}</p>

<h2>Menu</h2>
<ul>
    <li><a href="/warehouse/products">Show product list</a></li>
    <li><a href="/bucket/products">Show products in the bucket</a></li>
    <li><a href="/clear">Clear bucket</a></li>
    <li><a href="/order/create-order">Create order</a></li>
    <li><a href="/order/order-history">Order history</a></li>
    <li><a href="/order/declined-order">Accept saved orders</a></li>
    <li><a href="/logout">Exit</a></li>
</ul>
</body>
</html>