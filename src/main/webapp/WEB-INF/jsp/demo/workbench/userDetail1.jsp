
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>保存userDetail的版本</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
    <style>
       /* html, body {
            padding: 0;
            margin: 0;
            background: rgba(204, 204, 204, 0.17);
        }*/

        body {
            padding: 20px;
        }

        a {
            color: #000;
        }

       .hlul, li, ol {
            list-style: none;
            padding: 0;
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

        .clearfix:after {
            content: "";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden;
        }

        .clearfix {
            zoom: 1;
        }

        .marL {
            margin-left: 20px;
        }

        .marR {
            margin-right: 20px;
        }

        .marT {
            margin-top: 20px;
        }

        .marB {
            margin-bottom: 20px;
        }

      /*  body > div {
            !*width: 100%;*!
            !*margin: 20px;*!
            padding: 16px;
            border-radius: 5px;
            background: #fff;
        }*/

        .number {
            font-size: 28px;
            font-weight: bold;
        }

        .text {
            font-size: 14px;
        }

        input, button {
            outline: none;
        }

        .btn, .btn1 {
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
            margin-right: 10px;
            border: 1px solid #000;
            color: #000;
        }
        #btn-cancel{
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
            margin-right: 10px;
            border: 1px solid #000;

        }
        .active {
            background: #4cb2eb;
            color: #fff;
            border: none;
        }

        .detail > li {
            float: left;
            width: 30%;
            margin: 5px;
        }

        label {
            width: 150px;
            text-align: right;
            display: inline-block;
        }

        .personal > li {
            width: 16%;
            float: left;
            margin-right: .8%;
            height: 100px;
            background: rgba(204, 204, 204, 0.24);
            border-radius: 10px;
            text-align: center;
            padding: 20px 0;
            box-sizing: border-box;
        }

        .personal > li:last-child {
            margin-right: 0;
        }

        .personalBox > div, #train > .box > div {
            width: 100%;
            display: none;
        }

        .personalBox > div > div {
            width: 50%;
            float: left;
            height: 300px;

        }

        .echartsShow {
            display: block !important;
        }

        .sactive {
            color: #4cb2eb;
        }

        .box > div > div {
            display: none;
            height: 300px;
            margin-top: 30px;
        }

        .month > div > div, .week > div > div {
            width: 50%;
            height: 300px;
            float: left;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp"%>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp"%>
        <div  id="page-wrapper" class="gray-bg">
            <legend>查看信息>>儿童学习情况<button type="button" class="btn btn-success" id="btn-cancel" style="margin-left: 20px;">返回</button></legend>
            <input hidden value="${child.id}" id="cid" type="text">
            <div class="form marB" id="userInfo">

            </div>
            <div class="marB">
                <ul class="clearfix personal hlul" id="comprehensive">

                </ul>
                <div class="clearfix">
                    <div class="r">
                        <button class="btn active">PCDI</button>
                        <button class="btn">ABC</button>
                    </div>
                </div>
                <div class="personalBox clearfix">
                    <div class="clearfix echartsShow">
                        <div id="pcdixTime">

                        </div>
                        <div id="pcdixProgress">

                        </div>
                        <div id="pcdicTime">

                        </div>
                        <div id="pcdicProgress">

                        </div>
                        <div id="pcdibTime">

                        </div>
                        <div id="pcdibProgress">

                        </div>

                    </div>
                    <div class="">
                        <div id="abcTime">

                        </div>
                        <div id="abcProgress">

                        </div>
                    </div>
                </div>
            </div>
            <div id="train" style="padding-bottom: 70px;">
                <div class="clearfix">
                    <div class="r">
                        <button class="btn1 active">日</button>
                        <button class="btn1">周</button>
                        <button class="btn1">月</button>
                    </div>
                </div>
                <div class="box">
                    <div class="day echartsShow">
                        <i style="float: right;margin-top: 10px;">
                            <input type="date" id="startDateday"> <button id="dayBtnday">查询</button>
                        </i>
                        <p class="title">
                            <span class="sactive">名词短语结构/</span>
                            <span>动词短语结构/</span>
                            <span>句子成组短语结构/</span>
                            <span>句子分解短语结构/</span>
                        </p>
                        <div class="echartsShow"></div>
                        <div></div>
                        <div></div>
                        <div></div>
                    </div>
                    <div class="week">
                        <i style="float: right;margin-top: 10px;">
                            <input type="date" id="startDate"> ~ <input type="date"> <button id="weekBtn">查询</button>
                        </i>
                        <p class="title">
                            <span class="sactive">名词短语结构/</span>
                            <span>动词短语结构/</span>
                            <span>句子成组短语结构/</span>
                            <span>句子分解短语结构/</span>
                        </p>
                        <div class="echartsShow clearfix">
                            <div></div>
                            <div></div>
                        </div>
                        <div class="clearfix">
                            <div></div>
                            <div></div>
                        </div>
                        <div class="clearfix">
                            <div></div>
                            <div></div>
                        </div>
                        <div class="clearfix">
                            <div></div>
                            <div></div>
                        </div>
                    </div>
                    <div class="month">
                        <i style="float: right;margin-top: 10px;">
                            <input type="month"> <button id="monthBtn">查询</button>
                        </i>
                        <p class="title">
                            <span class="sactive">名词短语结构/</span>
                            <span>动词短语结构/</span>
                            <span>句子成组短语结构/</span>
                            <span>句子分解短语结构/</span>
                        </p>
                        <div class="echartsShow clearfix">
                            <div></div>
                            <div></div>
                        </div>
                        <div class="clearfix">
                            <div></div>
                            <div></div>
                        </div>
                        <div class="clearfix">
                            <div></div>
                            <div></div>
                        </div>
                        <div class="clearfix">
                            <div></div>
                            <div></div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
</div>
</body>
<script src="${path}/static/js/hjs/moment.js"></script>
<script src="${path}/static/js/hjs/echarts.min.js"></script>
<script src="${path}/static/js/hjs/jquery.tmpl.min.js"></script>
<script src="${path}/static/js/hjs/api.js"></script>
<script type="text/x-jquery-tmpl" id="userInfoI">
    <div>
        <ul class="clearfix detail hlul">
            <li>
                <label>昵称：</label>
                <span>${child.name}</span>
            </li>
            <li>
                <label>性别：</label>
                <span>${child.sex == null ? '' : child.sex == "0" ? "男": "女" }</span>
            </li>
            <li>
                <label>出生日期：</label>
                <span><fmt:formatDate value='${child.birthdate}' pattern='yyyy-MM-dd'/></span>
            </li>
            <li>
                <label>居住地：</label>
                <span>${child.countiy}-${child.province}-${child.city}</span>
            </li>
            <li>
                <label>医学诊断：</label>
                <span>
                <c:if test="${child.medical=='0' }">自闭症</c:if>
                <c:if test="${child.medical=='1' }">语言发育迟缓（其他正常）</c:if>
                <c:if test="${child.medical=='2' }">单纯性智力低下（无自闭症）</c:if>
                <c:if test="${child.medical=='3' }">${child.medicalState}</c:if>
                <c:if test="${child.medical=='4' }">正常</c:if>
                </span>
            </li>
            <li>
                <label>第一语言：</label>
                <span>
                 <c:if test="${child.firstLanguage=='0' }">普通话</c:if>
                 <c:if test="${child.firstLanguage=='1' }">方言</c:if>
                 <c:if test="${child.firstLanguage=='10' }">${child.firstRests}</c:if>
                </span>
            </li>
            <li>
                <label>第二语言：</label>
                <span>
                 <c:if test="${child.secondLanguage=='0' }">普通话</c:if>
                 <c:if test="${child.secondLanguage=='1' }">方言</c:if>
                 <c:if test="${child.secondLanguage=='10' }">${child.secondRests}</c:if>
                </span>
                <span class="other"><%--<c:if test="${child.secondLanguage=='10' }">${child.secondRests}</c:if>--%></span>
            </li>
            <li>
                <label>父亲文化程度：</label>
                <span>
                <c:if test="${child.fatherCulture=='0' }">小学-高中</c:if>
                 <c:if test="${child.fatherCulture=='1' }">大学</c:if>
                 <c:if test="${child.fatherCulture=='2' }">硕士研究生</c:if>
                   <c:if test="${child.fatherCulture=='3' }">博士或类似</c:if>
                </span>
            </li>
            <li>
                <label>母亲文化程度：</label>
                <span>
                <c:if test="${child.motherCulture=='0' }">小学-高中</c:if>
                 <c:if test="${child.motherCulture=='1' }">大学</c:if>
                 <c:if test="${child.motherCulture=='2' }">硕士研究生</c:if>
                   <c:if test="${child.motherCulture=='3' }">博士或类似</c:if>
                </span>
            </li>
             <li>
                <label>目前尝试的训练及疗法：</label>
                <span>
                     <c:if test="${child.trainingMethod=='1' }">ABA</c:if>
                     <c:if test="${child.trainingMethod=='2' }"></c:if>
                     <c:if test="${child.trainingMethod=='3' }">无训练</c:if>

                 </span>
                <span class="other">
                    <c:if test="${child.trainingMethod=='2'}">${child.trainingRests}</c:if>
                </span>

            </li>
        </ul>

    </div>
</script>
<script type="text/x-jquery-tmpl" id="comprehensiveI">
    <li>
        <p class="text">总学习时长</p>
        <p class="number">${(data.sumStudyTime)}分钟</p>
    </li>
    <li>
        <p class="text">总测试进度</p>
        <p class="number">${data.statisticsList4 }%</p>
    </li>
    <li>
        <p class="text">名词结构学习进度</p>
        <p class="number">${data.statisticsList0.rate1*100}%</p>
    </li>
    <li>
        <p class="text">动词结构学习进度</p>
        <p class="number">${data.statisticsList1.rate1*100}%</p>
    </li>
    <li>
        <p class="text">句子组成学习进度</p>
        <p class="number">${data.statisticsList2.rate1*100}%</p>
    </li>
    <li>
        <p class="text">句子分解学习进度</p>
        <p class="number">${data.statisticsList3.rate1*100}%</p>
    </li>
</script>
<script>
    $(function () {

        function color(){
            $('.personal .number').each(function () {
                var red = parseInt(Math.random() * 257).toString(16);
                var blue = parseInt(Math.random() * 257).toString(16);
                var green = parseInt(Math.random() * 257).toString(16);
                var color = '#' + red + blue + green;
                $(this).css('color', color)
            })
        }
        var StudyInfoData;
        var arrWeek;
            var getInfo = function (year, month) {
                var d = new Date();
                d.setFullYear(year, month - 1, 1);
                var w1 = d.getDay();
                if (w1 == 0) w1 = 7;
                d.setFullYear(year, month, 0);
                var dd = d.getDate();
                if (w1 != 1) {
                    var d1 = 7 - w1 + 2;
                } else {
                    d1 = 1;
                }
                var sn = [];
                var week_count = Math.ceil((dd - d1 + 1) / 7);
                for (var i = 0; i < week_count; i++) {
                    var monday = d1 + i * 7;
                    var sunday = monday + 6;

                    if (monday < 10) {
                        monday = (Array(2).join(0) + monday).slice(-2);
                    }
                    if (sunday < 10) {
                        sunday = (Array(2).join(0) + sunday).slice(-2);
                    }//此处是为了获取的日期中的日如果小10前边的0会自动消失的处理
                    var from = year + "/" + month + "/" + monday;
                    var to;
                    if (sunday <= dd) {
                        to = year + "/" + month + "/" + sunday;
                    } else {
                        d.setFullYear(year, month - 1, sunday);
                        to = d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate();
                    }
                    var d11 = new Date(year + "-" + month + "-" + monday);
                    var d2 = new Date(year + "-" + month + "-" + monday);
                    d2.setMonth(0);
                    d2.setDate(1);
                    var rq = d11 - d2;
                    var s1 = Math.ceil(rq / (24 * 60 * 60 * 1000));
                    var s2 = Math.ceil(s1 / 7);

                    sn.push(s2)
                }
                return sn
            };

        var option = {
            title: {
                left: 'center',
                bottom: 0
            },
            tooltip: {
                trigger: 'axis'
            },
            // legend: {
            //     data:['注册人数','学习人数','通关人数']
            // },
            grid: {
                left: '2%',
                right: '20%',
                bottom: '10%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                // boundaryGap: false,
                data: []
            },
            yAxis: {

                max: 2100,
                splitNumber: 6,
                type: 'value'
            },
            series: []
        };
        $('#startDate').change(function () {
            if($(this).val() != ''){
                $('.week input[type=date]').eq(1).attr('min',$(this).val())
            }
        })
        $('#dayBtnday').click(function () {
            dayQuery({dayTime:$('.day input[type=date]').eq(0).val()})
        })
        $('#weekBtn').click(function () {
            weekQuery({startTime:$('.week input[type=date]').eq(0).val(),endTime:$('.week input[type=date]').eq(1).val()})
        })
        $('#monthBtn').click(function () {
            monthQuery({month:$('.month input[type=month]').val()})
        })

        //abc学习时长
        var abcTimeOption = JSON.parse(JSON.stringify(option))
        abcTimeOption.yAxis = {
            splitNumber: 6,
            name: '量分表',
            max: 300
        }
        abcTimeOption.xAxis.name = '学习时长/h'
        abcTimeOption.title.text = 'ABC量表成绩随总测试进度变化趋势图'
        abcTimeOption.series = [
            {
                name: '评估分数',
                type: 'line',
                stack: null,
            }
        ]
        //abc总测试进度
        var abcProgressOption = JSON.parse(JSON.stringify(option))
        abcProgressOption.yAxis = {
            splitNumber: 6,
            name: '量分表',
            max: 300
        }
        abcProgressOption.xAxis.interval = 10
        abcProgressOption.xAxis.name = '总测试进度/%'
        abcProgressOption.title.text = 'ABC量表成绩随总测试进度变化趋势图'
        abcProgressOption.series = [
            {
                name: '评估分数',
                type: 'line',
                stack: null,
            }
        ]
        //pcdi句子部分学习时长
        var pcdixTimeOption = JSON.parse(JSON.stringify(option))
        pcdixTimeOption.yAxis = {
            splitNumber: 6,
            name: '量分表',
            max: 100
        }
        pcdixTimeOption.xAxis.name = '学习时长/h'
        pcdixTimeOption.title.text = 'PCDI句子部分量表成绩随学习时长变化趋势图'
        pcdixTimeOption.series = [
            {
                name: '评估分数',
                type: 'line',
                stack: null,
            }
        ]
        //pcdi句子部分总测试进度
        var pcdixProgressOption = JSON.parse(JSON.stringify(option))
        pcdixProgressOption.yAxis = {
            splitNumber: 6,
            name: '量分表',
            max: 100
        }
        pcdixProgressOption.xAxis.name = '总测试进度/%'
        pcdixProgressOption.title.text = 'PCDI句子部分量表成绩随总测试进度变化趋势图'
        pcdixProgressOption.series = [
            {
                name: '评估分数',
                type: 'line',
                stack: null,
            }
        ]
        //pcdi词汇部分学习时长
        var pcdicTimeOption = JSON.parse(JSON.stringify(option))
        pcdicTimeOption.yAxis = {
            splitNumber: 6,
            name: '量分表',
            max: 800
        }
        pcdicTimeOption.xAxis.name = '学习时长/h'
        pcdicTimeOption.title.text = 'PCDI词汇部分量表成绩随学习时长趋势图'
        pcdicTimeOption.series = [
            {
                name: '评估分数',
                type: 'line',
                stack: null,
            }
        ]
        //pcdi词汇部分总测试进度
        var pcdicProgressOption = JSON.parse(JSON.stringify(option))
        pcdicProgressOption.yAxis = {
            splitNumber: 6,
            name: '量分表',
            max: 800
        }
        pcdicProgressOption.xAxis.name = '总测试进度/%'
        pcdicProgressOption.title.text = 'PCDI词汇部分量表成绩随总测试进度变化趋势图'
        pcdicProgressOption.series = [
            {
                name: '评估分数',
                type: 'line',
                stack: null,
            }
        ]
        //pcdi句子必填学习时长
        var pcdibTimeOption = JSON.parse(JSON.stringify(option))
        pcdibTimeOption.yAxis = {
            splitNumber: 6,
            name: '量分表',
            max: 120
        }
        pcdibTimeOption.xAxis.name = '学习时长/h'
        pcdibTimeOption.title.text = 'PCDI词汇必填量表成绩随学习时长趋势图'
        pcdibTimeOption.series = [
            {
                name: '评估分数',
                type: 'line',
                stack: null,
            }
        ]
        //pcdi词汇必填总测试进度
        var pcdibProgressOption = JSON.parse(JSON.stringify(option))
        pcdibProgressOption.yAxis = {
            splitNumber: 6,
            name: '量分表',
            max: 120
        }
        pcdibProgressOption.xAxis.name = '总测试进度/%'
        pcdibProgressOption.title.text = 'PCDI词汇必填量表成绩随总测试进度变化趋势图'
        pcdibProgressOption.series = [
            {
                name: '评估分数',
                type: 'line',
                stack: null,
            }
        ]
        //个人信息请求
        var id = $("#cid").val();
        get('study/getUserDataInfo',{id: id}).then(
            function (res) {
                if(res.code == 200){
                    res.data.child.birthdate = moment(res.data.child.birthdate).format('YYYY-MM-DD')
                    res.data.statisticsList.forEach(function (item) {
                        item.rate1 = item.rate1.toFixed(2)
                    })
                    $('#userInfo').empty();
                    $('#userInfoI').tmpl(res.data.child).appendTo('#userInfo')
                    $('#comprehensive').empty();
                    $('#comprehensiveI').tmpl(res.data).appendTo('#comprehensive')
                    color();
                    //ABC 2个
                    var abcTimeX = []
                    var abcProgressX = []
                    var abcTimeY = []
                    res.data.abcList.forEach(function (item) {
                        abcTimeX.push(((item.learning_time/60)/60).toFixed(2))
                        abcTimeY.push(item.score)
                        abcProgressX.push(item.rate_all*100)
                    })
                    abcTimeOption.xAxis.data = abcTimeX
                    abcTimeOption.series[0].data = abcTimeY
                    abcProgressOption.xAxis.data = abcProgressX
                    abcProgressOption.series[0].data = abcTimeY

                    //PCDI
                    //第一组 横着2个算一组
                    var pcdixTimeX = []
                    var pcdixTimeY = []
                    var pcdixProgressX = []
                    res.data.pcdiList.forEach(function (item,i) {
                        pcdixTimeX.push(((item.learning_time/60)/60).toFixed(2))
                        pcdixTimeY.push(item.score)
                        pcdixProgressX.push(item.rate_all*100)
                    })
                    pcdixTimeOption.xAxis.data = pcdixTimeX
                    pcdixTimeOption.series[0].data = pcdixTimeY
                    pcdixProgressOption.xAxis.data = pcdixProgressX
                    pcdixProgressOption.series[0].data = pcdixTimeY
                //    第二组
                    var pcdicTimeX = []
                    var pcdicTimeY = []
                    var pcdicProgressX = []
                    res.data.pcdiOpList.forEach(function (item,i) {
                        pcdicTimeX.push(((item.learning_time/60)/60).toFixed(2))
                        pcdicTimeY.push(item.score)
                        pcdicProgressX.push(item.rate_all*100)
                    })
                    pcdicTimeOption.xAxis.data = pcdicTimeX
                    pcdicTimeOption.series[0].data = pcdicTimeY
                    pcdicProgressOption.xAxis.data = pcdicProgressX
                    pcdicProgressOption.series[0].data = pcdicTimeY
                    //    第三组
                    var pcdibTimeX = []
                    var pcdibTimeY = []
                    var pcdibProgressX = []
                    res.data.pcdiMustList.forEach(function (item,i) {
                        pcdibTimeX.push(((item.learning_time/60)/60).toFixed(2))
                        pcdibTimeY.push(item.score)
                        pcdibProgressX.push(item.rate_all*100)
                    })
                    pcdibTimeOption.xAxis.data = pcdibTimeX
                    pcdibTimeOption.series[0].data = pcdibTimeY
                    pcdibProgressOption.xAxis.data = pcdibProgressX
                    pcdibProgressOption.series[0].data = pcdibTimeY


                    //pcdi 默认渲染
                    var pcdixTime = echarts.init(document.getElementById('pcdixTime'));
                    var pcdixProgress = echarts.init(document.getElementById('pcdixProgress'));
                    var pcdicTime = echarts.init(document.getElementById('pcdicTime'));
                    var pcdicProgress = echarts.init(document.getElementById('pcdicProgress'));
                    var pcdibTime = echarts.init(document.getElementById('pcdibTime'));
                    var pcdibProgress = echarts.init(document.getElementById('pcdibProgress'));
                    pcdixTime.setOption(pcdixTimeOption);
                    pcdixProgress.setOption(pcdixProgressOption);
                    pcdicProgress.setOption(pcdicProgressOption);
                    pcdicTime.setOption(pcdicTimeOption);
                    pcdibTime.setOption(pcdibTimeOption);
                    pcdibProgress.setOption(pcdibProgressOption);
                }
            },
            function (err) {
                console.log(err)
            }
        )

        //个人学习训练请求
        function getStudyInfo(params,callback){
            params = params || {}
            var id = $("#cid").val();
            params.id = id;
            return get('study/getUserStudyInfo',params).then(
                function (res) {
                    if(res.code == 200){
                        callback(res.data);

                    }
                },
                function (err) {
                    console.log(err)
                }
            )
        }

        $('.btn').click(function () {
            var ind = $(this).index();
            $('.btn').removeClass('active')
            $(this).addClass('active')
            $('.personalBox>div').removeClass('echartsShow')
            $('.personalBox>div').eq(ind).addClass('echartsShow')
            if (ind == 1) {
                //abc
                var abcTime = echarts.init(document.getElementById('abcTime'));
                var abcProgress = echarts.init(document.getElementById('abcProgress'));
                abcProgress.setOption(abcProgressOption);
                abcTime.setOption(abcTimeOption);
            }
        })

        function oneShow(obj){
            $('.'+obj+' span').removeClass('sactive')
            $('.'+obj+' span').eq(0).addClass('sactive')
            $('.'+obj+'>div').removeClass('echartsShow')
            $('.'+obj+'>div').eq(0).addClass('echartsShow')
        }
        function Default(){
            oneShow('day');
            getStudyInfo({timeType:'1'},function (data) {
                StudyInfoData = data;
                var dayNounOption = JSON.parse(JSON.stringify(option))
                dayNounOption.yAxis = {
                    splitNumber: 6,
                    name: '正确率/百分比',
                    max: 100
                }
                var X = []
                var Y = []
                data.nonuList.forEach(function (item,i) {
                    X.push('第'+(i+1)+'次')
                    Y.push(item.accuracy*100)
                });
                dayNounOption.xAxis.data = X
                dayNounOption.xAxis.name = '训练次数/次'
                dayNounOption.title.text = '名词结构短语本日数据统计'
                dayNounOption.title.bottom = 'none'
                dayNounOption.series = [
                    {
                        name: '正确率',
                        type: 'line',
                        stack: null,
                        // areaStyle: {
                        //     color:clolr1
                        // },
                        data: Y
                    }
                ]
                var day = echarts.init(document.getElementsByClassName('day')[0].children[2])
                day.setOption(dayNounOption,true);
            });
        }
        function dayQuery(params) {
            oneShow('day');
            params = params || {}
            params.timeType = '1'
            console.log(params)
            getStudyInfo(params,function (data) {
                StudyInfoData = data;
                var dayNounOption = JSON.parse(JSON.stringify(option))
                dayNounOption.yAxis = {
                    splitNumber: 6,
                    name: '正确率/百分比',
                    max: 100
                }
                var X = []
                var Y = []
                data.nonuList.forEach(function (item,i) {
                    X.push('第'+(i+1)+'次')
                    Y.push(item.accuracy*100)
                });
                dayNounOption.xAxis.data = X
                dayNounOption.xAxis.name = '训练次数/次'
                dayNounOption.title.text = '名词结构短语本日数据统计'
                dayNounOption.title.bottom = 'none'
                dayNounOption.series = [
                    {
                        name: '正确率',
                        type: 'line',
                        stack: null,
                        // areaStyle: {
                        //     color:clolr1
                        // },
                        data: Y
                    }
                ]
                var day = echarts.init(document.getElementsByClassName('day')[0].children[2])
                day.setOption(dayNounOption,true);
            });
        }
        Default();
        function weekQuery(params){
            oneShow('week');
            params = params || {}
            params.timeType = '2'
            getStudyInfo(params,function (data) {
                console.log(data)
                StudyInfoData = data;
                var weekNounOption = JSON.parse(JSON.stringify(option))
                weekNounOption.yAxis = {
                    splitNumber: 6,
                    name: '.          正确率/百分比',
                    max: 100
                }
                var X = []
                var timeX = []
                var Y = []
                var timeY = []
                data.nonuList.forEach(function (item,i) {
                    X.push(item.time)
                    Y.push(item.accuracy*100)
                });
                data.nounTimeList.forEach(function (item,i) {
                    timeX.push(item.time)
                    timeY.push(((item.stayTime/60)/60).toFixed(2))
                });
                console.log("time")
                weekNounOption.xAxis.data = X
                weekNounOption.xAxis.name = ''
                weekNounOption.title.text = '名词结构短语本周测试正确率统计'
                weekNounOption.series = [
                    {
                        name: '正确率',
                        type: 'line',
                        stack: null,
                        // areaStyle: {
                        //     color:clolr1
                        // },
                        data: Y
                    }
                ]

                var weekNounOptions = JSON.parse(JSON.stringify(weekNounOption))
                weekNounOptions.xAxis.data = timeX
                weekNounOptions.title.text = '名词结构短语本周学习时长统计'
                weekNounOptions.yAxis = {
                    splitNumber: 6,
                    name: '.           学习时长/h',
                    max: null
                }
                weekNounOptions.series = [
                    {
                        name: '学习时长',
                        type: 'line',
                        stack: null,
                        // areaStyle: {
                        //     color:clolr1
                        // },
                        data: timeY
                    }
                ]

                var dom = document.getElementsByClassName('week')[0].children[2];
                var week1 = echarts.init(dom.children[0])
                var week2 = echarts.init(dom.children[1])
                week1.setOption(weekNounOption,true);
                week2.setOption(weekNounOptions,true);
            })
        }
        function monthQuery(params){
            oneShow('month');
            params = params || {}
            params.timeType = '3'
            getStudyInfo(params,function (data) {
                StudyInfoData = data;
                var monthNounOption = JSON.parse(JSON.stringify(option))
                arrWeek = getInfo(data.time.split('-')[0],data.time.split('-')[1])
                var weeks = [];
                var weeks2 = [];
                var Percentage = [];
                var stayTime = [];
                data.nonuList.forEach(function (item,i) {
                    /*for(var key in data){
                        if(data[key][i]){
                            if(item < parseInt(data[key][i].weeks)){
                                if(i==0){
                                    data[key].unshift({stayTime:0,accuracy:0})
                                }else{
                                    data[key].splice(i-1,0,{stayTime:0,accuracy:0})
                                }
                            }
                        }else{
                            data[key].push({stayTime:0,accuracy:0})
                        }
                    }*/


                    weeks.push('第'+(i+1)+'周')
                    Percentage.push(item.accuracy*100)
                   /* stayTime.push(((data.nonuList[i].stayTime/60)/60).toFixed(2))*/
                })
                data.nounTimeList.forEach(function (item,i) {

                    weeks2.push('第'+(i+1)+'周')
                    stayTime.push(((item.stayTime/60)/60).toFixed(2))
                })
                monthNounOption.yAxis = {
                    splitNumber: 6,
                    name: '正确率/百分比',
                    max: 100
                }
                monthNounOption.xAxis.data = weeks
                monthNounOption.xAxis.name = ''
                monthNounOption.title.text = '名词结构短语本月测试正确率数据统计'
                monthNounOption.series = [
                    {
                        name: '正确率',
                        type: 'line',
                        stack: null,
                        // areaStyle: {
                        //     color:clolr1
                        // },
                        data: Percentage
                    }
                ]
                var monthNounOptions = JSON.parse(JSON.stringify(monthNounOption));
                monthNounOptions.title.text = '名词结构短语本月学习时长数据统计'
                monthNounOptions.xAxis.data = weeks2
                monthNounOptions.yAxis = {
                    splitNumber: 6,
                    name: '学习时长/h',
                    max: null
                }
                monthNounOptions.series = [
                    {
                        name: '学习时长',
                        type: 'line',
                        stack: null,
                        // areaStyle: {
                        //     color:clolr1
                        // },
                        data: stayTime
                    }
                ]
                var dom = document.getElementsByClassName('month')[0].children[2];
                var month1 = echarts.init(dom.children[0])
                var month2 = echarts.init(dom.children[1])
                month1.setOption(monthNounOption,true);
                month2.setOption(monthNounOptions,true);
            })
        }
        $('.btn1').click(function () {
            var ind = $(this).index()
            $('.btn1').removeClass('active')
            $(this).addClass('active')
            $('.box>div').removeClass('echartsShow')
            $('.box>div').eq(ind).addClass('echartsShow')
            if (ind == 0) {
                Default();
            } else if (ind == 1) {
                weekQuery();
            } else if (ind == 2) {
                monthQuery();
            }
        });
        $('.day span').click(function () {
            var ind = $(this).index();
            $('.day span').removeClass('sactive')
            $(this).addClass('sactive')
            $('.day>div').removeClass('echartsShow')
            $('.day>div').eq(ind).addClass('echartsShow')
            var dayNounOption = JSON.parse(JSON.stringify(option))
            var datas;
            function indexS(json) {
                var X = [];
                var Y = []
                StudyInfoData[json].forEach(function (item,i) {
                   X.push('第'+(i+1)+'次');
                   Y.push(item.accuracy*100);
                })
                var text;
                if(json == 'vebList'){
                    text = '动词结构短语本日数据统计'
                }else if(json == 'sentenceGroupList'){
                    text = '句子成组短语本日数据统计'
                }else{
                    text = '句子分解短语本日数据统计'
                }
                return {
                    text:text,
                    Y:Y,
                    X:X,
                }
            }
            if(ind == 0){
                Default();
            }else if(ind == 1){
                datas = indexS('vebList')
            }else if(ind == 2){
                datas = indexS('sentenceGroupList')
            }else{
                datas = indexS('sentenceResolve')
            }
            dayNounOption.yAxis = {
                splitNumber: 6,
                name: '    正确率/百分比',
                max: 100
            }
            if(ind != 0){
                dayNounOption.xAxis.data = datas.X
                dayNounOption.xAxis.name = '训练次数/次'
                dayNounOption.title.text = datas.text
                dayNounOption.title.bottom = 'none'
                dayNounOption.series = [
                    {
                        name: '正确率',
                        type: 'line',
                        stack: null,
                        // areaStyle: {
                        //     color:clolr1
                        // },
                        data: datas.Y
                    }
                ]
                var day = echarts.init(document.getElementsByClassName('day')[0].children[ind + 2])
                day.setOption(dayNounOption);
            }
        })
        $('.week span').click(function () {
            var ind = $(this).index();
            $('.week span').removeClass('sactive')
            $(this).addClass('sactive')
            $('.week>div').removeClass('echartsShow')
            $('.week>div').eq(ind).addClass('echartsShow')
            var weekNounOption = JSON.parse(JSON.stringify(option))
            var datas;
            var datas2;
            function indexS(json) {
                 var X = [];
                var X2 = [];
                var Y = []
                var Y2 = []
                StudyInfoData[json].forEach(function (item,i) {
                    X.push(item.time);
                    Y.push(item.accuracy*100);
                })
                StudyInfoData[json].forEach(function (item,i) {
                    X2.push(item.time);
                    Y2.push(((item.stayTime/60)/60).toFixed(2));
                })
                var text1;
                var text2;
                if(json == 'nonuList'){
                    text1 = '名词结构短语本周测试正确率统计'
                    text2 = '名词结构短语本周学习市场统计'
                }else if(json == 'vebList'){
                    text1 = '动词结构短语本周测试正确率统计'
                    text2 = '动词结构短语本周学习市场统计'
                }else if(json == 'sentenceGroupList'){
                    text1 = '句子成组结构短语本周测试正确率统计'
                    text2 = '句子成组结构短语本周学习市场统计'
                }else{
                    text1 = '句子分解结构短语本周测试正确率统计'
                    text2 = '句子分解结构短语本周学习市场统计'
                }
                return {
                    text1:text1,
                    text2:text2,
                    Y:Y,
                    Y2:Y2,
                    X:X,
                    X2:X2,
                }
            }
            if(ind == 0){
                datas = indexS('nonuList')
                datas2 = indexS('nounTimeList');
            }else if(ind == 1){
                datas = indexS('vebList')
                datas2 = indexS('vebTimeList')
            }else if(ind == 2){
                datas = indexS('sentenceGroupList')
                datas2 = indexS('sentenceTimeList')
            }else{
                datas = indexS('sentenceResolve')
                datas2 = indexS('sentenceResolveTimeList')
            }
            weekNounOption.yAxis = {
                splitNumber: 6,
                name: '.            正确率/百分比',
                max: 100
            }
            weekNounOption.xAxis.data = datas.X
            weekNounOption.xAxis.name = '训练次数/次'
            weekNounOption.title.text = datas.text1
            weekNounOption.series = [
                {
                    name: '正确率',
                    type: 'line',
                    stack: null,
                    // areaStyle: {
                    //     color:clolr1
                    // },
                    data: datas.Y
                }
            ]
            var dom = document.getElementsByClassName('week')[0].children[ind+2];
            var week1 = echarts.init(dom.children[0])
            var week2 = echarts.init(dom.children[1])
            week1.setOption(weekNounOption);
            var weekNounOptions = JSON.parse(JSON.stringify(weekNounOption));
            weekNounOptions.xAxis.data = datas2.X2
            weekNounOptions.yAxis = {
                splitNumber: 6,
                name: '.                  学习时长/h',
                max: null
            }
            weekNounOptions.series = [
                {
                    name: '学习时长',
                    type: 'line',
                    stack: null,
                    // areaStyle: {
                    //     color:clolr1
                    // },
                    data: datas2.Y2
                }
            ]
            weekNounOptions.title.text = datas.text2
            week2.setOption(weekNounOptions);
        })
        $('.month span').click(function () {
            var ind = $(this).index();
            $('.month span').removeClass('sactive')
            $(this).addClass('sactive')
            $('.month>div').removeClass('echartsShow')
            $('.month>div').eq(ind).addClass('echartsShow')
            var monthNounOption = JSON.parse(JSON.stringify(option))
            var datas;
            var datas2;
            function indexS(json) {
                var X = [];
                var Y = []
                var X2 = [];
                var Y2 = []
                StudyInfoData[json].forEach(function (item,i) {
                    X.push('第'+(i+1)+'周');
                    X2.push('第'+(i+1)+'周');
                    Y.push(item.accuracy*100);
                    Y2.push(((item.stayTime/60)/60).toFixed(2));
                })
                var text1;
                var text2;
                if(json == 'nonuList'){
                    text1 = '名词结构短语本月测试正确率统计'
                    text2 = '名词结构短语本月学习市场统计'
                }else if(json == 'vebList'){
                    text1 = '动词结构短语本月测试正确率统计'
                    text2 = '动词结构短语本月学习市场统计'
                }else if(json == 'sentenceGroupList'){
                    text1 = '句子成组结构短语本月测试正确率统计'
                    text2 = '句子成组结构短语本月学习市场统计'
                }else{
                    text1 = '句子分解结构短语本月测试正确率统计'
                    text2 = '句子分解结构短语本月学习市场统计'
                }
                return {
                    text1:text1,
                    text2:text2,
                    Y:Y,
                    Y2:Y2,
                    X:X,
                }
            }
            if(ind == 0){
                datas = indexS('nonuList')
                datas2 = indexS('nounTimeList')
            }else if(ind == 1){
                datas = indexS('vebList')
                datas2 = indexS('vebTimeList')
            }else if(ind == 2){
                datas = indexS('sentenceGroupList')
                datas2 = indexS('sentenceTimeList')
            }else{
                datas = indexS('sentenceResolve')
                datas2 = indexS('sentenceResolveTimeList')
            }
            monthNounOption.yAxis = {
                splitNumber: 6,
                name: '正确率/百分比',
                max: 100
            }
            monthNounOption.xAxis.data = datas.X
            monthNounOption.xAxis.name = '训练次数/次'
            monthNounOption.title.text = datas.text1
            monthNounOption.series = [
                {
                    name: '测试正确率',
                    type: 'line',
                    stack: null,
                    // areaStyle: {
                    //     color:clolr1
                    // },
                    data: datas.Y
                }
            ]
            var dom = document.getElementsByClassName('month')[0].children[ind+2];
            var month1 = echarts.init(dom.children[0])
            var month2 = echarts.init(dom.children[1])
            month1.setOption(monthNounOption);
            var monthNounOptions = JSON.parse(JSON.stringify(monthNounOption));
            monthNounOptions.yAxis = {
                splitNumber: 6,
                name: '学习时长/h',
                max: null
            }
            monthNounOptions.series = [
                {
                    name: '学习时长',
                    type: 'line',
                    stack: null,
                    // areaStyle: {
                    //     color:clolr1
                    // },
                    data: datas2.Y2
                }
            ]
            monthNounOptions.title.text = datas.text2
            month2.setOption(monthNounOptions);
        })
        $("#btn-cancel").click(function () {
            window.history.go(-1);
        });
    })
    $('#side-menu').siblings().removeClass("active");
    $('#workbench_menu').addClass("active");
    $('#workbenchmenu2').addClass("active");
</script>
</html>
