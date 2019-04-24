<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>查看问卷答题详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
    <style>
        /* html{
             background: rgba(204, 204, 204, 0.17);
         }
         html, body {
             margin: 0;
             width: 100%;
             height: 100%;

             box-sizing: border-box;
         }*/
/*
        a {
            color: #000;
        }*/

        .htul, li, ol {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        p {
            margin: 0;
            padding: 0;
        }

        .l {
            float: left;
        }

        .r {
            float: right;
        }



        .clearfix {
            zoom: 1;
        }
        .clearfix:after {
            content: "";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden;
        }

        .htul {
            padding-left: 10px;
        }
        .wBox > li{
            margin-bottom: 10px;
        }

        li > span {
            margin: 0 20px;
        }

        .title:first-child {
            margin-top: 0;
        }

        .title {
            font-size: 15px;
            font-weight: bold;
            margin-bottom: 10px;
            margin-top: 20px;
        }
        .htul li ul {
            padding-left: 20px;
            margin-top: 5px;
        }

        .result {
            color: #cd4744 !important;
        }
        .htul img{
            width: 13px;
            height: 13px;
            vertical-align: middle;
        }
        #list1 {
            padding-left: 30px;
            padding-bottom: 50px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp"%>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp"%>
    <div  id="page-wrapper" class="gray-bg">
        <legend>问卷记录>>答题详情</legend>
        <div class="hdiv" id="list1">
            <c:if test="${data.type == '3'}">
                <ul class="wBox htul" >
                    <c:forEach items="${data.dataList}" var="item" varStatus="i">
                        <li>
                            <p>${i.index+1}.${item.topicTitle}</p>
                            <span class="${(item.sign == 1 && item.answer == '没有') ? 'result' : ''}"><img src="${ctx}/images${item.sign == 1 && item.answer == '没有' ? '/img/select.png' : '/img/no_select.png'}" alt="">没有</span>
                            <span class="${(item.sign == 2 && item.answer == '轻微') ? 'result' : ''}"><img src="${ctx}/images${item.sign == 2 && item.answer == '轻微' ? '/img/select.png' : '/img/no_select.png'}" alt="">轻微</span>
                            <span class="${(item.sign == 3 && item.answer == '明显') ? 'result' : ''}"><img src="${ctx}/images${item.sign == 3 && item.answer == '明显' ? '/img/select.png' : '/img/no_select.png'}" alt="">明显</span>
                            <span class="${(item.sign == 4 && item.answer == '严重') ? 'result' : ''}"><img src="${ctx}/images${item.sign == 4 && item.answer == '严重' ? '/img/select.png' : '/img/no_select.png'}" alt="">严重</span>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <c:if test="${data.type == '1'}">
                <c:forEach items="${data.dataList}" var="list">
                    <c:if test="${list.pcidType.nameEnum != 5}">
                        <p class="title">${list.pcidType.name}</p>
                        <ul class="wBox htul">
                            <c:forEach items="${list.pcidList}" var="item" varStatus="i">
                                <li>
                                    <p>${i.index+1}.${item.topicTitle}</p>
                                    <c:forEach items="${item.topicResult}" var="res" varStatus="j">
                                        <c:if test="${item.answer == res}">
                                            <span class="result"><img src="${ctx}/images/img/select.png" alt="">${res}</span>
                                        </c:if>
                                        <c:if test="${item.answer != res}">
                                            <span><img src="${ctx}/images/img/no_select.png" alt="">${res}</span>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${item.answers != null}">
                                        <p style="padding-left:40px;margin-top:20px;">您孩子最近说过的三个最长的句子</p>
                                        <ul style="padding-left:52px;">
                                            <c:forEach items="${item.answers}" var="answer">
                                                <li style="list-style: decimal;"><span style="border-bottom:1px solid #999;min-width:300px;display:inline-block;margin-left:0;">${answer}</span></li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${list.pcidType.nameEnum == 5}">
                        <p class="title">${list.pcidType.name}</p>
                        <ul style="width: 500px;" class="wBox htul">
                            <c:forEach items="${list.pcidList}" var="item" varStatus="j">
                                <li class="clearfix">
                                    <span class="l">${j.index+1}.${item.topicTitle}</span>
                                    <div class="r">
                                        <c:if test="${item.sign == '1'}">
                                            <span class="result"><img src="${ctx}/images/img/select.png" alt="">会说</span>
                                            <span><img src="${ctx}/images/img/no_select.png" alt="">不会说</span>
                                        </c:if>
                                        <c:if test="${item.sign == '2'}">
                                            <span><img src="${ctx}/images/img/no_select.png" alt="">会说</span>
                                            <span class="result"><img src="${ctx}/images/img/select.png" alt="">不会说</span>
                                        </c:if>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </c:forEach>
            </c:if>


            <c:if test="${data.type == '2'}">
                <h2 >PCDI必做问卷</h2>
                <c:forEach items="${data.dataList.must}" var="list">
                    <c:if test="${list.pcidType.nameEnum != 5}">
                        <p class="title">${list.pcidType.name}</p>
                        <ul class="wBox htul">
                            <c:forEach items="${list.pcidList}" var="item" varStatus="i">
                                <li>
                                    <p>${i.index+1}.${item.topicTitle}</p>
                                    <c:forEach items="${item.topicResult}" var="res" varStatus="j">
                                        <c:if test="${item.answer == res}">
                                            <span class="result"><img src="${ctx}/images/img/select.png" alt="">${res}</span>
                                        </c:if>
                                        <c:if test="${item.answer != res}">
                                            <span><img src="${ctx}/images/img/no_select.png" alt="">${res}</span>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${item.answers != null}">
                                        <p style="padding-left:40px;margin-top:20px;">您孩子最近说过的三个最长的句子</p>
                                        <ul style="padding-left:52px;">
                                            <c:forEach items="${item.answers}" var="answer">
                                                <li style="list-style: decimal;"><span style="border-bottom:1px solid #999;min-width:300px;display:inline-block;margin-left:0;">${answer}</span></li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${list.pcidType.nameEnum == 5}">
                        <p class="title">${list.pcidType.name}</p>
                        <ul style="width: 500px;" class="wBox htul">
                            <c:forEach items="${list.pcidList}" var="item" varStatus="j">
                                <li class="clearfix">
                                    <span class="l">${j.index+1}.${item.topicTitle}</span>
                                    <div class="r">
                                        <c:if test="${item.sign == '1'}">
                                            <span class="result"><img src="${ctx}/images/img/select.png" alt="">会说</span>
                                            <span><img src="${ctx}/images/img/no_select.png" alt="">不会说</span>
                                        </c:if>
                                        <c:if test="${item.sign == '2'}">
                                            <span><img src="${ctx}/images/img/no_select.png" alt="">会说</span>
                                            <span class="result"><img src="${ctx}/images/img/select.png" alt="">不会说</span>
                                        </c:if>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </c:forEach>
                <h2 >PCDI选做问卷</h2>
                <c:forEach items="${data.dataList.optional}" var="list">
                    <c:if test="${list.pcidType.nameEnum != 5}">
                        <p class="title">${list.pcidType.name}</p>
                        <ul class="wBox htul">
                            <c:forEach items="${list.pcidList}" var="item" varStatus="i">
                                <li>
                                    <p>${i.index+1}.${item.topicTitle}</p>
                                    <c:forEach items="${item.topicResult}" var="res" varStatus="j">
                                        <c:if test="${item.answer == res}">
                                            <span class="result"><img src="${ctx}/images/img/select.png" alt="">${res}</span>
                                        </c:if>
                                        <c:if test="${item.answer != res}">
                                            <span><img src="${ctx}/images/img/no_select.png" alt="">${res}</span>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${item.answers != null}">
                                        <p style="padding-left:40px;margin-top:20px;">您孩子最近说过的三个最长的句子</p>
                                        <ul style="padding-left:52px;">
                                            <c:forEach items="${item.answers}" var="answer">
                                                <li style="list-style: decimal;"><span style="border-bottom:1px solid #999;min-width:300px;display:inline-block;margin-left:0;">${answer}</span></li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${list.pcidType.nameEnum == 5}">
                        <p class="title">${list.pcidType.name}</p>
                        <ul style="width: 500px;" class="wBox htul">
                            <c:forEach items="${list.pcidList}" var="item" varStatus="j">
                                <li class="clearfix">
                                    <span class="l">${j.index+1}.${item.topicTitle}</span>
                                    <div class="r">
                                        <c:if test="${item.answer == '会说'}">
                                            <span class="result"><img src="${ctx}/images/img/select.png" alt="">会说</span>
                                            <span><img src="${ctx}/images/img/no_select.png" alt="">不会说</span>
                                        </c:if>
                                        <c:if test="${item.answer != '会说'}">
                                            <span><img src="${ctx}/images/img/no_select.png" alt="">会说</span>
                                            <span class="result"><img src="${ctx}/images/img/select.png" alt="">不会说</span>
                                        </c:if>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
<script>
    $('#side-menu').siblings().removeClass('active');
    $('#msq_Menu').addClass('active');
    $('#msq_Menu3').addClass('active');
</script>
