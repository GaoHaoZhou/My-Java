package com.glp.servlets;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glp.model.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/list.json")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        try {
            List<Article> articleList = Article.list();
            String jsonString = transform(articleList);
            writer.println(jsonString);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private String transform(List<Article> articleList) {
        JSONArray array = new JSONArray();
        for (Article article : articleList) {
            JSONObject o = new JSONObject();
            o.put("id", article.id);
            o.put("cover_image", article.coverImage);
            o.put("title", article.title);
            o.put("body", article.body);

            array.add(o);
        }

        return array.toJSONString();
    }
}
