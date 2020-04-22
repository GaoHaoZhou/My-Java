<%@ page import="com.glp.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /*
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.getWriter().println("<h1>请先登陆</h1>");
        return;
    }
    */
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>发表文章</title>
</head>
<body>
    <h1>发表文章</h1>
    <form action="/publish" method="post" enctype="multipart/form-data">
        <div>
            头图: <input type="file" name="cover_image">
        </div>
        <div>
            标题：<input type="text" name="title">
        </div>
        <div>
            正文：<textarea name="body"></textarea>
        </div>
        <div>
            <button type="submit">发表</button>
        </div>
    </form>
</body>
</html>
