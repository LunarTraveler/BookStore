package com.xcu.dao;

import com.xcu.pojo.User;

import java.util.List;

public interface UserDao {

    /**
     * 通过查询出相对应的User通过名字主键
     * @param username 名字主键
     * @return 相应的的User对象
     */
    User selectByName(String username);

    /**
     * 查询是否存在相应的User存在数据库中
     * @param username
     * @param password
     * @return
     */
    User selectByNameAndPassword(String username, String password);

    /**
     * 数据库对应的新增一条员工数据
     * @param user ORM思想中的一个员工对象
     * @return 受影响的行数
     */
    int insert(User user);

}
