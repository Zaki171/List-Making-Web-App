<%--
  Created by IntelliJ IDEA.
  User: zakit
  Date: 12/05/2021
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="myStyles.css">
    <title>Success!</title>
</head>
<body>
<h1 style="font-size: 64px; text-align: center; " >
    <%=request.getAttribute("message")%>
</h1>
</body>
<jsp:include page="/footer.jsp"/>
</html>
