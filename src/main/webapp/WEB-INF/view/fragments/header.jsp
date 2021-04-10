<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization" var="loc" scope="session"/>

<div class="header_wrapper">
    <div class="header_logo">
        <a href="controller?command=index" class="header_logo_link">
            <img src="static/img/png/small_logo.png" alt="Stoss game" class="header_logo_img">
        </a>
    </div>
    <div class="header_caption">
        <fmt:message key="local.header.caption" var="caption" bundle="${loc}" scope="session"/>
        <h1 class="logo_text">${caption}</h1>
    </div>
    <div class="user">
        <c:if test="${sessionScope.user != null}">
            <div class="user_info">
                <fmt:message key="local.message.welcome" var="hello" bundle="${loc}" scope="session"/>
                <p>${hello} ${sessionScope.user.name}</p>
                <br/>
                <fmt:message key="local.message.balance" var="balanceText" bundle="${loc}" scope="session"/>
                <p>${balanceText} %userbalance</p>
                <fmt:message key="local.message.place" var="placeText" bundle="${loc}" scope="session"/>
                <fmt:message key="local.message.placeAddings" var="placeTextAdd" bundle="${loc}" scope="session"/>
                <fmt:message key="local.message.points" var="pointsText" bundle="${loc}" scope="session"/>
                <p>${placeText} %place${placeTextAdd} %points ${pointsText}</p>
                <br/>
                <li class="logout">
                    <fmt:message key="local.button.logout" var="logoutText" bundle="${loc}" scope="session"/>
                    <a href="controller?command=logout">${logoutText}</a>
                </li>
            </div>
        </c:if>
    </div>
</div>






