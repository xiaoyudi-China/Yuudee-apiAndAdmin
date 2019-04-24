<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户详情统计</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp" %>
    <script src="${ctx}/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
    <style>

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
        <div class="container-fluid">
            <div class="row" style="margin-left: 10px;margin-top: 20px;">
                <div style="line-height: 34px;display: inline-block;">
                    <label>昵称</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <input class="form-control" placeholder="请输入昵称" type="text" id="name" name="name">
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>居住地</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="countiy" name="countiy"  class="form-control">
                            <option value="">请选择国家</option>

                        </select>
                    </div>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="province" name="province"  class="form-control">
                            <option value="">请选择省</option>

                        </select>
                    </div>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="city" name="city"  class="form-control">
                            <option value="">请选择市</option>

                        </select>
                    </div>
                </div>
                <br>
                <div style="line-height: 34px;display: inline-block;">
                    <label>儿童性别</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="sex" name="sex"  class="form-control">
                            <option value="">请选择性别</option>
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>手机号</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <input class="form-control" placeholder="请输入手机号" type="text" id="phoneNumber" name="phoneNumber">
                    </div>
                </div>
                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-success bt-search">查询</button>
                </div>
            </div>
            <div class="wrapper wrapper-content animated fadeInRight">
                <table class="table table-striped table-bordered table-hover dataTables-example" id="data-table">
                </table>
            </div>
        </div>
    </div>

</body>
</html>
<script>
    $(document).ready(function () {
        var name;
        var birthdate;
        var sex;
        var phoneNumber;
        var countiy;
        var province;
        var city;
        getCountiyList();
        var conf_dt = {
            paging: true,//分页
            ordering: false,//是否启用排序
            searching: false,//搜索
            //lengthMenu: [25, 50],
            scrollX: "100%",
            displayLength: 10,
            language: {
                lengthMenu: '<div class="dataTables_length" id="data-table_length"><label>展示<select name="data-table_length" aria-controls="data-table" class="">' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="100">100</option>' + '</select>条记录</laber></div></div>',
//                + '<button class="sendEmail btn btn-success" style="margin-left: 10px">发送邮件</button></div>',
                search: '<span class="label label-success"></span>',//右上角的搜索文本，可以写html标签
                paginate: {//分页的样式内容。
                    previous: "上一页",
                    next: "下一页",
                    first: "第一页",
                    last: "最后"
                },
                zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
                //下面三者构成了总体的左下角的内容。
                info: "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
                infoEmpty: "0条记录",//筛选为空时左下角的显示。
                infoFiltered: "",//筛选之后的左下角筛选提示，
            },
            paging: true,
            pagingType: "full_numbers",//分页样式的类型
            columns: [
                {
                    sTitle: '序号',
                    data: null,
                    className: 'text-center whiteSpace',
                    render: function (data, type, row, meta) {
                        return meta.row + 1 +
                            meta.settings._iDisplayStart;
                    }
                },
                {
                    "title": "ID",
                    "type": "html",
                    "data": "id",
                    "defaultContent": "",
                    "name": "id"
                },
                {
                    "title": "儿童昵称",
                    "type": "html",
                    "data": "name",
                    "defaultContent": "",
                    "name": "name"
                },
                {
                    "title": "儿童性别",
                    "type": "html",
                    "data": "sex",
                    "defaultContent": "",
                    "name": "sex",
                    "render": function (data, type, full, meta) {
                       if(data=="0"){
                           return "男"
                       }
                       if (data =="1"){
                           return "女"
                       }
                    }
                },
                {
                    "title": "出生日期",
                    "type": "html",
                    "data": "birthdate",
                    "defaultContent": "",
                    "name": "birthdate",
                    "render": function (data, type, full, meta) {
                        return timestampToTime(data/1000);
                    }
                },{
                    "title": "居住地",
                    "type": "html",
                    "data": "countiy",
                    "defaultContent": "",
                    "name": "countiy",
                    "render": function (data, type, full, meta) {
                        var province = full["province"];
                        var city = full["city"];
                        if (province == null) {
                            province = "-";
                        }
                        if (city == null) {
                            city = "-";
                        }
                        if (data == null) {
                            data = "-";
                        }
                        return data + "/" + province + "/" + city;
                    }},
                {
                    "title": "手机号",
                    "type": "html",
                    "data": "phoneNumber",
                    "defaultContent": "",
                    "name": "phoneNumber"
                },{
                    "title": "操作",
                    "type": "num",
                    "data": "id",
                    "defaultContent": "",
                    "name": "id",
                    "render": function (data, type, full, meta) {
                        var ret =
                            '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-lookInformation" id="' + data +
                            '">查看信息</button>'+
                            '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-lookHistory" id="' + data +
                            '">查看历史</button>';
                        return ret;
                    }
                }
            ],
            serverSide: true,
            ajax: {
                "url": "${path}/manage/trainTest/getUserDetailList",
                "data": function (d) {
                    d.name = name;
                    d.birthdate = birthdate;
                    d.sex = sex;
                    d.phoneNumber = phoneNumber;
                    d.countiy = countiy;
                    d.province = province;
                    d.city = city;
                }
            },
            "fnDrawCallback": function (obj) {

            },
            index: 0
        };
        var $dataTable = $('#data-table').dataTable(conf_dt);

        //查看学习信息
        $dataTable.on('click',".btn-lookInformation",function () {
            var id = $(this).attr("id");
            window.location.href = "${path}/manage/study/toUserStudyInfo?id=" + id;
        });

        //查看学习历史
        $dataTable.on('click',".btn-lookHistory",function () {
            var id = $(this).attr("id");
            window.location.href = "${path}/manage/trainTest/toTrainingResults?userId=" + id;
        });
        //查询
        $('.bt-search').click(function () {
            birthdate = $("#birthdate").val();
            sex = $("#sex").val();
            name = $("#name").val();
            countiy = $("#countiy").val();
            province = $("#province").val();
            city = $("#city").val();
            phoneNumber = $("#phoneNumber").val();
            $dataTable.fnDraw();
        });
    });
    function getCountiyList(){
        $.ajax({
            type:"GET",
            url: "${path}/app/system/getCityList",
            dataType: "json",
            data: {level:1},
            success:function(data){
                var html = '';
                html += '<option value="'+ '' +'" >'+ '请选择国家' +'</option>';
                $(data.data).each(function(index, value) {
                    html += '<option value="'+ value.areaid +'" >'+ value.areaname +'</option>';
                });
                getProvinceList(data.data[0].areaid)
                $("#countiy").html(html);
            }
        });
    }
    $("#countiy").change(function(){
        var opt=$("#countiy").val();
        if(opt != ''){
            getProvinceList(opt)
        }
    });
    function getProvinceList(data){
        $.ajax({
            type:"GET",
            url: "${path}/app/system/getCityList",
            dataType: "json",
            data: {level:2,parentId:data},
            success:function(data){
                var html = '';
                html += '<option value="'+ '' +'" >'+ '请选择省' +'</option>';
                if(data.data.length>0){
                    $(data.data).each(function(index, value) {
                        html += '<option value="'+ value.areaid +'" >'+ value.areaname +'</option>';
                    });
                    getCityList(data.data[0].areaid)
                }
                $("#province").html(html);
            }
        });
    }
    $("#province").change(function(){
        var opt=$("#province").val();
        if(opt != ''){
            getCityList(opt)
        }
    });
    function getCityList(data){
        $.ajax({
            type:"GET",
            url: "${path}/app/system/getCityList",
            dataType: "json",
            data: {level:3,parentId:data},
            success:function(data){
                var html = '';
                html += '<option value="'+ '' +'" >'+ '请选择市' +'</option>';
                if(data.data.length>0){
                    $(data.data).each(function(index, value) {
                        html += '<option value="'+ value.areaid +'" >'+ value.areaname +'</option>';
                    });
                }
                $("#city").html(html);
            }
        });
    }
    $('#side-menu').siblings().removeClass('active');
    $('#workbench_menu').addClass('active');
    $('#workbenchmenu2').addClass('active');
</script>

