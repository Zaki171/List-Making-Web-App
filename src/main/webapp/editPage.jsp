<%--
  Created by IntelliJ IDEA.
  User: zakit
  Date: 10/05/2021
  Time: 04:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="myStyles.css" type="text/css">
    <title>Edit item</title>
</head>
<body>
<form method="POST" action="/editItem.html" enctype="multipart/form-data">
    <h1>Edit</h1>
    <div class = "adding">
        <input type="text" name="newText" placeholder="Enter new text">
        <input type="file" name="newImage" accept="image/*,.mp3"/>
        <input type="url" name="newUrl" placeholder="Enter new URL...">
        <input type="text" name="newLink" placeholder="Enter new link to list...">
        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
        <input type="hidden" name="text" value="<%=request.getParameter("text")%>">
        <input type="hidden" name="image" value="<%=request.getParameter("image")%>">
        <input type="hidden" name="url" value="<%=request.getParameter("url")%>">
        <input type="hidden" name="linkToList" value="<%=request.getParameter("linkToList")%>">
        <input type="submit" value="Make changes">
    </div>
</form>
</body>
<jsp:include page="/footer.jsp"/>
</html>
