package servlet;

import DBUtil.DBUtil;
import service.UserRegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset = utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter writer=resp.getWriter();

        //以字母开头，长度在6~10之间，只能包含字母、数字和下划线
        String pattern = "^[a-zA-Z]\\w{5,9}$";
        Pattern p = Pattern.compile(pattern);
        if(p.matcher(password).matches()){
//            writer.write("<h2>以字母开头，包含数字和下划线，长度为6~10</h2>");
            UserRegisterService user = new UserRegisterService();

            int ret = user.regiter(username,password);

            if(ret==0){
                System.out.println("注册失败");
                writer.write("<h2>注册失败</h2>");
            }else{
                System.out.println("注册成功");
                writer.write("<h2>注册成功</h2>");
                resp.sendRedirect("index.html");
            }
        }else{
            resp.sendRedirect("index.html");

        }




    }
}
