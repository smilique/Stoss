<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="header">
    <jsp:include page="WEB-INF/view/fragments/header.jsp"/>
</div>

<div class="menu">
    <jsp:include page="WEB-INF/view/fragments/menu.jsp"/>
</div>
    <div class="container">
    <form action="/Stoss/controller" method="post"/>
        <input type="hidden" name="command" value="login"/>
        <input type="text" name="username"/>
        <br/>
        <input type="password" name="password"/>
        <br/>
        <input type="submit" value="submit"/>
    </form>
    </div>
</body>
</html>
