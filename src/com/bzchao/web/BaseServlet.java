package com.bzchao.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //默认以文本的方式显示数据，并设置编码为UTF-8
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain; charset=UTF-8");
        //获得请求方法名
        String methodName = req.getParameter("method");

        //不存在method参数,则执行默认方法
        if (methodName == null) {
            methodName = "defMethod";
        }

        try {
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            try {
                method.invoke(this, req, resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 默认方法
     * 当method参数不合法，或者未填写时，执行此方法
     *
     * @param req
     */
    public abstract void defMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
