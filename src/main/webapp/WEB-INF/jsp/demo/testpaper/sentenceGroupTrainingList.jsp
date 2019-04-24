
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>句子成组训练</title>
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
                <div style="line-height: 34px;display: inline-block;">
                    <label>组合文字</label>
                    <div style="width: 200px;display: inline-block;margin: 0px 20px 0px 10px;">
                        <input class="form-control" type="text" id="groupWord" name="groupWord">
                    </div>
                </div>
                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-success bt-search">查询</button>
                </div>
            </div>
            <div class="row" style="margin-left: 10px;margin-top: 20px;">
                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-success bt-add">添加</button>
                </div>
                <div style="display: inline-block;margin-left: 20px;">
                    <button class="btn btn-set">设置时长</button>
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
                            <label class="col-sm-2 control-label">辅助时长1:</label>
                            <%--占七列 并且造一个input组--%>
                            <div class="col-sm-3 input-group">
                                <select id="states1" class="form-control">
                                    <option value="0">请选择辅助时长</option>

                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">辅助时长2:</label>
                            <%--占七列 并且造一个input组--%>
                            <div class="col-sm-3 input-group">
                                <select id="states2" class="form-control">
                                    <option value="0">请选择辅助时长</option>

                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a href="javascript:;" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
                            <button class="btn btn-sm btn-addOK">确定</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

</body>
</html>
<script >
    $(document).ready(function () {
        var obj1 = document.getElementById("states1")
        var obj2 = document.getElementById("states2")
        for (var i = 0; i < 60; i++) {
            obj1.options.add(new Option(i + 1, i + 1)); //这个兼容IE与firefox
            obj2.options.add(new Option(i + 1, i + 1)); //这个兼容IE与firefox
        }
        var helptime ='';
        //获取辅助时长
        $.ajax({
            url: "${path}/manage/verb/getHelpTime",
            dataType: 'JSON',
            type: 'POST',
            data: {
                'topic': 3,
            },
            success: function (res) {
                console.log(res);
                $(res.helptime).each(function(index, value) {
                    if((index+1)==res.helptime.length){
                        helptime += value.helpTime
                    }else{
                        helptime += value.helpTime+","
                    }
                });
            }
        });
        var groupWord ;
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
                    "title": "轮播图",
                    "type": "html",
                    "data": "startSlideshow",
                    "defaultContent": "",
                    "name": "startSlideshow",
                    "render": function (data, type, full, meta) {
                        var dizhi = "${path}/manage/noun/getImg.html?data="+data;
                        var img = "<a href='" + dizhi + "' style='color: #0000cc'>点击查看</a>";
                        return img;
                    }
                },{
                    "title": "卡1图片",
                    "type": "html",
                    "data": "cardOneImage",
                    "defaultContent": "",
                    "name": "cardOneImage",
                    "render": function (data, type, full, meta) {
                        var img= '<img src="'+data+'" alt="" id="a_href_img" height="50px" id="imgc"  width="50px">';
                        return img;
                    }
                }, {
                    "title": "卡1 文字",
                    "type": "html",
                    "data": "cardOneChar",
                    "defaultContent": "",
                    "name": "cardOneChar"
                },
                {
                    "title": "卡1录音",
                    "type": "html",
                    "data": "cardOneChar",
                    "defaultContent": "",
                    "name": "cardOneChar",
                    "render": function (data, type, full, meta) {
                        return data+".mp3"
                    }
                },
                {
                    "title": "卡2文字",
                    "type": "html",
                    "data": "cardTwoChar",
                    "defaultContent": "",
                    "name": "cardTwoChar"
                },
                {
                    "title": "卡2图片",
                    "type": "html",
                    "data": "cardTwoImage",
                    "defaultContent": "",
                    "name": "cardTwoImage",
                    "render": function (data, type, full, meta) {
                        var dizhi = "${path}/manage/noun/getImg.html?data="+data;
                        var img = "<a href='" + dizhi + "' style='color: #0000cc'>点击查看</a>";
                        return img;
                    }
                },
                {
                    "title": "卡2录音",
                    "type": "html",
                    "data": "cardTwoChar",
                    "defaultContent": "",
                    "name": "cardTwoChar",
                    "render":function (data,type,full,meta) {
                        return data+".mp3";
                    }
                },{
                    "title": "组合文字",
                    "type": "html",
                    "data": "groupChar",
                    "defaultContent": "",
                    "name": "groupChar"
                },
                {
                    "title": "组合录音",
                    "type": "html",
                    "data": "groupChar",
                    "defaultContent": "",
                    "name": "groupChar",
                    "render":function (data,type,full,meta) {
                        return data+".mp3";
                    }
                },
                {
                    "title": "辅助时长",
                    "type": "html",
                    "data": "groupChar",
                    "defaultContent": "",
                    "name": "groupChar",
                    "render":function (data,type,full,meta) {
                        return helptime + "s";
                     }
                },
                {
                    "title": "上传时间",
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
                "url": "${path}/manage/sentence/getSentencegroupTrainingList",
                "data": function (d) {
                    d.groupWord = groupWord;
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
        $(".bt-add").click(function(){
            window.location.href = "${path}/manage/sentence/toAddSentenceGroupTraining";
        });
        //设置时长
        $(".btn-addOK").click(function () {
            var states1 = $("#states1").val()
            var states2 = $("#states2").val()
            $.ajax({
                url:"${path}/manage/noun/setHelpTimes",
                dataType:'JSON',
                type: 'POST',
                data:{
                    states1:states1,
                    states2:states2,
                    topicType:3
                },
                success: function (response) {
                    console.log(response);
                    if (response.status == 200) {
                        alert(response.msg)
                        $("#myModal").css("display","none");
                        window.location.reload();
                    }else {
                        alert(response.msg)
                    }
                }
            });
        })
        $(".btn-set").click(function(){
            $('#myModal').modal();
        });
        $dataTable.on('click', ".btn-edit", function () {
            var id = $(this).attr("id");
            window.location.href = "${path}/manage/sentence/toAddSentenceGroupTraining?id=" + id;
        });
        //删除
        $dataTable.on('click',".btn-delete",function () {
            if (!confirm('确实要删除该内容吗?')){
                return;
            }
            id= $(this).attr("id");
            $.ajax({
                url:"${path}/manage/sentence/deleteSentenceGroupTraining",
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
            groupWord = $("#groupWord").val();
            $dataTable.fnDraw();
        });
    });

    $('#side-menu').siblings().removeClass('active');
    $('#image_Menu').addClass('active');
    $('#image_manage_Menu12').addClass('active');
    $('#image_manage_Menu5').addClass('active');
</script>

