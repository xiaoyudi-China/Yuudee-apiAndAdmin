
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>添加/编辑角色</title>
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
                    <legend>角色>>添加/编辑角色<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色名称:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-3 input-group">
                            <input name="id"  hidden value="${eecManageRole.id}">
                            <input type="text" name="roleName" id="roleName" value="${eecManageRole.roleName}" class="form-control">
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色说明:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-3 input-group">
                            <input type="text" id="remark" value="${eecManageRole.remark}" name="remark" class="form-control">
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色唯一标识:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-3 input-group">
                            <input type="text" name="role" value="${eecManageRole.role}"  class="form-control">
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色状态:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-3 input-group">
                            <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 0px;">
                                <input type="text" hidden value="${eecManageRole.status}" id="statusHidd">
                                <select class="form-control" id="status" name="status">
                                    <option value="0">正常使用</option>
                                    <option value="1">暂停使用</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色权限:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-6 input-group" style="word-spacing:5px">
                            <c:forEach items="${accessList}" var="access">
                                   <input type="checkbox" class="checkboxList parent" name="roleId"  value="${access.id}" >${access.accessName}<span>:&nbsp;<br></span>
                                <c:forEach items="${access.submenu}" var="acce">
                                    <span id="ss">
                                       &nbsp;&nbsp; <input type="checkbox" class="checkboxList child" onclick="childOnclick(this)" name="roleId"  value="${acce.id}" >${acce.accessName}<span>:&nbsp;</span>
                                        <c:forEach items="${acce.submenu}" var="va" varStatus="i">
                                           <%-- <c:if test="${i.index == 0}"><br></c:if>--%>
                                             <span id="ss1">
                                                &nbsp;&nbsp;<input type="checkbox" class="checkboxList child"  name="roleId"  value="${va.id}" >${va.accessName}<span>&nbsp;</span>
                                             </span>
                                        </c:forEach>
                                         <br>
                                    </span>
                                </c:forEach>
                                <br>
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

    function childOnclick(checkbox){
        if ( checkbox.checked == true){

        }else {

        }
    }

    $(document).ready(function () {

        $(".tofalse").click(function () {
            window.location.href = "${path}/manage/roleAccess/toRoleListPage";
        });
        $("#status").val($("#statusHidd").val());
        var get = $(".checkboxList");
        $.ajax({
            url:"${path}/manage/roleAccess/eecManageRoleList",
            data:{
                id:'${eecManageRole.id}'
            },
            dataType: 'JSON',
            async: false,//设置为同步传输
            success: function (result) {
                console.log(result);
                if (result.code == 200) {
                    var test = $("input[name='roleId']");
                    var list =  result.data.accesses;
                    test.each(function(){
                        for(var i = 0; i< list.length; i++){
                            if (list[i].id == $(this).val()){
                                $(this).attr("checked", 'checked');
                            }
                        }
                    });
                }
            }
        });

        $(".totrue").click(function(){
            var roleName = $("#roleName").val();
            if (roleName.length == 0){
                alert("请填写角色名称！");
                return;
            }
            var remark = $("#remark").val();
            if (remark.length == 0){
                alert("请填写角色说明！");
                return;
            }
            var status = $("#status").val();
            if (status == null){
                alert("请选择角色使用状态！");
                return;
            }

            var data=$(".formDate").serialize();
            var get = $(".checkboxList");
            var access = "";
            for (i = 0 ; i < get.length; i++) {
                if (get[i].checked) {
                    access +=get[i].value+ "," ;
                }
            }

            if (access.length == 0){
                alert("您还没有选择分配的权限！");
                return;
            }
            data +="&access="+access;

            //序列化获得表单数据，结果为：user_id=12&user_name=John&user_age=20   出现乱码情况
            var submitData=decodeURIComponent(data,true);
            //submitData是解码后的表单数据，结果同上 不会出现中文乱码
            $.ajax({
                url:"${path}/manage/roleAccess/addorUpdateRole",
                data:submitData,
                dataType: 'JSON',
                type:"POST",
                async: false,//设置为同步传输
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        alert(data.msg);
                        //跳转到列表页
                        window.location.href = "${path}/manage/roleAccess/toRoleListPage";
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
    $('#account_Menu_Menu0').addClass("active");


</script>