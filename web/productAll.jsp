<%@ page import="java.util.List" %>
<%@ page import="com.bzchao.domain.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>所有商品 </title>
    <style>
        .product_all {
            border: 1px solid #bbb;
            text-align: center;
        }

        .product_info {
            float: left;
            margin: 10px 0px 10px 20px;
            width: 260px;
            height: 350px;
            border: 1px solid blue;
            padding: 5px;
            text-align: center;
        }

        .product_info img {
            width: 250px;
            height: 300px;
        }
    </style>
</head>

<%
    /*获得所有商品*/
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<body>

<h2>所有商品 | <a href="index.html">首页</a></h2>

<div class="product_all">
    <%for (Product product : products) {%>

    <div class="product_info">
        <img src='<%=product.getPic()%>'>
        <br/>
        <span><%=product.getName()%> </span><br/>
        <span>价格： <%=product.getPrice()%></span>
        <span>&nbsp;&nbsp;<a href="productInfo.action?pid=<%=product.getPid()%>">查看详情</a></span>
    </div>

    <% }%>

    <div style="clear: both"></div>
</div>

</body>
</html>
