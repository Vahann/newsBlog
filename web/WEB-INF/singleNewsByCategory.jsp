<%@ page import="model.News" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>News</title>
</head>
<body background="/css/images/images.jpg">
<%
    List<News> news = (List<News>) request.getAttribute("news");
    System.out.println("SINGLENEWSJSP" + news);
%>
<a href="/home">Go Home</a>| <a href="/logout">Logout</a>
<table border="5px" , style="background-color: darkgray">
    <tr>
            <%
            if (news != null && !news.isEmpty()) {
                for (News news1 : news) {
//                    System.out.println("singleNews JSP "+news);

        %>
        <td><img src="/getImage?picUrl=<%= news1.getPicUrl() %>" width="500"/></td>
        <br>
    <tr>
        <th>Title:<%= news1.getTitle()%>
        </th>

    </tr>
    <br>
    </tr>
    <tr>
        <th>Description:<%=news1.getDescription()%>
        </th>
    </tr>
    <br>
    <tr bgcolor="#6495ed">
        <th>Text:<%=news1.getText()%>
        </th>
        <br>
    </tr>
    <%
            }
        }
    %>
    </td>
</table>
</body>
</html>
