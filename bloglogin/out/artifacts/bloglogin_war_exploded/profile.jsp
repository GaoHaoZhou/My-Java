<%@ page import="com.glp.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("/login.html");
        return;
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人资料修改</title>
</head>
<body>
    <form action="/profile/edit" method="post">
        <div>
            昵称: <input type="text" name="nickname" value="<%= user.nickname %>">
        </div>
        <div>
            个性签名: <input type="text" name="brief" value="<%= user.brief %>">
        </div>
        <div>
            <button type="submit">修改</button>
        </div>
    </form>
</body>
</html>
