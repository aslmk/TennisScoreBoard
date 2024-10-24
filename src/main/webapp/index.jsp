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
    <div class="nav">
        <ul>
            <li><a href="/index.jsp">New match</a></li>
            <li><a href="/matches">Matches</a></li>
        </ul>
    </div>
    <div class="container">
        <form action="/new-match" method="post">
            <input type="text" name="player1" placeholder="Name of first player">
            <input type="text" name="player2" placeholder="Name of second player">
            <button>send</button>
        </form>
    </div>

</body>
</html>
