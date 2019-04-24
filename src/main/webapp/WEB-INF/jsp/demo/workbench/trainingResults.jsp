<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看学习历史</title>
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
                    <label>课件名称</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <input type="hidden" value="${userId}" id ="userId" name="userId">
                        <input class="form-control" placeholder="请输入课件名称" type="text" id="name" name="name">
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>模块</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="module" name="module" class="form-control">
                            <option value="">请选择模块</option>
                            <option value="1">名词</option>
                            <option value="2">动词</option>
                            <option value="3">句子组成</option>
                            <option value="4">句子分解</option>
                        </select>
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>场景</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="scene" name="scene" class="form-control">
                            <option value="">请选择场景</option>
                        </select>
                    </div>
                </div>
                <br>
                <div style="line-height: 34px;display: inline-block;">
                    <label>是否通过</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="pass" name="pass" class="form-control">
                            <option value="">请选择测试级是否通过</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>错误类型</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="errorType" name="errorType" class="form-control">
                            <option value="">请选择错误类型</option>
                            <option value="1">干扰形容词</option>
                            <option value="2">干扰名词</option>
                            <option value="3">预选形容词</option>
                            <option value="4">预选名词</option>
                        </select>
                    </div>
                </div>


                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-success bt-search">查询</button>
                    <button  style="margin-left: 15px" type="button" class="btn btn-cancel" id="btn-cancel">返回</button>
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
        var module;
        var scene;
        var userId = $("#userId").val();
        console.log(userId)
        var pass;
        var errorType;
        var conf_dt = {
            paging: true,//分页
            ordering: false,//是否启用排序
            searching: false,//搜索
            //lengthMenu: [25, 50],
            scrollX: "100%",
            displayLength: 10,
            language: {
                lengthMenu: '<div class="dataTables_length" id="data-table_length"><label>展示<select name="data-table_length" aria-controls="data-table" class="">' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="100">100</option>' + '</select>条记录</laber></div></div>'
                +'<p><b>查看学习历史</b></p>',
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
                    "title": "课件名称",
                    "type": "html",
                    "data": "name",
                    "defaultContent": "",
                    "name": "name"
                },
                {
                    "title": "学习开始时间",
                    "type": "html",
                    "data": "startTime",
                    "defaultContent": "",
                    "name": "startTime",
                    "render": function (data, type, full, meta) {
                        return timestampToTime(data/1000);
                    }
                },
                {
                    "title": "学习结束时间",
                    "type": "html",
                    "data": "endTime",
                    "defaultContent": "",
                    "name": "endTime",
                    "render": function (data, type, full, meta) {
                        return timestampToTime(data/1000);
                    }
                },
                {
                    "title": "停留时间",
                    "type": "html",
                    "data": "stayTime",
                    "defaultContent": "",
                    "name": "stayTime",
                    "render": function (data, type, full, meta) {
                        return data+"s";
                    }
                },
                {
                    "title": "模块",
                    "type": "html",
                    "data": "module",
                    "defaultContent": "",
                    "name": "module",
                    "render": function (data, type, full, meta) {
                        if(data=="1"){
                            return "名词"
                        }else if(data=="2"){
                            return "动词"
                        }
                        else if(data=="3"){
                            return "句子成组"
                        }
                        else if(data=="4"){
                            return "句子分解"
                        }
                    }
                },{
                    "title": "场景",
                    "type": "html",
                    "data": "scene",
                    "defaultContent": "",
                    "name": "scene",
                    "render": function (data, type, full, meta) {
                        if(data=="1"){
                            return "训练"
                        }else if(data=="2"){
                            return "测试"
                        }else if(data=="3"){
                            return "意义"
                        }
                    }
                },
                {
                    "title": "是否通过",
                    "type": "html",
                    "data": "pass",
                    "defaultContent": "",
                    "name": "pass",
                    "render": function (data, type, full, meta) {
                        if(data=="1"){
                            return "是"
                        }else {
                            return "----"
                        }
                    }
                },{
                    "title": "干扰课件",
                    "type": "html",
                    "data": "disturbName",
                    "defaultContent": "",
                    "name": "disturbName",
                    "render": function (data, type, full, meta) {
                        if(data!="" && data != null){
                            return data
                        }else {
                            return "无"
                        }
                    }
                },{
                    "title": "错误类型",
                    "type": "html",
                    "data": "errorType",
                    "defaultContent": "",
                    "name": "errorType",
                    "render": function (data, type, full, meta) {
                        if(data=="1"){
                            return "干扰形容词"
                        }else if(data=="2"){
                            return "干扰名词"
                        }else if(data=="3"){
                            return "预选形容词"
                        }else if(data=="4"){
                            return "预选名词"
                        }else{
                            return "----"
                        }
                    }
                },
            ],
            serverSide: true,
            ajax: {
                "url": "${path}/manage/trainTest/getTrainingResultList",
                "data": function (d) {
                    d.name = name;
                    d.module = module;
                    d.scene = scene;
                    d.pass = pass;
                    d.userId = userId;
                    d.errorType = errorType;
                }
            },
            "fnDrawCallback": function (obj) {

            },
            index: 0
        };
        $("#module").change(function(){
            var opt=$("#module").val();
            getCate2List(opt)
        });
        //取消按钮
        $("#btn-cancel").click(function(){
            window.history.go(-1);
        });
        function getCate2List(data) {
            $("#scene").html('')
            var html = '';
            if(data==1){
                html += "<option value='1' > 训练 </option>";
                html += "<option value='2' > 测试 </option>";
                html += "<option value='3' > 意义 </option>";
            }else if(data==2){
                html += "<option value='1' > 训练 </option>";
                html += "<option value='2'> 测试 </option>";
            }else if(data==3){
                html += "<option value='1' > 训练 </option>";
                html += "<option value='2' > 测试 </option>";
            }else if(data==4){
                html += "<option value='1' > 训练 </option>";
                html += "<option value='2' > 测试 </option>";
            }else{
                html += "<option value='' selected = 'selected'> 请选择场景 </option>";
            }
            $("#scene").html(html)
        }
        var $dataTable = $('#data-table').dataTable(conf_dt);
        //查看学习历史
        $(".bt-lookHistory").click(function(){
            var id = $(this).attr("id");
            window.location.href = "${path}/manage/trainTest/toTrainingResults?userId=" + id;
        });
        //查询
        $('.bt-search').click(function () {
            module = $("#module").val();
            scene = $("#scene").val();
            name = $("#name").val();
            pass = $("#pass").val();
            userId = $("#userId").val()
            errorType = $("#errorType").val();
            $dataTable.fnDraw();
        });
    });
    $('#side-menu').siblings().removeClass('active');
    $('#workbench_menu').addClass('active');
    $('#workbenchmenu2').addClass('active');
</script>

