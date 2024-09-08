package com.xcu.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    // 生成的订单号（时间戳（ms） + 用户的id号）（保证了唯一性）
    private String orderId;
    // 创建的时间(年月日 + 时分秒) 对应数据库中为Date  中间使用了 时间戳为转换
    private LocalDateTime createTime;
    // 总价格
    private BigDecimal price;
    // 0表示未发货  1表示已发货  2表示已签收
    private Integer status;
    // 对应于付费的用户id
    private Integer userId;

    public Order(String orderId, LocalDateTime createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
