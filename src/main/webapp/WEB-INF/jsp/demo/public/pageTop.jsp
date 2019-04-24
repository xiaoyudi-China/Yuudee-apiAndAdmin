
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper animated fadeInRight gray-bg">
    <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
            <ul class="nav navbar-top-links navbar-right">
                <li>
                    <a id="user" href="${path}/manage/account/toindexPage">
                        <input  hidden id="accountId" type="text" value="${sessionScope.get("user").id}"/>
                        <i class="fa fa-user" ></i>${sessionScope.get("user").accountName}
                    </a>
                </li>
                <li>
                    <a id="updatePassword">
                        <i class="fa fa-key" ></i>修改密码
                    </a>
                </li>

                <li>
                    <a href="${path}/manage/account/loginOut">
                        <i class="fa fa-sign-out"></i> 退出
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <div>
                    <div class="form-group">
                        <label class="control-label">旧密码:</label>

                        <input type="password" class="form-control" id="oldPassword">
                    </div>
                    <div class="form-group">
                        <label  class="control-label">新密码:</label>
                        <input type="password" class="form-control" id="newPassword1">
                    </div>
                    <div class="form-group">
                        <label  class="control-label">确认密码:</label>
                        <input type="password" class="form-control" id="newPassword2">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-success" id="updatePasswordBtn">确定</button>
            </div>
        </div>
    </div>
</div>

<script>
    $("#updatePassword").click(function () {
        $("#exampleModal").modal('show');
    });

        $("#updatePasswordBtn").click(function(){
            var oldPassword=$("#oldPassword").val();
            var newPassword1=$("#newPassword1").val();
            var newPassword2 =$("#newPassword2").val();
            if(oldPassword==""||oldPassword==null||oldPassword=="undefined"){
            alert("请填写旧密码！");
            return;
            }
            if(newPassword1==""||newPassword1==null||newPassword1=="undefined"){
                alert("请填写新密码！");
                return;
            }
            if(newPassword2==""||newPassword2==null||newPassword2=="undefined"){
                alert("请填写确认密码！");
                return;
            }
            if(newPassword1 != newPassword2){
                alert("新密码和确认密码不相同！");
                return;
            }

            $.ajax({
                url:"${path}/manage/account/updatePassword",
                type: 'POST',
                dataType:'json',
                data: {
                    "oldPassword":oldPassword,
                    "newPassword":newPassword1,
                    "accountId":$("#accountId").val()
                },
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        $("#exampleModal").modal('hide');
                        alert(data.msg);
                        window.location.href = "${path}/manage/account/loginOut";
                    }else{
                        alert(data.msg);
                    }
                }
            });
        });
</script>