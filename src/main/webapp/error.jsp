<%--
  Created by IntelliJ IDEA.
  User: smilique
  Date: 19.03.2021
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page errorPage="error.jsp" %>--%>

<html>
<head>
    <h2>Error</h2>
    ${sessionScope.errormessage}
    <li class="inactive">
        <a href="controller?command=index">login page</a>
    </li>
</head>
<body>

</body>
</html>
