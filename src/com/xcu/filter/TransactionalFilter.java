package com.xcu.filter;

import com.xcu.utils.JDBCUtil;

import javax.servlet.*;
import java.io.IOException;

public class TransactionalFilter implements Filter {
    /**
     * 相当于全局都加上了事务检查
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JDBCUtil.commit();
        } catch (Exception e) {
            JDBCUtil.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
