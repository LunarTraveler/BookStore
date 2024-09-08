package com.xcu.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车对象
 */
public class Cart {

    // 只通过get方法，不需要set方法
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    // K ->  商品的id   V ->  对应的商品项
    private Map<Integer, CartItem> cartItems = new LinkedHashMap<>();

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addCartItem(CartItem cartItem) {
        CartItem item = cartItems.get(cartItem.getId());
        if (item == null) {
            cartItems.put(cartItem.getId(), cartItem);
        } else {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
            // 或者是在当前的总价格加上一个价格
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteCartItem(Integer id) {
        cartItems.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        cartItems.clear();
    }

    /**
     * 修改商品的数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        CartItem cartItem = cartItems.get(id);
        if (cartItem != null) {
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (CartItem cartItem : cartItems.values()) {
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem cartItem : cartItems.values()) {
            totalPrice = totalPrice.add(cartItem.getPrice()).multiply(new BigDecimal(cartItem.getCount()));
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Integer, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", cartItems=" + cartItems +
                '}';
    }
}
