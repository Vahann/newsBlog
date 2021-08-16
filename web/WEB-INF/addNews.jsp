<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add News</title>
    <link rel="stylesheet" href="/cssFromLogin/style.css">
</head>
<body background="/css/images/newsBack.jpg">
<%String massage = (String) session.getAttribute("massage");%>
<%if (massage != null && !"".equals(massage)) { %>
<span><%=massage%></span>
<%
        session.removeAttribute("massage");
    }%>
<h1>AddNews</h1> | <a href="/logout">Logout</a> | <a href="/home">Home</a>

<p style="color: #eeeeee">AddNews</p>
<form id="signin" action="/addNews" method="post" enctype="multipart/form-data">
    Category:<input type="text" name="category" placeholder="Category"/>
    Title:<input type="text" name="title" placeholder="Title"/>
    Description: <input type="text" name="description" placeholder="Description"/>
    Text:<input type="text" name="text" placeholder="Text"/>
    Picture:<input type="file" name="picture" placeholder="picture"/>
    <!--  <input type="text" name="type"/> -->
    <input type="submit" value="AddNews">
</form>
</body>
</html>
