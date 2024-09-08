package com.xcu.dao.impl;

import com.xcu.dao.BaseDAO;
import com.xcu.dao.OrderDao;
import com.xcu.pojo.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDaoImpl extends BaseDAO implements OrderDao {

    @Override
    public int saveOrder(Order order) {

        try {
            String sql = "insert into t_order values(?,?,?,?,?)";
            return executeUpdate(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Order> queryOrders() {

        try {
            String sql = "select order_id as orderId, create_time as createTime, price, status, user_id as userId from t_order";
            return executeQuery(sql, Order.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int changeOrderStatus(String orderId, int status) {

        try {
            String sql = "update t_order set status = ? where order_id = ?";
            return executeUpdate(sql, status, orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Order queryOrderByUserId(int userId) {

        try {
            String sql = "select order_id as orderId, create_time as createTime, price, status, user_id as userId from t_order where user_id = ?";
            return executeQueryBean(sql, Order.class, userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
