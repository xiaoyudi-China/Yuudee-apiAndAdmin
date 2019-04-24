<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/static/common/taglibs.jsp" %>
<html>
<head>
    <title>工作台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/jsp/demo/public/headCssJs.jsp"%>
    <style>
       /* html, body {
            padding: 20px;
            margin: 0;
            background: rgba(204, 204, 204, 0.17);
        }*/

        a {
            color: #000;
        }

        .tjul, li, ol {
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

       /* body > div {
            width: 100%;
            !*margin: 20px;*!
            padding: 16px;
            border-radius: 5px;
            background: #fff;
        }*/

        .humanStatistics li {
            width: 10%;
            height: 80px;
            background: rgba(204, 204, 204, 0.24);
            margin-right: 5%;
            margin-bottom: 20px;
            border-radius: 10px;
            text-align: center;
            padding: 10px 0;
            box-sizing: border-box;
        }

        .humanStatistics li:nth-child(7) {
            margin-right: 0;
        }

        .humanStatistics li:last-child {
            margin-right: 0;
        }

        .number {
            font-size: 28px;
            font-weight: bold;
        }
        .text{
            font-size: 14px;
        }
        #allPeopleTrend,#dayPeopleTrend,#studyPeopleTrend{
            height: 500px;
            width: 100%;
        }
        input,button{
            outline: none;
        }
        .btn{
            background: #4cb2eb;
            border: none;
            border-radius: 5px;
            color: #fff;
            padding: 5px 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/demo/public/menu.jsp"%>
    <%@ include file="/WEB-INF/jsp/demo/public/pageTop.jsp"%>
    <div  id="page-wrapper" class="gray-bg">

        <div  class="humanStatistics marB">
            <h2 style="margin: 0px 0px 10px 5px">总人数统计</h2>
            <ul class="clearfix tjul">

            </ul>
        </div>
        <div class="marB">
            <div class="clearfix">
                <div class="r" id="zong">
                    <input type="date"><span> ~ </span><input type="date">
                    <button class="btn">筛选</button>
                </div>
            </div>
            <div id="allPeopleTrend">

            </div>
        </div>
        <div class="marB">
            <div class="clearfix">
                <div class="r" id="mr">
                    <input type="date"><span> ~ </span><input type="date">
                    <button class="btn">筛选</button>
                </div>
            </div>
            <div id="dayPeopleTrend">

            </div>
        </div>
        <div>
            <div class="clearfix">
                <div class="r" id="mk">
                    <input type="date"><span> ~ </span><input type="date">
                    <button class="btn">筛选</button>
                </div>
            </div>
            <div id="studyPeopleTrend">

            </div>
        </div>
    </div>
</div>
</body>
</html>
<script id="list2" type="text/x-jquery-tmpl">

    <li class="l">
            <p class="number">${data.registerCount}</p>
            <p class="text">注册人数</p>
        </li>
        <li class="l">
            <p class="number">${data.TrainingCount}</p>
            <p class="text">训练体验人数</p>
        </li>
        <li class="l">
            <p class="number">${data.testCount}</p>
            <p class="text">测试体验人数</p>
        </li>
        <li class="l">
            <p class="number">${data.prefect}</p>
            <p class="text">完善信息人数</p>
        </li>
        <li class="l">
            <p class="number">${data.pcdiMustCount}</p>
            <p class="text">PCDI必填问卷人数</p>
        </li>
        <li class="l">
            <p class="number">${data.pcdiOpCount}</p>
            <p class="text">PCDI选填问卷人数</p>
        </li>
        <li class="l">
            <p class="number">${data.abcCount}</p>
            <p class="text">ABC问卷人数</p>
        </li>
        <li class="l">
            <p class="number">${data.nonuTrainingCount}</p>
            <p class="text">学习人数</p>
        </li>
        <li class="l">
            <p class="number">${data.nounPlayerCount}</p>
            <p class="text">名词通关人数</p>
        </li>
        <li class="l">
            <p class="number">${data.vebTrainingCount}</p>
            <p class="text">动词学习人数</p>
        </li>
        <li class="l">
            <p class="number">${data.vebPlayerCount}</p>
            <p class="text">动词通关人数</p>
        </li>
        <li class="l">
            <p class="number">${data.senGroupTrainCount}</p>
            <p class="text">句子成组学习人数</p>
        </li>
        <li class="l">
            <p class="number">${data.senGroupPlayerCount}</p>
            <p class="text">句子成组通关人数</p>
        </li>
        <li class="l">
            <p class="number">${data.sumPlayerCount}</p>
            <p class="text">通关人数</p>
        </li>
</script>
<script src="${path}/static/js/hjs/echarts.min.js"></script>
<script src="${path}/static/js/hjs/jquery.tmpl.min.js"></script>
<script src="${path}/static/js/hjs/api.js"></script>
<script>
   $(function () {
       /*   get('workbench/getWorkCount').then(
            function (res) {
                if(res.code == 200){
                    console.log(res.data);
                    $('.tjul').empty();
                    $('#list2').tmpl().appendTo('.tjul');
                    $('.humanStatistics .number').each(function () {
                        var red = parseInt(Math.random()*257).toString(16);
                        var blue = parseInt(Math.random()*257).toString(16);
                        var green= parseInt(Math.random()*257).toString(16);
                        var color = '#'+red+blue+green;
                        $(this).css('color',color)
                    })
                }
            },
        function (err) {
            console.log(err)
        }
        );*/
                    $('.tjul').empty();
                    $('#list2').tmpl().appendTo('.tjul');
                    $('.humanStatistics .number').each(function () {
                        var red = parseInt(Math.random()*257).toString(16);
                        var blue = parseInt(Math.random()*257).toString(16);
                        var green= parseInt(Math.random()*257).toString(16);
                        var color = '#'+red+blue+green;
                        $(this).css('color',color)
                    });

        var all = echarts.init(document.getElementById('allPeopleTrend'));
        var day = echarts.init(document.getElementById('dayPeopleTrend'));
        var study = echarts.init(document.getElementById('studyPeopleTrend'));
        var option = {
            title: {
                text: '总人数累计增长趋势图'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                selectedMode:false,
                data:['注册人数','学习人数','通关人数']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['8-1','8-5','8-10','8-15','8-20','8-25','8-30'],
                // axisTick: {
                //     inside: true,
                //     show: true,
                //     // splitNumber:1,
                //     alignWithLabel: true,
                //     interval: 2,
                //     length: 5,
                //     lineStyle: {
                //         color: '#406485'
                //     }
                // },
                // axisLine: {
                //     show: false//是否显示坐标轴
                // },
                axisLabel: {
                    show: true,
                    interval: 3,

                },
            },
            yAxis: {
                splitNumber:6,
                type: 'value'
            },
            series: [
                {
                    name:'注册人数',
                    type:'line',
                    stack: null,
                    // areaStyle: {
                    //     color:clolr1
                    // },
                    data:[0, 132, 181, 214, 290, 370, 430]
                },
                {
                    name:'学习人数',
                    type:'line',
                    stack: null,
                    // areaStyle: {
                    //     color:clolr2
                    // },
                    data:[0, 152, 191, 234, 320, 390, 450]
                },
                {
                    name:'通关人数',
                    type:'line',
                    stack: null,
                    // areaStyle: {
                    //     color:clolr3
                    // },
                    data:[0, 182, 201, 264, 350, 410, 480]
                },
            ]
        };
        //第一个图表
        function getWorkSum(params){
           params = params || {}
           get('workbench/getWorkSum',params).then(
               function (res) {
                   if(res.code == 200){
                       console.log(res)
                       var X = []
                       var Y1 = []
                       var Y2 = []
                       var Y3 = []
                       res.data.regisList.forEach(function (item,i) {
                           X.push(item.days)
                           Y1.push(item.total)
                           Y2.push(res.data.stayList[i].total)
                           Y3.push(res.data.playerList[i].total)
                       })
                       option.xAxis.data = X;
                       option.series[0].data = Y1;
                       option.series[1].data = Y2;
                       option.series[2].data = Y3;
                       all.setOption(option,true);
                   }
               },
            function (err) {
                console.log(res)
            }
           )
        }
        getWorkSum();
        $('#zong input').eq(0).change(function () {
            $('#zong input').eq(1).attr('min',$('#zong input').eq(0).val())
        })
        $('#zong button').click(function () {
            var params = {
                startTime:$('#zong input').eq(0).val(),
                endTime:$('#zong input').eq(1).val(),
            }
            getWorkSum(params)
        });

        //第二个图表
        function getWorkday(params) {
            params = params || {}
            get('workbench/getWorkday',params).then(
                function (res) {
                    console.log(res)
                    if(res.code == 200){
                        var Doption = JSON.parse(JSON.stringify(option));
                        Doption.title.text = '每日用户统计趋势图'
                        var X = []
                        var Y1 = []
                        var Y2 = []
                        var Y3 = []
                        res.data.regisList.forEach(function (item,i) {
                            X.push(item.days)
                            Y1.push(item.coun)
                            Y2.push(res.data.stayList[i].coun)
                            Y3.push(res.data.playerList[i].coun)
                        })
                        Doption.xAxis.data = X;
                        Doption.series[0].data = Y1;
                        Doption.series[1].data = Y2;
                        Doption.series[2].data = Y3;
                        day.setOption(Doption,true);

                    }
                },
                function (err) {
                    console.log(res)
                }
            )
        }
        getWorkday();
        $('#mr button').click(function () {
            var params = {
                startTime:$('#mr input').eq(0).val(),
                endTime:$('#mr input').eq(1).val(),
            }
            getWorkday(params)
        });
        //第三个图表
        function getWorkModule(params) {
            params = params || {}
            get('workbench/getWorkModule',params).then(
                function (res) {
                    console.log(res)
                    if(res.code == 200){
                        var Soption = JSON.parse(JSON.stringify(option));
                        Soption.title.text = '各模块学习用户趋势图'
                        Soption.legend.data = ['名次结构','动次结构训练','句子成组结构训练','句子分解结构训练',]
                        var X = []
                        var Y1 = []
                        var Y2 = []
                        var Y3 = []
                        var Y4 = []
                        res.data.nonuList.forEach(function (item,i) {
                            X.push(item.days)
                            Y1.push(item.coun)
                            Y2.push(res.data.vebList[i].coun)
                            Y3.push(res.data.sentResoList[i].coun)
                            Y4.push(res.data.sentGrepList[i].coun)
                        })
                        Soption.xAxis.data = X;
                        Soption.series = [
                            {
                                name:'名次结构',
                                type:'line',
                                stack: null,
                                // areaStyle: {
                                //     color:clolr1
                                // },
                                data:Y1
                            },
                            {
                                name:'动次结构训练',
                                type:'line',
                                stack: null,
                                // areaStyle: {
                                //     color:clolr2
                                // },
                                data:Y2
                            },
                            {
                                name:'句子分解结构训练',
                                type:'line',
                                stack: null,
                                // areaStyle: {
                                //     color:clolr3
                                // },
                                data:Y3
                            },
                            {
                                name:'句子成组结构训练',
                                type:'line',
                                stack: null,
                                // areaStyle: {
                                //     color:clolr3
                                // },
                                data:Y4
                            },

                        ]
                        study.setOption(Soption,true);
                    }
                },
                function (err) {
                    console.log(res)
                }
            )
        }
        getWorkModule();
        $('#mk button').click(function () {
            var params = {
                startTime:$('#mk input').eq(0).val(),
                endTime:$('#mk input').eq(1).val(),
            }
            getWorkModule(params)
        });

    })
    $('#side-menu').siblings().removeClass("active");
    $('#workbench_menu').addClass("active");
    $('#workbenchmenu0').addClass("active");
</script>

