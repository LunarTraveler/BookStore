package com.xcu.service;

import com.xcu.pojo.User;

public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录用户
     * @param user
     * @return
     */
    public User loginUser(User user);

    /**
     * 检查用户名是否存在
     * @param username
     * @return false -> 不存在可用 true  -> 存在不可用
     */
    public boolean existUser(String username);

}
