<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: smilique
  Date: 06.04.2021
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
Blank register page
<form action="${pageContext.request.contextPath}/controller?command=register" method="post">
    <input class="register_form" type="hidden" name="local" value="en"/>
    <fmt:message key="local.message.username" var="usernameText" bundle="${loc}" scope="session"/>
    <p>Username: <input type="text" name="username" placeholder="username"/></p>
    <fmt:message key="local.message.password" var="passwordText" bundle="${loc}" scope="session"/>
    <p>Password: <input type="password" name="first_password" placeholder="password"/></p>
    repeat password
    name
    <fmt:message key="local.button.register" var="registerButtonText" bundle="${loc}" scope="session"/>
    <input type="submit" value="${registerButtonText}"/>
</body>
</html>
