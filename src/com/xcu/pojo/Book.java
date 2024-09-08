package com.xcu.pojo;

import java.math.BigDecimal;

public class Book {

    // 自增的书id号（主键）auto_increment
    private Integer id;
    // 书名
    private String name;
    // 书的价格 对应于DECIMAL(一共的存储位数， 小数的存储位数)
    private BigDecimal price;
    // 书的作者
    private String author;
    // 销量
    private Integer sales;
    // 库存
    private Integer stock;
    // 图片的位置
    private String imgPath = "static/img/default.jpg";

    public Book() {

    }

    public Book(Integer id, String name, BigDecimal price, String author, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
        // 为了防止图片显示不出来，因为目前就一个图片，所以都要以那个为展示
        if (imgPath != null && !"".equals(imgPath)) {
            this.imgPath = imgPath;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        // 为了防止图片显示不出来，因为目前就一个图片，所以都要以那个为展示
        if (imgPath != null && !"".equals(imgPath)) {
            this.imgPath = imgPath;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
