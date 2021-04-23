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
    <jsp:include page="WEB-INF/view/fragments/header.jsp"/>
</header>

<nav class="menu">
    <jsp:include page="WEB-INF/view/fragments/sidebar.jsp"/>
</nav>

<main class="container">
    <c:if test="${sessionScope.errormessage != null}">
        <div style="color:#ff0000">
            <c:if test="${sessionScope.errormessage == 'Invalid login or password!'}">
                <fmt:message key="local.message.userNotFound" var="loginMessage" bundle="${loc}" scope="session"/>
                ${loginMessage}
            </c:if>
        </div>
    </c:if>
    <jsp:include page="WEB-INF/view/fragments/login.jsp"/>
</main>

<div class="chatbox">
    <jsp:include page="WEB-INF/view/fragments/chat.jsp"/>
</div>
</body>
</html>
