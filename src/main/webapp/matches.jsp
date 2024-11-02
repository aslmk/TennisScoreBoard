<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 22.10.2024
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="aslmk.models.Match"%>
<%@ page import="java.util.List" %>


<html>
<head>
    <title>Matches</title>
    <style><%@ include file="/css/reset.css"%></style>
    <style><%@ include file="/css/styles.css"%></style>
    <style><%@ include file="/css/matches.css"%></style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>
<body>
    <%
        List<Match> allMatches = (List<Match>) request.getAttribute("allMatches");
        int pageNumber = (int) request.getAttribute("pageNumber");
        boolean hasNextPage = (boolean) request.getAttribute("hasNextPage");
        String filter_by_player_name = (String) request.getAttribute("filter_by_player_name");
    %>

    <%@include file="navigation.jsp"%>

    <div class="container">
        <div class="findMatchByPlayerName">
            <form action="${pageContext.request.contextPath}/matches" method="post">
                <input type="text" name="filter_by_player_name" value="<%=filter_by_player_name%>" placeholder="Enter player name to find his matches">
                <button>Find</button>
            </form>
        </div>
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>First player</th>
                    <th>Second player</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Match match: allMatches) {
                %>
                <tr>
                    <td>
                        <%= match.getFirstPlayer().getName() %>
                        <%if (match.getFirstPlayer().getName().equals(match.getWinner().getName())) { %>
                        <i class="fa-solid fa-trophy"></i>
                        <%}%>
                    </td>
                    <td>
                        <%if (match.getSecondPlayer().getName().equals(match.getWinner().getName())) { %>
                        <i class="fa-solid fa-trophy"></i>
                        <%}%>
                        <%= match.getSecondPlayer().getName() %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <div class="pageNavigation">
                <form action="${pageContext.request.contextPath}/matches" method="post" style="display: inline;">
                    <input type="hidden" name="filter_by_player_name" value="<%= filter_by_player_name %>">
                    <input type="hidden" name="page" value="<%= pageNumber - 1 %>">
                    <button type="submit" class="prevBtn" <%= pageNumber == 1 ? "style='visibility: hidden;'" : "" %>>Previous</button>
                </form>
                <% if (!filter_by_player_name.isEmpty()) { %>
                <form action="${pageContext.request.contextPath}/matches" method="post" style="display: inline;">
                    <button type="submit" class="showAllBtn">Show all</button>
                </form>
                <% } %>

                <% if (hasNextPage) { %>
                <form action="${pageContext.request.contextPath}/matches" method="post" style="display: inline;">
                    <input type="hidden" name="filter_by_player_name" value="<%= filter_by_player_name %>">
                    <input type="hidden" name="page" value="<%= pageNumber + 1 %>">
                    <button type="submit" class="nextBtn">Next</button>
                </form>
                <% } %>
            </div>
        </div>
    </div>

</body>
</html>
