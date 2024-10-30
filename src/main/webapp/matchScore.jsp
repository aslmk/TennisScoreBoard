<%@ page import="aslmk.Models.MatchScore" %><%--
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
    <style><%@ include file="/css/reset.css"%></style>
    <style><%@ include file="/css/styles.css"%></style>
    <style><%@ include file="/css/matchScore.css"%></style>
</head>
<body>
<%
    String uuid = request.getParameter("uuid");
%>
<div class="nav">
    <ul>
        <li><a href="/newMatch.jsp">New match</a></li>
        <li><a href="/matches">Matches</a></li>
    </ul>
</div>

<div class="container">
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Players</th>
                <th>Points</th>
                <th>Games</th>
                <th>Sets</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${currentMatch.getFirstPlayer().getName()}</td>
                <td>${currentMatch.getPointsOfPlayer(currentMatch.getFirstPlayer().getId())}</td>
                <td>${currentMatch.getGamesOfPlayer(currentMatch.getFirstPlayer().getId())}</td>
                <td>${currentMatch.getSetsOfPlayer(currentMatch.getFirstPlayer().getId())}</td>
            </tr>
            <tr>
                <td>${currentMatch.getSecondPlayer().getName()}</td>
                <td>${currentMatch.getPointsOfPlayer(currentMatch.getSecondPlayer().getId())}</td>
                <td>${currentMatch.getGamesOfPlayer(currentMatch.getSecondPlayer().getId())}</td>
                <td>${currentMatch.getSetsOfPlayer(currentMatch.getSecondPlayer().getId())}</td>
            </tr>
            </tbody>

        </table>
    </div>
    <div class="buttons">
        <form action="/match-score" method="post">
            <input type="hidden" name="uuid" value="<%= uuid %>">
            <input type="hidden" name="playerId" value="${currentMatch.getFirstPlayer().getId()}">
            <button>Player 1 wins current point</button>
        </form>
        <form action="/match-score" method="post">
            <input type="hidden" name="uuid" value="<%= uuid %>">
            <input type="hidden" name="playerId" value="${currentMatch.getSecondPlayer().getId()}">
            <button>Player 2 wins current point</button>
        </form>
    </div>

</div>
</body>
</html>
