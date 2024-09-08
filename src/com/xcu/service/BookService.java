package com.xcu.service;

import com.xcu.dao.BookDao;
import com.xcu.dao.impl.BookDaoImpl;
import com.xcu.pojo.Book;
import com.xcu.pojo.Page;

import java.util.List;

public interface BookService {

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book getBookById(Integer id);

    public List<Book> getAllBooks();

    public Page page(int pageNo, int pageSize);

    Page pageByPrice(int pageNo, int pageSize, int min, int max);
}
