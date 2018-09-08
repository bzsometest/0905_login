package com.bzchao.web;

import com.bzchao.dao.ProductImpl;
import com.bzchao.domain.Product;
import com.bzchao.utils.CookieUtils;
import com.bzchao.utils.ProductPidUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

/**
 * 单个商品信息
 */
@WebServlet("/productInfo.action")
public class ProductInfoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //显示当前商品(请求转发)
            showProductById(req);

            //显示历史列表(请求转发)
            showProductHistory(req);

            //将当前商品加入历史列表(Cookie储存)
            addProductHistory(req, resp);
        } catch (NumberFormatException e) {
            resp.setContentType("text/plain; charset=UTF-8");
            resp.getWriter().println("商城提示：提交的数据不符合要求！");
            return;
        }

        req.getRequestDispatcher("/productInfo.jsp").forward(req, resp);
    }

    /**
     * 显示商品历史记录(请求转发)
     *
     * @param req
     */
    private void showProductHistory(HttpServletRequest req) throws NumberFormatException {
        String[] pidStringArray = ProductPidUtils.getPidStringArray(req);

        //没有商品历史记录，则创建一个大小为0的数组，减小前端错误概率
        if (pidStringArray == null || pidStringArray.length == 0) {
            req.setAttribute("historys", new ArrayList<Product>());
            return;
        }

        //根据商品字符串数组获得商品整形数组
        int[] pidArray = ProductPidUtils.getPidArray(pidStringArray);

        //直接查询到的数据是无序的！
        // List<Product> productList = new ProductImpl().findById(pidArray);

        //有序查找商品
        List<Product> productList = new ProductImpl().findByIdOrder(pidArray);
        req.setAttribute("historys", productList);
    }

    /**
     * 根据商品pid显示商品(请求转发)
     *
     * @param req
     */
    private void showProductById(HttpServletRequest req) throws NumberFormatException {
        String pidString = req.getParameter("pid");
        int pid = Integer.valueOf(pidString);
        Product product = new ProductImpl().findById(pid);
        req.setAttribute("product", product);
    }

    /**
     * 添加商品历史记录中(Cookie储存)
     *
     * @param req
     * @param resp
     */
    private void addProductHistory(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException {

        String pidString = req.getParameter("pid");

        //获得商品pid字符串数组
        String[] pidStringArray = ProductPidUtils.getPidStringArray(req);
        //将此商品添加到历史记录中(有序)
        String[] newPidStringArray = ProductPidUtils.addPid(pidStringArray, pidString);
        //生成商品pid字符串
        String newPidString = ProductPidUtils.getPidString(newPidStringArray);
        //生成历史记录cookie
        Cookie newHistoryCookie = new Cookie(CookieUtils.HISTORY, newPidString);
        newHistoryCookie.setMaxAge(60 * 10);
        resp.addCookie(newHistoryCookie);
    }
}
