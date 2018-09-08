package com.bzchao.web;

import com.bzchao.dao.UserDao;
import com.bzchao.dao.UserOperation;
import com.bzchao.domain.User;
import com.bzchao.utils.Validates;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 用户登录控制
 */
@WebServlet("/login.action")
public class UserLoginServlet extends HttpServlet {
    UserDao userDao = new UserOperation();

    {
        //单例模式，只会被创建一次
        System.out.println("创建登录实例！");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
        super.init(config);
    }

    @Override
    public void destroy() {
        System.out.println("destroy()");
        super.destroy();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (Validates.isEmpty(username) || Validates.isEmpty(password)) {
            resp.getWriter().println("用户名或者密码不符合规范");
            return;
        }
        User user = userDao.login(new User(username, password));
        if (user == null) {
            resp.getWriter().println("登录失败！用户名或密码错误");
        } else {
            Cookie userCookie = new Cookie("username", user.getUsername());
            userCookie.setMaxAge(60 * 10);
            resp.addCookie(userCookie);
            resp.sendRedirect(req.getContextPath() + "/userCenter.jsp");
            System.out.println(req.getContextPath() + "/userCenter.jsp");
        }
    }
}
