package servlet;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        String address = req.getParameter("address");
        String qq = req.getParameter("qq");
        String email = req.getParameter("email");



        Writer writer = resp.getWriter();
        int ageInt = Integer.valueOf(age);
        User registerUser =new User(); //创建一个数据库实体类对象
        registerUser.setName(name);
        registerUser.setUsername(username);
        registerUser.setPassword(password);
        registerUser.setGender(gender);
        registerUser.setAge(ageInt);
        registerUser.setAddress(address);
        registerUser.setQq(qq);
        registerUser.setEmail(email);

        UserService service = new UserService();
        int ret = service.register(registerUser);

        if(ret == 0) {
            System.out.println("注册失败！");
            writer.write("<h2> 注册失败 </h2>" );
        }else {
            System.out.println("注册成功!");
            writer.write("<h2> 注册成功 </h2>" );
            resp.sendRedirect("/usermanager/login.html");
        }


    }
}
