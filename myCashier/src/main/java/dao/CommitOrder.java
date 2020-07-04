package dao;

import DBUtil.DBUtil;
import entity.Order;
import entity.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommitOrder {
    public boolean commitOrder(Order order) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String insertOrderSql = "insert into `order` (id, account_id,create_time, finish_time," +
                    "actual_amount,total_money,order_status,account_name) " +
                    "values (?,?,?,?,?,?,?,?)";



            connection = DBUtil.getConnection(false);
            ps = connection.prepareStatement(insertOrderSql);
            ps.setString(1,order.getId());
            ps.setInt(2,order.getAccount_id());
            ps.setString(3,order.getCreate_time());
            ps.setString(4,order.getFinish_time());
            ps.setInt(5,order.getActual_amountInt());
            ps.setInt(6,order.getTotal_moneyInt());
            ps.setInt(7,order.getOrder_statusDesc().getFlg());
            ps.setString(8,order.getAccount_name());

            int ret = ps.executeUpdate();
            if(ret == 0) {
                throw new RuntimeException("插入订单失败！");
            }


            String insertOrderItemSql = "insert into order_item(order_id, goods_id, goods_name," +
                    "goods_introduce, goods_num, goods_unit," +
                    " goods_price, goods_discount) " +
                    "values (?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(insertOrderItemSql);

            for (OrderItem orderItem : order.getOrderItemList()) {
                ps.setString(1,orderItem.getOrder_id());
                ps.setInt(2,orderItem.getGoods_id());
                ps.setString(3,orderItem.getGoods_name());
                ps.setString(4,orderItem.getGoods_introduce());
                ps.setInt(5,orderItem.getGoods_num());
                ps.setString(6,orderItem.getGoods_unit());
                ps.setInt(7,orderItem.getGoods_priceInt());
                ps.setInt(8,orderItem.getGoods_discount());
                ps.addBatch();// 缓存
            }


            int[] effect = ps.executeBatch();//批量的插入
            for ( int i : effect) {
                if(i == 0) {
                    throw new RuntimeException("插入订单明细失败！");
                }
            }

            //批量插入没有发生任何的异常
            connection.commit();
        }catch (Exception e) {
            e.printStackTrace();
            //判断链接是否断开
            if(connection != null) {
                try {
                    //回滚
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            return false;
        }finally {
            DBUtil.close(connection,ps,null);
        }
        return true;
    }
}
