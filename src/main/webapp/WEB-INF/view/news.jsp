<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dt" uri="dateTimeTag" %>

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
    <div class="news-wrapper">
        <c:set var="itemsPerPage" value="2"/>
        <c:set var="newsList" value="${requestScope.get('news')}"/>
        <c:forEach items="${newsList}" var="newsItem">
            <div class="news-item">
                    <p class="news-caption">${newsItem.caption}</p>
                    <p class="news-text">${newsItem.text}</p>
                        <div class="news-date">
                            <dt:dateTimeTag date="${newsItem.date}" time="${newsItem.time}" locale="${sessionScope.locale}"/>
                            <c:if test="${sessionScope.user.role == 'admin'}">
                                <div class="message-moderation">
                                    <form action="${pageContext.request.contextPath}/controller?command=deleteNewsItem" method="post">
                                        <input type="hidden" name="newsItemId" value="${newsItem.id}">
                                        <fmt:message key="local.button.delete" var="deleteButtonText" scope="session" bundle="${loc}"/>
                                        <input type="submit" value="${deleteButtonText}">
                                    </form>
                                </div>
                            </c:if>
                        </div>

            </div>
        </c:forEach>

        <jsp:include page="fragments/pagination.jsp">
            <jsp:param name="itemsPerPage" value="${itemsPerPage}"/>
        </jsp:include>
    </div>
</main>

</body>

</html>