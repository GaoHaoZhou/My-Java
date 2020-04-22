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
import java.sql.SQLException;

@WebServlet("/reg")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 首页，从请求中获取用户提交的信息（用户名/昵称/密码）
        // 按照 UTF-8 编码形式，从 请求中 读取用户的提交内容
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");

        // 对用户输入的内容，进行合法性校验 —— 永远不要相信用户的输入信息
        if (username == null || username.isEmpty()) {
            System.out.println("用户名不合法");
            resp.sendRedirect("/register.html");
            return;
        }

        if (nickname == null || nickname.isEmpty()) {
            System.out.println("昵称不合法");
            resp.sendRedirect("/register.html");
            return;
        }

        if (password == null || password.isEmpty()) {
            System.out.println("密码不合法");
            resp.sendRedirect("/register.html");
            return;
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        // 进行用户的注册过程
        try {
            User user = User.register(username, nickname, password);

            System.out.println(user);

            // 给出成功信息
            writer.println("<h1>注册成功</h1>");

            // 登录用户
            // 把注册成功的用户，放入 Session 中
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        } catch (SQLException  e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
