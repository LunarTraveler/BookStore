package com.xcu.dao.impl;

import com.xcu.dao.BaseDAO;
import com.xcu.dao.BookDao;
import com.xcu.pojo.Book;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class BookDaoImpl extends BaseDAO implements BookDao {

    @Override
    public Integer queryForPageTotalByPrice(int min, int max) {
        try {
            String sql = "select count(*) from t_book where price between ? and ?";
            return executeQuerySingle(sql, Integer.class, min, max);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max) {
        try {
            String sql = "select id, name, price, author, sales, stock, img_path as imgPath from t_book where price between ? and ? limit ?,?";
            return executeQuery(sql, Book.class, min, max, begin, pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int queryForPageTotal() {
        try {
            String sql = "select count(*) from t_book";
            return executeQuerySingle(sql, Integer.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> queryForItems(Integer begin, Integer pageSize) {
        try {
            String sql = "select id, name, price, author, sales, stock, img_path as imgPath from t_book limit ?,?";
            return executeQuery(sql, Book.class, begin, pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int addBook(Book book) {
        try {
            String sql = "insert into t_book(name, price, author, sales, stock, img_path) values(?,?,?,?,?,?)";
            return executeUpdate(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteBookById(Integer id) {
        try {
            String sql = "delete from t_book where id = ?";
            return executeUpdate(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateBook(Book book) {
        try {
            String sql = "update t_book set name = ?, price = ?, author = ?, sales = ?, stock = ?, img_path = ? where id = ?";
            return executeUpdate(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getBookById(Integer id) {
        try {
            String sql = "select id, name, price, author, sales, stock, img_path as imgPath from t_book where id = ?";
            return executeQueryBean(sql, Book.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        try {
            String sql = "select id, name, price, author, sales, stock, img_path as imgPath from t_book";
            return executeQuery(sql, Book.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
