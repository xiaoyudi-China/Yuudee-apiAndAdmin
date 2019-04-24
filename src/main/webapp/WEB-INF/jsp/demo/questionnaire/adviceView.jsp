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
    <title>查看建议</title>
    <style>
        /* Local style for demo purpose */

        .lightBoxGallery {
            text-align: center;
        }

        .lightBoxGallery img {
            margin: 5px;
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
            <form class="form-horizontal formDate"  role="form" >
                <fieldset>
                    <legend>查看建议>>编辑建议<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">问卷类型:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2">
                            <input name="id"  class="resultId" hidden value="${advice.id}">
                            <input name="id"  class="type" hidden value="${advice.type }">
                            <c:if test="${advice.type == 1}"> <span class="form-control">PCDI必填问卷</span></c:if>
                            <c:if test="${advice.type == 2}"> <span class="form-control">PCDI全部问卷</span></c:if>
                            <c:if test="${advice.type == 3}"> <span class="form-control">ABC问卷</span></c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">总分:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2">
                            <span class="form-control">${advice.score}</span>
                        </div>
                    </div>
                    <c:if test="${advice.type == 3}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">建议文案:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-3">
                            <textarea name="topicTitle" class="topicTitle" rows="5" cols="45" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5">${advice.title}</textarea>
                        </div>
                    </div>

                    </c:if>
                    <c:if test="${advice.type != 3}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">句子建议:</label>
                                <%--占七列 并且造一个input组--%>
                            <div class="col-sm-3">
                                <textarea name="sentenceTitle" class="sentenceTitle" rows="5" cols="45" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5">${advice.title}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">词汇建议:</label>
                                <%--占七列 并且造一个input组--%>
                            <div class="col-sm-3">
                                <textarea name="vocabulary" class="vocabulary" rows="5" cols="45" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5">${advice.vocabulary}</textarea>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>
                        <div class="col-sm-1 ">
                            <button type="button" class="btn btn-primary totrue">保存</button>
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
            window.location.href = "${path}/manage/roll/topcdiAndabc/resultPage";
        });
        //提交
        $(".totrue").click(function(){
            var type = $(".type").val();
            var id = $(".resultId").val();
            var topicTitle = $(".topicTitle").val();
            var sentenceTitle = $(".sentenceTitle").val();
            var vocabulary = $(".vocabulary").val();
            if (type == "3"){
                if (topicTitle == null){
                    alert("建议内容不能为空！");
                    return
                }
            }else {
                if (sentenceTitle.length == null || vocabulary.length == null){
                    alert("输入的内容不能为空！");
                    return
                }
            }
            $.ajax({
                url:"${path}/manage/roll/topcdiAndabc/adviceupdate",
                data:{
                    id:id,
                    topicTitle:topicTitle,
                    sentenceTitle:sentenceTitle,
                    vocabulary:vocabulary,
                },
                dataType: 'JSON',
                async: false,//设置为同步传输
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        alert(data.msg);
                        //跳转到列表页
                        window.location.href = "${path}/manage/roll/topcdiAndabc/resultPage";
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
    $('#msq_Menu3').addClass("active");


</script>
<script src="${ctx}/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>