package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PageBean;
import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findByPageServlet")
public class FindByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");//当前页：1
        String rows = request.getParameter("rows");//几行


        //获取到所有的前端参数：load(rows,currentPage,name,address,email)
        Map<String, String[]> parMap = request.getParameterMap();

        //这里注意一下，java的原生对象是不允许修改的,重新创建map，修改map就行了
        Map<String, String[]> map = new HashMap<>(parMap);
        map.remove("currentPage");
        map.remove("rows");

        //强制类型转换
        int current = Integer.parseInt(currentPage);//当前页:如第1页
        int row = Integer.parseInt(rows);//行数5

        //创建service层的对象
        UserService userService=new UserService();
        //查询一页的5条记录
        PageBean<User> pageBean = userService.findAllByPage(current, row, map);
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getWriter(),pageBean);
    }
}

