<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: smilique
  Date: 06.04.2021
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Chat messages here</h2>
<div class="message_container">
    <p>Hello</p>
    <span class="time">11:00</span>
</div>


    <c:if test="${sessionScope.user.name != null}">
        <h2>
            <textarea>Logged used detected. box for sending messages here.</textarea>
            <label for="chatInput">Chat</label><textarea id="chatInput" rows="2" spellcheck="false" placeholder="Enter your message..." style="display: none;"></textarea>
            <a href="controller?command=sendMessage${chatInput.text}" class="send_button">
                <span>Send</span>
            </a>
        </h2>
    </c:if>
</body>
</html>
