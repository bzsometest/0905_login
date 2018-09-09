<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品管理</title>

    <style type="text/css">
        table {
            width: 50%;
            position: relative;
            margin: 30px auto;
            border-collapse: collapse;
            border: 1px solid gray;
        }

        th {
            background: cornflowerblue;
            height: 50px;
        }

        td {
            height: 50px;
            text-align: center;
            border: 1px solid #ccc;
        }

        input {
            height: 2em;
        }

    </style>

</head>
<body>
<h2>管理商品 | <a href="index.html">首页</a> | <span style="color: red">${message}</span></h2>

<table>
    <thead>
    <tr>
        <th class="tdone">商品PID</th>
        <th class="tdtwo">商品名称</th>
        <th class="tdfour">单价</th>
        <th class="tdthree">商品效果</th>
        <th class="tdfive">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageBean.productList}" var="product">
        <tr class="trclass">
            <td>${product.pid}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <img src="${product.pic}" style="width:100px;">
            </td>
            <td>
                <a href="productAdmin.action?method=productShowOne&pid=${product.pid}">修改</a>
                <a href="productAdmin.action?method=productDelete&pid=${product.pid}">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<span>当前${pageBean.page}页</span> <span>总共${pageBean.sumPage}页</span> <span>总共${pageBean.totalCount}记录</span>

<c:choose>
    <c:when test="${pageBean.page > 1}">
        <a href="${pageContext.request.contextPath}/productAdmin.action?method=productShowPage&page=${pageBean.page-1}&count=3">上一页</a>
    </c:when>
    <c:when test="${pageBean.page <= 1}">
        <span>已是第一页</span>
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${pageBean.page< pageBean.sumPage}">
        <a href="${pageContext.request.contextPath}/productAdmin.action?method=productShowPage&page=${pageBean.page+1}&count=3">下一页</a>
    </c:when>
    <c:when test="${pageBean.page >= pageBean.sumPage}">
        <span>已是最后一页</span>
    </c:when>
</c:choose>
<a href="${pageContext.request.contextPath}/productAdd.jsp">新增商品</a>
</body>
</html>