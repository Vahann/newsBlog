<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="cssFromLogin/style.css">
</head>
<body>


<div id="wrapper">
    <p style="color: #eeeeee">Login</p>

    <form id="signin" action="/login" method="post">
        <input type="text" name="name" placeholder="name"/>
        <input type="password" name="password" placeholder="password"/>
        <button type="submit">&#xf0da;</button>
    </form>
</div>
<div>
    <p style="color: #eeeeee">Register</p>
    <form id="signin" action="/register" method="post">

        <input type="text" name="name" placeholder="Name"/>
        <input type="text" name="surname" placeholder="Surname"/>
        <input type="password" name="password" placeholder="Password"/>
        <input type="password" name="re-password" placeholder="re-password"/>
        <!--  <input type="text" name="type"/> -->
        <button type="submit">&#xf0da;</button>
    </form>
</div>

</body>
</html>
