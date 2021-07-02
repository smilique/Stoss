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
    <p>${requestScope.errormessage}</p>
    <div class="users-wrapper">
        <table class="users-table">
            <c:set var="itemsPerPage" value="10"/>
            <c:set var="usersList" value="${requestScope.get('users')}"/>
            <fmt:message key="local.users.login" var="login" scope="session" bundle="${loc}"/>
            <fmt:message key="local.users.name" var="username" scope="session" bundle="${loc}"/>
            <fmt:message key="local.users.balance" var="balance" scope="session" bundle="${loc}"/>
            <fmt:message key="local.users.points" var="points" scope="session" bundle="${loc}"/>
            <fmt:message key="local.users.userpic" var="avatar" scope="session" bundle="${loc}"/>
            <fmt:message key="local.users.action" var="actions" scope="session" bundle="${loc}"/>
            <tr class="table-caption"><th>${login}</th><th>${username}</th><th>${balance}</th><th>${points}</th><th>${avatar}</th><th>${actions}</th></tr>
            <c:forEach items="${usersList}" var="user">
                <tr class="user-item">
                    <td><p class="user-login">${user.login}</p></td>
                    <td><p class="user-name">${user.name}</p></td>
                    <td><p class="user-balance">${user.balance}</p></td>
                    <td><p class="user-points">${user.points}</p></td>
                    <td><img src="${user.userpic}" alt="${user.name}"/></td>
                    <td>
                        <fmt:message key="local.users.edit" var="edit" scope="session" bundle="${loc}"/>
                        <a href="controller?command=editUser&login=${user.login}">${edit}</a>
                        <fmt:message key="local.users.delete" var="delete" scope="session" bundle="${loc}"/>
                        <a href="controller?command=confirmUserDelete&login=${user.login}">${delete}</a>
                    </td>
                </tr>
            </c:forEach>

            <jsp:include page="fragments/pagination.jsp">
                <jsp:param name="itemsPerPage" value="${itemsPerPage}"/>
            </jsp:include>
        </table>
    </div>

</main>

</body>

</html>