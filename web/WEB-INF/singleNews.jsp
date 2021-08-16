<%@ page import="model.News" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>News</title>
</head>
<body background="/css/images/newsBack.jpg">
<%

    News news = (News) request.getAttribute("news");
%>
<a href="/home">Go Home</a>| <a href="/logout">Logout</a>

<h1><%= news.getTitle()%>
</h1>
<img src="/getImage?picUrl=<%= news.getPicUrl() %>" width="300"/><br>

User name: <%=news.getUser().getName()%> <br>
User surname: <%=news.getUser().getSurname()%> <br>
<table border="3">
    <tr bgcolor="#696969">
        <td>Text: <%=news.getText()%> <br></td>
    </tr>
</table>
</body>
</html>
