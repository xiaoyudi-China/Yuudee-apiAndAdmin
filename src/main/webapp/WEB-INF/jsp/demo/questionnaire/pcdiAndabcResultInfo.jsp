
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>内容管理</title>
    <style>
        /* Local style for demo purpose */

        .lightBoxGallery {
            text-align: center;
        }

        .lightBoxGallery img {
            margin: 5px;
        }

    </style>
    <link href="${ctx}/static/css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp" %>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp" %>
    <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form class="form-horizontal" role="form">
                <fieldset>
                    <legend>问卷记录>>问卷记录详情<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>
                </fieldset>
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active" id="presentation1"><a>pcdi必做</a></li>
                    <li role="presentation" id="presentation2"><a>pcdi选做</a></li>
                    <li role="presentation" id="presentation3"><a>abc问卷</a></li>
                </ul>
            </form>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight" id="divTable1">
            <table class="table table-striped table-bordered table-hover dataTables-example" id="data-table1">

            </table>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight" id="divTable2" >
            <table class="table table-striped table-bordered table-hover dataTables-example" id="data-table2">

            </table>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight" id="divTable3" >
            <table class="table table-striped table-bordered table-hover dataTables-example" id="data-table3">

            </table>
        </div>
    </div>
</div>
</body>
</html>
<script>
        $("#btn-cancel").click(function () {
            window.history.go(-1);
        });
        $("#presentation1").click(function () {
            $("#presentation1").addClass('active');
            $("#presentation2").removeClass('active');
            $("#presentation3").removeClass('active');
            $("#divTable1").show();
            $("#divTable2").hide();
            $("#divTable3").hide();
            $dataTable.fnDraw();
        });

        $("#presentation2").click(function () {
            $("#presentation2").addClass('active');
            $("#presentation1").removeClass('active');
            $("#presentation3").removeClass('active');
            $("#divTable1").hide();
            $("#divTable2").show();
            $("#page-wrapper2").css('display','block');
            $("#divTable3").hide();

        });

        $("#presentation3").click(function () {
            $("#presentation3").addClass('active');
            $("#presentation2").removeClass('active');
            $("#presentation1").removeClass('active');
            $("#divTable1").hide();
            $("#divTable2").hide();
            $("#page-wrapper3").css('display','block');
            $("#divTable3").show();

        })


        //关于我们添加
        $(".bt-add").click(function(){
            var servicePhone = $("#servicePhone").val();
            var serviceId = $("#serviceId").val();
            var Weixin = $("#Weixin").val();
            var WeixinId = $("#WeixinId").val();
            var mailaddr = $("#mailaddr").val();
            var mailaddrId = $("#mailaddrId").val();
            var indexaddr = $("#indexaddr").val();
            var indexaddrId = $("#indexaddrId").val();

            $.ajax({
                url:"/manage/content/addphone",
                type: 'POST',
                data: {
                    servicePhone: servicePhone,
                    serviceId: serviceId,
                    Weixin: Weixin,
                    WeixinId: WeixinId,
                    mailaddr: mailaddr,
                    mailaddrId: mailaddrId,
                    indexaddr: indexaddr,
                    indexaddrId: indexaddrId,
                },
                dataType: 'JSON',
                async: false,//设置为同步传输
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        alert(data.msg);
                        // window.location.href = "/manage/account/getAccountList.ajax";
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });

    $('#side-menu').siblings().removeClass('active');
    $('#msq_Menu').addClass('active');
    $('#msq_Menu4').addClass('active');
</script>
<script src="${ctx}/static/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
