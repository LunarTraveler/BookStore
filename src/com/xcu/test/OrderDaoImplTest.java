package com.xcu.test;

import com.xcu.dao.OrderDao;
import com.xcu.dao.impl.OrderDaoImpl;
import com.xcu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoImplTest {

    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("2", LocalDateTime.now(), new BigDecimal("100"), 0, 1));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("1", 2);
    }

    @Test
    public void queryOrderByUserId() {
        Order order = orderDao.queryOrderByUserId(1);
        System.out.println(order);
    }
}