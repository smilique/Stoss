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
        <%--        <fmt:message key="local.message.login" var="loginText" bundle="${loc}" scope="session"/>--%>
        <h2 class="welcome-message">%updateUserText</h2>
        <c:if test="${sessionScope.errormessage == 'Password must be longer than 3 symbols!'}">
            ${sessionScope.errormessage}
        </c:if>
        <c:if test="${sessionScope.errormessage == 'Please choose the file to upload!'}">
            ${sessionScope.errormessage}
        </c:if>
        <li class="user-edit-wrapper">
            <div class="user-edit-left">
                <form action="${pageContext.request.contextPath}/controller?command=updateUser" method="post">
                    <input class="user_edit_form" type="hidden" name="local" value="en"/>
                    <fmt:message key="local.message.password" var="passwordText" bundle="${loc}" scope="session"/>
                    <p>%changePasswordHere</p>
                    <input type="password" name="password" placeholder="enter new password"/>
                    <input type="submit" class="update_user" value="%updateParamsButton"/>
                </form>
            </div>
            <div class="user-edit-right">
                <form action="${pageContext.request.contextPath}/controller?command=updateUserpic" method="post" enctype="multipart/form-data">
                    <p>%chooseUserpic</p>
                    <input type="file" name="userpic" formenctype="multipart/form-data" accept="image/jpeg,image/png" size="1000000"/>
                    <fmt:message key="local.button.login" var="loginButtonText" bundle="${loc}" scope="session"/>
                    <input type="submit" class="update_user" value="%updateUserpicButton"/>
                </form>
            </div>
        </li>
        <h2 class="welcome-message">%depositOrWithdrawFunds</h2>
        <c:if test="${sessionScope.errormessage == 'Enter amount larger than 0!'}">
            ${sessionScope.errormessage}
        </c:if>
        <li class="user-edit-wrapper">
            <div class="user-edit-left">
                <form action="${pageContext.request.contextPath}/controller?command=deposit" method="post">
                    <p>Enter amount to deposit</p>
                    <input type="number" name="deposit" placeholder="%amount">
                    <input type="submit" class="update_balance" value="%addDeposit">
                </form>
            </div>
            <div class="user-edit-right">
                <form action="${pageContext.request.contextPath}/controller?command=withdraw" method="post">
                    <p>Enter amount to withdraw</p>
                    <input type="number" name="withdraw" placeholder="%amount">
                    <input type="submit" class="update_balance" value="%addDeposit">
                </form>
            </div>
        </li>
    </c:if>
</main>

</body>
</html>