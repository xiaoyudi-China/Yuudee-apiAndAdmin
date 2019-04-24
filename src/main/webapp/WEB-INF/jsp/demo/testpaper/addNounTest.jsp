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
    <title>添加名词测试</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp" %>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp" %>

    <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form class="form-horizontal" id="carouselFigure_form">
                <legend>名词管理>>添加/编辑名词测试
                    <button type="button" class="btn btn-success" style="margin-left: 20px;">
                    </button>
                </legend>
                <div class="form-group" id="imgDiv">
                    <label class="col-sm-2 control-label textColor">卡一颜色图:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" id="img" src="${nonuTest.cardColorImage}" width="160px"
                                     height="165px"/>
                                <input name="id" id="id" value="${nonuTest.id}" hidden>
                            </div>
                            <input class="file" type="file" name="cardOneImage"
                                   accept="image/gif, image/png, image/jpeg, image/bmp, image/jpg" id="cardOneImage"
                                   onchange="uploadHouseFile(this.id,'img')"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">卡一形容词</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="卡一形容词" value="${nonuTest.cardColorChar}" name="cardColorChar"
                               class="form-control" id="cardColorChar" style="width:300px;">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label textColor">卡一录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${nonuTest != null && nonuTest.cardColorRecord != null && nonuTest.cardColorRecord != ''}">
                                <audio id="audio" src="${nonuTest.cardColorRecord} " controls></audio>
                                <br/>
                            </c:if>
                            <input class="file" accept="audio/*" value="${nonuTest.cardColorRecord}" type="file" name="cardOneRecord"
                                   id="cardOneRecord"/>
                        </div>
                    </div>
                </div>

                <div class="form-group" id="imgDiv2">
                    <label class="col-sm-2 control-label textColor">卡二线框图:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" src="${nonuTest.cardWireImage}" id="img2" width="160px"
                                     height="165px"/>
                            </div>
                            <input class="file" type="file" name="cardTwoImage"
                                   accept="image/gif, image/png, image/jpeg, image/bmp, image/jpg" id="cardTwoImage"
                                   onchange="uploadHouseFile(this.id,'img2')"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">卡二名词</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="卡二名词" value="${nonuTest.cardWireChar}" name="cardWireChar"
                               class="form-control" id="cardWireChar" style="width:300px;">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label textColor">卡二文字录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${nonuTest != null && nonuTest.cardWireRecord != null && nonuTest.cardWireRecord != ''}">
                                <audio id="audio" src="${nonuTest.cardWireRecord} " controls></audio>
                                <br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" value="${nonuTest.cardWireRecord}" name="cardTwoRecord"
                                   id="cardTwoRecord"/>
                        </div>
                    </div>
                </div>

                <div class="form-group" id="imgDiv3">
                    <label class="col-sm-2 control-label textColor">组合图片:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" src="${nonuTest.groupImage}" id="img4" width="160px"
                                     height="165px"/>
                                <%--<input name="groupImage" id="groupImage" hidden>--%>
                            </div>
                            <input class="file" type="file" name="groupImage"
                                   accept="image/gif, image/png, image/jpeg, image/bmp, image/jpg" id="groupImage"
                                   onchange="uploadHouseFile(this.id,'img4')"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">组合文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="组合文字" value="${nonuTest.groupChar}" name="groupChar"
                               class="form-control" id="groupChar" style="width:300px;">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label textColor">组合录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <c:if test="${nonuTest != null && nonuTest.groupRecord != null && nonuTest.groupRecord != ''}">
                                <audio id="audio" src="${nonuTest.groupRecord} " controls></audio>
                                <br/>
                            </c:if>
                            <input class="file" accept="audio/*" type="file" value="${nonuTest.groupRecord}" name="groupRecord"
                                   id="groupRecord"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">辅助时长1:</label>
                    <div class="col-sm-4">
                        <input type="hidden" id="f1" value="${nonuTest.fristAssistTime}">
                        <input type="hidden" id="f2" value="${nonuTest.secondAssistTime}">
                        <select style="width: 100px" name="fristAssistTime" id="fristAssistTime">
                            <%--<option selected="selected" value="${nonuTest.fristAssistTime}">${nonuTest.fristAssistTime}</option>--%>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">辅助时长2:</label>
                    <div class="col-sm-4">
                        <select style="width: 100px" name="secondAssistTime" id="secondAssistTime">
                            <%--<option value="${nonuTest.secondAssistTime}">${nonuTest.secondAssistTime}</option>--%>
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
                $("#fristAssistTime").append("<option selected=\"selected\" value=\""+f1+"\">" + f1 + "</option>");
            } else {
                $("#fristAssistTime").append(" <option  value='" + i + "'>" + i + "</option>");
            }

            if (f2 == i) {
                $("#secondAssistTime").append("<option selected=\"selected\" value=\""+f1+"\">" + f2 + "</option>");
            } else {
                $("#secondAssistTime").append(" <option  value='" + i + "'>" + i + "</option>");
            }
        }

        //取消按钮
        $("#btn-cancel").click(function () {
            window.history.go(-1);
        });
        //确定按钮
        $("#btn-ok").click(function () {
            var formData = new FormData($('#carouselFigure_form')[0]);
            var id = $("#id").val()
            var url = ""
            if (id == null || id == "") {
                url = "${path}/manage/noun/addNounTest"
            } else {
                url = "${path}/manage/noun/updateNounTest"
            }
            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'json',
                data: formData,
                processData: false,  // 不处理数据
                contentType: false,  // 不设置内容类型
                success: function (response) {
                    console.log(response);
                    if (response.code == "200") {
                        alert("操作成功！")
                        window.location.href = "${path}/manage/noun/toNoumTestPage";
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
    $('#image_manage_Menu').addClass('active');
    $('#image_manage_Menu1 ').addClass('active');
</script>