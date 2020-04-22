<%@ page import="com.glp.model.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    // TODO: 合法性校验
    Article article = Article.getById(id);
    if (article == null) {
        response.setStatus(404);
        return;
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= article.title %></title>
</head>
<body>
    <img src="<%= article.coverImage %>" width="400" height="300">
    <h1><%= article.title %></h1>
    <p><%= article.body %></p>
    <!-- TODO: 作者、文章发表时间 -->
</body>
</html>
