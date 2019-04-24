<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看图片</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp" %>
    <script src="${ctx}/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
    <style>
        /* Local style for demo purpose */
        body,html{
            margin: 0;
            padding: 0;
        }
        #box img{
            width: 15%;
            margin: 0 1.5%;
        }

        .lightBoxGallery img {
            margin: 5px;
        }

    </style>
    <link href="${ctx}/css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">

</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp" %>
    <div id="page-wrapper" class="gray-bg">

        <input type="hidden" id="images" value="${images}">
        <div style="margin-top: -20px;" id="box">
            <h2><b>查看图片</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button style="width: 100px;height: 50px" type="button" class="btn btn-cancel" id="btn-cancel">返回</button></h2>
        </div>
    </div>

</body>
</html>
<script>
    $(document).ready(function () {
        //取消按钮
        $("#btn-cancel").click(function(){
            window.history.go(-1);
        });
        var c = $("#images").val()
        console.log(c.split(","))
        var imgs = c.split(',');
        imgs.forEach(function (item) {
            if(item != ""){
                var img = document.createElement('img')
                var Box = document.getElementById('box')
                img.setAttribute('src',item)
                Box.appendChild(img)
            }
        })
    });

</script>

