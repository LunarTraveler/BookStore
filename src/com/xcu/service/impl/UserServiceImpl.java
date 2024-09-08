package com.xcu.service.impl;

import com.xcu.dao.UserDao;
import com.xcu.pojo.User;
import com.xcu.service.UserService;
import com.xcu.dao.impl.UserDaoImpl;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.insert(user);
    }

    @Override
    public User loginUser(User user) {
        return userDao.selectByNameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existUser(String userName) {
        if (userDao.selectByName(userName) == null) {
            return false;
        }
        return true;
    }
}
