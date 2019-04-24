<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/16
  Time: 2:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>${title}</title>

    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/bootstrap-switch.css"  rel="stylesheet"/>
    <link href="${ctx}/css/currency.css">
    <link href="/static/css/pcdiRequired.css">
    <link href="${ctx}/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="${ctx}/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <link href="${ctx}/css/animate.css" rel="stylesheet">
    <link href="${ctx}/css/style.css" rel="stylesheet">
    <link href="${ctx}/css/plugins/dataTables/dataTables.responsive.css" rel="stylesheet">
    <link href="${ctx}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/plugins/dataTables/dataTables.tableTools.min.css" rel="stylesheet">
    <link href="${ctx}/css/plugins/datapicker/bootstrap-datetimepicker.min.css" rel="stylesheet">


    <script src="${ctx}/js/jquery-2.1.1.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <script src="${ctx}/js/bootstrap-switch.js"></script>
    <script src="${ctx}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${ctx}/js/inspinia.js"></script>
    <script src="${ctx}/js/plugins/slimscroll/jquery.slimscroll.min.js"  type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/js/plugins/pace/pace.min.js"  type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/js/jquery-ui-1.10.4.min.js"  type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/js/jquery-migrate-1.1.0.min.js"  type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/ckeditor/ckeditor.js"></script>
    <script src="${ctx}/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="${ctx}/js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="${ctx}/js/plugins/dataTables/dataTables.responsive.js"></script>
    <script src="${ctx}/js/plugins/dataTables/dataTables.tableTools.min.js"></script>
    <script src="${ctx}/js/plugins/datapicker/Moment.js"></script>
    <script src="${ctx}/js/plugins/datapicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctx}/js/plugins/jsTree/jstree.min.js"></script>
    <script src="${ctx}/js/plugins/chosen/chosen.jquery.js"></script>
    <script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
    <!-- 页面的js列表 -->
    <script src="${ctx}/js/sha1.js"></script>
    <script src="${ctx}/js/jquery.ajaxfileupload.js"></script>
</head>
<script>

    function switchChange(data){

        alert(data);
    }
    function getSwitch(data,id){
        var ret="";
        if(data==0){
            ret='<div class="switch switch-mini" id="switchId" >'+'<input  value="0" rowId='+id+' type="checkbox" checked />'+'</div>';

        }
        if(data==1){
            ret='<div class="switch switch-mini" id="switchId" >'+'<input value="1" rowId='+id+' type="checkbox" />'+'</div>';
        }
        return ret;
    }

    /**
     * 时间戳转日期
     * @param timestamp
     * @returns {*}
     */
    function timestampToTime(timestamp) {
        var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        if(s < 10){
            s = "0"+s;
        }
        return Y+M+D+h+m+s;
    }
    /**
     * 时间戳转日期
     * @param timestamp
     * @returns {*}
     */
    function timestampToyyyy_MM_dd(timestamp) {
        var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        return Y+M+D;
    }
</script>
