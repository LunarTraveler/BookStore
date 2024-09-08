package com.xcu.web;

import com.xcu.pojo.Page;
import com.xcu.service.BookService;
import com.xcu.service.impl.BookServiceImpl;
import com.xcu.utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {

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
        page.setUrl("client/bookServlet?action=page");
        // 3.保存到req域中
        req.setAttribute("page", page);
        // 4.请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * 依据价格来确定一页的数据页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取req域中的pageNo, pageSize
        int pageNo = WebUtil.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtil.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtil.parseInt(req.getParameter("min"), 0);
        int max = WebUtil.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        // 2.调用service层的page方法获得page对象
        Page page = bookService.pageByPrice(pageNo, pageSize, min, max);
        //page.setUrl("client/bookServlet?action=pageByPrice");
        StringBuilder url = new StringBuilder("client/bookServlet?action=pageByPrice");
        url.append("&min=").append(min);
        url.append("&max=").append(max);
        page.setUrl(url.toString());
        // 3.保存到req域中
        req.setAttribute("page", page);
        // 4.请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }


}
