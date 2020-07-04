package servlet;

import DBUtil.DBUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Goods;
import service.GoodsOutService;

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

@WebServlet("/delGoods")
public class GoodsSoldOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset = utf-8");

        String str = req.getParameter("id");

        PrintWriter writer = resp.getWriter();

        GoodsOutService out = new GoodsOutService();
        int ret =out.goodsOut(str);
        if(ret==1){
            writer.write("<h2>商品下架失败"+str+"</h2>");
        }else{
                writer.write("<h2>商品下架成功"+str+"</h2>");
                resp.sendRedirect("browse.html");
        }




    }
}
