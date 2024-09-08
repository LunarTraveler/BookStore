package com.xcu.dao;

import com.xcu.pojo.OrderItem;

public interface OrderItemDao {

    int saveOrderItem(OrderItem orderItem);

    OrderItem queryOrderItemByOrderId(int orderId);

}
