package servlet;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("utf-8"); //设置编码格式
        String id = request.getParameter("id"); //获取参数
        int i = Integer.parseInt(id); //将String类型的id转化为INT类型
//调用Service层的方法，查询id为i的数据
        UserService userService=new UserService();
        User user = userService.find(i);
//将user存到session域
        request.getSession().setAttribute("user",user);
//跳转到update.html
        response.sendRedirect("/usermanager/update.html");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        this.doPost(request, response);
    }
}

