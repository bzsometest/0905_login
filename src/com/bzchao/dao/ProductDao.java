package com.bzchao.dao;

import com.bzchao.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    /**
     * 获得商品总数量
     *
     * @return
     */
    int getCount() throws SQLException;

    /**
     * 查询所有商品
     *
     * @return
     */
    List<Product> findAll() throws SQLException;

    /**
     * 根据商品id查询商品
     *
     * @param pid
     * @return
     */
    Product findById(int pid) throws SQLException;

    /**
     * 根据商品id数组查询商品
     *
     * @param pids
     * @return
     */
    List<Product> findById(int[] pids) throws SQLException;

    /**
     * 根据商品id数组查询商品(返回结果有序)
     *
     * @param pids
     * @return
     */
    List<Product> findByIdOrder(int[] pids) throws SQLException;

    /**
     * 根据起始位置，查询条数查询
     *
     * @param start
     * @param count
     * @return
     */
    List<Product> findLimit(int start, int count) throws SQLException;

    /**
     * 新增商品
     *
     * @param product
     * @return
     */
    int insert(Product product) throws SQLException;

    /**
     * 删除商品
     *
     * @param pid
     * @return
     */
    int delete(int pid) throws SQLException;

    /**
     * 修改商品
     *
     * @param product
     * @return
     */
    int update(Product product) throws SQLException;

}
