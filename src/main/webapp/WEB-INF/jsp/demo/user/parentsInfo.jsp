
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>用户添加</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp"%>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp"%>
    <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form method="POST" class="form-horizontal" id="carouselFigure_form">
                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">手机号归属地:</label>
                    <div class="col-sm-2" >
                        <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                            <select id="qcellcoreId" class="form-control" <%--onchange="funCountiy(this)"--%> name="qcellcoreId">
                                <option value="">请选择手机号归属地</option>
                                <c:forEach items="${cityList}" var="item" >
                                        <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group" style="margin-bottom: 10px;"><label class="col-sm-2 control-label">手机号:</label>
                    <div class="col-sm-2" >
                        <input   class="form-control" type="text" id="phone" name="phone"  value="">
                    </div>
                </div>
                <div class="form-group" style="margin-bottom: 10px;"><label class="col-sm-2 control-label">密码:</label>
                    <div class="col-sm-2" >
                        <input  class="form-control"  type="text" id="password" name="password"  value="">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-2 ">
                        <button type="button" class="btn btn-primary btn-ok">确定</button>
                    </div>
                    <div class="col-sm-1 ">
                        <button type="button" class="btn btn-primary btn-out">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>
    //获取省
    function  funCountiy(e){
        alert(1);
        if (e.value == "0"){
            $("#qcellcoreId option:first").prop("selected", 'selected');
        }
    }

    $(document).ready(function () {
        //确定按钮
        $(".btn-out").click(function(){
            alert("确认取消吗？");
            window.location.href = "${path}/manage/user/toParentsPage";
        });

        //确定按钮
        $(".btn-ok").click(function(){
        /*    svs.updateElement();*/
            var qcellcoreId=$("#qcellcoreId").val();
            var phone=$("#phone").val();
            var password=$("#password").val();
            if ("" == qcellcoreId){
                alert("请选择手机号归属地！");
                return ;
            }
            if ("" == phone){
                alert("请输入有效的手机号！");
                return ;
            }
            if ("" == password){
                alert("密码不能为空！");
                return ;
            }
            $.ajax({
                url:"${path}/manage/user/parentsAdd",
                type: 'POST',
                dataType:'json',
                data:{
                    phone:phone,
                    qcellcoreId:qcellcoreId,
                    passwrod:password,

                },
                success: function (data) {
                    if (data.code == 200){
                        alert(data.msg);
                    }else {
                        alert(data.msg);
                    }
                    if(data.code==200){
                        window.location.href = "${path}/manage/user/toParentsPage";
                    }
                }
            });
        });
    });
    $('#side-menu').siblings().removeClass('active');
    $('#user_menu').addClass('active');
    $('#user_menu2').addClass('active');
</script>