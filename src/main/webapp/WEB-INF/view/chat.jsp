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
                <p>${message.username}: </p>
                <p>${message.text}</p>
            </div>
            <div class="message-right-info">
                <p>${message.time} </p>
                <p>${message.date}</p>
            </div>
        </div>
    </c:forEach>
    <p>Hello</p>
    <span class="time">11:00</span>
</div>


    <c:if test="${sessionScope.user.name != null}">
        <div class="message-send">
            <form  action="${pageContext.request.contextPath}/controller?command=sendMessage" method="post">
                <input type="text" name="text" placeholder="%yourMessageHere"/>
                <input type="submit" name="sendMessage" value="%sendMessage"/>
<%--                <input type="number" name="userId" hidden="hidden" value="${sessionScope.user.id}">--%>
            </form>
        </div>
<%--            <label for="chatInput">Chat</label>--%>
<%--        <textarea id="chatInput" rows="2" spellcheck="false" placeholder="Enter your message..." maxlength="255"></textarea>--%>
    </c:if>
</body>
</html>
