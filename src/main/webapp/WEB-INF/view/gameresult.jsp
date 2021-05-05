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
    <link href='<c:url value="static/favicon.ico"/>' rel="icon" type="image/x-icon"/>
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
                <c:set var="card" value="${requestScope.punterCard}"/>
                <c:set var="firstCard" value="${requestScope.firstCard}"/>
                <c:set var="secondCard" value="${requestScope.secondCard}"/>
                <c:set var="gameStatus" value="${requestScope.gameStatus}"/>
                <c:set var="suit" value="${card.suit}"/>
                <fmt:message key="local.game.bankerCards" var="bankerCardsText" bundle="${loc}" scope="session"/>
                <p>${bankerCardsText}</p>
                <div class="banker-cards">
                    <div class="card">
                        <c:if test="${secondCard.suit == 'hearts' || secondCard.suit == 'diamonds'}">
                            <p class="chosen-card-red">${secondCard.code}</p>
                        </c:if>
                        <c:if test="${secondCard.suit == 'clubs' || secondCard.suit == 'spades'}">
                            <p class="chosen-card-black">${secondCard.code}</p>
                        </c:if>
                    </div>
                    <div class="card">
                        <c:if test="${firstCard.suit == 'hearts' || firstCard.suit == 'diamonds'}">
                            <p class="chosen-card-red">${firstCard.code}</p>
                        </c:if>
                        <c:if test="${firstCard.suit == 'clubs' || firstCard.suit == 'spades'}">
                            <p class="chosen-card-black">${firstCard.code}</p>
                        </c:if>
                    </div>
                </div>
                <div class="card>">
                    <fmt:message key="local.game.yourCards" var="yourCardsText" bundle="${loc}" scope="session"/>
                    <p>${yourCardsText}</p>
                    <c:if test="${suit == 'hearts' || suit == 'diamonds'}">
                        <p class="chosen-card-red">${card.code}</p>
                    </c:if>
                    <c:if test="${suit == 'clubs' || suit == 'spades'}">
                        <p class="chosen-card-black">${card.code}</p>
                    </c:if>
                </div>

                <c:if test="${gameStatus == 'win'}">
                    <fmt:message key="local.game.youWin" var="youWinText" bundle="${loc}" scope="session"/>
                    <p>${youWinText}</p>
                    <fmt:message key="local.game.startNewGame" var="startButtonText" bundle="${loc}" scope="session"/>
                    <a href="controller?command=startGame">${startButtonText}</a>
                </c:if>
                <c:if test="${gameStatus == 'lose'}">
                    <fmt:message key="local.game.youLose" var="youLoseText" bundle="${loc}" scope="session"/>
                    <p>${youLoseText}</p>
                    <a href="controller?command=startGame">${startButtonText}</a>
                </c:if>


                <c:if test="${gameStatus == 'continue'}">

                        <p>${card.name} of ${card.suit} is chosen.</p>
                    <fmt:message key="local.game.noMatch" var="noMatchText" bundle="${loc}" scope="session"/>
                        <p>${noMatchText}</p>
                        <div class="bet-form">
                            <form action="${pageContext.request.contextPath}/controller?command=bet" method="post">
                                <label>
                                    <c:set var="betValue" value="${requestScope.betValue}"/>
                                    <input readonly type="number" name="betValue" value="${betValue}" min="5" max="30">
                                </label>
                                <fmt:message key="local.game.continueButton" var="continueButtonText" bundle="${loc}" scope="session"/>
                                <input type="submit" class="place-bet" value="${continueButtonText}">
                            </form>
                        </div>
                </c:if>



            </div>
        </div>
    </c:if>


</main>

</body>

</html>