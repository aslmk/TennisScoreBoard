<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.10.2024
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tennis score board</title>
    <style><%@ include file="/css/styles.css"%></style>
</head>
<body>
    <div class="container">
        <form action="/new-match" method="post">
            <input type="text" name="player1" placeholder="player1">
            <input type="text" name="player2" placeholder="player2">
            <button>send</button>
        </form>
    </div>
</body>
</html>
