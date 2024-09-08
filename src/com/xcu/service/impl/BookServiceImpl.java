package com.xcu.service.impl;

import com.xcu.dao.BookDao;
import com.xcu.dao.impl.BookDaoImpl;
import com.xcu.pojo.Book;
import com.xcu.pojo.Page;
import com.xcu.service.BookService;

import java.util.Collections;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public Page pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page page = new Page();
        // 设置page的五个参数
        // 每页显示数量
        page.setPageSize(pageSize);
        // 总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);
        // 总页码
        Integer pageTotal = (pageTotalCount + pageSize - 1) / pageSize;
        page.setPageTotal(pageTotal);
        // 当前页码
        page.setPageNo(pageNo);
        // 每页的数据
        int begin = (pageNo - 1) * pageSize;
        List<Book> items = bookDao.queryForItemsByPrice(begin, pageSize, min, max);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page page = new Page();
        // 设置page的五个参数
        page.setPageSize(pageSize);

        Integer pageTotalCount = bookDao.queryForPageTotal();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = (pageTotalCount + pageSize - 1) / pageSize;
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);

        int begin = (pageNo - 1) * pageSize;
        List<Book> items = bookDao.queryForItems(begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }


}
