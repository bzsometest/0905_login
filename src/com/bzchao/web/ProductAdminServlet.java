package com.bzchao.web;

import com.bzchao.domain.PageBean;
import com.bzchao.domain.Product;
import com.bzchao.service.ProductService;
import com.bzchao.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductAdminServlet", urlPatterns = "/productAdmin.action")
public class ProductAdminServlet extends BaseServlet {
    @Override
    public void defMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //默认显示第一页商品
        productShowPageOne(req, resp);
    }

    /**
     * 新增商品
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void productInsert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String pic = req.getParameter("pic");
        String priceStr = req.getParameter("price");
        Double price;
        try {
            price = Double.valueOf(priceStr);
        } catch (Exception e) {
            resp.getWriter().println("格式错误：价格");
            return;
        }
        Product product = new Product(name, price, pic);
        boolean isInsert = new ProductServiceImpl().insert(product);
        String message = isInsert ? "新增商品成功！" : "新增商品失败，请重试";
        req.setAttribute("message", message);
        req.getRequestDispatcher("/productAdd.jsp").forward(req, resp);
    }

    /**
     * 删除商品
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void productDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int pid = Integer.valueOf(req.getParameter("pid"));
        boolean idDelete = new ProductServiceImpl().delete(pid);
        String message = idDelete ? "删除商品成功！" : "删除商品失败，请重试";
        req.setAttribute("message", message);
        //商品操作均返回第一页
        productShowPageOne(req, resp);
    }

    /**
     * 修改商品
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void productUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("productUpdate 修改商品");
        int pid = Integer.valueOf(req.getParameter("pid"));
        String name = req.getParameter("name");
        String pic = req.getParameter("pic");
        String priceStr = req.getParameter("price");
        Double price = Double.valueOf(priceStr);

        Product product = new Product(name, price, pic);
        product.setPid(pid);
        boolean idDelete = new ProductServiceImpl().update(product);
        String message = idDelete ? "修改商品成功！" : "修改商品失败，请重试";
        req.setAttribute("message", message);
        //商品操作均返回第一页
        productShowPageOne(req, resp);
    }

    /**
     * 显示第一页商品
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void productShowPageOne(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int page = 1;
        int count = 3;
        List<Product> productList = new ProductServiceImpl().findPage(page, count);
        PageBean pageBean = getPageBean(page, count, productList);
        req.setAttribute("pageBean", pageBean);
        req.getRequestDispatcher("/productAdmin.jsp").forward(req, resp);
    }

    /**
     * 根据页码，条数显示商品
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void productShowPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.valueOf(req.getParameter("page"));
        int count = Integer.valueOf(req.getParameter("count"));
        List<Product> productList = new ProductServiceImpl().findPage(page, count);
        PageBean pageBean = getPageBean(page, count, productList);
        req.setAttribute("pageBean", pageBean);
        req.getRequestDispatcher("/productAdmin.jsp").forward(req, resp);
    }

    /**
     * 显示一个商品信息(用户修改信息)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void productShowOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = Integer.valueOf(req.getParameter("pid"));
        Product product = new ProductServiceImpl().findById(pid);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/productUpdate.jsp").forward(req, resp);
    }

    /**
     * 获得pageBean
     *
     * @param page
     * @param count
     * @param productList
     * @return
     */
    public PageBean getPageBean(int page, int count, List<Product> productList) {
        ProductService productService = new ProductServiceImpl();
        int totalCount = productService.getCount();
        int sumPage = (totalCount - 1) / count + 1;
        PageBean pageBean = new PageBean(page, sumPage, count, totalCount);
        pageBean.setProductList(productList);
        return pageBean;
    }
}
