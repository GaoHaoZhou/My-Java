package servlet;


import comment.OrderStatus;
import entity.Goods;
import entity.Order;

import service.CommitOrderService;
import service.UpdateStockService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

@WebServlet("/buyGoodsServlet")
public class buyGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset = utf-8");

        //拿到订单session
        HttpSession session = req.getSession();
        Order order = (Order)session.getAttribute("order");
        System.out.println(order);

        //拿到goodList
        List<Goods> goodsList = (List<Goods>)session.getAttribute("goodsList") ;
        System.out.println(goodsList);

        //更改订单信息
        order.setOrder_status(OrderStatus.OK);

        //设置完成时间
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //order.setFinish_time((LocalDate.now().format(formatter)));
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss"
        );
        String finishTime = format.format(date);
        order.setFinish_time(finishTime);


        //将订单写入数据库
        CommitOrderService com = new CommitOrderService();
        boolean flg = com.commitOrder(order);

        if(flg){
            //更新库存
            for ( Goods goods : goodsList) {
                UpdateStockService update = new UpdateStockService();
                boolean isUpdate =update.updateStock(goods,goods.getBuyGoodsNum());

                if(isUpdate) {
                    System.out.println("更新库存成功！");
                }else {
                    System.out.println("更新库存失败");
                    return;
                }
            }
        }else{

            System.out.println("插入订单失败！");
            return;
        }
        resp.sendRedirect("buyGoodsSuccess.html");
    }

}




















































