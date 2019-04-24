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
    <title>问卷记录列表</title>
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
                    <label>昵&nbsp;&nbsp;称</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 50px 0px 10px;">
                        <input class="form-control" type="text" id="name" name="name">
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>性&nbsp;&nbsp;别</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 50px 0px 10px;">
                        <select id="sex" class="form-control" name="sex">
                            <option value="">全部</option>
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>家长手机号</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 50px 0px 10px;">
                        <input class="form-control" type="text" id="phone" name="phone">
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>出生日期</label>
                    <div id="data_1" style="width: 210px;display: inline-block;margin: 0px 50px 0px 10px;vertical-align: top;">
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
            <div class="row" style="margin-left: 10px;margin-top: 20px;">
                <div style="line-height: 34px;display: inline-block;">
                    <label>问卷类型</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 50px 0px 10px;">
                        <select id="type" class="form-control" name="type">
                            <option value="">请选择</option>
                            <option value="1">PCDI必填</option>
                            <option value="2">PCDI全部</option>
                            <option value="3">ABC问卷</option>
                        </select>
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>是否有效</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 50px 0px 10px;">
                        <select id="valid" class="form-control" name="valid">
                            <option value="">请选择</option>
                            <option value="1">有效</option>
                            <option value="2">无效</option>
                        </select>
                    </div>
                </div>

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
        var type ;
        var name ;
        var sex  ;
        var phone ;
        var birthdate ;
        var valid ;
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
                    "title": "ID",
                    "type": "html",
                    "data": "id",
                    "defaultContent": "",
                    "name": "id"
                },
                {
                    "title": "昵称",
                    "type": "html",
                    "data": "name",
                    "defaultContent": "",
                    "name": "name"
                },
                {
                    "title": "性别",
                    "type": "html",
                    "data": "sex",
                    "defaultContent": "",
                    "name": "sex",
                    "render": function (data, type, full, meta) {
                        if (data == "1"){
                            return "女";
                        }else {
                            return "男"
                        }
                    }
                },
                {
                    "title": "出生日期",
                    "type": "html",
                    "data": "birthDate",
                    "defaultContent": "",
                    "name": "birthDate",
                    "render": function (data, type, full, meta) {
                        return timestampToyyyy_MM_dd(data/1000);
                    }
                },
                {
                    "title": "家长手机号",
                    "type": "html",
                    "data": "phone",
                    "defaultContent": "",
                    "name": "phone"
                },
                {
                    "title": "问卷类型",
                    "type": "html",
                    "data": "type",
                    "defaultContent": "",
                    "name": "type",
                    "render": function (data, type, full, meta) {
                        if (data == "3"){
                            return "ABC问卷";
                        }else {
                            return "PCDI问卷"
                        }
                    }
                },
                {
                    "title": "是否有效",
                    "type": "html",
                    "data": "isValid",
                    "defaultContent": "",
                    "name": "isValid",
                    "render": function (data, type, full, meta) {
                        if (data == "1"){
                            return "有效";
                        }
                        if(data == "2"){
                            return "无效"
                        }
                    }
                },
                {
                    "title": "总分",
                    "type": "html",
                    "data": "score",
                    "defaultContent": "",
                    "name": "score"
                },
                {
                    "title": "答题时间",
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
                        var ret = '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-resultInfo" id="' + data +
                            '">修改</button>'+
                            '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-info" id="' + data +
                            '">查看问卷</button>';
                        return ret;
                    }
                }
            ],
            serverSide: true,
            ajax: {
                "url": "${path}/manage/roll/getresultList.ajax",
                "data": function (d) {
                    d.type = type;
                    d.name = name;
                    d.phone = phone;
                    d.sex = sex;
                    d.valid = valid;
                    d.birthdate = birthdate;
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
        //查看问卷
        $dataTable.on('click',".btn-info",function () {
            id= $(this).attr("id");
            window.location.href = "${path}/manage/roll/topcdiAndabc/resultInfo?id="+id;
        });

        //文案查看
        $dataTable.on('click',".btn-resultInfo",function () {
            id= $(this).attr("id");
            window.location.href = "${path}/manage/roll/topcdiAndabc/adviceInfo?resultId="+id;
        });

        $('.bt-search').click(function () {
            type=$("#type").val();
            name=$("#name").val();
            phone=$("#phone").val();
            sex=$("#sex").val();
            valid=$("#valid").val();
            birthdate=$("#birthdate").val();
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

    $('#side-menu').siblings().removeClass('active');
    $('#msq_Menu').addClass('active');
    $('#msq_Menu3').addClass('active');
</script>
