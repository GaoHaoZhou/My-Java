package servlet;

import DBUtil.DBUtil;
import service.GoodsPutService;

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

@WebServlet("/inbound")
public class GoodsPutAwayServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset = utf-8");


        String name = req.getParameter("name");
        String stock = req.getParameter("stock");
        String introduce = req.getParameter("introduce");
        String unit = req.getParameter("unit");
        String price = req.getParameter("price");
        String discount = req.getParameter("discount");

        PrintWriter writer = resp.getWriter();
        GoodsPutService put = new GoodsPutService();
        int ret = put.put(name,stock,introduce,unit,price,discount);

        if(ret==0){
            writer.write("<h2>商品上架失败</h2>");
        }else{
            writer.write("<h2>商品上架成功</h2>");
            resp.sendRedirect("browse.html");
        }


    }
}
