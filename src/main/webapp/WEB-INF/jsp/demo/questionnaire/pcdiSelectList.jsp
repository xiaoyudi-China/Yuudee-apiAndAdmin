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
    <title>pcdi选做问卷列表</title>
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
                    <label>类别</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 50px 0px 10px;">
                        <select id="type" class="form-control" name="type">
                            <option value="">全部</option>
                            <c:forEach items="${typeList}" var="item" >
                                <option value="${item.value}">${item.desc}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div style="line-height: 34px;display: inline-block;">
                    <label>词汇名称</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 50px 0px 10px;">
                        <input class="form-control" type="text" id="topictitle" name="topictitle">
                    </div>
                </div>
                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-success bt-search">查询</button>
                </div>
            </div>

            <div class="row" style="margin-left: 10px;margin-top: 20px;">
                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-success bt-add" id="bt-add">添加</button>
                </div>
            </div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            <table class="table table-striped table-bordered table-hover dataTables-example" id="data-table" >
            </table>
        </div>
    </div>

</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="wrapper wrapper-content animated fadeInRight" id="divTable1">
                <div class="form-group">
                    <label class="col-sm-2 control-label">词汇名称:</label>
                    <%--占七列 并且造一个input组--%>
                    <div class="col-sm-3 input-group">
                        <input name="id" id="vabId" hidden class="vabId">
                        <input type="text" name="name" id="vabName"  class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">词汇类型:</label>
                    <%--占七列 并且造一个input组--%>
                    <div class="col-sm-3 input-group">
                        <select id="cihuiType"  class="form-control">
                            <option value="0" >请选择问卷类型</option>
                            <c:forEach items="${typeList}" var="item" >
                                <option value="${item.value}">${item.desc}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href="javascript:;" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
                    <button  class="btn btn-sm btn-addOK">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
<script >
    $(document).ready(function () {
        var type ;
        var topictitle ;
        var conf_dt = {
            paging: true,//分页
            ordering: false,//是否启用排序
            searching: false,//搜索
            //lengthMenu: [25, 50],
            scrollX: "100%",
            displayLength:10,
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
               /* {
                    sTitle: '序号',
                    data: null,
                    className: 'text-center whiteSpace',
                    render:function(data,type,row,meta) {
                        return meta.row + 1 +
                            meta.settings._iDisplayStart;
                    }
                },*/
                {
                    "title": "ID",
                    "type": "html",
                    "data": "id",
                    "defaultContent": "",
                    "name": "id"
                },{
                    "title": "类型",
                    "type": "html",
                    "data": "name",
                    "defaultContent": "",
                    "name": "name"
                },
                {
                    "title": "词汇(题目)",
                    "type": "html",
                    "data": "topicTitle",
                    "defaultContent": "",
                    "name": "topicTitle"
                },
                {
                    "title": "创建时间",
                    "type": "html",
                    "data": "createTime",
                    "defaultContent": "",
                    "name": "createTime",
                    "render": function (data, type, full, meta) {
                        return timestampToTime(data/1000);
                    }
                },
                {
                    "title": "更新时间",
                    "type": "html",
                    "data": "updateTime",
                    "defaultContent": "",
                    "name": "updateTime",
                    "render": function (data, type, full, meta) {
                        if (data == null){
                            return "";
                        }
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
                        var names = full["topicTitle"];
                        var nameEnum = full["nameEnum"];
                        names = names +"@"+nameEnum;
                        var ret = '<button style="margin-right: 5px;" class=" btn btn-success btn-xs  btn-edit" name="'+names+'"   id="' + data +
                            '">编辑</button>'+
                            '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-delete" id="' + data +
                            '">删除</button>';
                        return ret;
                    }
                }
            ],
            serverSide: true,
            ajax: {
                "url": "${path}/manage/roll/getpcdiSelectList.ajax",
                "data": function (d) {
                    d.type = type;
                    d.topictitle = topictitle;
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
        //添加
        $("#bt-add").click(function(){
            $('#myModal').modal();
        });
        //编辑
        $dataTable.on('click',".btn-edit",function () {
            id= $(this).attr("id");
            names= $(this).attr("name");
            $("#vabId").val(id);
            var str = names.split('@');
            $("#vabName").val(str[0]);
            var obj = document.getElementById('cihuiType');
            $.each(obj .options, function (i, n) {
                if (n.value === str[1]) {
                    n.selected = true;
                }
            });
            $('#myModal').modal();
        });
        //删除
        $dataTable.on('click',".btn-delete",function () {
            id= $(this).attr("id");
            $.ajax({
                url:"${path}/manage/roll/toPcdiSelectDelete",
                dataType:'JSON',
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
            topictitle = $("#topictitle").val();
            $dataTable.fnDraw();
        });

        //添加、提交
        $(".btn-addOK").click(function () {
            var vabId = $(".vabId").val();
            var vabName = $("#vabName").val();
            if(vabName.length ==0){
                alert("输入的词汇不能为空！")
                return;
            }
            var cihuiType = $("#cihuiType").val();
            $.ajax({
                url: "${path}/manage/roll/PcdiSelectAdd",
                dataType: 'JSON',
                data: {
                    id: vabId,
                    name: vabName,
                    type: cihuiType,
                },
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        alert(data.msg);
                        window.location.reload();
                    } else {
                        alert(data.msg);
                    }
                }
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
    $('#msq_Menu1').addClass('active');
</script>
