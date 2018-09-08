package com.bzchao.domain;

import java.util.HashMap;
import java.util.Map;

public class ShopCart {
    //键值key：商品id，值value：商品条目
    Map<Integer, ProductItem> cartMap = new HashMap<>();

    /**
     * 向购物车中加入商品
     *
     * @param product
     */
    public void addProudct(Product product) {
        ProductItem productItem;
        if (cartMap.containsKey(product.getPid())) {
            //购物车中存在此商品
            productItem = cartMap.get(product.getPid());
            //商品数量加1
            productItem.setCount(productItem.getCount() + 1);
        } else {
            //购物车中不存在此商品
            productItem = new ProductItem(product, 0);
            productItem.setCount(1);
        }
        cartMap.put(product.getPid(), productItem);
    }

    public Map<Integer, ProductItem> getCart() {
        return cartMap;
    }

    public double getTotal() {
        double total = 0.0;
        for (Map.Entry<Integer, ProductItem> productEntry : cartMap.entrySet()) {
            ProductItem productItem = productEntry.getValue();
            double productTotal = productItem.getProduct().getPrice() * productItem.getCount();
            total += productTotal;
        }
        return total;
    }
}