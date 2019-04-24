<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/2
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8" />
    <title>后台登录页面</title>
   <%-- <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp" %>--%>
    <script src="${ctx}/js/jquery-2.1.1.js"></script>
    <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="${ctx}/css/login/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="${ctx}/css/login/simple-line-icons.css" type="text/css" />
    <link rel="stylesheet" href="${ctx}/css/login/app.css" type="text/css" />
</head>
<body background="${ctx}/images/login/yudee_bg.png">
<section id="content" class="m-t-lg wrapper-md animated fadeInUp ">
    <div class="container aside-xl" style="margin-top: 256px;">
        <a class="navbar-brand block"><span class="h1 font-bold" style="color: #ffffff">新雨滴数据管理系统</span></a>
        <section class="m-b-lg">
            <header class="wrapper text-center">
               <%-- <strong class="text-sucess">管理员登录</strong>--%>
            </header>
            <form action="" method="post" class="formDate" >
                <div class="form-group">
                    <input type="username" name="username" placeholder="" class="username form-control  input-lg text-center no-border">
                </div>
                <div class="form-group">
                    <input type="password" name="password" placeholder="" class="password form-control  input-lg text-center no-border">
                </div>
            </form>
            <button type="button" class=" btn-lg btn-danger lt b-white b-2x btn-block">
                登录</button>
        </section>
    </div>
</section>


<%--<div style="text-align:center;">
    <p>忘记密码：<a href="" target="_blank">点击这里</a></p>
</div>--%>

<script>
    $(document).ready(function () {
        $(".btn-block").click(function(){
            var password_hex_sha1="";
            var username=$(".username").val();
            var password=$(".password").val();
            if(username==""||username==null||username=="undefined"){
                alert("请填用户名！");
                return;
            }
            if(password==""||password==null||password=="undefined"){
                alert("请填写密码！");
                return;
            }
            $.ajax({
                url:"${path}/manage/account/login",
                type: 'POST',
                dataType:'json',
                data: {
                    "username":username,
                    "password":password
                },
                async: false,//设置为同步传输
                success: function (result) {
                    console.log(result);
                    if (result.code == 200){
                        alert(result.msg);
                        //跳转到系统介绍页
                        window.location.href = "${path}/manage/account/toindexPage";
                    }else{
                        alert(result.msg);
                    }
                }
            });
        });
    });
</script>
</body>
</html>