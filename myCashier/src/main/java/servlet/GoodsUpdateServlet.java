package servlet;

import DBUtil.DBUtil;
import entity.Goods;
import service.UpdateGoodService;

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

@WebServlet("/updategoods")
public class GoodsUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset = utf-8");

        String goodsID = req.getParameter("goodsID");
        String name = req.getParameter("name");
        String introduce = req.getParameter("introduce");
        String stock = req.getParameter("stock");
        String unit = req.getParameter("unit");

        String price = req.getParameter("price");
        double doublePrice = Double.valueOf(price) * 100;
        int realPrice = (int) doublePrice;

        String discount = req.getParameter("discount");

        PrintWriter writer = resp.getWriter();
        //是否存在此goodsID
        UpdateGoodService ob = new UpdateGoodService();
        Goods goods = ob.getGoods(Integer.valueOf(goodsID));
        if(goods==null){
            writer.write("<h2>没有该商品</h2>");
            System.out.println("没有该商品");
        }else{
            //存储需要更新的商品
            goods.setId(Integer.valueOf(goodsID));
            goods.setName(name);
            goods.setIntroduce(introduce);
            goods.setStock(Integer.valueOf(stock));
            goods.setUnit(unit);
            goods.setPrice(realPrice);
            goods.setDiscount(Integer.valueOf(discount));

            //将商品写到数据库中
            boolean flg = ob.modify(goods);
            if(flg){
                writer.write("<h2>商品更新成功</h2>");
                resp.sendRedirect("browse.html");
            }else{
                writer.write("<h2>商品更新失败</h2>");
            }
        }
    }
}
