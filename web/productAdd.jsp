<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增商品</title>
    <style>
        form, table {
            margin: 50px auto;
            position: relative;
            text-align: center;
        }

        table {
            border: 1px solid #bbbbbb;
            padding: 20px;
        }

        td {
            height: 50px;
        }

        td input {
            height: 32px;
        }

    </style>
</head>
<body>

<h2>新增商品 | <a href="index.html">首页</a> | <span style="color: red">${message}</span></h2>

<form action="productAdmin.action" method="post">
    <input type="hidden" name="method" value="productInsert">
    <table>
        <tr>
            <td>商品名：</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>价 格：</td>
            <td><input type="text" name="price"></td>
        </tr>
        <tr>
            <td>图片地址：</td>
            <td><input type="text" name="pic"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;background: #bbbbbb;">
                <input type="submit" value="提交">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
