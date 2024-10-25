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
    <style><%@ include file="/css/reset.css"%></style>
    <style><%@ include file="/css/styles.css"%></style>
    <style><%@ include file="/css/currentMatchResults.css"%></style>
</head>
<body>
<%
    Match match = (Match) request.getAttribute("match");
    String match_uuid = (String) request.getAttribute("match_uuid");
%>
    <div class="nav">
        <ul>
            <li><a href="/newMatch.jsp">New match</a></li>
            <li><a href="/matches">Matches</a></li>
        </ul>
    </div>
    <div class="container">
        <div class="results-container">
            <h1>Match results</h1>
            <h2>Match UUID: <%= match_uuid %></h2>
            <h2>First player: <%= match.getFirstPlayer().getName() %></h2>
            <h2>Second player: <%= match.getSecondPlayer().getName() %></h2>
            <h2>Winner: <%= match.getWinner().getName() %></h2>
        </div>
    </div>


</body>
</html>
