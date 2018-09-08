package com.bzchao.dao;

import com.bzchao.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.bzchao.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class UserOperation implements UserDao {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public boolean register(User user) {
        String sql = "insert into user (username,password,email,sex,birthday) values (?,?,?,?,?)";
        Connection connection = DBUtils.getConnection();
        try {
            Object[] params = new Object[]{user.getUsername(), user.getPassword(), user.getEmail(), user.getSex(), user.getBirthday()};
            int res = queryRunner.update(connection, sql, params);
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return false;
    }

    @Override
    public User login(User user) {
        String sql = "select * from user where username=? and password=?";
        Connection connection = DBUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<User>(User.class), new Object[]{user.getUsername(), user.getPassword()});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //2.在执行finally时，return的方法体已经被执行，即无论return的是否是匿名对象
            close(connection);
        }
        return null;
    }

    @Override
    public User findById(int uid) {
        String sql = "select * from user where uid=?";
        Connection connection = DBUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<User>(User.class), new Object[]{uid});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return null;
    }

    @Override
    public void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
