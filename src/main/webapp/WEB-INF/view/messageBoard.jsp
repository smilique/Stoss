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

    <c:set var="messages" value="${requestScope.get('messages')}"/>
<div class="message-container">
    <c:forEach items="${messages}" var="message">
        <div class="message">
            <div class="message-left-info">
                <p class="message-user">${message.username}: </p>
                <p>${message.text}</p>
            </div>
            <div class="message-right-info">
<%--                <div class="message-date"--%>
                <c:choose>
                    <c:when test="${sessionScope.locale == 'en'}">
                        <div class="message-date">
                            <p><fmt:formatDate value="${message.date}" pattern="yyyy-MM-dd"/></p>
                        </div>
                        <div class="message-time">
                            <p><fmt:formatDate value="${message.time}" pattern="hh:mm:ss aa"/> </p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="message-date">
                            <p><fmt:formatDate value="${message.date}" pattern="dd.MM.yyyy"/></p>
                        </div>
                        <div class="message-time">
                            <p><fmt:formatDate value="${message.time}" pattern="HH:mm:ss"/> </p>
                        </div>
                    </c:otherwise>
                </c:choose>
<%--                </div>--%>

    <c:if test="${sessionScope.user.role == 'admin'}">
        <div class="message-moderation">
            <form action="${pageContext.request.contextPath}/controller?command=deleteMessage" method="post">
                <input type="hidden" name="messageId" value="${message.id}">
                <fmt:message key="local.button.delete" var="deleteButtonText" scope="session" bundle="${loc}"/>
                <input type="submit" value="${deleteButtonText}">
            </form>
        </div>
    </c:if>
        </div>
        </div>
    </c:forEach>
</div>

        <c:if test="${sessionScope.user != null}">
        <div class="message-send">
            <form  action="${pageContext.request.contextPath}/controller?command=sendMessage" method="post">
                <fmt:message key="local.chat.enterMessage" var="enterMessageText" bundle="${loc}" scope="session"/>
                <input class="message-text" type="text" name="text" placeholder="${enterMessageText}"/>
                <fmt:message key="local.chat.sendMessage" var="sendButtonText" bundle="${loc}" scope="session"/>
                <input class="message-submit" type="submit" name="sendMessage" value="${sendButtonText}"/>
            </form>
        </div>
        </c:if>

</main>
</body>
</html>
