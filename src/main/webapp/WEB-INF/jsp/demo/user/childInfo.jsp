<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/14
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
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
            <form method="POST" class="form-horizontal formDate" id="carouselFigure_form">
                <legend>用户管理>>编辑信息<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>
                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">昵称:</label>

                    <div class="col-sm-3" >
                        <div style="width: 200px;display: inline-block;margin: 0px 10px 0px 0px;">
                            <input hidden type="text" id="id" name="id"  value="${child.id}">
                            <input class="form-control" type="text" id="name" name="name"  value="${child.name}">
                        </div>
                    </div>
                </div>
                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">性别:</label>
                    <div class="col-sm-3" >
                        <div style="width: 200px;display: inline-block;margin: 0px 10px 0px 0px;">
                            <select id="sex" class="form-control"  name="sex">
                                <option value="">请选择性别</option>
                                <option value="0"  <c:if test="${child.sex=='0' }"> selected="selected"</c:if> >男</option>
                                <option value="1"  <c:if test="${child.sex == '1'}"> selected="selected"</c:if>>女</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">出生日期:</label>
                    <div class="col-sm-4" >
                        <div id="data_1" style="width: 210px;display: inline-block;margin: 0px 10px 0px 0px;vertical-align: top;">
                            <div class='input-group date' id='datetimepicker1'>
                                <input type='date' class="form-control" name="birthTime" id="birthdate" value="<fmt:formatDate value='${child.birthdate}' pattern='yyyy-MM-dd'/>" />
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">居住地:</label>
                    <div class="col-sm-4" >
                        <div style="width: 200px;display: inline-block;margin: 0px 10px 0px 0px;">
                            <select id="countiy" class="form-control" onchange="funCountiy(this)" name="countiy">
                                <option value="0">请选择国家</option>
                                <c:forEach items="${countiyList}" var="item" >
                                    <option value="${item.areaid}"  <c:if test="${child.countiy == item.areaname }"> selected="selected"</c:if>>${item.areaname}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="provinceList" style="width: 120px;display: none;margin: 0px 0px 0px 10px;">
                            <select id="province" class="form-control" onchange="funProvince(this)" name="province">
                                <option value="0">请选择省</option>
                            </select>
                        </div>
                        <div class="cityList" style="width: 120px;display:none;margin: 0px 20px 0px 10px;">
                            <select id="city" class="form-control" name="city">
                                <option value="0">请选择城市</option>

                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">医学诊断:</label>
                    <div class="col-sm-4" >
                        <div style="width: 200px;display: inline-block;margin: 0px 10px 0px 0px;">
                            <select id="medical" class="form-control" onchange="funmedical(this)" name="medical">
                                <option value="">请选择医学诊断</option>
                                <c:forEach items="${medicalList}" var="item" >
                                    <option value="${item.value}"  <c:if test="${child.medical==item.value }"> selected="selected"</c:if>>${item.desc}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group medicalStateDiv" style="margin-bottom: 20px;display: none"><label class="col-sm-2 control-label">请选择严重程度:</label>
                    <div class="col-sm-3" >
                        <label>轻</label>
                        <input class="medicalState" type="radio" name="medicalState" <c:if test="${child.medicalState=='1'}">checked="checked"</c:if> value="1">
                        <label>中</label>
                        <input class="medicalState" type="radio" name="medicalState" <c:if test="${child.medicalState=='2'}">checked="checked"</c:if> value="2">
                        <label>重</label>
                        <input class="medicalState" type="radio" name="medicalState" <c:if test="${child.medicalState=='3'}">checked="checked"</c:if> value="3">
                        <label>不清楚</label>
                        <input class="medicalState" type="radio" name="medicalState" <c:if test="${child.medicalState=='4'}">checked="checked"</c:if>  value="4">
                    </div>
                </div>

                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">第一语言:</label>
                    <div class="col-sm-4" >
                        <div style="width: 200px;display: inline-block;margin: 0px 10px 0px 0px;">
                            <select id="firstLanguage" class="form-control" onchange="funfirstLanguage(this)" name="firstLanguage">
                                <option value="">请选择第一语言</option>
                                <c:forEach items="${lanageList}" var="item" >
                                    <option value="${item.value}" <c:if test="${child.firstLanguage==item.value}"> selected="selected"</c:if>>${item.desc}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group firstRests"  style="margin-bottom: 20px;display: none"><label class="col-sm-2 control-label">请输入其他语言:</label>
                    <div class="col-sm-2" >
                        <input  class="form-control" type="text" id="firstRests" name="firstRests"  value="${child.firstRests}">
                    </div>
                </div>


                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">第二语言:</label>
                    <div class="col-sm-4" >
                        <div style="width: 200px;display: inline-block;margin: 0px 10px 0px 0px;">
                            <select id="secondLanguage" class="form-control" onchange="funsecondRests(this)" name="secondLanguage">
                                <option value="">请选择第二语言</option>
                                <c:forEach items="${lanageList}" var="item" >
                                    <option value="${item.value}" <c:if test="${child.secondLanguage==item.value}"> selected="selected"</c:if>>${item.desc}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group secondRests" style="margin-bottom: 20px;display: none"><label class="col-sm-2 control-label">请输入其他语言:</label>
                    <div class="col-sm-2" >
                        <input  class="form-control" type="text" id="secondRests" name="secondRests"  value="${child.secondRests}">
                    </div>
                </div>

                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">父亲文化程度:</label>
                    <div class="col-sm-4" >
                        <div style="width: 200px;display: inline-block;margin: 0px 10px 0px 0px;">
                            <select id="fatherCulture" class="form-control" name="fatherCulture">
                                <option value="">请选择父亲文化程度</option>
                                <c:forEach items="${EducationList}" var="item" >
                                    <option value="${item.value}" <c:if test="${child.fatherCulture==item.value}"> selected="selected"</c:if>>${item.desc}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">母亲文化程度:</label>
                    <div class="col-sm-4" >
                        <div style="width: 200px;display: inline-block;margin: 0px 10px 0px 0px;">
                            <select id="motherCulture" class="form-control" name="motherCulture">
                                <option value="">请选择母亲文化程度</option>
                                <c:forEach items="${EducationList}" var="item" >
                                    <option value="${item.value}" <c:if test="${child.motherCulture==item.value}"> selected="selected"</c:if>>${item.desc}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">目前训练及疗法:</label>
                    <div class="col-sm-4" >
                        <div style="width: 200px;display: inline-block;margin: 0px 10px 0px 0px;">
                            <select id="trainingMethod" class="form-control" onchange="funtrainingMethod(this)" name="trainingMethod">
                                <option value="">请选择目前尝试的训练及疗法</option>
                                <c:forEach items="${trainingList}" var="item" >
                                    <option value="${item.value}" <c:if test="${child.trainingMethod==item.value}"> selected="selected"</c:if>>${item.desc}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group trainingMethod" style="margin-bottom: 20px;display: none"><label class="col-sm-2 control-label">请输入其他训练方法:</label>
                    <div class="col-sm-2" >
                        <input  class="form-control" type="text" id="trainingRests" name="trainingRests"  value="${child.trainingRests}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-1 ">
                        <button type="button" class="btn btn-primary btn-success" id="btn-ok">确定</button>
                    </div>
                    <div class="col-sm-1 ">
                        <button type="button" class="btn btn-primary btn-out" id="btn-out">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>
    //医学诊断选中时间
    function funmedical(a) {
        if (a.value == "0"){
            $(".medicalStateDiv").attr("style","display:block;");//显示div
        }else {
            $(".medicalStateDiv").attr("style","display:none;");//显示div
        }
    }
    //第一语言
    function funfirstLanguage(b) {
        if (b.value == "10"){
            $(".firstRests").attr("style","display:block;");//显示div
        }else {
            $(".firstRests").attr("style","display:none;");//显示div
        }
    }
    //第二语言
    function funsecondRests(c) {
        if (c.value == "10"){
            $(".secondRests").attr("style","display:block;");//显示div
        }else {
            $(".secondRests").attr("style","display:none;");//显示div
        }
    }
    //训练方式
    function funtrainingMethod(d) {
        if (d.value == "2"){
            $(".trainingMethod").attr("style","display:block;");//显示div
        }else {
            $(".trainingMethod").attr("style","display:none;");//显示div
        }
    }
    //获取省
    function  funCountiy(e){
        if (e.value == "0"){
            $(".provinceList").attr("style","width: 120px;display: none;margin: 0px 0px 0px 10px;");//显示div
            return;
        }
        $.ajax({
            url:"${path}/manage/system/getCityList",
            type: 'GET',
            dataType:'JSON',
            data:{
                parentId:e.value,
                level:2,
            },
            success: function (data) {
                if (data.code == 200){
                    var str = '<select id="province" class="form-control" onchange="funProvince(this)" name="province">'
                     str += '<option value="0">请选择省</option>';
                    $.each(data.data,function(i,value) {
                        str +=  '<option value="'+value.areaid+'">'+value.areaname+'</option>';
                    });
                    str +='</select>'
                    $("#province").html(str);
                    console.log(str)
                    $(".provinceList").attr("style","width: 120px;display: inline-block;margin: 0px 0px 0px 10px;");//显示div
                }else {
                    alert(data.msg);
                }
            }
        });
    }

    //获取城市
    function  funProvince(e){
        if (e.value == "0"){
            $(".cityList").attr("style","width: 120px;display: none;margin: 0px 0px 0px 10px;");//显示div
            return;
        }
        $.ajax({
            url:"${path}/manage/system/getCityList",
            type: 'GET',
            dataType:'JSON',
            data:{
                parentId:e.value,
                level:3,
            },
            success: function (data) {
                if (data.code == 200){
                    var str = '<option value="0">请选择城市</option>';
                    $.each(data.data,function(i,value) {
                        str +=  '<option value="'+value.areaid+'">'+value.areaname+'</option>';
                    });
                      $("#city").html(str);
                      $(".cityList").attr("style","width: 120px;display: inline-block;margin: 0px 0px 0px 10px;");//显示div
                }else {
                    alert(data.msg);
                }
            }
        });
    }

    $(document).ready(function () {
        //确定按钮
        $("#btn-out").click(function(){
            window.location.href = "${path}/manage/user/toChildPage";
            });

        $("#btn-cancel").click(function () {
            window.history.go(-1);
        });

        //时间选择器
        $(function () {
            $('#datetimepicker1').datetimepicker({
                format: 'YYYY-MM-DD'
            });
        });

        //确定按钮
        $("#btn-ok").click(function(){
            var formNode=$("#carouselFigure_form")[0];
            var modelData=new FormData(formNode);
            console.log(modelData);
            //序列化获得表单数据，结果为：user_id=12&user_name=John&user_age=20   出现乱码情况
//            var submitData=decodeURIComponent(data,true);
//
           /*  svs.updateElement();*/
           var countiy =  $("#countiy").val();
           var province = $("#province").val();
           var city = $("#city").val();
          /* if (countiy == "0" || city == "0" || province == "0" || province == null || city == null || countiy == null){
               alert("居住城市不能为空！")
               return;
           }*/
            $.ajax({
                url:"${path}/manage/user/childUpdate",
                type: 'POST',
                processData:false,
                contentType:false,
                dataType:'json',
                data:modelData ,
                success: function (data) {
                    if (data.code == 200){
                        alert(data.msg);
                    }else {
                        alert(data.msg);
                    }
                    if(data.code==200){
                        window.location.href = "${path}/manage/user/toChildPage";
                    }
                }
            });
        });
    });
    $('#side-menu').siblings().removeClass('active');
    $('#user_menu').addClass('active');
    $('#user_menu3').addClass('active');
</script>