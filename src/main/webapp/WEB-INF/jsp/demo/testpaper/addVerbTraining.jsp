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
    <title>添加动词训练</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp" %>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp" %>

    <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form class="form-horizontal" id="carouselFigure_form">
                <input type="hidden" id="id" name="id" value="${verbTraining.id}">
                <legend>名词管理>>添加/编辑动词训练
                    <button type="button" class="btn btn-success" style="margin-left: 20px;">
                    </button>
                </legend>
                <div class="form-group" id="imgDiv">
                    <label class="col-sm-2 control-label textColor">轮播图:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" id="img" src="${verbTraining.startSlideshow}"
                                     width="160px" height="165px"/>
                            </div>
                            <input class="file" multiple type="file" name="startSlideshow"
                                   accept="image/gif, image/png, image/jpeg, image/bmp, image/jpg" id="startSlideshow"
                                   onchange="uploadHouseFile(this.id,'img')"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">动词类型:</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="verbType" name="verbType">
                            <option value="1" <c:if test="${verbTraining.verbType == 1}"> selected ="selected" </c:if>>吃</option>
                            <option value="2" <c:if test="${verbTraining.verbType == 2}"> selected ="selected" </c:if>>喝</option>
                            <option value="3" <c:if test="${verbTraining.verbType == 3}"> selected ="selected" </c:if>>玩</option>
                            <option value="4" <c:if test="${verbTraining.verbType == 4}"> selected ="selected" </c:if>>洗</option>
                            <option value="5" <c:if test="${verbTraining.verbType == 5}"> selected ="selected" </c:if>>穿</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="线框图文字" value="${verbTraining.verbChar}" name="verbChar" class="form-control" id="verbChar"
                               style="width:300px;">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label textColor">文字录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${verbTraining != null && verbTraining.verbRecord != null && verbTraining.verbRecord != ''}">
                                <audio id="audio" src="${verbTraining.verbRecord} " controls ></audio><br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" name="verbRecord" id="verbRecord"/>
                        </div>
                    </div>
                </div>
                <div class="form-group" id="imgDiv2">
                    <label class="col-sm-2 control-label textColor">卡片:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" src="${verbTraining.cardImage}" id="img2"
                                     width="160px" height="165px"/>
                            </div>
                            <input class="file" type="file" name="cardImage"
                                   accept="image/gif, image/png, image/jpeg, image/bmp, image/jpg" id="cardImage"
                                   onchange="uploadHouseFile(this.id,'img2')"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">卡片文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="卡片文字" value="${verbTraining.cardChar}" name="cardChar" class="form-control" id="cardChar"
                               style="width:300px;">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label textColor">卡片录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${verbTraining != null && verbTraining.cardRecord != null && verbTraining.cardRecord != ''}">
                                <audio id="audio" src="${verbTraining.cardRecord} " controls ></audio><br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" name="cardRecord" id="cardRecord"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">组合文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="组合文字" value="${verbTraining.groupChar}" name="groupChar" class="form-control" id="groupChar"
                               style="width:300px;">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label textColor">组合录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${verbTraining != null && verbTraining.groupRecord != null && verbTraining.groupRecord != ''}">
                                <audio id="audio" src="${verbTraining.groupRecord} " controls ></audio><br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" name="groupRecord" id="groupRecord"/>
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
    $(document).ready(function () {

        //取消按钮
        $("#btn-cancel").click(function () {
            window.history.go(-1);
        });

        //确定按钮
        $("#btn-ok").click(function () {
            var formData = new FormData($('#carouselFigure_form')[0]);
            var formData = new FormData($('#carouselFigure_form')[0]);
            var id = $("#id").val()
            var url = ""
            if (id == null || id=="") {
                url = "${path}/manage/verb/addVerbTraining"
            } else {
                url = "${path}/manage/verb/updateVerbTraining"
            }
            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'json',
                data: formData,
                processData: false,  // 不处理数据
                contentType: false,  // 不设置内容类型
                success: function (response) {
                    if (response.code == "200") {
                        alert(response.msg)
                        window.location.href = "${path}/manage/verb/toVerbTrainingPage";
                    } else {
                        alert(response.msg)
                    }
                },
                fail: function (e) {
                    console.log(e)
                }
            });
        });
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
    $('#image_manage_Menu1').addClass('active');
    $('#image_manage_Menu4').addClass('active');
</script>