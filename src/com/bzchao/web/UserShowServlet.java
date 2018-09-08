package com.bzchao.web;

import com.bzchao.dao.UserOperation;
import com.bzchao.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据uid显示用户信息
 */
@WebServlet("/userShow.action")
public class UserShowServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        int uid;
        try {
            String idStr = req.getParameter("uid");
            uid = Integer.valueOf(idStr);
        } catch (NumberFormatException e) {
            resp.getWriter().println("商城提示：提交的数据不符合要求！");
            return;
        }

        User user = new UserOperation().findById(uid);
        resp.getWriter().println(user);
    }
}
