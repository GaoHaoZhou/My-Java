package servlet;

import DBUtil.DBUtil;
import comment.OrderStatus;
import entity.Account;
import entity.Goods;
import entity.Order;
import entity.OrderItem;
import service.GetGoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/pay")
public class ReadyBuyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset = utf-8");

        String goodsIDAndNum = req.getParameter("goodsIDandNum");
        //1-8,5-4

        //用List保存多个货物
        List<Goods> goodsList = new ArrayList<>();

        String[] strings = goodsIDAndNum.split(",");
        for(String x:strings){
            String[] str = x.split("-");
            //查询货物是否存在
            GetGoodsService get = new GetGoodsService();
            Goods good = get.getGoods(Integer.valueOf(str[0]));

            if(good!=null){
                good.setBuyGoodsNum(Integer.valueOf(str[1]));
                goodsList.add(good);
            }
        }
        System.out.println(goodsList);
        //货物都装进了goodsList中，现在开始创建订单了

        Order order = new Order();

        //通过时间戳生成订单id
        order.setId(String.valueOf(System.currentTimeMillis()));

        //通过session获取账户id
        HttpSession session =req.getSession();
        Account account = (Account) session.getAttribute("user");
        order.setAccount_id(account.getId());
        order.setAccount_name(account.getUsername());

        //设置创建时间
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       // order.setCreate_time(LocalDate.now().format(formatter));

        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss"
        );
        String createTime = format.format(date);
        order.setCreate_time(createTime);

        //计算总金额和实际消费
        int totalMoney =0;
        int actualMoney =0;
        for (Goods goods:goodsList){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder_id(order.getId());
            orderItem.setGoods_id(goods.getId());
            orderItem.setGoods_discount(goods.getDiscount());
            orderItem.setGoods_name(goods.getName());
            orderItem.setGoods_introduce(goods.getIntroduce());
            orderItem.setGoods_unit(goods.getUnit());
            orderItem.setGoods_num(goods.getBuyGoodsNum());
            orderItem.setGoods_price(goods.getPriceInt());
            order.orderItemList.add(orderItem);

            int currentMoney = goods.getBuyGoodsNum()*goods.getPriceInt();
            totalMoney +=currentMoney;  //totalMoney取整了，用于存入数据库
            actualMoney += currentMoney*goods.getDiscount()/100;//除100是折扣，
        }
        order.setTotal_money(totalMoney);
        order.setActual_amount(actualMoney);
        order.setOrder_status(OrderStatus.PLAYING);

        System.out.println(order);

        //将订单写到session中
        HttpSession session2 = req.getSession();
        session2.setAttribute("order",order);

        //将goods存入Session中
        HttpSession session3 = req.getSession();
        session3.setAttribute("goodsList",goodsList);


        //如果是跳转到另一个网页的话，对应的数据不好拿到，所以在这里直接进行打印网页
        //通过响应体对前端传入数据。
        resp.getWriter().println("<html>");
        resp.getWriter().println("<p>"+"【用户名称】:"+order.getAccount_name()+"</p>");
        resp.getWriter().println("<p>"+"【订单编号】:"+order.getId()+"</p>");
        resp.getWriter().println("<p>"+"【订单状态】:"+order.getOrder_status()+"</p>");
        resp.getWriter().println("<p>"+"【创建时间】:"+order.getCreate_time()+"</p>");

        resp.getWriter().println("<p>"+"编号  "+"名称   "+"数量  "+"单位  "+"单价（元）   "+"</p>");
        resp.getWriter().println("<ol>");
        for (OrderItem orderItem  : order.orderItemList) {
            resp.getWriter().println("<li>" + orderItem.getGoods_name() +" " + orderItem.getGoods_num()+ " "+
                    orderItem.getGoods_unit()+" " + orderItem.getGoods_price()+"</li>");
        }
        resp.getWriter().println("</ol>");
        resp.getWriter().println("<p>"+"【总金额】:"+order.getTotal_money() +"</p>");
        resp.getWriter().println("<p>"+"【优惠金额】:"+order.getDiscount() +"</p>");
        resp.getWriter().println("<p>"+"【应支付金额】:"+order.getActual_amount() +"</p>");
        //这个标签<a href = > 只会以get方式请求，所以buyGoodsServlet的 doGet方法
        resp.getWriter().println("<a href=\"buyGoodsServlet\">确认</a>");
        //resp.getWriter().println("<form action=\"buyGoodsServlet\" method=\"post\"><button type=\"submit\">确认</button></form>");
        resp.getWriter().println("<a href= \"browse.html\">取消</a>");
        resp.getWriter().println("</html>");
    }

}
