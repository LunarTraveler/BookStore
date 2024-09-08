package com.xcu.web;

import com.xcu.pojo.User;
import com.xcu.service.UserService;
import com.xcu.service.impl.UserServiceImpl;
import com.xcu.utils.WebUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 用户登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 验证是否登录
        User loginUser = userService.loginUser(new User(null, username, password, null));
        if (loginUser != null) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 60 * 24 * 7); // 保存一周
            resp.addCookie(cookie);
            // 登录之后的信息全部保存到session域中
            req.getSession().setAttribute("user", loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            // 把错误的信息输出来，并且要会显出来特定的参数
            req.setAttribute("msg", "用户名或密码错误!");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * 用户注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // User user = WebUtil.copyParamToBean(req.getParameterMap(), new User());
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY); //获取完就要删除，以免重复提交

        // 首先验证 验证码是否正确 验证码服务器生成
        if (token != null && token.equalsIgnoreCase(code)) {
            // 判断用户名是否存在
            if (userService.existUser(username)){
                req.setAttribute("msg", "the username is existed");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                userService.registerUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "code error");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 用户注销的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.销毁会话
        req.getSession().invalidate();
        // 2.重定向到首页（或者是登录页面）
        resp.sendRedirect(req.getContextPath());
    }

}
