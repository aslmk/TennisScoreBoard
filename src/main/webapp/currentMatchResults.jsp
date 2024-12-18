<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12.10.2024
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="aslmk.models.Match" %>
<%@ page import="aslmk.dto.MatchDTO" %>
<html>
<head>
    <title>Current match results</title>
    <style><%@ include file="/css/reset.css"%></style>
    <style><%@ include file="/css/styles.css"%></style>
    <style><%@ include file="/css/currentMatchResults.css"%></style>
</head>
<body>
<%
    MatchDTO match = (MatchDTO) request.getAttribute("match");
%>
<%@include file="navigation.jsp"%>

    <div class="container">
        <div class="results-container">
            <h1>Match results</h1>
            <h2>Match UUID: <%= match.getUUID() %></h2>
            <h2>First player: <%= match.getFirstPlayer().getName() %></h2>
            <h2>Second player: <%= match.getSecondPlayer().getName() %></h2>
            <h2>Winner: <%= match.getWinner().getName() %></h2>
        </div>
    </div>


</body>
</html>
