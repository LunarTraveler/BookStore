package com.xcu.service;

import com.xcu.pojo.Cart;
import com.xcu.pojo.Order;
import com.xcu.pojo.OrderItem;

import java.util.List;

public interface OrderService {

    String createOrder(Cart cart, int userId);

    List<Order> showAllOrders();

    int sendOrder(String orderId);

    OrderItem showOrderDetail(int orderItemId);

    Order showOrderByUserId(int userId);

    int receiveOrder(String orderId);

}
