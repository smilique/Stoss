<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization" var="loc" scope="session"/>

<html>
<body>
<c:if test="${sessionScope.user == null}">
    <fmt:message key="local.message.login" var="loginText" bundle="${loc}" scope="session"/>
    <h2 class="welcome-message">${loginText}</h2>
    <li>
        <form action="${pageContext.request.contextPath}/controller?command=login" method="post">
            <input class="login-form" type="hidden" name="local" value="en"/>
            <fmt:message key="local.message.username" var="usernameText" bundle="${loc}" scope="session"/>
            <p><input type="text" name="username" placeholder="${usernameText}"/></p>
            <fmt:message key="local.message.password" var="passwordText" bundle="${loc}" scope="session"/>
            <p><input type="password" name="password" placeholder="${passwordText}"/></p>
            <fmt:message key="local.button.login" var="loginButtonText" bundle="${loc}" scope="session"/>
            <input type="submit" value="${loginButtonText}"/>
        </form>
    </li>
    <li>
        <fmt:message key="local.button.register" var="registerButtonText" bundle="${loc}" scope="session"/>
        <fmt:message key="local.message.or" var="orText" bundle="${loc}" scope="session"/>
        <h3>${orText} </h3><a href="controller?command=registerPage">${registerButtonText}</a>
    </li>
</c:if>

</body>
</html>
