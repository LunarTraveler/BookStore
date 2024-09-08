package com.xcu.test;

import com.xcu.web.UserServlet;
import org.junit.Test;

import java.lang.reflect.Method;

public class UserServletTest {

    public void login() {
        System.out.println("login");
    }

    public void regist() {
        System.out.println("regist");
    }

    public static void main(String[] args) {
        String action = "regist";
        try {
            Method method = UserServletTest.class.getMethod(action);
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
