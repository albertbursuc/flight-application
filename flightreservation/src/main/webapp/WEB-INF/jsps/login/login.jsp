<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<form action="login" method="post">
<pre>
<h2>Log in</h2>
Email: <input type="text" name="email"/>
Password: <input type="password" name="password"/>
<input type="submit" value="Login"/>
${wrong}
</pre>
</form>
</body>
</html>