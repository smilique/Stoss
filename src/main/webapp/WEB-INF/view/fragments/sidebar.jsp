<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization" var="loc" scope="session"/>

    <aside class="sidebar">
        <nav class="nav">
            <ul>
                <c:if test="${sessionScope.user != null}">
                    <li class="active">
                        <fmt:message key="local.menu.mainPage" var="mainPage" bundle="${loc}" scope="session"/>
                        <a href="controller?command=mainPage">${mainPage}</a>
                    </li>
                    <c:if test="${sessionScope.user.role == 'admin'}">
                        <li>
                            <fmt:message key="local.menu.userAdmin" var="userAdminText" bundle="${loc}" scope="session"/>
                            <a href="controller?command=users">${userAdminText}</a>
                        </li>
                    </c:if>
                    <li class="inactive">
                        <fmt:message key="local.menu.news" var="newsText" bundle="${loc}" scope="session"/>
                        <a href="controller?command=news&page=1&items=2">${newsText}</a>
                    </li>
                    <li class="inactive">
                        <fmt:message key="local.menu.chat" var="chatText" bundle="${loc}" scope="session"/>
                        <a href="controller?command=chat">${chatText}</a>
                    </li>
                    <li class="inactive">
                        <fmt:message key="local.menu.rules" var="rules" bundle="${loc}" scope="session"/>
                        <a href="controller?command=rules">${rules}</a>
                    </li>
                    <li class="inactive">
                        <fmt:message key="local.menu.rating" var="rating" bundle="${loc}" scope="session"/>
                        <a href="controller?command=rating">${rating}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <li class="inactive">
                        <fmt:message key="local.menu.loginPage" var="loginPage" bundle="${loc}" scope="session"/>
                        <a href="controller?command=index">${loginPage}</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </aside>
