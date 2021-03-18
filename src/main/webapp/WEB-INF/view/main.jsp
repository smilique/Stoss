<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="header">
    <jsp:include page="fragments/header.jsp"/>
</div>

<div class="menu">
    <jsp:include page="fragments/menu.jsp"/>
</div>

<div class="container">
    <c:if test="${name != null}">
        <h2>
            Hello, ${name}
        </h2>
    </c:if>
    <c:if test="${errorMessage != null}">
        <div style="color:#FF0000">
                ${errorMessage}
        </div>
    </c:if>
</div>

</body>

</html>