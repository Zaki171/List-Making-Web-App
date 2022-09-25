<%--
  Created by IntelliJ IDEA.
  User: zakit
  Date: 13/05/2021
  Time: 04:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add List</title>
    <link rel="stylesheet" href="myStyles.css" type="text/css">
</head>
<body>
<h1>Enter a name for the list</h1>
<div class = "input">
    <form method = "GET" action="/runAdd.html" style="text-align: center">
        <input type = "text" id ="search" name = "label" placeholder="Label..." required/>
        <input type = "submit" value="Add"/>
    </form>
</div>
</body>
<jsp:include page="/footer.jsp"/>
</html>
