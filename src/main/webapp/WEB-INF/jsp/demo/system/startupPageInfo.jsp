
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>启动页/编辑启动页</title>
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
                    <legend>启动页>>添加/编辑启动页<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">说明:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 input-group">
                            <input name="id"  id="systemImageId" hidden value="${systemImage.id}">
                            <input type="text" value="${systemImage.title}" name="title" id="title" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图片:</label>
                        <%--占七列 并且造一个input组--%>
                        <div class="col-sm-2 ">
                            <form  class="uploadForm" enctype="multipart/form-data">
                                本地目录： <input type="file" accept="image/*" name="image" id="FileUpload">
                                <div class="lightBoxGallery">
                                    <a href="${systemImage.image}" id="a_href_url" title="启动页图片"  data-gallery="">
                                        <img src="${systemImage.image}" alt="" id="a_href_img" height="50px" id="imgc"  width="50px"></a>
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
                        <label class="col-sm-2 control-label"></label>
                        <div class="col-sm-2 ">
                            <button type="button" class="btn btn-primary totrue">确定</button>
                        </div>

                        <div class="col-sm-1 ">
                            <button type="button" class="btn btn-primary tofalse" href="#">取消</button>
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

    $(".tofalse").click(function () {
        window.location.href = "${path}/manage/system/toStrartPage";
    });

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
        var id = $("#systemImageId").val();
        var title= $("#title").val()
        var image =$("#a_href_url").attr("href");
        $.ajax({
            url:"${path}/manage/system/strartPageAdd",
            data:{
                id:id,
                title:title,
                image:image,
            },
            dataType: 'JSON',
            type: 'POST',
            async: false,//设置为同步传输
            success: function (data) {
                console.log(data);
                if (data.code == 200) {
                    alert(data.msg);
                    //跳转到列表页
                    window.location.href = "${path}/manage/system/toStrartPage";
                } else {
                    alert(data.msg);
                }
            }
        });
    });

    $('#side-menu').siblings().removeClass("active");
    $('#system_menu').addClass("active");
    $('#system_menu2').addClass("active");

</script>
<script src="${ctx}/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>