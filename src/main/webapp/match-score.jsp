<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.10.2024
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="aslmk.Services.Impl.OngoingMatchesServiceImpl" %>
<%@ page import="aslmk.Models.MatchScore" %>
<%@ page import="java.util.UUID" %>
<html>
<head>
    <title>Current match</title>
</head>
<body>
<%
    String uuid = request.getParameter("uuid");
    OngoingMatchesServiceImpl ongoingMatchesService = new OngoingMatchesServiceImpl();
    MatchScore currentMatch = ongoingMatchesService.getMatchByUUID(UUID.fromString(uuid));
%>
<p>Player1: ${firstPlayerName}</p>
<p>Points: ${firstPlayerPoints}</p>
<p>Games: ${firstPlayerGames}</p>
<p>Sets: ${firstPlayerSets}</p>
<form action="/match-score" method="get">
    <input type="hidden" name="uuid" value="<%= uuid %>">
    <input type="hidden" name="playerId" value="<%= ongoingMatchesService.getFirstPlayerId() %>">
    <button>player 1 wins</button>
</form>

<p>Player2: ${secondPlayerName}</p>
<p>Points: ${secondPlayerPoints}</p>
<p>Games: ${secondPlayerGames}</p>
<p>Sets: ${secondPlayerSets}</p>
<form action="/match-score" method="get">
    <input type="hidden" name="uuid" value="<%= uuid %>">
    <input type="hidden" name="playerId" value="<%= ongoingMatchesService.getSecondPlayerId() %>">
    <button>player 2 wins</button>
</form>

<p>Winner: ${winner}</p>
</body>
</html>
