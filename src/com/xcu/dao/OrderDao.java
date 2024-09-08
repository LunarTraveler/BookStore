package com.xcu.dao;

import com.xcu.pojo.Order;

import java.util.List;

public interface OrderDao {

    int saveOrder(Order order);

    List<Order> queryOrders();

    int changeOrderStatus(String orderId, int status);

    Order queryOrderByUserId(int userId);

}
