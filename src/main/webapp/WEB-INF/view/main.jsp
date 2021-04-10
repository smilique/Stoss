<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<header class="header">
    <jsp:include page="fragments/header.jsp"/>
</header>

<nav class="menu">
    <jsp:include page="fragments/sidebar.jsp"/>
</nav>

<main class="container">

    <c:if test="${sessionScope.user.name != null}">
        <h2>
            Hello, ${sessionScope.user.name}
        </h2>
    </c:if>

<%--        <c:if test="${sessionScope.user.name == null}">--%>
<%--            <div style="color:#7e36ec">--%>
<%--                    ${sessionScope.errormessage}--%>
<%--                    ${requestScope.get(0)}--%>
<%--            </div>--%>
<%--        </c:if>--%>
</main>

</body>

</html>