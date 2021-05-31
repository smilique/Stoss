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
    <title>${caption} | Rules</title>
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
        <p>
            The game starts with choosing the card and the bet by punter (player).
            <br>
            After the card has been chosen and the bet has been placed, the banker (AI) takes two cards from his deck and campares them with the punter's card.
            <br>
            If the first card of the banker matches the punters card by it's name - banker wins.
            <br>
            If the second banker's card matches the punter's card - punter wins.
            <br>
            If no one wins the game continues and the banker pulls another pair of cards from his deck until the winner will be found.
        </p>
</main>

</body>

</html>