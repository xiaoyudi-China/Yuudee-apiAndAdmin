
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>添加/编辑文案</title>
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
                    <legend>建议>>添加/编辑文案<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">问卷类型:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input name="id" id="id"  hidden value="${word.id}">
                            <select class="form-control"  id="type" onchange="funtopType(this)" name="type">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${word.type == '1'}"> selected="selected"</c:if>>pcdi问卷</option>
                                <option value="2" <c:if test="${word.type == '2'}"> selected="selected"</c:if>>abc问卷</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否选做:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <select class="form-control" id="isOptional"  <c:if test="${word.type == '2'}">disabled="disabled"</c:if>  onchange="funsOption(this)" name="isOptional">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${word.isOptional == '1'}"> selected="selected"</c:if>>必做</option>
                                <option value="2" <c:if test="${word.isOptional == '2'}"> selected="selected"</c:if>>选做</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">题型:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <select class="form-control"  <c:if test="${word.type == '2'}">disabled="disabled"</c:if>  onchange="funstopType(this)" id="topType" name="topType">
                                <option value="">请选择</option>
                                <option value="4" <c:if test="${word.topicType == '4'}"> selected="selected"</c:if>>D:复杂性</option>
                                <option value="5" <c:if test="${word.topicType == '5'}"> selected="selected"</c:if>>E:词汇掌握前测</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <select class="form-control" id="sex" name="sex">
                                <option value="">请选择</option>
                                <option value="0" <c:if test="${word.sex == '0'}"> selected="selected"</c:if>>男孩</option>
                                <option value="1" <c:if test="${word.sex == '1'}"> selected="selected"</c:if>>女孩</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">儿童月龄范围:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <select class="form-control" id="more" name="more">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${word.more == '1'}"> selected="selected"</c:if>>小于${defaultAgeMin}个月</option>
                                <option value="2" <c:if test="${word.more == '2'}"> selected="selected"</c:if>>${defaultAgeMin}-${defaultAgeMax}个月内</option>
                                <option value="3" <c:if test="${word.more == '3'}"> selected="selected"</c:if>>大于${defaultAgeMax}个月</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">起始值:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input type="text" name="state" value="${word.state}" id="state"  class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">结束值:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input type="text" name="end" value="${word.end}"   id="end" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">建议文案:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-3">
                            <textarea name="sentenceTitle" class="sentenceTitle" rows="5" cols="45" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5">${word.content}</textarea>
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
    var type = $("#type").val();
    var isOptional = $("#isOptional").val();
    var topType = $("#topType").val();
    var sex = $("#sex").val();
    var more = $("#more").val();
   if (type == "2"){
       //把是否选做和类型置位不可选
       $("#isOptional").find("option:first").prop("selected",true);
       $("#topType").find("option:first").prop("selected",true);
       $("#isOptional").attr("disabled",true);
       $("#topType").attr("disabled",true);
       $("#sex").find("option:first").prop("selected",true);
       $("#sex").attr("disabled",true);
       $("#more").find("option:first").prop("selected",true);
       $("#more").attr("disabled",true);
   }
   if (isOptional == "2"){
       //类型置位不可选
       $("#topType").find("option:first").prop("selected",true);
       $("#topType").attr("disabled",true);
       $("#sex").find("option:first").prop("selected",true);
       $("#sex").attr("disabled",true);
   }
   if (topType == 5){
       $("#sex").find("option:first").prop("selected",true);
       $("#sex").attr("disabled",true);
       $("#more").find("option:first").prop("selected",true);
       $("#more").attr("disabled",true);
   }
    function funtopType(th){
        if (th.value == null){
            return
        }
        if ( th.value== "2"){
            //把是否选做和类型置位不可选
            $("#isOptional").find("option:first").prop("selected",true);
            $("#topType").find("option:first").prop("selected",true);
            $("#isOptional").attr("disabled",true);
            $("#topType").attr("disabled",true);
            $("#sex").find("option:first").prop("selected",true);
            $("#sex").attr("disabled",true);
            $("#more").find("option:first").prop("selected",true);
            $("#more").attr("disabled",true);
        }else {
            $("#isOptional").attr("disabled",false);
            $("#topType").attr("disabled",false);
            $("#sex").attr("disabled",false);
            $("#more").attr("disabled",false);
        }
    }
    function funsOption(th){
        if (th.value == null){
            return
        }
        if ( th.value== "2"){
            //类型置位不可选
            $("#topType").find("option:first").prop("selected",true);
            $("#topType").attr("disabled",true);
            $("#sex").find("option:first").prop("selected",true);
            $("#sex").attr("disabled",true);
        }else {
            $("#topType").attr("disabled",false);
            $("#sex").attr("disabled",false);
        }
    }
    function funstopType(th){
        if (th.value == null){
            return
        }
        if ( th.value== "5"){
            $("#sex").find("option:first").prop("selected",true);
            $("#sex").attr("disabled",true);
            $("#more").find("option:first").prop("selected",true);
            $("#more").attr("disabled",true);
        }else {
            $("#sex").attr("disabled",false);
            $("#more").attr("disabled",false);
        }
    }

    $(document).ready(function () {
        $(".tofalse").click(function () {
            window.location.href = "${path}/manage/roll/toWordListPage";
        });

        $(".totrue").click(function(){
            var id = $("#id").val();
            var type = $("#type").val();
            var topType = $("#topType").val();
            var sex = $("#sex").val();
            var isOptional = $("#isOptional").val();
            if (type == null || type == ""){
                alert("请选择问卷类型！");
                return;
            }
            if (type == "1" && isOptional == "1"){
                if (topType == "" || topType == "null"){
                    alert("请选择题型！");
                    return ;
                }
                /*else {
                    if (topType == "4" && sex == ""){
                        alert("请选择性别！");
                        return;
                    }
                }*/
            }
            var content = $(".sentenceTitle").val();
            if (content == null || content.length == 0){
                alert("建议内容不能为空！");
                return;
            }
            var state = $("#state").val();
            var end = $("#end").val();
            if (state == null || end == null || state == "" || end ==""){
                alert("请输入分值范围！");
                return;
            }
            function isNumber( s ){
                var regu = "^[0-9]+$";
                var re = new RegExp(regu);
                if (s.search(re) != -1) {
                    return true;
                } else {
                    return false;
                }
            }
            if (!isNumber(state)){
                layer.msg("起始值输入不合法，只能输入纯数字！", {icon:1,time:2000});
                return ;
            }
            if (!isNumber(end)){
                layer.msg("结束值输入不合法，只能输入纯数字！", {icon:1,time:2000});
                return ;
            }
            if (end*1 < state*1){
                alert("请输入合理的分值范围！");
                return;
            }
            var more = $("#more").val();
           /* if (more == "" || more == null){
                alert("请选择儿童月龄！");
                return;
            }*/


            $.ajax({
                url:"${path}/manage/roll/wordAdd",
                data:{
                    id:id,
                    type:type,
                    sex:sex,
                    state:state,
                    end:end,
                    content:content,
                    isOptional:isOptional,
                    topicType:topType,
                    more:more
                },
                dataType: 'JSON',
                type:"POST",
                async: false,//设置为同步传输
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        layer.msg(data.msg, {icon:1,time:1000});
                        //跳转到列表页
                        window.location.href = "${path}/manage/roll/toWordListPage";
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
    $('#msq_Menu').addClass("active");
    $('#msq_Menu4').addClass("active");


</script>