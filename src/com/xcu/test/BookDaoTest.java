package com.xcu.test;

import com.xcu.dao.BookDao;
import com.xcu.dao.impl.BookDaoImpl;
import com.xcu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        Book book = new Book(null, "litte", new BigDecimal(33.33), "liutao", 100, 200, null);
        int res = bookDao.addBook(book);
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(16, "query", new BigDecimal(66.66), "liutao", 100, 200, null));
    }

    @Test
    public void getBookById() {
        System.out.println(bookDao.getBookById(16));
    }

    @Test
    public void getAllBooks() {
        bookDao.getAllBooks();
    }

    @Test
    public void queryForPageTotal() {
        int i = bookDao.queryForPageTotal();
        System.out.println(i);
    }

    @Test
    public void queryForItems() {
        List<Book> books = bookDao.queryForItems(0, 4);
        books.forEach(e -> System.out.println(e));
    }
}