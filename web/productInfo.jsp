<%@ page import="java.util.List" %>
<%@ page import="com.bzchao.domain.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Product productNow = (Product) request.getAttribute("product");%>
<% List<Product> productHistoty = (List<Product>) request.getAttribute("historys");%>

<html>
<head>
    <title>
        查看商品 - <%=productNow.getName()%>
    </title>
    <style>
        .product_now_info {
            float: left;
            margin: 10px;
            width: 70%;
            height: 440px;
            border: 1px solid blue;
            padding: 2px;
        }

        .product_now_info img {
            float: left;
            width: 400px;
            height: 400px;
            padding: 10px 32px;
        }
        .product_introduce{
            float: left;
            margin: 20px 10px;
            font-size: 32px;
        }
        .product_history {
            float: right;
            width: 200px;
            text-align: center;
            border: 1px solid gray;
        }

        .product_history .product_info {
            width: 200px;
            height: 270px;
            background-color: #bbbbbb;
            margin: 10px 0px;
        }

        .product_history .product_info img {
            width: 180px;
            height: 200px;
        }
    </style>
</head>
<body>
<h2>商品详情 | <a href="productAll.action">所有商品</a></h2>
<div class="product_now_info">
    <% if (productNow != null) {%>
    <img src='<%=productNow.getPic()%>'>

    <div class="product_introduce">
        <span><%=productNow.getName()%> </span><br/>
        <span>价格： <%=productNow.getPrice()%></span><br/>
        <span><a href="shopCartAdd.action?pid=<%=productNow.getPid()%>">加入购物车</a></span>
    </div>
    <%} else {%>
    <p style="color: red">未查询到此商品！</p>
    <% }%>
    <div style="clear: both"></div>
</div>

<div class="product_history">
    <div>商品浏览记录</div>
    <%for (int i = 0; productHistoty != null && i < productHistoty.size(); i++) {%>
    <% Product product = productHistoty.get(i);%>
    <div class="product_info">
        <img src='<%=product.getPic()%>'>
        <span><%=product.getName()%> </span><br/>
        <span>价格： <%=product.getPrice()%></span><br/>
        <span>&nbsp;&nbsp;<a href="productInfo.action?pid=<%=product.getPid()%>">查看详情</a></span>
    </div>
    <% }%>
    <div style="clear: both"></div>
</div>

</body>
</html>
