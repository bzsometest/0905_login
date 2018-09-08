package com.bzchao.dao;

import com.bzchao.domain.Product;
import com.bzchao.utils.DBUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 更改业务逻辑，优化并发操作导致的问题。
 * 每次执行数据库操作均重新获取连接
 */
public class ProductImpl implements ProductDao {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<Product> findAll() {
        String sql = "select * from product";
        Connection connection = DBUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return null;
    }

    @Override
    public Product findById(int pid) {
        String sql = "select * from product where pid = ?";
        Connection connection = DBUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<Product>(Product.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return null;
    }

    @Override
    public List<Product> findById(int[] pids) {
        //sql拼接
        StringBuffer sqlBuffer = new StringBuffer("select * from product where pid ").append(" IN ( ");
        for (int i = 0; i < pids.length; i++) {
            if (i == pids.length - 1) {
                sqlBuffer.append(pids[i] + " ) ");
            } else {
                sqlBuffer.append(pids[i] + ",");
            }
        }
        Connection connection = DBUtils.getConnection();
        //开始查询
        try {
            return queryRunner.query(connection, sqlBuffer.toString(), new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return null;
    }

    @Override
    public List<Product> findByIdOrder(int[] pids) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < pids.length; i++) {
            Product product = findById(pids[i]);
            if (product != null) {
                productList.add(product);
            }
        }
        return productList;
    }

    public static void main(String[] args) {
        List<Product> list = new ProductImpl().findById(new int[]{1, 3});
        System.out.println(list.size());
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
