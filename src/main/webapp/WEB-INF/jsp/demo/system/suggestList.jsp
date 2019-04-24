
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>建议反馈</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
    <script src="${ctx}/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
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
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp"%>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp"%>
    <div id="page-wrapper" class="gray-bg">
        <div class="container-fluid">
            <div class="row" style="margin-left: 10px;margin-top: 20px;">

                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-success bt-add" id="bt-add">添加</button>
                </div>
                <%-- <div style="display: inline-block;margin-left: 20px;">
                     <button class="btn btn-success bt-search">搜索</button>
                 </div>--%>
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
                    "title": "客服手机号",
                    "type": "html",
                    "data": "phone",
                    "defaultContent": "",
                    "name": "phone"
                },
                {
                    "title": "网站地址",
                    "type": "html",
                    "data": "network",
                    "defaultContent": "",
                    "name": "network"
                },
                {
                    "title": "微信图片",
                    "type": "html",
                    "data": "weixin",
                    "defaultContent": "",
                    "name": "weixin",
                    "render": function (data, type, full, meta) {
                        var img= '<div class="lightBoxGallery"> <a href="'+data+'" id="a_href_url" title="微信二维码"  data-gallery=""> ' +
                            '<img src="'+data+'" alt="" id="a_href_img" height="50px" id="imgc"  width="50px"></a> <div id="blueimp-gallery" class="blueimp-gallery"> ' +
                            '<div class="slides"></div> <h3 class="title"></h3> ' +
                            '<a class="prev">‹</a> <a class="next">›</a> <a class="close">×</a> <a class="play-pause"></a> <ol class="indicator"></ol> </div> </div>';
                        return img;
                    }
                },
                {
                    "title": "qq群号码",
                    "type": "html",
                    "data": "qqun",
                    "defaultContent": "",
                    "name": "qqun"

                },
                {
                    "title": "邮箱地址",
                    "type": "html",
                    "data": "mail",
                    "defaultContent": "",
                    "name": "mail"

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
                    "title": "操作",
                    "type": "num",
                    "data": "id",
                    "defaultContent": "",
                    "name": "id",
                    "render": function (data, type, full, meta) {
                        var ret =
                            '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-edit" id="' + data +
                            '">修改</button>'+
                            '<button style="margin-right: 5px;" class=" btn btn-success btn-xs   btn-delete" id="' + data +
                            '">删除</button>';
                        return ret;
                    }
                }
            ],
            serverSide: true,
            ajax: {
                "url": "${path}/manage/system/getSuggestList.ajax",
                "data": function (d) {
                    d.type;
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
            window.location.href = "${path}/manage/system/toSuggestAdd";
        });

        //修改
        $dataTable.on('click',".btn-edit",function () {
            id= $(this).attr("id");
            window.location.href = "${path}/manage/system/toSuggestAdd?id="+id;
        });
        //删除
        $dataTable.on('click',".btn-delete",function () {
            id= $(this).attr("id");
            $.ajax({
                url:"${path}/manage/system/uggestsDelete",
                dataType:'JSON',
                data:{
                    id:id,
                },
                success: function (response) {
                    console.log(response);
                    if (response.code == 200) {
                        alert(response.msg);
                        window.location.reload();
                    }else{
                        alert(response.msg);
                    }
                }
            });
        });
        $('.bt-search').click(function () {
            type ;
            $dataTable.fnDraw();
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
    $('#system_menu').addClass('active');
    $('#system_menu4').addClass('active');
</script>
