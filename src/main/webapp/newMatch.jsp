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
    <style><%@ include file="/css/reset.css"%></style>
    <style><%@ include file="/css/styles.css"%></style>
    <style><%@ include file="/css/newMatch.css"%></style>
</head>
<body>
    <div class="nav">
        <ul>
            <li><a href="/newMatch.jsp">New match</a></li>
            <li><a href="/matches">Matches</a></li>
        </ul>
    </div>
    <div class="container">
        <div class="form-container">
            <h1>Tennis score board</h1>
            <form action="/new-match" method="post">
                <div class="form-item">
                    <input type="text" name="player1" placeholder="Name of the first player">
                </div>
                <div class="form-item">
                    <input type="text" name="player2" placeholder="Name of the second player">
                </div>
                <div class="form-item">
                    <button>start</button>
                </div>
            </form>
        </div>
    </div>


</body>
</html>
