<%--
  Created by IntelliJ IDEA.
  User: Finger
  Date: 3/23/2021
  Time: 7:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>注册账号</title>
    <link rel="stylesheet" href="./layui/css/layui.css"/>
    <style type="text/css">
        .layui-layout-register {
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

<form class="layui-form layui-layout layui-layout-register" action="/register" method="post">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>注册账户</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">用户昵称</label>
        <div class="layui-input-inline">
            <input type="text" name="reader" required lay-verify="required" placeholder="请输入你的昵称" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户账号</label>
        <div class="layui-input-inline">
            <input type="text" name="username" required lay-verify="required" placeholder="请输入账号" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="pwd" placeholder="请输入密码" autocomplete="off"
                   class="layui-input" id="pass1">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-inline">
            <input type="password" name="repassword" required lay-verify="pwd" placeholder="请再次输入密码" autocomplete="off"
                   class="layui-input"
                   id="pass2">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">注册账号</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    <div class="layui-form-item">
        <a style="padding-left: 10px;color: #2badce" href="index.jsp">返回登录</a>
    </div>
</form>

</body>
<script type="text/javascript" src="./layui/layui.js"></script>
<script>
    layui.use('form', function () {
        var form = layui.form;

    });
</script>

</html>
