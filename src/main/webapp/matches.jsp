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
    <style><%@ include file="/css/reset.css"%></style>
    <style><%@ include file="/css/styles.css"%></style>
    <style><%@ include file="/css/matches.css"%></style>
</head>
<body>
    <%
        List<Match> allMatches = (List<Match>) request.getAttribute("allMatches");
        int pageNumber = (int) request.getAttribute("pageNumber");
        boolean hasNextPage = (boolean) request.getAttribute("hasNextPage");
        boolean isFilterApplied = (boolean) request.getAttribute("isFilterApplied");
    %>

    <div class="nav">
        <ul>
            <li><a href="/newMatch.jsp">New match</a></li>
            <li><a href="/matches">Matches</a></li>
        </ul>
    </div>

    <div class="container">
        <div class="findMatchByPlayerName">
            <form action="/matches" method="post">
                <input type="text" name="filter_by_player_name" placeholder="Enter player name to find his matches">
                <button>Find</button>
            </form>
        </div>
        <div class="table-container">
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
                    <td><%= match.getFirstPlayer().getName() %></td>
                    <td><%= match.getSecondPlayer().getName() %></td>
                    <td><%= match.getWinner().getName() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <div class="pageNavigation">
                <a href="/matches?page=${pageNumber - 1}" ${pageNumber == 1 ? 'style="visibility: hidden;"' : ''}>Previous page</a>
                <% if (hasNextPage) {%>
                    <a href="/matches?page=${pageNumber + 1}" >Next page</a>
                <%}%>
                <% if (isFilterApplied) {%>
                    <a href="/matches">Show all</a>
                <%}%>
            </div>
        </div>
    </div>

</body>
</html>
