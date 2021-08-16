<%@ page import="model.News" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body background="/css/images/newsBack.jpg">
<%String massage = (String) session.getAttribute("massage");%>
<%News news = (News) request.getAttribute("news");%>
<% if (massage != null && !"".equals(massage)) {%>
<span><%=massage%></span>
<%
        session.removeAttribute("massage");
    }
%>
<h1>Add News</h1> | <a href="/logout">Logout</a> | <a href="/home">Home</a>
<form action="/updateNews" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<%=news.getId()%>">
    <%--  Category:  <input type="text" name="category" value="<%=news.getNewsCategory()%>">--%>
    Title: <input type="text" name="title" value="<%=news.getTitle()%>"> <br>
    Description: <input type="text" name="description" value="<%=news.getDescription()%>"><br>
    Text: <input type="text" name="text" value="<%=news.getText()%>"><br>
    Picture: <input type="file" name="picture"><br>
    <input type="submit" value="update News">

</form>

</body>
</html>
