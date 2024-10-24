<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12.10.2024
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="aslmk.Models.Match" %>
<html>
<head>
    <title>Current match results</title>
    <style><%@ include file="/css/styles.css"%></style>
</head>
<body>
<%
    Match match = (Match) request.getAttribute("match");
    String match_uuid = (String) request.getAttribute("match_uuid");
%>
<div class="nav">
    <ul>
        <li><a href="/index.jsp">New match</a></li>
        <li><a href="/matches">Matches</a></li>
    </ul>
</div>
    <h1>Match UUID: <%= match_uuid %></h1>
    <h1>First player: <%= match.getFirstPlayer().getName() %></h1>
    <h1>Second player: <%= match.getSecondPlayer().getName() %></h1>
    <h1>Winner of this match: <%= match.getWinner().getName() %></h1>
</body>
</html>
