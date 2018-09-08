package com.bzchao.web;

import com.bzchao.dao.UserDao;
import com.bzchao.dao.UserOperation;
import com.bzchao.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 用户注册
 */
@WebServlet("/register.action")
public class UserRegisterServlet extends HttpServlet {
    UserDao userDao = new UserOperation();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            //  ConvertUtils.register(new DateLocaleConverter(), Date.class);
            ConvertUtils.register(new Converter() {
                @Override
                public Object convert(Class aClass, Object object) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        return format.parse(String.valueOf(object));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }, Date.class);

            BeanUtils.populate(user, map);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("商城提示：提交的数据不符合要求！");
            return;
        }

        boolean isReg = userDao.register(user);
        if (isReg) {
            resp.getWriter().println("注册成功！正在跳转至登录页面...");
            resp.setStatus(302);
            resp.setHeader("refresh", "5;login.jsp");
        } else {
            resp.getWriter().println("注册失败，用户名可能已被注册！");
            resp.setStatus(302);
            resp.setHeader("refresh", "5;url=register.html");
        }
    }
}
