<%@ page import="uk.ac.ucl.dataStructure.Item" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: zakit
  Date: 14/05/2021
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List making app</title>
    <link rel="stylesheet" href="myStyles.css" type="text/css">
</head>
<body>
<h1>All items:</h1>
<%
    ArrayList<Item> allItems = (ArrayList<Item>) request.getAttribute("items");
    if(allItems.size()==0){%>
<h2>No items</h2> <%
    }
    else{%>
<ul><%
        for(Item i : allItems){%>
    <ul id="allItems"><%
        if(!i.getFile().equals("")){
            String fn = i.getFile();
            if(fn.substring(fn.length()-4).equals(".mp3")){%>
        <li><audio controls><source src="files/<%=fn%>" type="audio/mp3" style="display: inline-block">Your browser does not support the audio element</audio></li><%
            }
            else{%>
        <li><img src="files/<%=i.getFile()%>" alt = "<%=i.getFile()%>" style="max-width: 150px; max-height: 100px; object-fit: cover;"></li><%
            }
        }
        if(!i.getURL().equals("")){%>
        <li><a href = "<%=i.getURL()%>" style="width: 300px; display: inline-block; overflow: hidden"><%=i.getURL()%></a></li><%
        }
        if(!(i.getText().equals(""))){%>
        <li><p style="width: 100px;"><%=i.getText()%></p></li><%
        }
        if(!i.getLink().equals(""))
        { String href = "/viewItemListInfo.html?id="+ i.getLink();%>
        <li><a href = "<%=href%>" style="width: 100px"><%=i.getLink()%></a></li><%
        }%>
    </ul>
        <%}
    }%>
</ul>
</body>
<jsp:include page="/footer.jsp"/>
</html>
