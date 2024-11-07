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
    <style><%@ include file="/css/reset.css"%></style>
    <style><%@ include file="/css/styles.css"%></style>
    <style><%@ include file="/css/matchScore.css"%></style>
</head>
<body>
<%
    String uuid = request.getParameter("uuid");
%>
<%@include file="navigation.jsp"%>

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
                <td>${currentMatchScore.getFirstPlayer().getName()}</td>
                <td>${currentMatchScore.getPlayerPoints(0)}</td>
                <td>${currentMatchScore.getGameScore(0)}</td>
                <td>${currentMatchScore.getSetsScore(0)}</td>
            </tr>
            <tr>
                <td>${currentMatchScore.getSecondPlayer().getName()}</td>
                <td>${currentMatchScore.getPlayerPoints(1)}</td>
                <td>${currentMatchScore.getGameScore(1)}</td>
                <td>${currentMatchScore.getSetsScore(1)}</td>
            </tr>
            </tbody>

        </table>
    </div>
    <div class="buttons">
        <form action="/match-score" method="post">
            <input type="hidden" name="uuid" value="<%= uuid %>">
            <input type="hidden" name="playerId" value="0">
            <button>Player 1 wins current point</button>
        </form>
        <form action="/match-score" method="post">
            <input type="hidden" name="uuid" value="<%= uuid %>">
            <input type="hidden" name="playerId" value="1">
            <button>Player 2 wins current point</button>
        </form>
    </div>

</div>
</body>
</html>
