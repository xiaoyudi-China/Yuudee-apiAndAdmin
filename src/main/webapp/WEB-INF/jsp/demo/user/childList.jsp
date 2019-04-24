<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/16
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>孩子列表</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp"%>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp"%>
    <div id="page-wrapper" class="gray-bg">
        <div class="container-fluid">
            <div class="row" style="margin-left: 10px;margin-top: 20px;">
                <div style="line-height: 34px;display: inline-block;">
                    <label>昵称</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <input class="form-control" type="text" id="name" name="name">
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>性别</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="sex" class="form-control" name="sex">
                            <option value="">全部</option>
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>居住地</label>
                    <div style="width: 150px;display: inline-block;margin: 0px 10px 0px 10px;">
                        <select id="countiy" class="form-control" onchange="funCountiy(this)" name="countiy">
                            <option value="">全部</option>
                            <c:forEach items="${countiyList}" var="item" >
                                <option value="${item.areaid}">${item.areaname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div style="width: 120px;display: inline-block;margin: 0px 0px 0px 10px;">
                        <select id="province" class="form-control" onchange="funProvince(this)" name="province">
                            <option value="">全部</option>
                        </select>
                    </div>
                    <div style="width: 120px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <select id="city" class="form-control" name="city">
                            <option value="">全部</option>
                        </select>
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>出生日期</label>
                    <div id="data_1" style="width: 210px;display: inline-block;margin: 0px 20px 0px 10px;vertical-align: top;">
                        <div class='input-group date' id='datetimepicker1'>
                            <input type='text' class="form-control" id="birthdate"  />
                            <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                        </div>
                    </div>
                </div>
                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-success bt-search">查询</button>
                </div>
            </div>
            <div class="wrapper wrapper-content animated fadeInRight">
                <table class="table table-striped table-bordered table-hover dataTables-example" id="data-table" >
                </table>
            </div>
        </div>
    </div>

    <div id="deleteCarouselFigure" class="modal fade " tabindex="-1" role="dialog"
         aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">提示</h4>
                </div>
                <div class="modal-body">
                    <p>您确定要删除吗？</p>
                    <input id="deleteId" type="hidden"/>
                </div>
                <div class="modal-footer">
                    <a href="javascript:;" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
                    <button id="ok" class="btn btn-sm btn-success">确定</button>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
<script >
    $(document).ready(function () {
        var countiy ;
        var province;
        var city ;
        var birthdate;
        var sex;
        var name;
        var conf_dt = {
            paging: true,//分页
            ordering: false,//是否启用排序
            searching: false,//搜索
            //lengthMenu: [25, 50],
            scrollX: "100%",
            displayLength: 10,
            language: {
                lengthMenu: '<div class="dataTables_length" id="data-table_length"><label>展示<select name="data-table_length" aria-controls="data-table" class="">'+'<option value="10">10</option>'  + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="100">100</option>'+'</select>条记录</laber></div></div>',
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
                    render:function(data,type,row,meta) {
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
                },{
                    "title": "昵称",
                    "type": "html",
                    "data": "name",
                    "defaultContent": "",
                    "name": "name"
                }, {
                    "title": "性别",
                    "type": "html",
                    "data": "sex",
                    "defaultContent": "",
                    "name": "sex",
                    "render": function (data, type, full, meta) {
                        return data == "0" ? "男" : "女";
                    }
                },
                {
                    "title": "出生日期",
                    "type": "html",
                    "data": "birthdate",
                    "defaultContent": "",
                    "name": "birthdate",
                    "render": function (data, type, full, meta) {
                        return timestampToyyyy_MM_dd(data/1000);
                    }
                }, {
                    "title": "家长手机号",
                    "type": "html",
                    "data": "phoneNumber",
                    "defaultContent": "",
                    "name": "phoneNumber"
                },{
                    "title": "居住地",
                    "type": "html",
                    "data": "countiy",
                    "defaultContent": "",
                    "name": "countiy",
                    "render": function (data, type, full, meta) {
                        var province = full["province"];
                        var city = full["city"];
                        if(province == null){
                            province = "-";
                        }
                        if(city == null){
                            city = "-";
                        }
                        if(data == null){
                            data = "-";
                        }
                        return data+"/"+province+"/"+city;
                    }
                }, {
                    "title": "医学诊断",
                    "type": "html",
                    "data": "medical",
                    "defaultContent": "",
                    "name": "medical",
                    "render": function (data, type, full, meta) {
                        var str = "";
                        var medicalState = full["medicalState"];
                        switch (data){
                            case "0": str = "自闭症"; break;
                            case "1": str = "语言发育迟缓（其他正常）"; break;
                            case "2": str = "单纯性智力低下（无自闭症）"; break;
                            case "3": str = medicalState; break;
                            case "4": str = "正常"; break;
                        }
                        return str;
                    }
                },
                {
                    "title": "第一语言",
                    "type": "html",
                    "data": "firstLanguage",
                    "defaultContent": "",
                    "name": "firstLanguage",
                    "render": function (data, type, full, meta) {
                        var str = "";
                        var firstRests = full["firstRests"];
                        switch (data){
                            case "0": str = "普通话"; break;
                            case "1": str = "方言"; break;
                            default: str =firstRests;
                        }
                        return str;
                    }
                },
                {
                    "title": "注册时间",
                    "type": "html",
                    "data": "createTime",
                    "defaultContent": "",
                    "name": "createTime",
                    "render": function (data, type, full, meta) {
                        return timestampToTime(data/1000);
                    }
                },
                {
                    "title": "操作",
                    "type": "num",
                    "data": "id",
                    "defaultContent": "",
                    "name": "id",
                    "render": function (data, type, full, meta) {
                        var ret =
                            '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-edit" id="' + data +
                            '">编辑</button>'+
                            '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-delete" id="' + data +
                            '">删除</button>';
                        return ret;
                    }
                }
            ],
            serverSide: true,
            ajax: {
                "url": "${path}/manage/user/getChildList.ajax",
                "data": function (d) {
                    d.province = province;
                    d.countiy = countiy;
                    d.birthdate = birthdate;
                    d.sex = sex;
                    d.name = name;
                    d.city = city;

                }
            },
            "fnDrawCallback":function(obj){
                $('#switchId input').bootstrapSwitch({onText:'正常',offText:'冻结',onSwitchChange:function(){
                    var switch_status=$(this).val();
                    var rowId=$(this).attr("rowId");
                    if(switch_status==0){//正常 变冻结
                        updateStatus(rowId,1);
                    }
                    if(switch_status==1){//冻结 变正常
                        updateStatus(rowId,0);
                    }
                } });

            },
            index: 0
        };
        var $dataTable = $('#data-table').dataTable(conf_dt);
        var id;

        //编辑
        $dataTable.on('click',".btn-edit",function () {
            id= $(this).attr("id");
            window.location.href = "${path}/manage/user/toChildInfo?id="+id;
        });
        //删除
        $dataTable.on('click',".btn-delete",function () {
            id= $(this).attr("id");
            $.ajax({
                url:"${path}/manage/user/childDelete",
                dataType:'JSON',
                type: 'POST',
                data:{
                    id:id,
                },
                success: function (response) {
                    console.log(response);
                    if (response.code == 200) {
                        alert(response.msg);
                        window.location.reload();
                    }else {
                        alert(response.msg)
                    }
                }
            });
        });
        $('.bt-search').click(function () {
            type = $("#type").val();
            name = $("#name").val();
            sex = $("#sex").val();
            city = $("#city").val();
            province = $("#province").val();
            countiy = $("#countiy").val();
            birthdate = $("#birthdate").val();
            $dataTable.fnDraw();
        });

        //时间选择器
        $(function () {
            $('#datetimepicker1').datetimepicker({
                format: 'YYYY-MM-DD'
            });
        });

        function updateStatus(id,status) {
            $.ajax({
                url:"${path}/manage/account/updateStatus",
                dataType:'JSON',
                data:{
                    id:id,
                    status:status
                },
                success: function (response) {
                    console.log(response);
                    if (response == 200) {
                        $dataTable.fnDraw();
                    }else{
                        alert("出现异常！");
                    }
                }
            });
        }
    });

    //获取省
    function  funCountiy(e){
        if (e.value == "0"){
            return;
        }
        $.ajax({
            url:"${path}/manage/system/getCityList",
            type: 'GET',
            dataType:'JSON',
            data:{
                parentId:e.value,
                level:2,
            },
            success: function (data) {
                if (data.code == 200){
                    var str = '<select id="province" class="form-control" onchange="funProvince(this)" name="province">'
                    str += '<option value="">全部</option>';
                    $.each(data.data,function(i,value) {
                        str +=  '<option value="'+value.areaid+'">'+value.areaname+'</option>';
                    });
                    str +='</select>'
                    $("#province").html(str);
                }else {
                    alert(data.msg);
                }
            }
        });
    }

    //获取城市
    function  funProvince(e){
        if (e.value == "0"){
            return;
        }
        $.ajax({
            url:"${path}/manage/system/getCityList",
            type: 'GET',
            dataType:'JSON',
            data:{
                parentId:e.value,
                level:3,
            },
            success: function (data) {
                if (data.code == 200){
                    var str = '<option value="">全部</option>';
                    $.each(data.data,function(i,value) {
                        str +=  '<option value="'+value.areaid+'">'+value.areaname+'</option>';
                    });
                    $("#city").html(str);
                }else {
                    alert(data.msg);
                }
            }
        });
    }


    $('#side-menu').siblings().removeClass('active');
    $('#user_menu').addClass('active');
    $('#user_menu3').addClass('active');
</script>

