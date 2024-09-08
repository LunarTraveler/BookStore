package com.xcu.dao.impl;

import com.xcu.dao.BaseDAO;
import com.xcu.dao.OrderItemDao;
import com.xcu.pojo.OrderItem;

import java.sql.SQLException;

public class OrderItemDaoImpl extends BaseDAO implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {

        try {
            String sql = "insert into t_order_item values(?,?,?,?,?,?)";
            return executeUpdate(sql, orderItem.getId(), orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public OrderItem queryOrderItemByOrderId(int orderId) {

        try {
            String sql = "select id, name, count, price, total_price as totalPrice, order_id as orderId from t_order_item where order_id = ?";
            return executeQueryBean(sql, OrderItem.class, orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
