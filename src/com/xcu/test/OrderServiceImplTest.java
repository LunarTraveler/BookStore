package com.xcu.test;

import com.xcu.pojo.Cart;
import com.xcu.pojo.CartItem;
import com.xcu.service.OrderService;
import com.xcu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceImplTest {

    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addCartItem(new CartItem(1, "java", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addCartItem(new CartItem(2, "Golang", 1, new BigDecimal(10), new BigDecimal(10)));
        orderService.createOrder(cart, 1);
    }

    @Test
    public void showAllOrders() {
        orderService.showAllOrders();
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("17256941033301");
    }

    @Test
    public void showOrderDetail() {
        orderService.showOrderDetail(3);
    }

    @Test
    public void showOrderByUserId() {
        orderService.showOrderByUserId(1);
    }

    @Test
    public void receiveOrder() {
        orderService.receiveOrder("17256941033301");
    }
}