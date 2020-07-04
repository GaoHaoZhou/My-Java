package servlet;


import entity.Account;
import service.UserLoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");


        PrintWriter writer =resp.getWriter();

        UserLoginService userService = new UserLoginService();
        Account user = userService.login(username,password);
        if(user.getId()==null){
                writer.write("<h2>没有该用户"+username+"</h2>");
        }else if(!password.equals(user.getPassword())){
                writer.write("<h2>密码错误"+username+"</h2>");
        }else{
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                writer.write("<h2>登录成功"+username+"</h2>");
                resp.sendRedirect("browse.html");
        }

    }
}
