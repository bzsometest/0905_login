package com.bzchao.dao;

import com.bzchao.domain.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {
    /**
     * 查询所有商品
     *
     * @return
     */
    List<Product> findAll();

    /**
     * 根据商品id查询商品
     *
     * @param pid
     * @return
     */
    Product findById(int pid);

    /**
     * 根据商品id数组查询商品
     *
     * @param pids
     * @return
     */
    List<Product> findById(int[] pids);

    /**
     * 根据商品id数组查询商品(返回结果有序)
     *
     * @param pids
     * @return
     */
    List<Product> findByIdOrder(int[] pids);

    /**
     * 关闭连接
     */
    void close(Connection connection);
}
