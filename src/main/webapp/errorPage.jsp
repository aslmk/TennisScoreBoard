<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 25.10.2024
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Something wrong...</title>
    <style><%@ include file="/css/reset.css"%></style>
    <style><%@ include file="/css/styles.css"%></style>
    <% String errorMessage = (String) request.getAttribute("errorMessage");%>
    <% int statusCode = (int) request.getAttribute("statusCode"); %>
</head>
<body>
<%@include file="navigation.jsp"%>

    <div class="container">
        <h1><%=errorMessage%></h1>
        <h2><%=statusCode%></h2>

    </div>
</body>
</html>
