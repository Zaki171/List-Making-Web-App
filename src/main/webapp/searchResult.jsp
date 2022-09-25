<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>List making app</title>
  <link rel="stylesheet" href="myStyles.css" type="text/css">
</head>
<body>
<div class="main">
  <h1>Search Result</h1>
  <%
    ArrayList<String> lists = (ArrayList<String>) request.getAttribute("results");
  if (lists.size() !=0)
    {%>
  <ul>
      <%
        for (String l : lists)
        {
          String href = "/viewItemListInfo.html?id=" + l;
      %><li id="list"><a href = "<%=href%>"><%=l%></a></li><% }
    }
    else
    {%>
    <p style="font-size: 32px; padding: 10px">Nothing found</p>
    <%}%>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>