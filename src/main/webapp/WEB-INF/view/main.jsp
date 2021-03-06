<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <c:if test="${sessionScope.user != null}">
        <c:if test="${sessionScope.user.role != 'admin'}">
            <div class="game">
                <div class="welcome-message">
                    <fmt:message key="local.main.userWelcome" var="userWelcomeText" bundle="${loc}" scope="session"/>
                    <p>${userWelcomeText}</p>
                </div>
                <div class="game-button">
                    <fmt:message key="local.game.startNewGame" var="startButtonText" bundle="${loc}" scope="session"/>
                    <a class="start-button" href="controller?command=startGame">${startButtonText}</a>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.user.role == 'admin'}">
            <div class="welcome-message">
                <fmt:message key="local.main.adminWelcome" var="adminWelcomeText" bundle="${loc}" scope="session"/>
                <p>${adminWelcomeText}</p>
            </div>
        </c:if>
    </c:if>

</main>

</body>

</html>