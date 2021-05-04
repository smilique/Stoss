<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization" var="loc" scope="session"/>

    <aside class="sidebar">
        <nav class="nav">
            <div class="language-select">
                <a href="controller?command=localeEn">English</a>
                <a href="controller?command=localeRu">Русский</a>
                <a href="controller?command=localeBy">Беларуская</a>
<%--                <select id="standard-select" onchange="window.location.href=this.options[this.selectedIndex].value">--%>
<%--                    <option>Change Language</option>--%>
<%--                    <fmt:message key="locale.en" var="en" bundle="${loc}" scope="session"/>--%>
<%--                    <option value="controller?command=localeEn">${en}</option>--%>
<%--                    <fmt:message key="locale.ru" var="ru" bundle="${loc}" scope="session"/>--%>
<%--                    <option value="controller?command=localeRu">${ru}</option>--%>
<%--                    <fmt:message key="locale.by" var="by" bundle="${loc}" scope="session"/>--%>
<%--                    <option value="controller?command=localeBy">${by}</option>--%>
<%--                </select>--%>
            </div>
            <ul>
                <c:if test="${sessionScope.username != null}">
                    <li class="active">
                        <fmt:message key="local.menu.mainPage" var="mainPage" bundle="${loc}" scope="session"/>
                        <a href="controller?command=mainPage">${mainPage}</a>
                    </li>
                    <c:if test="${sessionScope.user.role == 'admin'}">
                        <li>
                            <a href="">%userAdministration</a>
                        </li>
                    </c:if>
                    <li class="inactive">
                        <fmt:message key="local.menu.news" var="newsText" bundle="${loc}" scope="session"/>
                        <a href="controller?command=news&page=1&items=2">${newsText}</a>
                    </li>
                    <li class="inactive">
                        <a href="controller?command=chat">%chat</a>
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
                <c:if test="${sessionScope.username == null}">
                    <li class="inactive">
                        <fmt:message key="menu.loginPage" var="loginPage" bundle="${loc}" scope="session"/>
                        <a href="controller?command=index">${loginPage}</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </aside>
