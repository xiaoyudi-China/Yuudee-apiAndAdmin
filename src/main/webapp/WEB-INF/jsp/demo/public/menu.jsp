<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .firstLevelMenu{
        font-size: medium;
    }
   #side-menu li{
        display: none;
        color: red;
    }
</style>
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul  class="nav metismenu" id="side-menu1">
                <li class="nav-header">
                    <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="${path}/static/images/page/baidumarker.png"/>
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">管理人员</strong>
                             </span>  </span> </a>
                    </div>
                    <div class="logo-element">
                        <input  hidden id="thisUserid" type="text" value="${sessionScope.get("user").id}">
                    </div>
                </li>
            </ul>
            <ul class="nav metismenu" id="side-menu">
                <li id="workbench_menu">
                    <a href=""><i class="fa fa-th-large"></i> <span class="nav-label firstLevelMenu" >工作台</span></span><span class="fa arrow"></span> </a>
                    <ul class="nav nav-second-level collapse">
                        <li id="workbenchmenu0"><a href="${path}/manage/workbench/toWorkBenchPage">工作台</a></li>
                        <li id="workbenchmenu1"><a href="${path}/manage/trainTest/toCoursewaretListPage">课件统计</a></li>
                        <li id="workbenchmenu2"><a href="${path}/manage/trainTest/touserDetailList">用户详情统计</a></li>
                    </ul>
                </li>
                <li id="image_Menu">
                    <a href=""><i class="fa fa-th-large"></i> <span class="nav-label firstLevelMenu" >图片管理</span></span><span class="fa arrow"></span> </a>
                    <ul class="nav nav-second-level collapse">
                        <li id="image_manage_Menu"><a href="">名词图片管理</a>
                            <ul class="nav nav-second-level collapse hul">
                                <li id="image_manage_Menu0"><a href="${path}/manage/noun/toNoumTrainingPage">训练</a></li>
                                <li id="image_manage_Menu1"><a href="${path}/manage/noun/toNoumTestPage">测试</a></li>
                                <li id="image_manage_Menu2"><a href="${path}/manage/noun/toNounSensePage">意义</a></li>
                            </ul>
                        </li>
                        <li id="image_manage_Menu11"><a href="">动词图片管理</a>
                            <ul class="nav nav-second-level collapse hul">
                                <li id="image_manage_Menu3"><a href="${path}/manage/verb/toVerbTrainingPage">训练</a></li>
                                <li id="image_manage_Menu4"><a href="${path}/manage/verb/toVerbTestPage">测试</a></li>
                            </ul>
                        </li>
                        <li id="image_manage_Menu12"><a href="">句子成组管理</a>
                            <ul class="nav nav-second-level collapse hul">
                                <li id="image_manage_Menu5"><a href="${path}/manage/sentence/toSentenceGroupTrainingPage">训练</a></li>
                                <li id="image_manage_Menu6"><a href="${path}/manage/sentence/toSentenceGroupTestPage">测试</a></li>
                            </ul>
                        </li>
                        <li id="image_manage_Menu13"><a href="">句子分解管理</a>
                            <ul class="nav nav-second-level collapse hul">
                                <li id="image_manage_Menu7"><a href="${path}/manage/sentence/toSentenceResolveTrainingPage">训练</a></li>
                                <li id="image_manage_Menu8"><a href="${path}/manage/sentence/toSentenceResolveTestPage">测试</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li id="msq_Menu">
                    <a href=""><i class="fa fa-th-large"></i> <span class="nav-label firstLevelMenu" >问卷管理</span></span><span class="fa arrow"></span> </a>
                    <ul class="nav nav-second-level collapse">
                        <li id="msq_Menu0"><a href="${path}/manage/roll/toPcdiList">pcdi必做问卷</a></li>
                        <li id="msq_Menu1"><a href="${path}/manage/roll/toPcdiSelectList">pcdi选做问卷</a></li>
                        <li id="msq_Menu2"><a href="${path}/manage/roll/toAbcListPage">ABC问卷</a></li>
                        <li id="msq_Menu3"><a href="${path}/manage/roll/topcdiAndabc/resultPage">问卷记录</a></li>
                        <li id="msq_Menu4"><a href="${path}/manage/roll/toWordListPage">建议设置</a></li>
                    </ul>
                </li>
                <li id="user_menu">
                    <a href=""><i class="fa fa-th-large"></i> <span class="nav-label firstLevelMenu" >人员管理</span></span><span class="fa arrow"></span> </a>
                    <ul class="nav nav-second-level collapse">
                        <li id="user_menu0"><a href="${path}/manage/account/getAccountList">账号管理</a></li>
                        <li id="user_menu1"><a href="${path}/manage/roleAccess/toRoleListPage">角色管理</a></li>
                        <li id="user_menu2"><a href="${path}/manage/user/toParentsPage">用户管理</a></li>
                        <li id="user_menu3"><a href="${path}/manage/user/toChildPage">儿童管理</a></li>
                    </ul>
                </li>
                <li id="system_menu">
                    <a href=""><i class="fa fa-th-large"></i> <span class="nav-label firstLevelMenu" >系统管理</span></span><span class="fa arrow"></span> </a>
                    <ul class="nav nav-second-level collapse">
                        <li id="system_menu1"><a href="${path}/manage/system/toVersionPage">版本日志</a></li>
                        <li id="system_menu2"><a href="${path}/manage/system/toStrartPage">启动页</a></li>
                        <li id="system_menu3"><a href="${path}/manage/system/toIntroduceList">产品介绍</a></li>
                        <li id="system_menu4"><a href="${path}/manage/system/toSuggestPage">信息设置</a></li>
                        <li id="system_menu5"><a href="${path}/manage/city/toCityList">地区设置</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
<script>
    $(document).ready(function (){
        var  id= $("#thisUserid").val();
       var AccessList = '';
        //获取该用户的权限
        $.ajax({
            url:"${path}/manage/account/getUserAccessList",
            data:{id:id},
            dataType: 'JSON',
            type:"POST",
            success: function (result) {
                console.log(result);
                if (result.code == 200) {
                    var list = result.data;
                    for(var i = 0; i< list.length; i++){
                        if (list[i].access != null){
                            $("#"+list[i].access).css("display","block");
                        }
                    }
                }else {
                    alert(result.msg);
                }
            }
        });
    })

</script>
<%--<script src="${path}/static/js/vconsole.min.js"></script>--%>
<script>
    // init vConsole
   /* var vConsole = new VConsole();
    console.log('Hello world');*/
</script>
