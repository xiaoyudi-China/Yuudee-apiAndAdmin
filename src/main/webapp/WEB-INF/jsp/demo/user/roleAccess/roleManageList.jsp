
<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>角色管理</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp" %>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp" %>
    <div id="page-wrapper" class="gray-bg">
        <div class="container-fluid">
            <div class="row" style="margin-left: 10px;margin-top: 20px;">

                <div style="line-height: 34px;display: inline-block;">
                    <label>角色名称</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <input class="form-control" type="text" id="roleName" name="roleName">
                    </div>
                </div>
                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-success bt-search">搜索</button>
                </div>
            </div>
            <div class="row" style="margin-left: 10px;margin-top: 20px;">
                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-success bt-add" onclick="window.location.href='${path}/manage/roleAccess/toRoleaddPage'">添加</button>
                </div>
            </div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            <table class="table table-striped table-bordered table-hover dataTables-example" id="data-table">
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
<script>
    $(document).ready(function () {
        var thisId;
        var $dataTable;
        var roleName;
        var phone;
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
                    render:function(data,type,row,meta) {
                        return meta.row + 1 +
                            meta.settings._iDisplayStart;
                    }
                },
                {
                    "title": "角色ID",
                    "type": "html",
                    "data": "id",
                    "defaultContent": "",
                    "name": "id"
                }, {
                    "title": "角色名称",
                    "type": "html",
                    "data": "roleName",
                    "defaultContent": "",
                    "name": "roleName"

                },
                {
                    "title": "角色说明",
                    "type": "html",
                    "data": "remark",
                    "defaultContent": "",
                    "name": "remark"

                },
                {
                    "title": "唯一标识",
                    "type": "html",
                    "data": "role",
                    "defaultContent": "",
                    "name": "role"

                },
                {
                    "title": "使用状态",
                    "type": "html",
                    "data": "status",
                    "defaultContent": "",
                    "name": "status",
                    "render": function (data, type, full, meta) {
                        if (data == 0){
                            return "正常使用"
                        }
                        if (data == 1){
                            return "暂停使用"
                        }
                    }

                },
                {
                    "title": "创建时间",
                    "type": "html",
                    "data": "createTime",
                    "defaultContent": "",
                    "name": "createTime",
                    "render": function (data, type, full, meta) {
                        return timestampToTime(data / 1000);
                    }
                },
                {
                    "title": "更新时间",
                    "type": "html",
                    "data": "updateTime",
                    "defaultContent": "",
                    "name": "updateTime",
                    "render": function (data, type, full, meta) {
                        return timestampToTime(data / 1000);
                    }
                },
                {
                    "title": "操作",
                    "type": "num",
                    "data": "id",
                    "defaultContent": "",
                    "name": "id",
                    "render": function (data, type, full, meta) {
                        var ret = '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-info" id="' + data + '">编辑</button>' +
                            '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-delete" id="' + data + '">删除</button>';
                        return ret;
                    }
                }
            ],
            serverSide: true,
            ajax: {
                "url": "${path}/manage/roleAccess/getRoleList.ajax",
                "type" : "POST",
                "data": function (d) {
                    d.roleName = roleName;
                }
            },
            "fnDrawCallback": function (obj) {
                $('#switchId input').bootstrapSwitch({
                    onText: '正常', offText: '冻结', onSwitchChange: function () {
                        var switch_status = $(this).val();
                        var rowId = $(this).attr("rowId");
                        if (switch_status == 0) {//正常 变冻结
                            updateStatus(rowId, 1);
                        }
                        if (switch_status == 1) {//冻结 变正常
                            updateStatus(rowId, 0);
                        }
                    }
                });

            },
            index: 0
        };

        $dataTable = $('#data-table').dataTable(conf_dt);
        //详情页操作
        $dataTable.on('click',".btn-info",function () {
            thisId= $(this).attr("id");
            window.location.href = "${path}/manage/roleAccess/infoRole?roleId="+thisId;
        });
        //删除操作
        $dataTable.on('click',".btn-delete",function () {
            thisId= $(this).attr("id");
            $.ajax({
                url: "${path}/manage/roleAccess/deleteRole",
                dataType: 'JSON',
                type:"POST",
                data: {
                    roleId: thisId,
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

        $('.bt-search').click(function () {
            roleName = $("#roleName").val();
            $dataTable.fnDraw();
        });

        //时间选择器
        $(function () {
            $('#datetimepicker1').datetimepicker({
                format: 'YYYY-MM-DD HH:mm:ss'
            });
        });
        //时间选择器
        $(function () {
            $('#datetimepicker2').datetimepicker({
                format: 'YYYY-MM-DD HH:mm:ss'
            });
        });
        function updateStatus(id, status) {
            $.ajax({
                url: "/manage/account/updateStatus",
                dataType: 'JSON',
                data: {
                    id: id,
                    status: status
                },
                success: function (response) {
                    console.log(response);
                    if (response == 200) {
                        $dataTable.fnDraw();
                    } else {
                        alert("出现异常！");
                    }
                }
            });
        }
    });

    $('#side-menu').siblings().removeClass('active');
    $('#user_menu').addClass('active');
    $('#user_menu1').addClass('active');
</script>