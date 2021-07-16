<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization" var="loc" scope="session"/>

<div class="header-wrapper">
    <div class="header_logo">
        <c:if test="${sessionScope.user == null}">
            <a href="controller?command=index" class="header_logo_link">
                <img src="static/img/png/small_logo.png" alt="Stoss game" class="header-logo-img">
            </a>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <a href="controller?command=mainPage" class="header_logo_link">
                <img src="static/img/png/small_logo.png" alt="Stoss game" class="header-logo-img">
            </a>
        </c:if>
    </div>
    <div class="header-caption">
        <fmt:message key="local.header.caption" var="caption" bundle="${loc}" scope="session"/>
        <h1 class="logo-text">${caption}</h1>
    </div>
    <div class="user-wrapper">
        <c:if test="${sessionScope.user != null}">
            <div class="user-info">
                <fmt:message key="local.message.welcome" var="hello" bundle="${loc}" scope="session"/>
                <p>${hello}, <a href="controller?command=user" class="user_profile_link">${sessionScope.user.name}</a></p>
                <fmt:message key="local.message.balance" var="balanceText" bundle="${loc}" scope="session"/>
                <p>${balanceText}: <a href="controller?command=balance">${sessionScope.user.balance}</a></p>
                <fmt:message key="local.message.place" var="placeText" bundle="${loc}" scope="session"/>
                <fmt:message key="local.message.points" var="pointsText" bundle="${loc}" scope="session"/>
                <p>${placeText} ${sessionScope.user.points} ${pointsText}</p>
                <li class="logout">
                    <fmt:message key="local.button.logout" var="logoutText" bundle="${loc}" scope="session"/>
                    <a href="controller?command=logout">${logoutText}</a>
                </li>
            </div>
            <div class="userpic">
                <a href="controller?command=user" class="user_profile_link">
                    <img src="${sessionScope.user.userpic}" alt="${sessionScope.user.name}" class="userpic-img">
                </a>
            </div>
        </c:if>
        <div class="language-select">
            <div class="language-element">
                <c:choose>
                    <c:when test="${sessionScope.locale == 'en'}">
                        <a class="current-language" href="controller?command=changeLocale&languageTag=en">English</a>
                    </c:when>
                    <c:otherwise>
                        <a class="language" href="controller?command=changeLocale&languageTag=en">English</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="language-element">
                <c:choose>
                    <c:when test="${sessionScope.locale == 'ru'}">
                        <a class="current-language" href="controller?command=changeLocale&languageTag=ru">Русский</a>
                    </c:when>
                    <c:otherwise>
                        <a class="language" href="controller?command=changeLocale&languageTag=ru">Русский</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="language-element">
                <c:choose>
                    <c:when test="${sessionScope.locale == 'by'}">
                        <a class="current-language" href="controller?command=changeLocale&languageTag=by">Беларуская</a>
                    </c:when>
                    <c:otherwise>
                        <a class="language" href="controller?command=changeLocale&languageTag=by">Беларуская</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>






