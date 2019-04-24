<%--
  Created by IntelliJ IDEA.
  User: King
  Date: 2018/9/28
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>小雨滴登录</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
    <style>
        .mycenter
        {
            margin-top: 100px;
            margin-left: auto;
            margin-right: auto;
            height: 350px;
            width: 500px;
            padding: 5%;
            padding-left: 5%;
            padding-right: 5%;
        }
        .mycenter mysign
        {
            width: 440px;
        }
        .mycenter input, checkbox, button
        {
            margin-top: 2%;
            margin-left: 10%;
            margin-right: 10%;
        }
    </style>
</head>

<body>

<form id="from">
    <div class="mycenter">
        <div class="mysign">
            <div class="col-lg-11 text-center text-info">
                <h2>请登录</h2>
            </div>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="loginAccount" name="loginAccount" placeholder="请输入账户名" required
                       autofocus />
            </div>
            <div class="col-lg-10"></div>
            <div class="col-lg-10">
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" required
                       autofocus />
            </div>
            <div class="col-lg-10"></div>
            <div class="col-lg-10"></div>
            <div class="col-lg-10">
                <button type="button" id="btn" class="btn btn-success col-lg-12">
                    登录</button>
            </div>
        </div>
    </div>
</form>

</body>
</html>
<script >
    $(document).ready(function () {

        $("#btn").click(function(){
            var loginAccount = $('#loginAccount').val();
            var password = $('#password').val();

            if(loginAccount == '') {
                alert('用户名不能为空');
                return;
            }

            if(password == '') {
                alert('密码不能为空');
                return;
            }

            password = hex_sha1(password);

            $.ajax({
                url:"${path}/manage/account/login",
                type:"post",
                dataType:'JSON',
                data:{'loginAccount':loginAccount, 'password':password},
                success: function (data) {
                    if (data.status == 200) {
                        window.location = '${path}/manage/user/statistics';
                    } else{
                        alert(data.msg);
                    }
                }
            });
        });


    });
</script>