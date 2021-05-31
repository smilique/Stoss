<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization" var="loc" scope="session"/>

    <aside class="sidebar">
        <nav class="nav">
            <ul>
                <c:if test="${sessionScope.user != null}">
                    <fmt:message key="local.menu.mainPage" var="mainPage" bundle="${loc}" scope="session"/>
                    <c:choose>
                        <c:when test="${sessionScope.currentPage == 'WEB-INF/view/main.jsp'}">
                            <li class="active">
                                <a href="controller?command=mainPage">${mainPage}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="inactive">
                                <a href="controller?command=mainPage">${mainPage}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${sessionScope.user.role == 'admin'}">
                            <fmt:message key="local.menu.userAdmin" var="userAdminText" bundle="${loc}" scope="session"/>
                            <c:choose>
                                <c:when test="${sessionScope.currentPage == 'WEB-INF/view/users.jsp' ||
                        sessionScope.currentPage == 'WEB-INF/view/userDeleteConfirm.jsp'}">
                                    <li class="active">
                                        <a href="controller?command=users">${userAdminText}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a href="controller?command=users">${userAdminText}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                    </c:if>

                    <fmt:message key="local.menu.news" var="newsText" bundle="${loc}" scope="session"/>
                    <c:choose>
                        <c:when test="${sessionScope.currentPage == 'WEB-INF/view/news.jsp'}">
                            <li class="active">
                                <a href="controller?command=news&page=1&items=2">${newsText}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="inactive">
                                <a href="controller?command=news&page=1&items=2">${newsText}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    <fmt:message key="local.menu.messageBoard" var="messageBoardText" bundle="${loc}" scope="session"/>
                    <c:choose>
                        <c:when test="${sessionScope.currentPage == 'WEB-INF/view/messageBoard.jsp'}">
                            <li class="active">
                                <a href="controller?command=chat">${messageBoardText}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="inactive">
                                <a href="controller?command=chat">${messageBoardText}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

<%--                    <fmt:message key="local.menu.rules" var="rules" bundle="${loc}" scope="session"/>--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${sessionScope.currentPage == 'WEB-INF/view/rules.jsp'}">--%>
<%--                            <li class="active">--%>
<%--                                <a href="controller?command=rules">${rules}</a>--%>
<%--                            </li>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <li class="inactive">--%>
<%--                                <a href="controller?command=rules">${rules}</a>--%>
<%--                            </li>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
                <c:if test="${sessionScope.user.role != 'admin'}">
                    <fmt:message key="local.menu.rating" var="rating" bundle="${loc}" scope="session"/>
                    <c:choose>
                        <c:when test="${sessionScope.currentPage == 'WEB-INF/view/rating.jsp'}">
                            <li class="active">
                                <a href="controller?command=rating">${rating}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="inactive">
                                <a href="controller?command=rating">${rating}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <li class="active">
                        <fmt:message key="local.menu.loginPage" var="loginPage" bundle="${loc}" scope="session"/>
                        <a href="controller?command=index">${loginPage}</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <div class="language-select">
            <div class="language-element">
                <a class="active" href="controller?command=changeLocale&languageTag=en">English</a>
            </div>
            <div class="language-element">
                <a href="controller?command=changeLocale&languageTag=ru">Русский</a>
            </div>
            <div class="language-element">
                <a href="controller?command=changeLocale&languageTag=by">Беларуская</a>
            </div>
        </div>
    </aside>
