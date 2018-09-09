package com.bzchao.utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    /**
     * 获取连接的次数
     */
    private static int count = 0;

    /**
     * 数据源
     */
    private static DataSource dataSource = new ComboPooledDataSource();

    /**
     * 获得数据源
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获得连接
     */
    public static Connection getConnection() {
        //默认只能获取16个连接
        //用完连接之后必须关闭，否则将导致一直获取不到连接
        //对于查询方法，使用finally自动关闭连接
        System.out.println("获取连接Connection：" + count++);

        try {
            Connection connection = dataSource.getConnection();
            System.out.println("获取成功！");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
