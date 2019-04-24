
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>添加/编辑账号</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp" %>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp" %>
    <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form class="form-horizontal formDate" role="form">
                <fieldset>
                    <legend>账号管理>>添加/编辑账号<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-3 input-group">
                            <input name="id"  hidden value="${accountUser.id}">
                            <input name="type" hidden  value="${accountUser.status}">
                            <input type="text" name="account" id="account" value="${accountUser.account}" class="form-control">
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系人:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-3 input-group">
                            <input type="text" value="${accountUser.accountName}" id="accountName" name="name" class="form-control">
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">手机号:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-3 input-group">
                            <input type="text" value="${accountUser.phone}" id="phone" name="phone" class="form-control">
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-phone"></span>
                                </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角 色:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-3 input-group">
                            <c:forEach items="${eecManageRoles}" var="role" >
                                    <c:choose>
                                        <c:when test="${role.pitchOn == true}">
                                            <input type="checkbox" checked="checked" class="checkboxList" name="roleId" value="${role.id}" >${role.roleName}<span>&emsp;</span>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="checkboxList" name="roleId" value="${role.id}" >${role.roleName}<span>&emsp;</span>
                                        </c:otherwise>
                                    </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>
                        <div class="col-sm-2 ">
                            <button type="button" class="btn btn-primary totrue">确定</button>
                        </div>
                        <div class="col-sm-1 ">
                            <button type="button" class="btn btn-primary tofalse">取消</button>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>

    $(document).ready(function () {
        $(".tofalse").click(function () {
            window.location.href = "${path}/manage/account/getAccountList";
        });

        $(".totrue").click(function(){
            //手机号验证
            var phone = $("#phone").val();
            var account = $("#account").val();
            var accountName = $("#accountName").val();
            if (account.length <= 0){
                alert("账户名不能为空！");
                return;
            }
            if (accountName.length <= 0){
                alert("联系人不能为空！");
                return;
            }
            var reg =/^(13[0-9]|14[1579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
            if (phone.length <= 0){
                alert("请输入手机号！");
                return;
            }
            if(!(reg.test(phone))){
                alert("手机号码格式不正确，请重新输入！");
                return ;
            }
            var get = $(".checkboxList");
            var roleIds = "";
            for (i = 0 ; i < get.length; i++) {
                if (get[i].checked) {
                    roleIds +=get[i].value+ "," ;
                }
            }
            if (roleIds.length == 0){
                alert("您还没有选择要分配的角色！");
                return;
            }
            var data=$(".formDate").serialize();
            //序列化获得表单数据，结果为：user_id=12&user_name=John&user_age=20   出现乱码情况
            var password = hex_sha1("123456");
            data+="&password="+password+"&roleIds="+roleIds;
            var submitData=decodeURIComponent(data,true);
            //submitData是解码后的表单数据，结果同上 不会出现中文乱码
            $.ajax({
                url:"${path}/manage/account/addAccount",
                data:submitData,
                dataType: 'JSON',
                async: false,//设置为同步传输
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        alert(data.msg);
                        //跳转到列表页
                        window.location.href = "${path}/manage/account/getAccountList";
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });

        $("#btn-cancel").click(function () {
            window.history.go(-1);
        });
    });
    $('#side-menu').siblings().removeClass("active");
    $('#system_management').addClass("active");
    $('#system_management4').addClass("active");

</script>