<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录界面</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <style type="text/css">

    </style>
</head>
<body>
<%--<div class="login">
    <div class="login-layout">
        <form class="layui-form" action="doLogin.jsp" method="post">

        </form>
    </div>
</div>--%>
<div class="layui-layout layui-layout-login">
    <h1>
        <strong>Login Demo</strong>
        <em>Management System</em>
    </h1>
    <div class="layui-user-icon larry-login">
        <input type="text" placeholder="账号" class="login_txtbx"/>
    </div>
    <div class="layui-pwd-icon larry-login">
        <input type="password" placeholder="密码" class="login_txtbx"/>
    </div>
    <div class="layui-submit larry-login">
        <input type="button" value="立即登陆" class="submit_btn"/>
    </div>
</div>
<script src="./layui/layui.js"></script>
</body>
</html>