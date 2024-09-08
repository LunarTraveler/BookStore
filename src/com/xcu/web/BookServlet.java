package com.xcu.web;

import com.xcu.pojo.Book;
import com.xcu.pojo.Page;
import com.xcu.pojo.User;
import com.xcu.service.BookService;
import com.xcu.service.impl.BookServiceImpl;
import com.xcu.utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 这个用于获取page数据来展示页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取req域中的pageNo, pageSize
        int pageNo = WebUtil.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtil.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2.调用service层的page方法获得page对象
        Page page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        // 3.保存到req域中
        req.setAttribute("page", page);
        // 4.请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     * 该方法是添加图书的功能
     * @param req 这个参数里面有提交的表单的信息
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.通过反射获取book对象注入
        Book book = WebUtil.copyParamToBean(req.getParameterMap(), new Book());
        // 2.调用service层的添加图书方法
        bookService.addBook(book);
        // 3.跳转到图书展示的页面上
        // 请求转发会有问题的，一直按f5会不断的添加图书进而有bug(浏览器会缓存上一步的请求状态进而出现问题)
        // 所以这种最好就是重定向(Post/Redirect/Get)PRG模式解决表单重复提交的最佳实践
        // req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        // 请求重定向的话就可以避免重复的添加
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");
    }

    /**
     * 这个方法的功能主要是删除图书的功能
     * @param req 带的请求参数有id可以通过这个找到相应的书籍
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.通过req获取到id对应的值
        Integer id = Integer.parseInt(req.getParameter("id"));
        // 2.调用service层的方法进行删除的操作
        bookService.deleteBookById(id);
        // 3.请求重定向 就可以刷新页面了
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");
    }

    /**
     * 这个方法的功能主要是更新图书的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.注入book
        Book book = WebUtil.copyParamToBean(req.getParameterMap(), new Book());
        // 2.调用service层的方法进行update
        bookService.updateBook(book);
        // 3.返回展示页面进行刷新
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");
    }

    /**
     * 用于补充的方法，带来一个id的参数可以得到全部的图书数据，数据在req的域中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取id进而得到全部的信息
        Integer id = Integer.parseInt(req.getParameter("id"));
        Book book = bookService.getBookById(id);
        // 2.把信息保存到req的域中
        req.setAttribute("book", book);
        // 3.进行请求转发
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    /**
     * 这个的功能是展示全部的书籍，用于展示页面(页面的参数里面是有全部的数据，并在jsp页面里展示JSTL标签库)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取列表集合
        List<Book> books = bookService.getAllBooks();
        // 2.将该参数保存到req的域中
        req.setAttribute("books", books);
        // 3.请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }


}
