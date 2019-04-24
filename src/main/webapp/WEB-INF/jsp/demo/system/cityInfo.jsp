
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>添加/城市信息</title>
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
                    <legend>城市管理>>添加/编辑城市<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">行政级别:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <select id="level" name = "level" class="form-control" onchange="funLevel(this)">
                                <option value="1" <c:if test="${xydArea.level == '1'}"> selected="selected"</c:if> >国家级别</option>
                                <option value="2" <c:if test="${xydArea.level == '2'}"> selected="selected"</c:if>>省级别</option>
                                <option value="3" <c:if test="${xydArea.level == '3'}"> selected="selected"</c:if>>市级别</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">上级地区:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <select id="parentid" name = "parentid" <c:if test="${xydArea.level=='1' || xydArea.areaid == null }"> disabled="disabled"</c:if>   class="form-control">
                                <option value="">选择上级地区</option>
                                <c:forEach items="${parentList}" var="item" >
                                    <option value="${item.areaid}" <c:if test="${item.areaid==xydArea.parentid }"> selected="selected"</c:if> >${item.areaname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">地区名称:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input name="areaid" id="areaid" hidden value="${xydArea.areaid}">
                            <input type="text" value="${xydArea.areaname}" id="areaname" name="areaname" class="form-control">
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
    function funLevel(th){
        if (th.value == null){
            return
        }
        if ( th.value== "1"){
            //类型置位不可选
            $("#parentid").find("option:first").prop("selected",true);
            $("#parentid").attr("disabled",true);
        }else {
            $("#parentid").attr("disabled",false);
            //动态获取数据
            $.ajax({
                url:"${path}/manage/system/getCityList",
                data:{
                    level:th.value -1,
                },
                dataType: 'JSON',
                type: 'POST',
                async: false,//设置为同步传输
                success: function (data) {
                    console.log(data);

                    if (data.code == 200) {
                        var list = data.data;
                        console.log(list)
                        $('#parentid').html("");
                        var html = " <option value=''>选择上级地区</option>"
                        $.each(list, function(i,item){
                            html += "<option value= '"+item.areaid+"'>"+item.areaname+"</option>";
                        });
                        $('#parentid').html(html);

                    } else {
                        alert(data.msg);
                    }
                }
            });
        }
    }

    $(document).ready(function () {
        $(".tofalse").click(function () {
            window.location.href = "${path}/manage/city/toCityList";
        });

        $(".totrue").click(function(){
            areaid = $("#areaid").val();
            level = $("#level").val();
            parentid = $("#parentid").val();
            areaname = $("#areaname").val();
            if (level == "1"){

            }else {
                if (parentid == ""){
                    alert("请选择上一级地区！");
                    return ;
                }
            }

            if (areaname == ""){
                alert("地区名称不能为空！");
                return ;
            }


            $.ajax({
                url:"${path}/manage/city/addCity",
                data:{
                    areaid:areaid,
                    level:level,
                    parentid:parentid,
                    areaname:areaname,

                },
                dataType: 'JSON',
                type: 'POST',
                async: false,//设置为同步传输
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        alert(data.msg);
                        //跳转到列表页
                        window.location.href = "${path}/manage/city/toCityList";
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
    $('#system_menu5').addClass("active");

</script>