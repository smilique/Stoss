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
        <fmt:message key="local.user.userData" var="welcomeMessage" bundle="${loc}" scope="session"/>
        <h2 class="welcome-message">${welcomeMessage}</h2>
        <c:if test="${sessionScope.errormessage == 'Password must be longer than 3 symbols!'}">
            <div style="color:#ff0000">
                <fmt:message key="local.user.passwordError" var="passwordErrorText" bundle="${loc}" scope="session"/>
                    ${passwordErrorText}
            </div>
        </c:if>
        <c:if test="${sessionScope.errormessage == 'Please choose the file to upload!'}">
            <div style="color:#ff0000">
                <fmt:message key="local.user.fileError" var="fileErrorText" bundle="${loc}" scope="session"/>
                    ${fileErrorText}
            </div>
        </c:if>
        <li class="user-edit-wrapper">
            <div class="user-edit-left">
                <form action="${pageContext.request.contextPath}/controller?command=updateUser" method="post">
                    <input class="user_edit_form" type="hidden" name="local" value="en"/>
                    <fmt:message key="local.user.changePassword" var="passwordText" bundle="${loc}" scope="session"/>
                    <p>${passwordText}</p>
                    <fmt:message key="local.user.enterNewPassword" var="passwordFieldText" bundle="${loc}" scope="session"/>
                    <input type="password" name="password" placeholder="${passwordFieldText}"/>
                    <input type="submit" class="update-user" value="${passwordText}"/>
                </form>
            </div>
            <div class="user-edit-right">
                <form action="${pageContext.request.contextPath}/controller?command=updateUserpic" method="post" enctype="multipart/form-data">
                    <fmt:message key="local.user.changeUserpic" var="userpicText" bundle="${loc}" scope="session"/>
                    <p>${userpicText}</p>
                    <input type="file" name="userpic" formenctype="multipart/form-data" accept="image/jpeg,image/png" size="1000000"/>
                    <fmt:message key="local.user.upload" var="uploadButtonText" bundle="${loc}" scope="session"/>
                    <input type="submit" class="update_user" value="${uploadButtonText}"/>
                </form>
            </div>
        </li>
        <fmt:message key="local.user.funds" var="fundsText" bundle="${loc}" scope="session"/>
        <h2 class="welcome-message">${fundsText}</h2>
        <fmt:message key="local.user.zeroError" var="zeroErrorMessage" bundle="${loc}" scope="session"/>
        <c:if test="${sessionScope.errormessage == 'Enter amount larger than 0!'}">
            ${zeroErrorMessage}
        </c:if>
        <li class="user-edit-wrapper">
            <div class="user-edit-left">
                <form action="${pageContext.request.contextPath}/controller?command=deposit" method="post">
                    <fmt:message key="local.user.depositText" var="depositText" bundle="${loc}" scope="session"/>
                    <input type="number" name="deposit" placeholder="${depositText}" min="50" step="5">
                    <fmt:message key="local.user.depositButton" var="depositButtonText" bundle="${loc}" scope="session"/>
                    <input type="submit" class="update_balance" value="${depositButtonText}">
                </form>
            </div>
            <div class="user-edit-right">
                <form action="${pageContext.request.contextPath}/controller?command=withdraw" method="post">
                    <fmt:message key="local.user.withdrawText" var="withdrawText" bundle="${loc}" scope="session"/>
                    <input type="number" name="withdraw" placeholder="${withdrawText}" min="100">
                    <fmt:message key="local.user.withdrawButton" var="withdrawButtonText" bundle="${loc}" scope="session"/>
                    <input type="submit" class="update_balance" value="${withdrawButtonText}">
                </form>
            </div>
        </li>
    </c:if>
</main>

</body>
</html>