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
            <div class="cards-wrapper">
                <c:set var="card" value="${sessionScope.punterCard}"/>
                <c:set var="suit" value="${card.suit}"/>
                    <c:if test="${suit == 'hearts' || suit == 'diamonds'}">
                        <p class="chosen-card-red">${card.code}</p>
                    </c:if>
                    <c:if test="${suit == 'clubs' || suit == 'spades'}">
                        <p class="chosen-card-black">${card.code}</p>
                    </c:if>
                <label>
                    <p>${card.name} of ${card.suit} is chosen.</p>
                    <div class="bet-form">
                        <form action="${pageContext.request.contextPath}/controller?command=bet" method="post">
                            <c:if test="${sessionScope.errormessage == 'Insufficient funds'}">
                                <fmt:message key="local.balance.insufficient" var="insufficientBalanceMessage" bundle="${loc}" scope="session"/>
                                ${insufficientBalanceMessage}
                            </c:if>
                            <fmt:message key="local.game.placeBetText" var="placeBetText" bundle="${loc}" scope="session"/>
                            <p>${placeBetText}</p>
                            <input type="number" name="betValue" value="10" min="5" max="30">
                            <fmt:message key="local.game.placeBetButton" var="placeBetButtonText" bundle="${loc}" scope="session"/>
                            <input type="submit" class="place-bet" value="${placeBetButtonText}">
                        </form>
                    </div>
                </label>
            </div>
        </div>
    </c:if>



</main>

</body>

</html>