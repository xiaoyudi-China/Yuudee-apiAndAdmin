
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
    <title>添加名词训练</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp"%>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp"%>

    <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form class="form-horizontal" id="carouselFigure_form">
                <legend>名词管理>>添加/编辑名词训练
                    <%--<button type="button" class="btn btn-success"  style="margin-left: 20px;">--%>
                    <%--</button>--%>
                </legend>
                <div class="form-group" id="imgDiv">
                    <label class="col-sm-2 control-label textColor">线框图片:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <c:if test="${nonuTraining.wireImage != null &&nonuTraining.wireImage != ''}">
                                    <img class="img-result img" id="img" src="${nonuTraining.wireImage}" width="160px" height="165px"/>
                                </c:if>
                                <c:if test="${nonuTraining.wireImage == null || nonuTraining.wireImage == ''}">
                                    <img class="img-result img" id="img" src="${path}/images/fileUploadIcon.jpeg" width="160px" height="165px"/>
                                </c:if>
                                <input name="id" id="id" type="hidden" value="${nonuTraining.id}">
                            </div>
                            <input class="file" type="file" name="wireImage" accept="image/gif, image/png, image/jpeg, image/bmp, image/jpg" id="wireImage" onchange="uploadHouseFile(this.id,'img')"/>
                        </div>
                    </div>
                </div>
                <div class="form-group"  >
                    <label class="col-sm-2 control-label textColor">线框图录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${nonuTraining != null && nonuTraining.wireRecord != null && nonuTraining.wireRecord != ''}">
                                <audio id="audio" src="${nonuTraining.wireRecord} " controls ></audio><br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" value="${nonuTraining.wireRecord}" name="wireRecord" id="wireRecord" />
                        </div>
                    </div>
                </div>

                <div class="form-group" >
                    <label class="col-sm-2 control-label"  >线框图文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="线框图文字" value="${nonuTraining.wireChar}" name="wireChar" class="form-control" id="wireChar" style="width:300px;">
                    </div>
                </div>

                <div class="form-group" >
                    <label class="col-sm-2 control-label"  >颜色笔文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="颜色笔文字" value="${nonuTraining.colorPenChar}" name="colorPenChar" class="form-control" id="colorPenChar" style="width:300px;">
                    </div>
                </div>

                <div class="form-group"  >
                    <label class="col-sm-2 control-label textColor">颜色笔录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${nonuTraining != null && nonuTraining.colorPenRecord != null && nonuTraining.colorPenRecord != ''}">
                                <audio id="audio" src="${nonuTraining.colorPenRecord} " controls ></audio><br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" value="${nonuTraining.colorPenRecord}" name="colorPenRecord" id="colorPenRecord" />
                        </div>
                    </div>
                </div>

                <div class="form-group" id="imgDiv2">
                    <label class="col-sm-2 control-label textColor">组合图片:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <c:if test="${nonuTraining.groupImage != null &&nonuTraining.groupImage != ''}">
                                    <img class="img-result img" id="img2" src="${nonuTraining.groupImage}" width="160px" height="165px"/>
                                </c:if>
                                <c:if test="${nonuTraining.groupImage == null || nonuTraining.groupImage == ''}">
                                    <img class="img-result img" id="img2" src="${path}/images/fileUploadIcon.jpeg" width="160px" height="165px"/>
                                </c:if>
                            </div>
                            <input class="file" type="file" name="groupImage" accept="image/gif, image/png, image/jpeg, image/bmp, image/jpg" id="groupImage" onchange="uploadHouseFile(this.id,'img2')"/>
                        </div>
                    </div>
                </div>

                <div class="form-group" >
                    <label class="col-sm-2 control-label"  >组合文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="组合文字" value="${nonuTraining.groupWord}" name="groupWord" class="form-control" id="groupWord" style="width:300px;">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label textColor">组合录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${nonuTraining != null && nonuTraining.groupRecord != null && nonuTraining.groupRecord != ''}">
                                <audio id="audio" src="${nonuTraining.groupRecord} " controls ></audio><br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" value="${nonuTraining.groupRecord}" name="groupRecord" id="groupRecord" />
                        </div>
                    </div>
                </div>

                <div class="form-group pull-left" style="margin-left:110px;">
                    <button type="button" class="btn btn-success" id="btn-ok">确认添加</button>
                    <button type="button" class="btn btn-success" id="btn-cancel">取消</button>
                </div>
            </form>

        </div>
    </div>

</div>
</body>
</html>
<script>
    <%--$(document).ready(function () {--%>
        //取消按钮
        $("#btn-cancel").click(function(){
            window.history.go(-1);
        });

        //确定按钮
        $("#btn-ok").click(function(){
            var formData = new FormData($('#carouselFigure_form')[0]);
            var id = $("#id").val()
            var url = ""
            if (id == null || id=="") {
                url = "${path}/manage/noun/addNounTraining"
            } else {
                url = "${path}/manage/noun/updateNounTraining"
            }
            $.ajax({
                url:url,
                type: 'POST',
                dataType:'json',
                data:formData,
                processData: false,  // 不处理数据
                contentType: false ,  // 不设置内容类型
                success: function (response) {
                    console.log(response);
                    if(response.code == "200"){
                        alert(response.msg)
                        window.location.href = "${path}/manage/noun/toNoumTrainingPage";
                    }else{
                        alert(response.msg)
                    }
                },
                fail:function (e) {
                    console.log(e)
                }
            });
//        });
    });

    //图片预览
    function getFileUrl(sourceId) {
        var url;
        if (window.ActiveXObject || "ActiveXObject" in window) { // IE
            url = document.getElementById(sourceId).value;
        } else if (navigator.userAgent.indexOf("Firefox") > 0) { // Firefox
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
        } else if (navigator.userAgent.indexOf("Chrome") > 0) { // Chrome
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
        }
        return url;
    }
    function uploadHouseFile(sourceId, targetId) {
        var url = getFileUrl(sourceId);
        var imgPre = document.getElementById(targetId);
        if (imgFormat(sourceId)) {
            imgPre.src = url;
        }
    }
    //图片格式校验
    function imgFormat(nodeId) {
        if ($("#" + nodeId).val() != "") {
            var imgFileName = "jpg,png,jpeg";
            var imgName = $("#" + nodeId).val();
            if (imgFileName.indexOf(imgName.substring(imgName.lastIndexOf(".") + 1, imgName.length).toLowerCase()) != -1) {
                return true;
            } else {
                alert("只支持格式为" + imgFileName + "的图片！");
                return false;
            }
        } else if ($("input[name='license']").val() != "" || $("input[name='shopLogo']").val() != ""
            || $("input[name='shopImages']").val() != "" && $("#" + nodeId).val() == "") {
            return true;
        } else {
            alert("请上传图片！");
            return false;
        }
    }

    $('#side-menu').siblings().removeClass('active');
    $('#image_Menu').addClass('active');
    $('#image_manage_Menu').addClass('active');
    $('#image_manage_Menu').addClass('active');
</script>