package com.xcu.dao;

import com.xcu.pojo.Book;

import java.util.List;

public interface BookDao {

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book getBookById(Integer id);

    public List<Book> getAllBooks();

    public int queryForPageTotal();

    public List<Book> queryForItems(Integer begin, Integer pageSize);

    List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max);

    Integer queryForPageTotalByPrice(int min, int max);
}
