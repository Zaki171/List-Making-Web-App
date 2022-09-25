<%--
  Created by IntelliJ IDEA.
  User: zakit
  Date: 12/05/2021
  Time: 04:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error!</title>
    <link rel="stylesheet" href="myStyles.css" type="text/css">
</head>
<body>
<h1>
    Sorry!
</h1>
<h2>
    <%=request.getAttribute("message")%>
</h2>
</body>
<jsp:include page="/footer.jsp"/>
</html>
