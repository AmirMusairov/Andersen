<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Sign in</title>
</head>
<body>
<h1>Sign in</h1>

<form action="/auth" method="POST">
    <label>
        Login: <input type="text" name="login">
    </label>
    <label>
        Password: <input type="password" name="password">
    </label>
    <button type="submit">sign in</button>
</form>
<p>
    <a href="/registration">Registration</a>
</p>
</body>
</html>