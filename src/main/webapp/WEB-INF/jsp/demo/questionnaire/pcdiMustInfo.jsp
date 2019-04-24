<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/7
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>pcdi必填问卷详情</title>
    <style>
        /* Local style for demo purpose */

        .lightBoxGallery {
            text-align: center;
        }

        .lightBoxGallery img {
            margin: 5px;
        }
         form > div {
             margin: 10px 0;
         }

        input, select, textarea, button {
            outline: none;
        }

        select {
            background: #fff;
            width: auto;
            height: 34px;
            min-width: 200px;
            border-radius: 0;
            -webkit-border-radius: 0;
            -moz-border-radius: 0;
            -khtml-border-radius: 0;
            -webkit-appearance: button;
            box-sizing: border-box;
            padding: 5px 10px;
            border: 1px solid #ccc;
        }

        input {
            height: 34px;
            padding-left: 10px;
            box-sizing: border-box;
            border: 1px solid #e5e6e7;
        }

        textarea {
            border: 1px solid #ccc;
            vertical-align: top;
        }

        input[type="radio"] {
            height: auto !important;
        }
        input[type="checkbox"] {
            vertical-align: middle;
           position: relative !important;
            margin-left: 0 !important;
        }

        button[type="button"] {
            height: 34px;
            width: 34px;
            padding-bottom: 1px;
            border: 1px solid #ccc;
            border-left: 0;
            font-size: 21px;
            vertical-align: bottom;
            cursor: pointer;
            margin-bottom: 10px;

        }

        button[type="button"]:active {
            background: #ccc;
            color: #fff;

        }
        #topicResult, input[name="topicResult"] {
            border-top: 1px solid #ccc;
            border-left: 1px solid #ccc;
            border-right: 0;
            border-bottom: 1px solid #ccc;
            margin-bottom: 10px;
        }
        .checkbox{
            font-size: 12px;
        }
        div>.checkboxH{
            display: none;

        }
        .checkboxS{
            display: block;
        }
    </style>
    <link href="${ctx}/css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp" %>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp" %>
    <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form action="" class="form-horizontal formDate" role="form">
                <legend>问卷>>添加/编辑题目</legend>
                <div class="form-group">
                    <label class="col-sm-2 control-label">问卷类型：</label>
                    <div class="col-sm-2 input-group">
                        <select name="nameEnum"  class="form-control">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${pcidType.nameEnum == 1}"> selected="selected"</c:if>>小孩怎么使用</option>
                            <option value="2" <c:if test="${pcidType.nameEnum == 2}"> selected="selected"</c:if>>句子和语句</option>
                            <option value="3" <c:if test="${pcidType.nameEnum == 3}"> selected="selected"</c:if>>组合句子</option>
                            <option value="4" <c:if test="${pcidType.nameEnum == 4}"> selected="selected"</c:if>>复杂性</option>
                            <option value="5" <c:if test="${pcidType.nameEnum == 5}"> selected="selected"</c:if>>词汇掌握前侧</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">题目描述：</label>
                    <div class="col-sm-2 input-group">
                        <input type="text" name="id" id="id" value="${pcdi.id}" hidden >
                        <input type="text" <c:if test="${pcidType.nameEnum != 5}"> value="${pcdi.describes}"</c:if> name="describes" placeholder="题目描述" class="form-control">
                    </div>
                </div>
                <div  <c:if test="${pcidType.nameEnum != 5}">style="display: none;"</c:if>  id="vabType" class="form-group">
                    <label class="col-sm-2 control-label">词汇类型：</label>
                    <div class="col-sm-2 input-group">
                        <select name="vabType"  class="form-control">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${pcidType.nameEnum == 5 && pcdi.type == '1'}"> selected="selected"</c:if>>名词</option>
                            <option value="2" <c:if test="${pcidType.nameEnum == 5 && pcdi.type == '2'}"> selected="selected"</c:if>>动词</option>
                            <option value="3" <c:if test="${pcidType.nameEnum == 5 && pcdi.type == '3'}"> selected="selected"</c:if>>形容词</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">题目序号：</label>
                    <div class="col-sm-2 input-group">
                        <input type="number" value="${pcdi.sort}" name="sort" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">问卷题目：</label>
                    <div class="col-sm-2 input-group">
                        <textarea id="topicTitle" name="topicTitle" cols="35" rows="5">${pcdi.topicTitle}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">是否记分：</label>
                    <div class="col-sm-2 input-group">
                        <input type="radio" class="score"  <c:if test="${ pcdi.isScore == '1'}">checked</c:if>  name="score" value="1">记分
                        <input type="radio" class="score" <c:if test="${ pcdi.isScore == '2'}">checked</c:if>   name="score" value="2">不记分
                    </div>
                </div>
                <div id="topicResultList" class="form-group">
                    <label class="col-sm-2 control-label">答案选项：</label>
                    <div class="col-sm-2 input-group">
                        <input type="text" id="topicResult"><button type="button" class="plus">+</button>
                    </div>
                    <c:forEach items="${answers}" var="item" varStatus="index" >
                        <div style="margin-left: 16.5%">
                            <c:if test="${pcidType.nameEnum == 3}"> <span class="checkbox">是否有子选项:<input type="checkbox"></span></c:if>
                            <input type="text" value="${item}" name="topicResult">
                            <button type="button" class="reduce">-</button>
                        </div>
                    </c:forEach>

                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-2 ">
                        <input id="submit" type="button" value="提交" name="submit">
                    </div>
                    <div class="col-sm-2 ">
                        <input id="toout" type="button" value="返回" name="">
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>
<script>

    $(function () {
        $('.plus').click(function () {

            var val = $('#topicResult').val();
            var Eval = $('select[name=nameEnum]').val()
            if (val.length > 100){
                alert("每个选项最多只能输入100个字符！")
                return
            }
            if (val != '') {
                var a= "";
                if (Eval != "3"){
                    a = "checkboxH";
                }
                var html = '<div style="margin-left: 16.5%"><span class="checkbox '+a+'">是否有子选项:<input type="checkbox"></span> <input type="text" value="'+val+'" name="topicResult"><button type="button" class="reduce">-</button></div>'
                $('#topicResultList').append(html);
                $('#topicResult').focus()
                $('#topicResult').val('')
            }
        });
        $('#topicResultList').delegate('.reduce','click',function () {
            var ind = $('.reduce').index($(this))
            $('#topicResultList>div').eq(ind+1).remove()
        })
        $('select[name=nameEnum]').change(function () {
            var val = $(this).val();
            if(val != '5'){
                $('#vabType').hide()
            }else{
                $('#vabType').show()
            }
            if(val != '3'){
                $('.checkbox').hide()
            }else{
                $('.checkbox').show()
            }
        })
        $('#submit').click(function() {
            var params = {};
            var arr = $('form').serializeArray();
            $.each(arr, function() {
                params[this.name] = this.value;
            });
            var n = [];
            var s = [];
            var flaseT = "1";
            $('input[name=topicResult]').each(function (i,item) {
                if ( $(this).val().length > 100){
                    alert("每个选项最多不能超过100个字符！")
                    flaseT ="0"
                    return
                }
                if ( $(this).val().length == ""){
                    alert("问题选项不能为空！")
                    flaseT ="0"
                    return
                }
                n.push($(this).val())
            });
            if ("0" == flaseT){
                return;
            }
            $('input[type=checkbox]').each(function (i,item) {
                if($(this).is(':checked')){
                    var ind = $('input[type=checkbox]').index($(this))+1
                    s.push(ind)
                }
            });
            params.isOtherOptions = s.join(',');
            params.topicResult = n.join('|');
            if(params.nameEnum != '5'){
                delete params.vabType
            }
            if(params.nameEnum != '3'){
                delete params.isOtherOptions
            }
            console.log(params);
            var topicTitle = $("#topicTitle").val();
            if (topicTitle.length > 10000){
                alert("问卷题目内容输入过多！");
                return
            }
            $.ajax({
                url:"${path}/manage/roll/addPcdi/Must",
                data: params,
                type:'POST',
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        alert(data.msg);
                        //跳转到列表页
                        window.location.href = "${path}/manage/roll/toPcdiList";
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });
    })

    $(document).ready(function () {
        $("#toout").click(function(){
            window.location.href = "${path}/manage/roll/toPcdiList";
        });
        $("#btn-cancel").click(function () {
            window.history.go(-1);
        });
    });
    //pcdi问卷类型
    function varietiesto1(){
        var typePcid = $("#pcidMustType").val();
        var chType = $("#type").val();
       if (5 != typePcid){
            if (chType != 4 && chType != 0){
                chType = $("#type").val(0);
                alert("只有词汇才能选择词汇类型！");
                return
            }
       }
    }
    //pcdi必做词汇类型
    function varietiesto2(){
        var typePcid = $("#pcidMustType").val();
        var chType = $("#type").val();
        if (typePcid == 0){
            chType = $("#type").val(0);
            alert("请先选择问卷类型！");
            return
        }
        if (typePcid != 5){
            if (chType != 4 && chType != 0){
                chType = $("#type").val(0);
                alert("只有词汇才能选择词汇类型！");
                return
            }
        }
    }
    $('#side-menu').siblings().removeClass("active");
    $('#system_management').addClass("active");
    $('#system_management4').addClass("active");


</script>
<script src="${ctx}/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>