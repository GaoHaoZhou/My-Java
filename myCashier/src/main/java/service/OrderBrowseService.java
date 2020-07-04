package service;

import dao.OrderBrowse;
import entity.Order;

import java.util.List;

public class OrderBrowseService {
    public List<Order> orderBrowse(Integer id) {
        OrderBrowse order = new OrderBrowse();
        List<Order> orders = order.orderBrowse(id);
        return orders;
    }
}
