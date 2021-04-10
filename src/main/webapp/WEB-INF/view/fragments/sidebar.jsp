<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: smilique
  Date: 05.04.2021
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <aside class="sidebar">
        <nav class="nav">
            <div class="language-select">
                <select id="standard-select" onchange="window.location.href=this.options[this.selectedIndex].value">
                    <option>Change Language</option>
                    <option value="controller?command=localeEn">EN</option>
                    <option value="controller?command=localeRu">RU</option>
                    <option value="controller?command=localeBy">BY</option>
                </select>
            </div>
            <ul>
                <c:if test="${sessionScope.user.name != null}">
                    <li class="active">
                        <a href="controller?command=mainPage">main page</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.name == null}">
                    <li class="inactive">
                        <a href="controller?command=index">login page</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </aside>
