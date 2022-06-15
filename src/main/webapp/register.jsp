<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="fly-html-layui fly-html-store">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/front/layui/dist/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/front/css/global.css" charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/front/css/global(1).css" charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/front/css/store.css" charset="utf-8">
    <link rel="icon" href="${pageContext.request.contextPath}/statics/front/images/favicon.ico">
    <title>酒店管理系统</title>
<body>
<!-- 顶部start -->
<div class="layui-header header header-store" style="background-color: #393D49;">
    <div class="layui-container">
        <a class="logo" href="index.html">
            <img src="${pageContext.request.contextPath}/statics/front/images/logo.png" alt="layui">
        </a>
        <div class="layui-form component" lay-filter="LAY-site-header-component"></div>
        <ul class="layui-nav" id="layui-nav-userinfo">
            <li data-id="index" class="layui-nav-item layui-hide-xs">
                <a class="fly-case-active" data-type="toTopNav" href="/home.jsp">首页</a>
            </li>
            <li data-id="room" class="layui-nav-item layui-hide-xs">
                <a class="fly-case-active" data-type="toTopNav" href="/room/roomList.html">房间</a>
            </li>
            <li data-id="login" class="layui-nav-item layui-hide-xs ">
                <a class="fly-case-active" data-type="toTopNav" href="/login.jsp">登入</a>
            </li>
            <li data-id="register" class="layui-nav-item layui-hide-xs  layui-this">
                <a class="fly-case-active" data-type="toTopNav" href="/register.jsp">注册</a>
            </li>
            <span class="layui-nav-bar" style="left: 560px; top: 55px; width: 0px; opacity: 0;"></span></ul>
    </div>
</div>
<!-- 顶部end -->

<!-- 中间区域开始 -->
<div class="shop-nav shop-index">
    <!--搜索 start-->
    <div id="LAY-topbar" style="height: auto;">
        <form class="layui-form layuimini-form">
            <div class="input-search">
                <div id="searchRoom"><input type="text" placeholder="搜索你需要的房间" name="keywords" id="searchKeywords"
                                            autocomplete="off" value="">
                    <button class="layui-btn layui-btn-shop" lay-submit="" lay-filter="searchHotelRoom" style="background-color: #009688"><i
                            class="layui-icon layui-icon-search"></i></button>
                </div>
                <div class="layui-container layui-hide-xs"><a href="#" class="topbar-logo"> <img
                        src="images/logo-1.png" alt="layui"> </a></div>
            </div>
        </form>
    </div>
    <!--搜索 end-->
</div>
<!-- 中间区域结束 -->

<!-- 注册start -->
<div class="layui-container shopdata">
    <div class="layui-card shopdata-intro">

        <div class=" login-content">
            <!--登录 start-->
            <div class="login-bg">
                <div class="login-cont w1200">
                    <div class="form-box">
                        <form class="layui-form" action="">
                            <legend>用户注册</legend>
                            <div class="layui-form-item">

                                <div class="layui-inline iphone">
                                    <div class="layui-input-inline">
                                        <i class="layui-icon layui-icon-user iphone-icon"></i>
                                        <input type="text" name="loginName" id="mNickname" lay-verify="required" placeholder="请输入昵称" autocomplete="off" class="layui-input">
                                    </div>
                                </div>

                                <div class="layui-inline iphone">
                                    <div class="layui-input-inline">
                                        <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                                        <input type="tel" name="phone" id="phone" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
                                    </div>
                                </div>

                                <div class="layui-inline iphone">
                                    <div class="layui-input-inline">
                                        <i class="layui-icon layui-icon-password iphone-icon"></i>
                                        <input id="pnum" type="password" name="password" lay-verify="required" placeholder="请输入登录密码" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item login-btn">
                                <div class="layui-input-block">
                                    <button class="layui-btn" style="background-color: #009688" lay-submit="" lay-filter="register" onclick="return false">提交</button>
                                    <button type="reset" style="display: none" id="resetBtn" class="layui-btn layui-btn-primary">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!--登录 end-->

        </div>
    </div>

</div>
<!-- 登注册end -->

<!-- 底部 -->
<div class="fly-footer">
    <p><a href="#">酒店系统</a> 2020 © <a href="#">test.cn</a></p>
    <p>
        友情链接
        <a href="http://java.goodym.cn" target="_blank">java项目源码分享网</a>
        <a href="http://www.goodym.cn/code/list/all/1/20.html" target="_blank">源码下载平台</a>
        <a href="http://www.goodym.cn/market/list/all/1/20.html" target="_blank">源码市场</a>
        <a href="http://www.goodym.cn/resumetemplate/list/1/20.html" target="_blank">简历制作</a>
        <a href="http://www.goodym.cn/forum/list/0/1/20.html" target="_blank">社区论坛</a> </p>

</div>


<!-- 脚本开始 -->
<script src="${pageContext.request.contextPath}/statics/layui/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/front/layui/dist/layui.js"></script>
<script>
    layui.use(["form","element","carousel"], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            carousel = layui.carousel,
            $ = layui.$;

        //渲染轮播图
        carousel.render({
            elem: '#LAY-store-banner'
            ,width: '100%' //设置容器宽度
            ,height: '460' //设置容器高度
            ,arrow: 'always' //始终显示箭头
        });
        // 用户名是否存在标识，true标识存在
        var flage = false;
        var flage2 = false;

        // 登录名称失去焦点事件出发查询
        $("#mNickname").blur(function(){
            // 获取用户名
            var loginName = $(this).val().trim();
            // 判断当前用户名不为空的时候，发送请求验证数据
            if(loginName.length > 0){
                $.get("/user/checkName",{"loginName":loginName},function (result) {
                    if(result.exist){
                        layer.open({
                            content: result.messages,
                            yes: function(index, layero){
                                flage2 = true;
                                $("#mNickname").val("");
                                layer.close(index); //如果设定了yes回调，需进行手工关闭
                            }
                        });
                    }
                    else {
                        flage = false;
                    }
                },"json");
            }
        });

        // 手机号失去焦点事件出发查询
        $("#phone").blur(function(){
            // 获取用户名
            var phone = $(this).val().trim();
            // 判断当前用户名不为空的时候，发送请求验证数据
            if(phone.length > 0){
                $.get("/user/checkPhone",{"phone":phone},function (result) {
                    if(result.exist){
                        layer.open({
                            content: result.messages,
                            yes: function(index, layero){
                                flage2 = true;
                                $("#phone").val("");
                                layer.close(index); //如果设定了yes回调，需进行手工关闭
                            }
                        });
                    }
                    else {
                        flage2 = false;
                    }
                },"json");
            }
        });

        // 表单提交事件
        form.on("submit(register)",function (data) {
            if(!flage && !flage2){
                // 发生请求
                $.post("/user/addUser",data.field,function (result) {
                    if(result.succser){
                        layer.alert(result.messages,{icon:6});
                        // 重置表单
                        $("#resetBtn").click();
                    }else {
                        layer.alert(result.messages,{icon:5});
                    }
                },"json");
            }else {
                layer.alert("用户名或手机号已存在，请重新输入",{icon:5});
            }
            return false;
        });
    });
</script>
<!-- 脚本结束 -->
<ul class="layui-fixbar">
    <li class="layui-icon layui-fixbar-top" lay-type="top" style=""></li>
</ul>
<div class="layui-layer-move"></div>

</body>
</html>