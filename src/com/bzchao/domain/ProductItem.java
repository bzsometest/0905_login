package com.bzchao.domain;

/**
 * 商品条目对象
 */
public class ProductItem {
    /**
     * 商品
     */
    private Product product;
    /**
     * 商品数量
     */
    private Integer count;

    public ProductItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
