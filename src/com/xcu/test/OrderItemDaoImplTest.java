package com.xcu.test;

import com.xcu.dao.OrderItemDao;
import com.xcu.dao.impl.OrderItemDaoImpl;
import com.xcu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"Book Or",1,new BigDecimal(29.99),new BigDecimal(29.99),"1"));
    }

    @Test
    public void queryOrderItemByOrderId() {
        OrderItem orderItem = orderItemDao.queryOrderItemByOrderId(1);
        System.out.println(orderItem);
    }
}