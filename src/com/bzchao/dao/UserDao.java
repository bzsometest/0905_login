package com.bzchao.dao;

import com.bzchao.domain.User;

import java.sql.Connection;

public interface UserDao {
    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 查询用户
     *
     * @param uid
     * @return
     */
    User findById(int uid);

    /**
     * 关闭连接
     */
    void close(Connection connection);
}
