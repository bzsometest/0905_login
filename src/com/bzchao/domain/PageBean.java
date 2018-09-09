package com.bzchao.domain;

import java.util.List;

public class PageBean {
    //当前页面条目
    List<Product> productList;
    /**
     * 当前页面
     */
    private int page;
    /**
     * 总共页码
     */
    private int sumPage;

    /**
     * 每页显示条数
     */
    private int count;
    /**
     * 总条数
     */
    private int totalCount;

    public PageBean(int page, int sumPage, int count, int totalCount) {
        this.page = page;
        this.sumPage = sumPage;
        this.count = count;
        this.totalCount = totalCount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
