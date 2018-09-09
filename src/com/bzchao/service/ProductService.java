package com.bzchao.service;

import com.bzchao.domain.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    /**
     * 获得商品总数量
     *
     * @return
     */
    int getCount();
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
     * 根据页码，每页条数查询
     *
     * @param page
     * @param count
     * @return
     */
    List<Product> findPage(int page, int count);


    /**
     * 新增商品
     *
     * @param product
     * @return
     */
    boolean insert(Product product);

    /**
     * 删除商品
     *
     * @param pid
     * @return
     */
    boolean delete(int pid);
    /**
     * 修改商品
     *
     * @param product
     * @return
     */
    boolean update(Product product);
    /**
     * 关闭连接
     */
    void close(Connection connection);


}
