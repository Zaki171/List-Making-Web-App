<%@ page import="java.util.ArrayList" %>
<%@ page import="uk.ac.ucl.dataStructure.Item" %><%--
  Created by IntelliJ IDEA.
  User: zakit
  Date: 04/05/2021
  Time: 00:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List app</title>
    <link rel="stylesheet" href="myStyles.css" type="text/css">
</head>
<body>
<h1><%=request.getParameter("id")%>:</h1>
<div class = "allItems">
        <%
            ArrayList<Item> ts = (ArrayList<Item>) request.getAttribute("items");
            if (ts.size()==0)
            {%> <p style="font-size: 25px; margin: 15px">No items found</p><%}
            else{
            for(Item i : ts)
            {%>
    <ul id="section">
        <ul id="items"><%
                if(!i.getFile().equals("")){
                    String fn = i.getFile();
                    String substring = fn.substring(fn.length() - 4);
                    if(substring.equals(".mp3")){%>
            <li><audio controls><source src="files/<%=fn%>" type="audio/mp3" style="display: inline-block">Your browser does not support the tag</audio><%}
                    else{%>
            <li><img src="files/<%=i.getFile()%>" alt = "<%=i.getFile()%>" style="width: 100px; max-height: 100px; object-fit: cover;"></li><%}
                }
                if(!i.getURL().equals("")){%>
            <li><a href = "<%=i.getURL()%>" style="width: 100px;"><%=i.getURL()%></a></li><%
                }
                if(!(i.getText().equals(""))){%>
            <li><p style="width: 100px;"><%=i.getText()%></p></li><%
                }
                if(!i.getLink().equals(""))
                {
                    String href = "/viewItemListInfo.html?id="+ i.getLink();%>
            <li><a href = "<%=href%>" style="font-size: 1.2em"><%=i.getLink()%></a></li><%
                }%>
        </ul>
        <ul id = "actions">
            <li>
                <form method="POST" action="/delItem.html">
                    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                    <input type="hidden" name="text" value="<%=i.getText()%>">
                    <input type="hidden" name="image" value="<%=i.getFile()%>">
                    <input type="hidden" name="url" value="<%=i.getURL()%>">
                    <input type="hidden" name="linkToList" value="<%=i.getLink()%>">
                    <input type="submit" value="Delete item">
                </form>
            </li>
            <li>
                <form method="POST" action="/editPage.jsp">
                    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                    <input type="hidden" name="text" value="<%=i.getText()%>">
                    <input type="hidden" name="image" value="<%=i.getFile()%>">
                    <input type="hidden" name="url" value="<%=i.getURL()%>">
                    <input type="hidden" name="linkToList" value="<%=i.getLink()%>">
                    <input type="submit" value="Edit item">
                </form>
            </li>
        </ul>
    </ul>
    <%}%>
    <%}%>
</div>
<%if(ts.size()!=0&&request.getAttribute("search")==null){%>
    <h2 style="float: left; clear: left">Search for an item:</h2>
    <form method ="POST" action = "/searchItem.html">
    <div class = "adding">
        <input type = "hidden" name = "id" value = "<%=request.getParameter("id")%>">
<input type = "text" name = "item" placeholder="Enter item to search for..." required>
<input type = "submit" value = "Search">
</div>
</form>
<%}%>
<%
if (request.getAttribute("search")==null){%>
<h2 style="float: left; clear: left">Add an item:</h2>
<form method="POST" action="/additem.html" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    <div class="adding">
        <input type="text" name="listToLink" placeholder="Enter the label of a list to link to...">
        <input type="text" name="description" placeholder="Enter some text...">
        <input type="url" name="url" placeholder="Enter URL...">
        <input type="file" name="file" accept="image/*,.mp3"/>
        <input type="submit" value="Add item">
    </div>
</form>
<%}%>
</body>
<jsp:include page="/footer.jsp"/>
</html>
