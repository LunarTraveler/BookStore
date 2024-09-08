package com.xcu.pojo;

public class User {

    // 用户自增的id号（主键）
    private Integer id;
    // 用户账号的姓名
    private String username;
    // 用户账号的密码
    private String password;
    // 用户账号的邮箱（后续可以在这里 1. 设置验证码 2. 还可以用于找回忘记的密码）
    private String email;

    public User() {

    }

    public User(Integer userId, String username, String password, String email) {
        this.id = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getUserId() {
        return id;
    }

    public void setUserId(Integer userId) {
        this.id = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
