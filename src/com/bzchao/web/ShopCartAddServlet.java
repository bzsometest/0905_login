package com.bzchao.web;

import com.bzchao.dao.ProductImpl;
import com.bzchao.domain.Product;
import com.bzchao.domain.ShopCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShopCartAddServlet", urlPatterns = "/shopCartAdd.action")
public class ShopCartAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pidString = req.getParameter("pid");
        Integer pid = Integer.valueOf(pidString);
        //查询到新增商品
        Product product = new ProductImpl().findById(pid);
        //获得用户购物车
        ShopCart shopCart = getShopCart(req);
        //向购物车中添加商品
        shopCart.addProudct(product);

        //将购物车储存在session中
        req.getSession().setAttribute("shopCart", shopCart);

        //响应重定向，显示购物车
        resp.sendRedirect("shopCart.jsp");
    }

    /**
     * 获得用户购物车
     */
    public ShopCart getShopCart(HttpServletRequest req) {
        ShopCart shopCart = (ShopCart) req.getSession().getAttribute("shopCart");
        if (shopCart == null) {
            shopCart = new ShopCart();
        }
        return shopCart;
    }
}
