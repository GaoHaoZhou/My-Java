package com.glp.servlets;

import com.glp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 代表用户没有登陆
            // 跳转到用户登陆页
            resp.sendRedirect("/login.html");
            return;
        }

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <meta charset='utf-8'>");
        writer.println("        <title>个人资料修改</title>");
        writer.println("    </head>");
        writer.println("    <body>");
        writer.println("        <form action='/profile/edit' method='post'>");
        writer.println("            <div>");
        writer.println("                昵称: <input type='text' name='nickname' value='" + user.nickname + "'>");
        writer.println("            </div>");
        writer.println("            <div>");
        writer.println("                个性签名: <input type='text' name='brief' value='" + user.brief + "'>");
        writer.println("            </div>");
        writer.println("            <div>");
        writer.println("                <button type='submit'>修改</button>");
        writer.println("            </div>");
        writer.println("        </form>");
        writer.println("    </body>");
        writer.println("</html>");
    }
}
