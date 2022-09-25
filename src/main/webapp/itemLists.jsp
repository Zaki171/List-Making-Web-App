<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>ALL LISTS</title>
  <link rel = "stylesheet" href = "myStyles.css?version=5" type="text/css">
</head>
<body>
<div class="main">
  <h1>Lists:</h1>
  <ul id = "lists">
    <%
      List<String> listLabels = (List<String>) request.getAttribute("labels");
      if(listLabels.size()==0)
      {%>
    <h2>No lists</h2>
    <%}
      for (String l : listLabels)
      {
        String href = "/viewItemListInfo.html?id=" + l;
        String delRef = "/delList.html?id="+ l;
        String renameRef = "/renameList.html";
    %>
    <li id="list"><a href="<%=href%>"><%=l%></a></li>
    <ul>
      <li class = "delete"><a href = "<%=delRef%>">Delete this list</a></li>
      <li><form action = "<%=renameRef%>" method = "GET" >
        <input type = "hidden" name = "id" value = "<%=l%>">
        <input type = "text" name = "newName" placeholder="Change name..." required>
        <input type = "submit" value = "Rename">
      </form></li>
    </ul>
    <% } %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
