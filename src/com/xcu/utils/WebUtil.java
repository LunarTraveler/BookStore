package com.xcu.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtil {
    /**
     * 用于注入JavaBean的快捷方法
     * @param value 用于注入的键值对
     * @param bean 用于注入的实例对象
     */
    public static <T, K, V> T copyParamToBean(Map<K, V> value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    public static int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static Cookie getCookie(String name, Cookie[] cookies) {
        if (name == null || cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

}
