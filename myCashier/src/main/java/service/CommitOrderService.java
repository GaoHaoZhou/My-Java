package service;

import dao.CommitOrder;
import entity.Order;

public class CommitOrderService {
    public boolean commitOrder(Order order) {
        CommitOrder com = new CommitOrder();
        boolean flg = com.commitOrder(order);
        return flg;
    }
}
