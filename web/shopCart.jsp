<%@ page import="com.bzchao.domain.ShopCart" %>
<%@ page import="com.bzchao.domain.ProductItem" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>

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
            height: 2.5em;
        }

        .tdone {
            width: 10%;
        }

        .tdtwo {
            width: 20%;
        }

        .tdthree {
            width: 20%;
        }

        .tdfour {
            width: 20%;
        }

        .tdfive {
            width: 20%;
        }

        .tdsix {
            width: 10%;
        }

        td {
            height: 2em;
            text-align: center;
            border: 1px solid #ccc;
        }

        .num {
            display: inline-block;
            width: 3em;
        }

        input {
            height: 2em;
        }

        .talast {
            text-align: left;
        }
    </style>

</head>
<body>
<table>
    <thead>
    <tr>
        <th class="tdone">序号</th>
        <th class="tdtwo">商品名称</th>
        <th class="">商品效果</th>
        <th class="tdthree">数量</th>
        <th class="tdfour">单价</th>
        <th class="tdfive">小计</th>
        <th class="tdsix">操作</th>
    </tr>
    </thead>
    <tbody>

    <%
        ShopCart shopCart = (ShopCart) session.getAttribute("shopCart");
        Map<Integer, ProductItem> map = shopCart.getCart();
        Set<Integer> shopCartSet = map.keySet();
    %>

    <% for (Integer id : shopCartSet) { %>
    <% ProductItem productItem = map.get(id);%>
    <tr class="trclass">
        <td class="tdone xuhao"><%=id %>
        </td>
        <td class="tdtwo "><%=productItem.getProduct().getName() %>
        </td>
        <td><img src="<%=productItem.getProduct().getPic()%>" style="width:100px;"/></td>
        <td class="tdthree"><span class="jiajie"><input type="button" value="-"><span
                class="num"><%=productItem.getCount()%></span><input type="button" value="+"></span></td>
        <td class="tdfour"><span>单价：</span><span class="unit"><%=productItem.getProduct().getPrice()%></span></td>
        <td class="tdfive"><span>小计：</span><span class="subtal"><%=productItem.getProduct().getPrice() * productItem.getCount() %></span></td>
        <td class="tdsix">
            <button class="del">删除</button>
        </td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="6" ; class="talast">共计花费 <span class="pricetal"><%=shopCart.getTotal() %></span> 元</td>
    </tr>
    </tbody>
</table>

<a href="<%=request.getContextPath()%>/productAll.action">继续购买</a>
<script type="text/javascript" src="js/shopCart.js"></script>
</body>
</html>