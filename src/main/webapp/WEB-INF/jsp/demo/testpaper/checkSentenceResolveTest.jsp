
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/14
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>查看句子分解测试</title>
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp"%>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp"%>

    <div id="page-wrapper" class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form class="form-horizontal" id="carouselFigure_form">
                <input type="hidden" id="id" name="id" value="${sentenceResolveTest.id}">
                <legend>句子成组管理>>查看句子分解测试
                </legend>
                <div class="form-group" id="imgDiv6">
                    <label class="col-sm-2 control-label textColor">轮播图:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc" style="width: 1000px;height: 165px">
                                <c:forEach  items="${sentenceResolveTest.startSlideshowList}" var="item">
                                    <img class="img-result img" src="${item}" width="160px" height="165px"/>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group" id="imgDiv">
                    <label class="col-sm-2 control-label textColor">卡一图片:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" id="img" src="${sentenceResolveTest.cardOneImage}" width="160px" height="165px"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group" >
                    <label class="col-sm-2 control-label"  >卡一文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="卡一文字" value="${sentenceResolveTest.cardOneChar}" name="cardOneChar" class="form-control" id="cardOneChar" style="width:300px;">
                    </div>
                </div>
                <div class="form-group"  >
                    <label class="col-sm-2 control-label textColor">卡一录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <input class="form-control" value="${sentenceResolveTest.cardOneRecord}" name="cardOneRecord" id="cardOneRecord" />
                        </div>
                    </div>
                </div>

                <div class="form-group" id="imgDiv2">
                    <label class="col-sm-2 control-label textColor">卡二图片:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" src="${sentenceResolveTest.cardTwoImage}"  id="img2" width="160px" height="165px"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group"  >
                    <label class="col-sm-2 control-label"  >卡二文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="卡二名词" value="${sentenceResolveTest.cardTwoChar}" name="cardTwoChar" class="form-control" id="cardTwoChar" style="width:300px;">
                    </div>
                </div>

                <div class="form-group"  >
                    <label class="col-sm-2 control-label textColor">卡二录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <input class="form-control" value="${sentenceResolveTest.cardTwoRecord}" name="cardTwoRecord" id="cardTwoRecord" />
                        </div>
                    </div>
                </div>

                <div class="form-group" id="imgDiv4">
                    <label class="col-sm-2 control-label textColor">卡三图片:</label>
                    <div class="col-sm-3">
                        <div>
                            <div style="width: 1000px;height: 165px" class="sc">
                                <c:forEach  items="${sentenceResolveTest.cardThreeImage}" var="item">
                                    <img class="img-result img" src="${item}" width="160px" height="165px"/>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group"  >
                    <label class="col-sm-2 control-label"  >卡三文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="卡二名词" value="${sentenceResolveTest.cardThreeChar}" name="cardThreeChar" class="form-control" id="cardThreeChar" style="width:300px;">
                    </div>
                </div>

                <div class="form-group"  >
                    <label class="col-sm-2 control-label textColor">卡三录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <input class="form-control" value="${sentenceResolveTest.cardThreeRecord}" name="cardThreeRecord" id="cardThreeRecord" />
                        </div>
                    </div>
                </div>

                <div class="form-group" id="imgDiv5">
                    <label class="col-sm-2 control-label textColor">卡四图片:</label>
                    <div class="col-sm-3">
                        <div>
                            <div class="sc">
                                <img class="img-result img" src="${sentenceResolveTest.cardFourImage}"  id="img7" width="160px" height="165px"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group"  >
                    <label class="col-sm-2 control-label"  >卡四文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="卡二名词" value="${sentenceResolveTest.cardFourChar}" name="cardFourChar" class="form-control" id="cardFourChar" style="width:300px;">
                    </div>
                </div>

                <div class="form-group"  >
                    <label class="col-sm-2 control-label textColor">卡四录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <input class="form-control" value="${sentenceResolveTest.cardFourRecord}" name="cardFourRecord" id="cardFourRecord" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label"  >组合文字</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="组合文字" value="${sentenceResolveTest.groupChar}" name="groupChar" class="form-control" id="groupChar" style="width:300px;">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label textColor">组合录音:</label>
                    <div class="col-sm-3">
                        <div>
                            <input class="form-control" value="${sentenceResolveTest.groupRecord}" name="groupRecord" id="groupRecord" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"  >辅助时长1</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="辅助时长1" value="${sentenceResolveTest.cardOneTime}" class="form-control"  style="width:300px;">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"  >辅助时长2</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="辅助时长2" value="${sentenceResolveTest.cardTwoTime}" class="form-control"  style="width:300px;">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"  >辅助时长3</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="辅助时长3" value="${sentenceResolveTest.cardThreeTime}" class="form-control"  style="width:300px;">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"  >辅助时长4</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="辅助时长4" value="${sentenceResolveTest.cardFourTime}" class="form-control"  style="width:300px;">
                    </div>
                </div>

                <div class="form-group pull-left" style="margin-left:110px;">
                    <button type="button" class="btn btn-success" id="btn-cancel">返回</button>
                </div>
            </form>

        </div>
    </div>

</div>
</body>
</html>
<script>
    $(document).ready(function () {
        //取消按钮
        $("#btn-cancel").click(function () {
            window.history.go(-1);
        });
    });
    $('#side-menu').siblings().removeClass('active');
    $('#image_Menu').addClass('active');
    $('#image_manage_Menu3').addClass('active');
    $('#image_manage_Menu8').addClass('active');
</script>