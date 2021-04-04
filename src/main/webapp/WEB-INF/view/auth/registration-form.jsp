<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Sign up</title>
</head>
<body>
<h1>Sign up</h1>
<form action="/registration" method="POST">
    <label>
        Login: <input type="text" name="login">
    </label>
    <label>
        Password: <input type="password" name="password">
    </label>
    <button type="submit">sign up</button>
</form>
</body>
</html>