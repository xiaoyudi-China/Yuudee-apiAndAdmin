
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>添加/编辑版本信息</title>
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
                    <legend>版本管理>>添加/编辑版本<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">版本号:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input name="id" id="id" hidden value="${version.id}">
                            <input type="text" name="number" id="number" value="${version.number}" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">版本类型:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <select id="type" name = "type"   class="form-control">
                                <option value="">选择类型</option>
                                <option value="1" <c:if test="${version.type == '1'}"> selected="selected"</c:if> >android</option>
                                <option value="2" <c:if test="${version.type == '2'}"> selected="selected"</c:if>>ios</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">版本说明:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input type="text" value="${version.title}" id="title" name="title" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">下载地址链接:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input type="text" value="${version.download}" id="download" name="download" class="form-control">
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
            window.location.href = "${path}/manage/system/toVersionPage";
        });

        $(".totrue").click(function(){
            //手机号验证
            var number = $("#number").val();
            var type = $("#type").val();
            var download = $("#download").val();
            var title = $("#title").val();
            var id = $("#id").val();
            if (number.number <= 0){
                alert("版本号不能为空！");
                return;
            }
            if (download.download <= 0){
                alert("下载地址不能为空！");
                return;
            }
            if (type == null){
                alert("请选择版本类型！");
                return;
            }
            $.ajax({
                url:"${path}/manage/system/versionAdd",
                data:{
                    id:id,
                    type:type,
                    title:title,
                    download:download,
                    number:number
                },
                dataType: 'JSON',
                type: 'POST',
                async: false,//设置为同步传输
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        alert(data.msg);
                        //跳转到列表页
                        window.location.href = "${path}/manage/system/toVersionPage";
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
    $('#system_menu').addClass("active");
    $('#system_menu1').addClass("active");

</script>