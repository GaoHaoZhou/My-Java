package servlet;

import DBUtil.DBUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import comment.OrderStatus;
import entity.Account;
import entity.Order;
import entity.OrderItem;
import service.OrderBrowseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orderbrowse")
public class OrderBrowseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset = utf-8");
        HttpSession session = req.getSession();
        Account account = (Account)session.getAttribute("user");

        OrderBrowseService order = new OrderBrowseService();
        List<Order> orders = order.orderBrowse(account.getId());

        Writer writer = resp.getWriter();
        if(orders.isEmpty()){
            System.out.println("你还没有订单");
            writer.write("<h2>你还没有产生订单"+account.getId()+"</h2>");
        }else{
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                //将list转换为json字符串，并将该字符串写到流当中
                objectMapper.writeValue(writer,orders);
                System.out.println("json字符串："+writer.toString());
                //推到前端
                writer.write(writer.toString());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }





}
