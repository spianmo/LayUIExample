<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录界面</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <style type="text/css">
        .layui-layout-login {
            width: 350px;
            height: auto;
            overflow: hidden;
            margin: 10% auto 0 auto;
            box-shadow: 2px 1px 10px 10px #eeeeee;
            border-radius: 8px;
            z-index: 10;
        }
    </style>
</head>
<body>

<form class="layui-form layui-layout layui-layout-login" action="doLogin.jsp" method="post">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>登录演示</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">账号</label>
        <div class="layui-input-inline">
            <input type="text" name="account" required lay-verify="required" placeholder="请输入账号"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <div class="layui-btn-group">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即登录</button>
                <button type="reset" class="layui-btn layui-btn-primary">忘记密码</button>
            </div>
        </div>
    </div>
</form>
<script src="./layui/layui.js"></script>
</body>
</html>