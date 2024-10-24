<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.10.2024
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Current match</title>
    <style><%@ include file="/css/styles.css"%></style>
</head>
<body>
<%
    String uuid = request.getParameter("uuid");
%>
<div class="nav">
    <ul>
        <li><a href="/index.jsp">New match</a></li>
        <li><a href="/matches">Matches</a></li>
    </ul>
</div>

<div class="container">
    <div class="playerScoreContainer">
        <div class="firstPlayerContainer">
            <div class="firstPlayerScores">
                <p>Player1: ${firstPlayerName}</p>
                <p>Points: ${firstPlayerPoints}</p>
                <p>Games: ${firstPlayerGames}</p>
                <p>Sets: ${firstPlayerSets}</p>
            </div>
        </div>
        <div class="secondPlayerContainer">
            <div class="secondPlayerScores">
                <p>Player2: ${secondPlayerName}</p>
                <p>Points: ${secondPlayerPoints}</p>
                <p>Games: ${secondPlayerGames}</p>
                <p>Sets: ${secondPlayerSets}</p>
            </div>

        </div>
    </div>
    <div class="buttons">
        <form action="/match-score" method="post">
            <input type="hidden" name="uuid" value="<%= uuid %>">
            <input type="hidden" name="playerId" value="${firstPlayerId}">
            <button>Player 1 wins current point</button>
        </form>
        <form action="/match-score" method="post">
            <input type="hidden" name="uuid" value="<%= uuid %>">
            <input type="hidden" name="playerId" value="${secondPlayerId}">
            <button>Player 2 wins current point</button>
        </form>
    </div>

</div>
</body>
</html>
