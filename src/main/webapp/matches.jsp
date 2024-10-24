<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 22.10.2024
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="aslmk.Models.Match"%>
<%@ page import="java.util.List" %>


<html>
<head>
    <title>Matches</title>
    <style><%@ include file="/css/styles.css"%></style>
</head>
<body>
    <%
        List<Match> allMatches = (List<Match>) request.getAttribute("allMatches");
    %>

    <div class="nav">
        <ul>
            <li><a href="/index.jsp">New match</a></li>
            <li><a href="/matches">Matches</a></li>
        </ul>
    </div>

    <table>
        <thead>
            <tr>
                <th>First player</th>
                <th>Second player</th>
                <th>Winner</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Match match: allMatches) {
        %>
            <tr>
                <th><%= match.getFirstPlayer().getName() %></th>
                <th><%= match.getSecondPlayer().getName() %></th>
                <th><%= match.getWinner().getName() %></th>
            </tr>
        <% } %>
        </tbody>
    </table>

</body>
</html>