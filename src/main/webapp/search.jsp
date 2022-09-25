<%--
  Created by IntelliJ IDEA.
  User: zakit
  Date: 12/05/2021
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List app</title>
    <link rel = "stylesheet" href = "/myStyles.css" type="text/css">
</head>
<body>
<h1>Search</h1>
<div class="input">
    <form method="POST" action="/runsearch.html" style= "text-align: center">
        <input type="text" id = "search" name="searchstring" placeholder="Enter label of list to search for..." required/>
        <input type="submit" value="Search"/>
    </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>