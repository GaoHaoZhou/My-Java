package com.glp.servlets;

import com.glp.model.Article;
import com.glp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;

@WebServlet("/publish")
@MultipartConfig
public class PublishServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Part coverImage = req.getPart("cover_image");
        String filename = coverImage.getSubmittedFileName();
        String path = req.getServletContext().getRealPath("images") + "\\" + filename;//获取images的绝对路径
        //String path = "G:\\1workplace\\bloglogin\\out\\artifacts\\bloglogin_war_exploded\\images\\" +  filename;
        InputStream is = coverImage.getInputStream();

        // 类似 IO 时，文件复制的代码
        byte[] buffer = new byte[8192];
        int len;
        try (OutputStream os = new FileOutputStream(path)) {
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        }

        String title = req.getParameter("title");
        String body = req.getParameter("body");
        String coverImageUrl = "/images/" + filename;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/login.html");
            return;
        }

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        try {
            Article article = Article.publish(user, title, body, coverImageUrl);
            if (article != null) {
                writer.println("<h1>文章发表成功</h1>");
            } else {
                writer.println("<h1>文章发表失败</h1>");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        /*
        req.setCharacterEncoding("utf-8");
        String title = req.getParameter("title");
        System.out.println(title);
        String body = req.getParameter("body");
        System.out.println(body);
        Part coverImage = req.getPart("cover_image");
        System.out.println(coverImage.getName());
        System.out.println(coverImage.getSubmittedFileName());
        System.out.println(coverImage.getContentType());
        System.out.println(coverImage.getSize());
        InputStream inputStream = coverImage.getInputStream();
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        System.out.println(scanner.next());
        */

        /*
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/login.html");
            return;
        }

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        try {
            Article article = Article.publish(user, title, body);
            if (article != null) {
                writer.println("<h1>文章发表成功</h1>");
            } else {
                writer.println("<h1>文章发表失败</h1>");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        */
    }
}
