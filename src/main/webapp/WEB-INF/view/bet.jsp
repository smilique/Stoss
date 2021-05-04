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
        <div class="game">

            <c:set var="game" value="${sessionScope.game}"/>
            <c:set var="punterCards" value="${game.punterDeck}"/>
            <div class="cards-wrapper">
                <c:set var="card" value="${sessionScope.punterCard}">
                    <c:if test="${card.suit == 'diamonds'}">
                        <a class="card-red" href="controller?command=chooseCard&card=${card.code}">${card.code}</a>
                    </c:if>
                    <c:if test="${card.suit == 'hearts'}">
                        <a class="card-red" href="controller?command=chooseCard&card=${card.code}">${card.code}</a>
                    </c:if>
                    <c:if test="${card.suit == 'clubs'}">
                        <a class="card-black" href="controller?command=chooseCard&card=${card.code}">${card.code}</a>
                    </c:if>
                    <c:if test="${card.suit == 'spades'}">
                        <a class="card-black" href="controller?command=chooseCard&card=${card.code}">${card.code}</a>
                    </c:if>
                    <form action=""
                </c:set>
            </div>
        </div>
    </c:if>



</main>

</body>

</html>