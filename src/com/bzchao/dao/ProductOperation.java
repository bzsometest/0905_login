package com.bzchao.dao;

import com.bzchao.domain.Product;
import com.bzchao.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Connection由service层通过构造方法传入
 * 从而将事物的处理交给service层
 * Connection在service层关闭
 */
public class ProductOperation implements ProductDao {
    private QueryRunner queryRunner = new QueryRunner();
    private Connection connection;

    public ProductOperation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getCount() throws SQLException {
        String sql = "select count(name) from product";
        Object object = queryRunner.query(connection, sql, new ScalarHandler());
        return Integer.valueOf(object + "");
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String sql = "select * from product";
        return queryRunner.query(connection, sql, new BeanListHandler<Product>(Product.class));
    }

    @Override
    public Product findById(int pid) throws SQLException {
        String sql = "select * from product where pid = ?";
        return queryRunner.query(connection, sql, new BeanHandler<Product>(Product.class), pid);
    }

    @Override
    public List<Product> findById(int[] pids) throws SQLException {
        //sql拼接
        StringBuffer sqlBuffer = new StringBuffer("select * from product where pid ").append(" IN ( ");
        for (int i = 0; i < pids.length; i++) {
            if (i == pids.length - 1) {
                sqlBuffer.append(pids[i] + " ) ");
            } else {
                sqlBuffer.append(pids[i] + ",");
            }
        }

        return queryRunner.query(connection, sqlBuffer.toString(), new BeanListHandler<Product>(Product.class));

    }

    @Override
    public List<Product> findByIdOrder(int[] pids) throws SQLException {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < pids.length; i++) {
            Product product = findById(pids[i]);
            if (product != null) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> findLimit(int start, int count) throws SQLException {
        String sql = "select * from product limit ?,?";
        Object[] params = new Object[]{start, count};
        return queryRunner.query(connection, sql, new BeanListHandler<Product>(Product.class), params);
    }

    @Override
    public int insert(Product product) throws SQLException {
        String sql = "insert into product (name,price,pic) values (?,?,?)";
        Object[] params = new Object[]{product.getName(), product.getPrice(), product.getPic()};
        return queryRunner.update(connection, sql, params);
    }

    @Override
    public int delete(int pid) throws SQLException {
        String sql = "delete from product where pid = ?";
        Object[] params = new Object[]{pid};
        return queryRunner.update(connection, sql, params);
    }

    @Override
    public int update(Product product) throws SQLException {
        String sql = "update product set name=?,price=?,pic=? where pid = ?";
        Object[] params = new Object[]{product.getName(),product.getPrice(),product.getPic(),product.getPid()};
        return queryRunner.update(connection, sql, params);
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtils.getConnection();
        ProductOperation p = new ProductOperation(connection);
        System.out.println(p.getCount());
    }

}
