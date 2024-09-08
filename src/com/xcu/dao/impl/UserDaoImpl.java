package com.xcu.dao.impl;

import com.xcu.dao.BaseDAO;
import com.xcu.dao.UserDao;
import com.xcu.pojo.User;

import java.sql.SQLException;

public class UserDaoImpl extends BaseDAO implements UserDao{

    @Override
    public User selectByName(String username) {
        try {
            String sql = "select id, username, password, email from t_user where username = ?";
            return executeQueryBean(sql, User.class, username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User selectByNameAndPassword(String username, String password) {
        try {
            String sql = "select id, username, password, email from t_user where username = ? and password = ?";
            return executeQueryBean(sql, User.class, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(User user) {
        try {
            String sql = "insert into t_user(username, password, email) values(?, ?, ?);";
            return executeUpdate(sql, user.getUsername(), user.getPassword(), user.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
