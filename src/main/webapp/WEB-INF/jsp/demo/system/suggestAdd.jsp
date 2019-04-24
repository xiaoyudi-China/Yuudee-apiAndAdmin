
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>添加/编辑建议反馈信息</title>
    <style>
        /* Local style for demo purpose */

        .lightBoxGallery {
            text-align: center;
        }

        .lightBoxGallery img {
            margin: 5px;
        }
        .versionsId2{
            display: none;
        }
        .versionsId1{
            display: none;
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
            <form class="form-horizontal formDate" role="form">
                <fieldset>
                    <legend>建议管理>>添加/编辑反馈信息<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">客服手机号:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input name="id" id="id" hidden value="${suggest.id}">
                            <input type="text" name="phone" id="phone" value="${suggest.phone}" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">网站地址:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input type="text" name="network" id="network" value="${suggest.network}" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">移动端类型:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <select id="type" class="form-control" onchange="typeFunction(this)" name="type">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${suggest.type =='1'}"> selected="selected"</c:if> >android</option>
                                <option value="2" <c:if test="${suggest.type =='2'}"> selected="selected"</c:if> >ios</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group versionsId1">
                        <label class="col-sm-2 control-label">版本号:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <select id="versionsId1" class="form-control" name="versionsId1">
                                <option value="0">请选择版本号</option>
                                <c:forEach items="${versionList1}" var="item" >
                                    <option value="${item.id}" <c:if test="${suggest.versionsId==item.id }"> selected="selected"</c:if> >${item.number}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group versionsId2">
                        <label class="col-sm-2 control-label">版本号:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <select id="versionsId2" class="form-control" name="versionsId1">
                                <option value="0">请选择版本号</option>
                                <c:forEach items="${versionList2}" var="item" >
                                    <option value="${item.id}" <c:if test="${suggest.versionsId==item.id }"> selected="selected"</c:if> >${item.number}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">图片:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 ">
                            <form  class="uploadForm" enctype="multipart/form-data">
                                本地目录： <input type="file"  accept="image/*" name="image" id="FileUpload">
                                <div class="lightBoxGallery">
                                    <a href="${suggest.weixin}" id="a_href_url" title="启动页图片"  data-gallery="">
                                        <img src="${suggest.weixin}" alt="" id="a_href_img" height="50px" id="imgc"  width="50px"></a>
                                    <div id="blueimp-gallery" class="blueimp-gallery">
                                        <div class="slides"></div>
                                        <h3 class="title"></h3>
                                        <a class="prev">‹</a>
                                        <a class="next">›</a>
                                        <a class="close">×</a>
                                        <a class="play-pause"></a>
                                        <ol class="indicator"></ol>
                                    </div>
                                </div>
                                <input type="button" class="upload" value="上传图片"/></form>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">qq群号码:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input type="text" value="${suggest.qqun}" id="qqun" name="qqun" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱地址:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input type="text" value="${suggest.mail}" id="mail" name="mail" class="form-control">
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
    function typeFunction(th){
        if (th.value == null || th.value == ""){
            return
        }
        if ( th.value== "1"){
            //类型置位不可选
            $(".versionsId2").attr("style","display:none;");
            $(".versionsId1").attr("style","display:block;");
        }else {
            $(".versionsId1").attr("style","display:none;");
            $(".versionsId2").attr("style","display:block;");
        }
    }

    $(document).ready(function () {
        $(".tofalse").click(function () {
            window.location.href = "${path}/manage/system/toSuggestPage";
        });

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

        $(".upload").click(function(){
            var fileObj = document.getElementById("FileUpload").files[0]; // js 获取文件对象
            if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
                alert("请选择图片");
                return;
            }
            var formFile = new FormData();
            formFile.append("image", fileObj); //加入文件对象
            console.log(formFile);
            $.ajax({
                url:"${path}/manage/system/oss/fileUpload",
                data:formFile,
                cache: false,
                dataType: 'JSON',
                type : "post",
                async: false,//设置为同步传输
                processData: false,
                contentType : false,
                success: function (data) {
                    console.log(data);
                    if (data.data.images != null) {
                        alert("上传成功！");
                        $('#a_href_url').attr('href', data.data.images[0] != null ? data.data.images[0] : "");
                        $('#a_href_img').attr('src', data.data.images[0] != null ? data.data.images[0] : "");
                    }else {
                        alert("上传失败")
                    }
                }
            });
        });


        $(".totrue").click(function(){
            //手机号验证
            var phone = $("#phone").val();
            var qqun = $("#qqun").val();
            var network = $("#network").val();
            var id = $("#id").val();
            var weixin =$("#a_href_url").attr("href");
            var type = $("#type").val();
            var versionsId1 = $("#versionsId1").val();
            var versionsId2 = $("#versionsId2").val();
            var mail = $("#mail").val();
            var versionsId = null;

            if (phone == "" || phone == null){
                alert("客服手机号不能为空！");
                return;
            }
            if (network == "" || network == null ){
                alert("网站地址不能为空！");
                return;
            }
            if (type == ""){
                alert("请选择移动端类型！")
                return ;
            }
            if (type == "1"){
                if (versionsId1 == "0" || versionsId1 == null){
                    alert("版本号不能为空！");
                    return;
                }
                versionsId = versionsId1;
            }else {
                if (versionsId2 == "0" || versionsId2 == null){
                    alert("版本号不能为空！");
                    return;
                }
                versionsId = versionsId2;
            }

            if (mail == "" || mail == null ){
                alert("邮箱地址不能为空！");
                return;
            }
            $.ajax({
                url:"${path}/manage/system/insertSuggest",
                data:{
                    id:id,
                    phone:phone,
                    qqun:qqun,
                    network:network,
                    weixin:weixin,
                    versionsId:versionsId,
                    mail:mail,
                },
                dataType: 'JSON',
                type: 'POST',
                async: false,//设置为同步传输
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        alert(data.msg);
                        //跳转到列表页
                        window.location.href = "${path}/manage/system/toSuggestPage";
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
    $('#system_menu1').addClass("active");
</script>
<script src="${ctx}/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>