package com.bzchao.web;

import com.bzchao.dao.ProductImpl;
import com.bzchao.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询并显示所有商品
 */
@WebServlet("/productAll.action")
public class ProductAllServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = new ProductImpl().findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/productAll.jsp").forward(req, resp);
    }
}
