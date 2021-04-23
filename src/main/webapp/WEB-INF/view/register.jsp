<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fmt_rt" uri="http://java.sun.com/jstl/fmt_rt" %>--%>

<c:if test="${sessionScope.locale != null}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="localization" var="loc" scope="session"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <fmt:message key="local.header.caption" var="caption" bundle="${loc}" scope="session"/>
    <title>${caption}</title>
    <link rel="stylesheet" href="static/reset.css">
    <link rel="stylesheet" href="static/style.css">
    <link href='<c:url value="static/favicon.ico"/>' rel="icon" type="image/x-icon" />
</head>
<body>
<header class="header">
    <jsp:include page="fragments/header.jsp"/>
</header>

<nav class="menu">
    <jsp:include page="fragments/sidebar.jsp"/>
</nav>

<main class="container">
    ${sessionScope.errormessage}
    <c:if test="${sessionScope.errormessage != null}">
        <div style="color:#ff0000">
            <c:if test="${sessionScope.errormessage == 'User already exists!'}">
                <fmt:message key="local.message.userNotFound" var="loginMessage" bundle="${loc}" scope="session"/>
                %registerMessage
            </c:if>
        </div>
    </c:if>
    <c:if test="${sessionScope.user == null}">
<%--        <fmt:message key="local.message.login" var="loginText" bundle="${loc}" scope="session"/>--%>
        <h2 class="welcome_message">%registerText</h2>
        <li>
            <form action="${pageContext.request.contextPath}/controller?command=register" method="post">
                <input class="login_form" type="hidden" name="local" value="en"/>
                <fmt:message key="local.message.username" var="usernameText" bundle="${loc}" scope="session"/>
                <p>${usernameText}<input type="text" name="username" placeholder="username"/></p>
                <fmt:message key="local.message.password" var="passwordText" bundle="${loc}" scope="session"/>
                <p>${passwordText}<input type="password" name="password" placeholder="password"/></p>
                <p>%name<input type="text" name="name" placeholder="name"></p>
                <p>%userPicHere<input type="file" accept="image/jpeg,image/png" size="1000000"/></p>
                <fmt:message key="local.button.login" var="loginButtonText" bundle="${loc}" scope="session"/>
                <input type="submit" value="%registerButtonText"/>
                <fmt:message key="local.button.register" var="registerButtonText" bundle="${loc}" scope="session"/>
                <fmt:message key="local.message.or" var="orText" bundle="${loc}" scope="session"/>
            </form>
        </li>
        <li>
            <h3>${orText} </h3><a href="controller?command=index">%loginButtonText</a>
        </li>
    </c:if>
</main>

<div class="chatbox">
    <jsp:include page="fragments/chat.jsp"/>
</div>
</body>
</html>