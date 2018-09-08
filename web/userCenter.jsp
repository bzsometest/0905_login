<%@ page import="java.util.Date" %>
<%@ page import="com.bzchao.utils.DateUtils" %>
<%@ page import="com.bzchao.utils.CookieUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户中心</title>
</head>
<body>

<h2>用户中心 | <a href="index.html">首页</a></h2>

<%
    Cookie countCookie = CookieUtils.getCookie("count", request.getCookies());
    Cookie userCookie = CookieUtils.getCookie("username", request.getCookies());
    Cookie preDateCookie = CookieUtils.getCookie("preDate", request.getCookies());
    if (userCookie != null) {
        out.println("欢迎登录，" + userCookie.getValue());
    } else {
        out.println("游客");
    }

    int count = 0;
    if (countCookie != null) {
        String countStr = countCookie.getValue();
        count = Integer.valueOf(countStr);
        count++;
        out.println("，你是第" + count + "次访问");
        out.println("<br/>");
        if (preDateCookie != null) {
            out.println("上次访问时间: " + DateUtils.getStringByCTS(preDateCookie.getValue()));
        }
    } else {
        count = 1;
        out.println("你是首次访问!");
    }

    Cookie respCookie = new Cookie("count", String.valueOf(count));
    Cookie dateCookie = new Cookie("preDate", new Date().toString());
    respCookie.setMaxAge(60 * 10);
    dateCookie.setMaxAge(60 * 10);
    response.addCookie(respCookie);
    response.addCookie(dateCookie);
%>
</body>
</html>
