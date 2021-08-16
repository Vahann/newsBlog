<%@ page import="model.User" %>
<%@ page import="model.News" %>
<%@ page import="java.util.List" %>
<%@ page import="manager.NewsManager" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Home</title>
</head>
<body class="wrapper bgded" style="background-image:url('/css/images/unnamed.jpg');">
<% User user = (User) session.getAttribute("user");
    String massage = (String) session.getAttribute("massage");
%>

<%
    if (massage != null && !"".equals(massage)) {%>
<span><%=massage%></span>
<% session.removeAttribute("message");
}%>
<%
    List<News> news = (List<News>) request.getAttribute("news");
    NewsManager newsManager = new NewsManager();

%>
<table border="5px" , style="background-color: darkgray">
    <tr>
        <td>
            <h1 style="border: blue"> Welcome,dear<br>
                <%=user.getName()%>
            </h1>
            | <a href="/logout"><h3>Logout</h3></a> |
            <a href="/addNews"><h3>Add News</h3></a>
        </td>
    </tr>
</table>
<div style align="center">
    <th style="text-align:center"><h2 style="border: 2px">Выбор по категории</h2></th>
    <table border="8">
        <tr>
            <td><a href="/singleNewsByCategory?category=CAR"> <img src="/css/images/CarTeam.jpg" width="150"></a></td>
            <td><a href="/singleNewsByCategory?category=IT"> <img src="/css/images/ItTeam.jpg" width="150"></a></td>
            <td><a href="/singleNewsByCategory?category=POLITIC"> <img src="/css/images/politicsTeam.jpg"
                                                                       width="150"></a></td>
        </tr>
    </table>
</div>
<div style align="center">
    <table border="8">
        <tr bgcolor="#a9a9a9">
            <th>Photo</th>
            <th>Title</th>
            <th>Description</th>
            <th>Show More</th>
        </tr>

        <tr bgcolor="#f0fff0">
            <%
                if (news != null && !news.isEmpty()) {
                    for (News news1 : news) {

            %>
            <td bgcolor="#a9a9a9"><a href="/singleNews?id=<%=news1.getId()%>"> <img
                    src="/getImage?picUrl=<%= news1.getPicUrl() %>" width="300"/></a><br>
            </td>
            <td bgcolor="#a9a9a9"><%=news1.getTitle()%>
            </td>

            <td bgcolor="#a9a9a9"><%=news1.getDescription()%>
            </td>

            <td bgcolor="#a9a9a9"><a href="/singleNews?id=<%=news1.getId()%>">Show More</a> |
                <a href="/updateNews?id=<%=news1.getId()%>">Update News</a> |
                <a href="/deleteNews?id=<%=news1.getId()%>">Delete News</a> |
            </td>
        </tr>
        <%
                }
            }
        %>

    </table>
</div>
</body>
</html>
