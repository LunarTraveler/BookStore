package com.xcu.service.impl;

import com.xcu.dao.BookDao;
import com.xcu.dao.OrderDao;
import com.xcu.dao.OrderItemDao;
import com.xcu.dao.impl.BookDaoImpl;
import com.xcu.dao.impl.OrderDaoImpl;
import com.xcu.dao.impl.OrderItemDaoImpl;
import com.xcu.pojo.*;
import com.xcu.service.OrderService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, int userId) {
        // 确保了唯一性（尽可能的）
        String orderId = System.currentTimeMillis() + "" + userId;
        // 生成订单
        Order order = new Order(orderId, LocalDateTime.now(), cart.getTotalPrice(), 0, userId);
        // 保存订单
        orderDao.saveOrder(order);
        // 遍历购物车中的每一个商品转换为订单项保存到数据库中
        for (CartItem cartItem : cart.getCartItems().values()) {
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);
            /**
             * 这里要对数量进行严格的把控
             * 1. 后端控制，数量小与0直接抛出异常
             * 2. 数据库层面的约束 check(quantity >= 0) 满足才会执行sql语句
             * 3. 直接业务逻辑控制 如果库存小与购买数量 直接抛出异常
             * 4. 并发控制 {
             *      1.悲观锁
             * 悲观锁的思想是，在操作库存时加锁，其他用户在此期间无法操作库存数据。这样可以防止多个用户同时修改同一商品的库存。
             *      2. 乐观锁
             * 乐观锁通过不直接加锁，而是利用版本号或者时间戳进行控制。每次更新时检查版本号是否匹配，不匹配则说明有其他人已经修改了数据，操作会失败，需要重试。
             *      3. 数据库原子操作
             * 使用数据库的原子操作来确保库存不变成负数。例如，使用 UPDATE 语句时确保只有库存大于等于要购买的数量时才进行更新。
             *      4. 事务控制
             * 通过将读取库存和更新库存的操作放入同一个事务中，确保库存操作的完整性。在事务中进行库存的读取和更新操作，如果库存不足或者事务冲突，系统会回滚并重新处理。
             * }
             */
            Book book = bookDao.getBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
            if (book.getStock() < 0) {
                throw new IllegalArgumentException("库存不足");
            }
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public int sendOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public OrderItem showOrderDetail(int orderItemId) {
        return orderItemDao.queryOrderItemByOrderId(orderItemId);
    }

    @Override
    public Order showOrderByUserId(int userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public int receiveOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 2);
    }
}
