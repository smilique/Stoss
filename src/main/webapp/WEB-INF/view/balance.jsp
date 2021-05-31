<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
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
    <fmt:message key="local.header.balance" var="pageName" bundle="${loc}" scope="session"/>
    <title>${caption} | ${pageName}</title>
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
        <fmt:message key="local.user.userData" var="welcomeMessage" bundle="${loc}" scope="session"/>
        <h2 class="welcome-message">${welcomeMessage}</h2>

        <fmt:message key="local.balance.funds" var="fundsText" bundle="${loc}" scope="session"/>
        <h2 class="welcome-message">${fundsText}</h2>
        <fmt:message key="local.balance.zeroError" var="zeroErrorMessage" bundle="${loc}" scope="session"/>
        <c:if test="${sessionScope.errormessage == 'Enter amount larger than 0!'}">
            ${zeroErrorMessage}
        </c:if>
        <li class="user-edit-wrapper">
            <div class="user-edit-left">
                <form class="deposit-left" action="${pageContext.request.contextPath}/controller?command=deposit" method="post">
                    <fmt:message key="local.balance.depositButton" var="depositButtonText" bundle="${loc}" scope="session"/>
                    <h2>${depositButtonText}</h2>
                    <label>
                        <fmt:message key="local.balance.number" var="cardNumberText" bundle="${loc}" scope="session"/>
                        ${cardNumberText}
                        <input type="text" name="cardNumber" placeholder="1111 2222 3333 4444"/>
                    </label>
                    <label>
                        <fmt:message key="local.balance.owner" var="cardOwnerText" bundle="${loc}" scope="session"/>
                        ${cardOwnerText}
                        <input class="card-owner" type="text" name="cardOwner" placeholder="NAME SURNAME"/>
                    </label>
                    <label>
                        <fmt:message key="local.balance.valid" var="cardValidText" bundle="${loc}" scope="session"/>
                        ${cardValidText}
                        <input type="text" name="expires" placeholder="MM/YY"/>
                    </label>
                    <label>
                        <fmt:message key="local.balance.cvv" var="cardCvvText" bundle="${loc}" scope="session"/>
                        ${cardCvvText}
                        <input type="number" name="cvv" placeholder="CVV" min="100" max="999"/>
                    </label>
                    <fmt:message key="local.balance.depositText" var="depositText" bundle="${loc}" scope="session"/>
                    <input type="number" name="deposit" placeholder="${depositText}" min="50" step="5">

                    <input type="submit" class="update_balance" value="${depositButtonText}">
                </form>
            </div>
            <div class="user-edit-right">
                <form class="deposit-left" action="${pageContext.request.contextPath}/controller?command=withdraw" method="post">
                    <fmt:message key="local.balance.withdrawButton" var="withdrawButtonText" bundle="${loc}" scope="session"/>
                    <h2>${withdrawButtonText}</h2>
                    <label>
                        <fmt:message key="local.balance.number" var="cardNumberText" bundle="${loc}" scope="session"/>
                            ${cardNumberText}
                        <input type="text" name="cardNumber" placeholder="1111 2222 3333 4444"/>
                    </label>
                    <label>
                        <fmt:message key="local.balance.owner" var="cardOwnerText" bundle="${loc}" scope="session"/>
                            ${cardOwnerText}
                        <input class="card-owner" type="text" name="cardOwner" placeholder="NAME SURNAME"/>
                    </label>
                    <label>
                        <fmt:message key="local.balance.valid" var="cardValidText" bundle="${loc}" scope="session"/>
                            ${cardValidText}
                        <input type="text" name="expires" placeholder="MM/YY"/>
                    </label>
                    <label>
                        <fmt:message key="local.balance.cvv" var="cardCvvText" bundle="${loc}" scope="session"/>
                            ${cardCvvText}
                        <input type="number" name="cvv" placeholder="CVV" min="100" max="999"/>
                    </label>
                    <fmt:message key="local.balance.withdrawText" var="withdrawText" bundle="${loc}" scope="session"/>
                    <input type="number" name="withdraw" placeholder="${withdrawText}" min="100">
                    <input type="submit" class="update_balance" value="${withdrawButtonText}">
                </form>
            </div>
        </li>
    </c:if>
</main>

</body>
</html>