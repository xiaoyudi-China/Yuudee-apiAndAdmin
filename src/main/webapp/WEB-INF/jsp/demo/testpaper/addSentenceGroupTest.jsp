
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
    <title>添加句子成组测试</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp"%>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp"%>

    <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form class="form-horizontal" id="carouselFigure_form">
                <input type="hidden" name="id" id="id" value="${sentenceGroupTest.id}">
                <legend>句子成组管理>>添加/编辑句子成组测试
                    <button type="button" class="btn btn-success"  style="margin-left: 20px;">
                    </button>
                </legend>
                <div class="form-group" id="imgDiv5">
                    <label class="col-sm-2 control-label textColor">轮播图:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" id="img5" src="${ctxBOSS}/images/fileUploadIcon.jpeg" width="160px" height="165px"/>
                                <%--<input name="cardColorImage" id="cardColorImage" hidden>--%>
                            </div>
                            <input class="file" multiple type="file" name="startSlideshow" accept="image/gif, image/png, image/jpeg, image/bmp, image/jpg" id="startSlideshow" onchange="uploadHouseFile(this.id,'img5')"/>
                        </div>
                    </div>
                </div>
                <div class="form-group" id="imgDiv">
                    <label class="col-sm-2 control-label textColor">卡一图片:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" id="img" src="${sentenceGroupTest.cardOneImage}/" width="160px" height="165px"/>
                                <%--<input name="cardColorImage" id="cardColorImage" hidden>--%>
                            </div>
                            <input class="file" type="file" name="cardOneImage" accept="image/gif, image/png, image/jpeg, image/bmp, image/jpg" id="cardOneImage" onchange="uploadHouseFile(this.id,'img')"/>
                        </div>
                    </div>
                </div>
                <div class="form-group" >
                    <label class="col-sm-2 control-label"  >卡一文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="卡一文字" value="${sentenceGroupTest.cardOneChar}" name="cardOneChar" class="form-control" id="cardOneChar" style="width:300px;">
                    </div>
                </div>
                <div class="form-group"  >
                    <label class="col-sm-2 control-label textColor">卡一录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${sentenceGroupTest != null && sentenceGroupTest.cardOneRecord != null && sentenceGroupTest.cardOneRecord != ''}">
                                <audio id="audio" src="${sentenceGroupTest.cardOneRecord} " controls ></audio><br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" name="cardOneRecord" id="cardOneRecord" />
                        </div>
                    </div>
                </div>

                <div class="form-group" id="imgDiv2">
                    <label class="col-sm-2 control-label textColor">卡二图片:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" src="${sentenceGroupTest.cardTwoImage}"  id="img2" width="160px" height="165px"/>
                            </div>
                            <input class="file" multiple type="file" name="cardTwoImage" accept="image/gif, image/png, image/jpeg, image/bmp, image/jpg" id="cardTwoImage" onchange="uploadHouseFile(this.id,'img2')"/>
                        </div>
                    </div>
                </div>
                <div class="form-group"  >
                    <label class="col-sm-2 control-label"  >卡二文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="卡二名词" value="${sentenceGroupTest.cardTwoChar}" name="cardTwoChar" class="form-control" id="cardTwoChar" style="width:300px;">
                    </div>
                </div>

                <div class="form-group"  >
                    <label class="col-sm-2 control-label textColor">卡二录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${sentenceGroupTest != null && sentenceGroupTest.cardTwoRecord != null && sentenceGroupTest.cardTwoRecord != ''}">
                                <audio id="audio" src="${sentenceGroupTest.cardTwoRecord} " controls ></audio><br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" name="cardTwoRecord" id="cardTwoRecord" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label"  >组合文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="组合文字" value="${sentenceGroupTest.groupChar}" name="groupChar" class="form-control" id="groupChar" style="width:300px;">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label textColor">组合录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${sentenceGroupTest != null && sentenceGroupTest.groupRecord != null && sentenceGroupTest.groupRecord != ''}">
                                <audio id="audio" src="${sentenceGroupTest.groupRecord} " controls ></audio><br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" name="groupRecord" id="groupRecord" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">辅助时长1:</label>
                    <div class="col-sm-4">
                        <input type="hidden" id="f1" value="${sentenceGroupTest.cardOneTime}">
                        <input type="hidden" id="f2" value="${sentenceGroupTest.cardTwoTime}">
                        <select style="width: 100px" name="cardOneTime" id="cardOneTime">
                            <%--<option value="${sentenceGroupTest.cardOneTime}">${sentenceGroupTest.cardOneTime}</option>--%>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">辅助时长2:</label>
                    <div class="col-sm-4">
                        <select style="width: 100px" name="cardTwoTime" id="cardTwoTime">
                            <%--<option value="${sentenceGroupTest.cardTwoTime}">${sentenceGroupTest.cardTwoTime}</option>--%>
                        </select>
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
        var f1 = $("#f1").val()
        var f2 = $("#f2").val()
        for (var i = 0; i < 61; i++) {
            if (f1 == i) {
                $("#cardOneTime").append("<option selected=\"selected\" value=\""+f1+"\">" + f1 + "</option>");
            } else {
                $("#cardOneTime").append(" <option  value='" + i + "'>" + i + "</option>");
            }

            if (f2 == i) {
                $("#cardTwoTime").append("<option selected=\"selected\" value=\""+f1+"\">" + f2 + "</option>");
            } else {
                $("#cardTwoTime").append(" <option  value='" + i + "'>" + i + "</option>");
            }
        }

        //取消按钮
        $("#btn-cancel").click(function(){
            window.history.go(-1);
        });
        //确定按钮
        $("#btn-ok").click(function(){
            var formData = new FormData($('#carouselFigure_form')[0]);
            var id = $("#id").val()
            var url = ""
            console.log(id)
            if (id == null || id=="") {
                url = "${path}/manage/sentence/addSentenceGroupTest"
            } else {
                url = "${path}/manage/sentence/updateSentenceGroupTest"
            }
            alert(url)
            $.ajax({
                url:url,
                type: 'POST',
                dataType:'json',
                data:formData,
                processData: false,  // 不处理数据
                contentType: false ,  // 不设置内容类型
                success: function (response) {
                    if(response.code == "200"){
                        alert("添加成功！")
                        window.location.href = "${path}/manage/sentence/toSentenceGroupTestPage";
                    }else{
                        alert(response.msg)
                    }
                },
                fail:function (e) {
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
    $('#image_manage_Menu2').addClass('active');
    $('#image_manage_Menu6').addClass('active');
</script>