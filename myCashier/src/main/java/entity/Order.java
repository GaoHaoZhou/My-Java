package entity;

import comment.OrderStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private String id;
    private Integer account_id;
    private String account_name;
    private String create_time;
    private String finish_time;
    private Integer actual_amount;
    private Integer total_money;
    private OrderStatus order_status;
    //保存订单项
    public List<OrderItem> orderItemList = new ArrayList<>();

    public String getOrder_status(){
        return order_status.getDesc();
    }
    public OrderStatus getOrder_statusDesc(){
        return order_status;
    }

    public double getTotal_money(){
        return total_money*1.0/100;
    }
    public int getTotal_moneyInt(){
        return total_money;
    }

    public double getActual_amount(){
        return actual_amount*1.0/100;
    }
    public int getActual_amountInt(){
        return actual_amount;
    }

    //优惠金额
    public double getDiscount(){
        return (this.total_money-this.actual_amount)*1.00/100;
    }
}
