package com.bzchao.service;

import com.bzchao.dao.ProductDao;
import com.bzchao.dao.ProductOperation;
import com.bzchao.domain.Product;
import com.bzchao.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public int getCount() {
        Connection connection = DBUtils.getConnection();
        ProductDao productDao = new ProductOperation(connection);
        try {
            return productDao.getCount();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return -1;
    }

    @Override
    public List<Product> findAll() {
        Connection connection = DBUtils.getConnection();
        ProductDao productDao = new ProductOperation(connection);
        try {
            return productDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return null;
    }

    @Override
    public Product findById(int pid) {
        Connection connection = DBUtils.getConnection();
        ProductDao productDao = new ProductOperation(connection);
        try {
            return productDao.findById(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return null;
    }

    @Override
    public List<Product> findById(int[] pids) {
        Connection connection = DBUtils.getConnection();
        ProductDao productDao = new ProductOperation(connection);
        try {
            return productDao.findById(pids);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return null;
    }

    @Override
    public List<Product> findByIdOrder(int[] pids) {
        Connection connection = DBUtils.getConnection();
        ProductDao productDao = new ProductOperation(connection);
        try {
            return productDao.findByIdOrder(pids);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return null;
    }

    @Override
    public List<Product> findPage(int page, int count) {
        //处理传过来的参数
        page = page > 0 ? page : 1;
        count = count > 0 ? count : 3;

        //获得查询起始位置
        int start = (page - 1) * count;
        Connection connection = DBUtils.getConnection();
        ProductDao productDao = new ProductOperation(connection);
        try {
            return productDao.findLimit(start, count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return null;
    }

    @Override
    public boolean insert(Product product) {
        Connection connection = DBUtils.getConnection();
        ProductDao productDao = new ProductOperation(connection);
        try {
            int rs = productDao.insert(product);
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return false;
    }

    @Override
    public boolean delete(int pid) {
        Connection connection = DBUtils.getConnection();
        ProductDao productDao = new ProductOperation(connection);
        try {
            int rs = productDao.delete(pid);
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        Connection connection = DBUtils.getConnection();
        ProductDao productDao = new ProductOperation(connection);
        try {
            int rs = productDao.update(product);
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
        return false;
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
