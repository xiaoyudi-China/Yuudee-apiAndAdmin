
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>系统介绍页</title>
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
                    <div><p>个人信息</p></div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">账号ID</label>
                        <div class="col-sm-3">
                            <span class="form-control">${account.id}</span>
                        </div>
                        <label class="col-sm-1 control-label">账号名</label>
                        <div class="col-sm-3">
                            <span class="form-control">${account.account}</span>
                        </div>
                        <label class="col-sm-1 control-label">联系人</label>
                        <div class="col-sm-3">
                            <span class="form-control">${account.accountName}</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">手机号</label>
                        <div class="col-sm-3">
                            <span class="form-control">${account.phone}</span>
                        </div>
                        <label class="col-sm-1 control-label">创建时间</label>
                        <div class="col-sm-3">
                            <span class="form-control"><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                                                       value="${account.createTime}"/></span>
                        </div>
                    </div>
                    <div><p>系统介绍</p></div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">开发时间</label>
                        <div class="col-sm-3">
                            <span class="form-control">3个月</span>
                        </div>
                        <label class="col-sm-1 control-label">开发jdk版本</label>
                        <div class="col-sm-3">
                            <span class="form-control">1.8</span>
                        </div>
                        <label class="col-sm-1 control-label">服务器地址</label>
                        <div class="col-sm-3">
                            <span class="form-control">***</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">系统介绍</label>
                        <div class="col-sm-3">
                            <p>欢迎来到小雨滴。。。。。。系统介绍系统介绍系统介绍系统介绍系统介绍系统介绍系统介绍系统介绍系统介绍系统介绍系统介绍系统介绍系统介绍系统介绍系统介绍</p>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>

</script>