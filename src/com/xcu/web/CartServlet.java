package com.xcu.web;

import com.xcu.pojo.Book;
import com.xcu.pojo.Cart;
import com.xcu.pojo.CartItem;
import com.xcu.service.BookService;
import com.xcu.service.impl.BookServiceImpl;
import com.xcu.utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 添加商品进入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取商品id得到
        int id = WebUtil.parseInt(req.getParameter("id"), 0);
        // 2.图书的全部信息
        Book book = bookService.getBookById(id);
        // 3.把图书的信息转换为购物车商品的信息
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 4.添加商品进入购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addCartItem(cartItem);
        req.getSession().setAttribute("lastName", book.getName());
        // 返回首页
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除购物车中的一个商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取商品id得到
        int id = WebUtil.parseInt(req.getParameter("id"), 0);
        // 2.获取到购物车的信息
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteCartItem(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 清空购物车中的商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取到购物车的信息
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 更新购物车中的商品（没做 前台传来数据进行修改）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取商品id得到
        int id = WebUtil.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, 1);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }


}
