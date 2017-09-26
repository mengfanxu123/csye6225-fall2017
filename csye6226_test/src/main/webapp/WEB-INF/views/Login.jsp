<%--
  Created by IntelliJ IDEA.
  User: xss
  Date: 2017/9/25
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/login.htm" method="post">
    E-mail: <br>
    <input type="email" name="email"> <br>
    Password: <br>
    <input type="password" name="password"> <br>

    <input type="submit" name="submit" value="submit">
    <br>
    <a href="/account.htm">Register an Account</a>

</form>
</body>
</html>
