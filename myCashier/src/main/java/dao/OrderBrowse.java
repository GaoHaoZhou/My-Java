package dao;

import DBUtil.DBUtil;
import comment.OrderStatus;
import entity.Order;
import entity.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBrowse {
    public List<Order> orderBrowse(Integer id) {
        Connection con= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();

        try{
            String sql = "select o1.id              as order_id,\n" +
                    "       o1.account_id      as account_id,\n" +
                    "       o1.create_time     as create_time,\n" +
                    "       o1.finish_time     as finish_time,\n" +
                    "       o1.actual_amount   as actual_amount,\n" +
                    "       o1.total_money     as total_money,\n" +
                    "       o1.order_status    as order_status,\n" +
                    "       o1.account_name    as account_name,\n" +
                    "       o2.id              as item_id,\n" +
                    "       o2.goods_id        as goods_id,\n" +
                    "       o2.goods_name      as goods_name,\n" +
                    "       o2.goods_introduce as goods_introduce,\n" +
                    "       o2.goods_num       as goods_num,\n" +
                    "       o2.goods_unit      as goods_unit,\n" +
                    "       o2.goods_price     as goods_price,\n" +
                    "       o2.goods_discount  as goods_discount\n" +
                    "from `order` as o1\n" +
                    "         left join order_item as o2 on\n" +
                    "          o1.id = o2.order_id\n" +
                    "where o1.account_id = ? order by order_id;";
            con = DBUtil.getConnection(true);
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs =  ps.executeQuery();

            Order order = null;

            while(rs.next()){
                if(order==null){
                    //只添加了订单信息，订单项信息还未添加
                    order= new Order();
                    extractOrder(order,rs);
                    orders.add(order);
                }

                //如果order_id不相同了，说明上一个订单已经处理完，此时需要新建一个新的订单
                String order_ID = rs.getString("order_id");
                if(!order.getId().equals(order_ID)){
                    //只添加了订单信息，订单项信息还未添加
                    order= new Order();
                    extractOrder(order,rs);
                    orders.add(order);
                }

                //为每个订单中加入订单项信息
                OrderItem orderItem = extractOrderItem(rs);
                order.getOrderItemList().add(orderItem);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(con,ps,rs);
        }
        return orders;
    }

    public OrderItem extractOrderItem(ResultSet resultSet) throws SQLException {

        OrderItem orderItem = new OrderItem();
        orderItem.setId(resultSet.getString("item_id"));
        orderItem.setGoods_id(resultSet.getInt("goods_id"));
        orderItem.setGoods_name((resultSet.getString("goods_name")));
        orderItem.setGoods_introduce(resultSet.getString("goods_introduce"));
        orderItem.setGoods_num(resultSet.getInt("goods_num"));
        orderItem.setGoods_unit(resultSet.getString("goods_unit"));
        orderItem.setGoods_price(resultSet.getInt("goods_price"));
        orderItem.setGoods_discount(resultSet.getInt("goods_discount"));
        return orderItem;
    }

    private void extractOrder(Order order,ResultSet rs) throws SQLException {
        order.setId(rs.getString("order_id"));
        order.setAccount_id(rs.getInt("account_id"));
        order.setAccount_name(rs.getString("account_name"));
        order.setCreate_time(rs.getString("create_time"));
        order.setFinish_time(rs.getString("finish_time"));
        order.setActual_amount(rs.getInt("actual_amount"));
        order.setTotal_money(rs.getInt("total_money"));
        order.setOrder_status(OrderStatus.valueOf(rs.getInt("order_status")));
    }
}
