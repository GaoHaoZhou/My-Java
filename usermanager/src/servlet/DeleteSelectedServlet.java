package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/deleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String[] values = request.getParameterValues("id[]");
        System.out.println("deleteSelectedServlet："+Arrays.toString(values));
//删除
        int sum=0;
        UserService userService=new UserService();
        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<values.length;i++){
            int j = Integer.parseInt(values[i]);
//调用Service层方法删除
            int delete = userService.delete(j);
            sum=sum+delete;
        }
        System.out.println("sum: "+sum);
//sum==values.length 说明选中的所有元素已经全部删除了
        if(sum==values.length){
//证明删除成功
            map.put("msg",true);
        }else {
            map.put("msg",false);
        }
//将map转化为json
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        this.doPost(request, response);
    }
}

