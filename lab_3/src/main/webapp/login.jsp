<%--
  Created by IntelliJ IDEA.
  User: yerseg
  Date: 19.09.2019
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
</head>
<body>
<h2>Enter Login and Password for SIGN IN</h2>
${message}
<form method="POST">
    <label for="login">Login:</label>
    <input id="login" type="text" placeholder="Enter the login" name="login" required>
    <p><label for="password">Password:</label>
    <input id="password" type="password" placeholder="Enter the password" name="password" required></p>
    <p><input type="submit" value="Submit"></p>
    <input type="hidden" name="hash" value=${hashNum}>
</form>

</body>
</html>