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
    <fmt:message key="local.errors.caption" var="caption" bundle="${loc}" scope="session"/>
    <title>${caption}</title>
    <link rel="stylesheet" href="static/reset.css">
    <link rel="stylesheet" href="static/style.css">
    <link href='<c:url value="static/favicon.ico"/>' rel="icon" type="image/x-icon" />
</head>
<body>

<div class="welcome-message">
    <p>AN ERROR OCCURED</p>
</div>
<main class="container">

    <c:set var="errormessage" value="${requestScope.errormessage}"/>
    <c:choose>
        <c:when test="${errormessage == 'unknown command'}">
            <fmt:message key="local.errors.unknownCommand" var="errorText" bundle="${loc}" scope="session"/>
            <p>${errorText}</p>
        </c:when>
        <c:otherwise>
            <p>${errormessage}</p>
        </c:otherwise>
    </c:choose>

    <fmt:message key="local.errors.message" var="messageText" bundle="${loc}" scope="session"/>
    <h2>${messageText}</h2>
    <li>
        <fmt:message key="local.menu.mainPage" var="mainPage" bundle="${loc}" scope="session"/>
        <a href="controller?command=mainPage">${mainPage}</a>
    </li>

</main>
</body>
</html>