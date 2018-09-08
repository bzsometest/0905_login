<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <style>
        form,table {
            margin: 50px auto;
            position: relative;
            text-align: center;
        }
        table{
            border: 1px solid #bbbbbb;
            padding: 20px;
        }
        td {
            height: 50px;
        }

        td > span {
            vertical-align: middle;
            text-align: center;
            display: table-cell;
        }

        td input {
            height: 32px;
        }

        #checkImg {
            height: 30px;
            width: 80px;
        }

    </style>
</head>
<body>

<h2>用户登录 | <a href="index.html">首页</a></h2>
<h4 style="color: red">${errorMsg}</h4>
<form action="login.action" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密 码：</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>验证码：</td>
            <td style="display: table-cell;">
                <span><input type="text" name="checkCode" size="6"></span>
                <span>
                    <img id="checkImg" src="checkCode.action" alt="正在加载二维码..." border="1">
                </span>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;background: #bbbbbb;"><input type="submit" value="立即登录"></td>
        </tr>
    </table>
    <script>
        var checkImg = document.getElementById("checkImg");
        checkImg.src = checkImg.src + "?random=" + Math.random();
    </script>
</form>
</body>
</html>