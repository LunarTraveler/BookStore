package com.xcu.test;

import com.xcu.dao.impl.UserDaoImpl;
import com.xcu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    public UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void selectByName() {
        User user = userDao.selectByName("admin");
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("null");
        }
    }

    @Test
    public void selectByNameAndPassword() {
        User user = userDao.selectByNameAndPassword("admin", "admin");
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("null");
        }
    }

    @Test
    public void insert() {
        System.out.println("ok");
    }
}