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

    <table class="users-wrapper">
        <c:set var="itemsPerPage" value="10"/>
        <c:set var="usersList" value="${requestScope.get('users')}"/>
        <c:forEach items="${usersList}" var="user">
            <table-row class="user-item">
                <div class="user-item-wrapper">
                    <p class="user-name">${user.name}</p>
                    <p class="user-balance">${user.balance}</p>
                </div>
                <div class="user-item-wrapper">
                    <p class="user-points">${user.points}</p>
                    <img src="${user.userpic}"/>
                </div>
                <div class="user-item-wrapper">
                    <a href="controller?command=confirmUserDelete&login=${user.login}">DELETE USER</a>
                    <a href="controller?command=editUser&login=${user.login}">EDIT USER</a>
                </div>
            </table-row>
        </c:forEach>

        <jsp:include page="fragments/pagination.jsp">
            <jsp:param name="itemsPerPage" value="${itemsPerPage}"/>
        </jsp:include>
    </table>



</main>

</body>

</html>