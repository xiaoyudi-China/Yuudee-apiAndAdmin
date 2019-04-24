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
    <title>介绍编辑</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp"%>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp"%>
    <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form method="POST" class="form-horizontal" id="carouselFigure_form">
                <%--<div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">版本号:</label>
                    <div class="col-sm-4" >
                        <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 0px;">
                            <select id="versionId" name = "versionId" class="form-control">
                                <option value="0" >请选择版本号</option>
                                <c:forEach items="${versionList}" var="item" >
                                    <option value="${item.id}" <c:if test="${introduce.versionsId==item.id }"> selected="selected"</c:if> >${item.number}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>--%>
                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">类型:</label>
                    <div class="col-sm-4" >
                        <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 0px;">
                            <select id="type" name = "type"  class="form-control">
                                <option value="0">介绍类型</option>
                                    <option value="1" <c:if test="${introduce.type=='1' }"> selected="selected"</c:if>  >产品介绍</option>
                                    <option value="2" <c:if test="${introduce.type=='2'  }"> selected="selected"</c:if>  >产品详情</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group" style="margin-bottom: 20px;"><label class="col-sm-2 control-label">标题:</label>
                    <div class="col-sm-4" >
                        <input  hidden  type="text" id="id" name="id"  value="${introduce.id}">
                        <input class="form-control" type="text" id="title" name="title"  value="${introduce.title}">
                    </div>
                </div>

                <div class="form-group" style="padding-bottom: 10px;"><label class="col-sm-2 control-label">介绍内容:</label>
                    <div class="col-sm-10" > <textarea name="content" class="editor" id="#editor" >${introduce.content}</textarea></div>
                </div>
                <div class="form-group pull-left" style="margin-left:28%;margin-top: 0px;">
                    <button type="button" class="btn btn-lg btn-success" id="btn-ok">保存</button>
                </div>
                <div class="form-group pull-left" style="margin-left:230px;margin-top: 0px;">
                    <button type="button" class="btn btn-lg btn-success" id="btn-out">取消</button>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>
<script>
    $(document).ready(function () {
        var svs = CKEDITOR.replace('#editor',{ height: '300px', width: '800px' });
        //取消
        $("#btn-out").click(function () {
            window.location.href = "${path}/manage/system/toIntroduceList";
        });
        //确定按钮
        $("#btn-ok").click(function(){
            svs.updateElement();
            var editor=$(".editor").val();

            $.ajax({
                url:"${path}/manage/system/toIntroduceAdd",
                type: 'POST',
                dataType:'json',
                data: $('#carouselFigure_form').serialize(),
                success: function (data) {
                    if (data.code == 200){
                        alert(data.msg);
                    }else {
                        alert(data.msg);
                    }
                    if(data.code==200){
                        window.location.href = "${path}/manage/system/toIntroduceList";
                    }
                }
            });
        });
    });
    $('#side-menu').siblings().removeClass('active');
    $('#system_menu').addClass('active');
    $('#system_menu3').addClass('active');
</script>