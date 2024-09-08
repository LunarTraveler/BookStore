package com.xcu.test;

import com.xcu.dao.BookDao;
import com.xcu.dao.impl.BookDaoImpl;
import com.xcu.pojo.Book;
import com.xcu.service.BookService;
import com.xcu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "spring", new BigDecimal(34.56), "author", 100, 200, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(17);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(5, "spring", new BigDecimal(34.56), "author", 100, 200, null));
    }

    @Test
    public void getBookById() {
        bookService.getBookById(1);
    }

    @Test
    public void getAllBooks() {
        bookService.getAllBooks();
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, 4));
    }

}