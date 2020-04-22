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

@WebServlet("/log")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // TODO: 合法性校验

        try {
            User user = User.login(username, password);
            resp.setContentType("text/html; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                writer.println("<h1>登陆成功</h1>");
            } else {
                writer.println("<h1>登陆失败</h1>");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
