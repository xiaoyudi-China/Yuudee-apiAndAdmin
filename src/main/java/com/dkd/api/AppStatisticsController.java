package com.dkd.api;

import com.dkd.common.constant.ResultStant;
import com.dkd.common.utils.DateUtil;
import com.dkd.common.utils.IsObjectNullUtils;
import com.dkd.common.utils.TokenProccessor;
import com.dkd.model.XydChild;
import com.dkd.model.XydGroupTraining;
import com.dkd.model.XydParents;
import com.dkd.model.XydSystemStatistics;
import com.dkd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * Created by mai xiaogang on 2018/10/10.
 * 儿童学习统计
 */
@RestController
@RequestMapping(value = "/app/statistics")
public class AppStatisticsController {
    @Autowired
    private XydChildService xydChildService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    private XydSystemStatisticsService systemStatisticsService;
    @Autowired
    private XydTrainingResultService xydTrainingResultService;
    @Autowired
    private XydAnswerRecordService xydAnswerRecordService;

    /**
     *  训练档案、按天查询统计学习情况记录
     * @param token
     * @param module 1 名词 2 动词 3 句子成组 4句子分解'
     * @param scene 1训练 2测试 3意义
     * @return
     */
    @RequestMapping(value = "/noun/dayInfo")
    public Map getNounDayInfo(@RequestParam(value = "token")String token, @RequestParam(value = "module")String module,
                              @RequestParam(value = "scene")String scene){
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(module) || IsObjectNullUtils.is(scene)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法相关信息，请稍后重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                Map<String, Object> dataMap = new HashMap();
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (IsObjectNullUtils.is(xydParents.getChildId())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "您还没有完善儿童信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                if (IsObjectNullUtils.is(xydChild) || !"1".equals(xydChild.getPerfection())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "您还没有完善儿童信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
                xydSystemStatistics.setUserId(xydParents.getChildId());
                xydSystemStatistics.setStates("1");
                xydSystemStatistics.setModule(module);
                List<XydSystemStatistics> statisticsList = systemStatisticsService.selectByList(xydSystemStatistics);
                if (!IsObjectNullUtils.is(statisticsList) && statisticsList.size() > 0){
                    if (Integer.parseInt(module) > Integer.parseInt(statisticsList.get(0).getModule())){
                        resultMap.put("code", ResultStant.RESULT_CODE_NULL);
                        resultMap.put("msg", "该模块还没有训练记录,");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_NULL);
                    resultMap.put("data", "");
                    resultMap.put("msg", "该模块还没有训练记录,");
                    return resultMap;
                }
                //获取通关进度和总学习时长
                dataMap.put("countTime", statisticsList.get(0).getLearningTime());
                dataMap.put("schedule", statisticsList.get(0).getRate1());
                //获取近五天的记录
                Date timenext = new Date();
                List<Map> mapList = new ArrayList<>();
                for(int i = 0; i < 5; i++){
                    //Date time = DateUtil.getDayZeroClock(new Date(), i);
                   List<XydGroupTraining> dayList = xydTrainingResultService.selectDayResultList(xydParents.getChildId(), timenext, scene, module);
                   //统计当天学习时长
                   Integer studyTime = xydTrainingResultService.selectDayCountTime(xydParents.getChildId(), timenext, module);
                    Map<String, Object> map = new HashMap<>();
                    map.put("str", DateUtil.getDate(timenext));
                    map.put("time", timenext);
                    map.put("dayResultList", dayList);
                    map.put("studyTime", studyTime == null ? 0 : studyTime);
                   if ((IsObjectNullUtils.is(dayList) && IsObjectNullUtils.is(studyTime)) || (dayList.size() < 1) && studyTime == 0){

                   }else {
                       mapList.add(map);
                   }
                   timenext = DateUtil.getPriorDayByDate(timenext);
                }
                dataMap.put("resultList", mapList);
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("data", dataMap);
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                resultMap.put("msg", "无法获取用户信息，请重新登录!");
                resultMap.put("data", "");
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统异常，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     *  训练档案、按周查询统计学习情况记录
     * @param token
     * @param module 1 名词 2 动词 3 句子成组 4句子分解'
     * @param scene 1训练 2测试 3意义
     * @return
     */
    @RequestMapping(value = "/noun/weekInfo")
    public Map getNounWeekInfo(@RequestParam(value = "token")String token, @RequestParam(value = "module")String module,
                              @RequestParam(value = "scene")String scene){
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(module) || IsObjectNullUtils.is(scene)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法相关信息，请稍后重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                Map<String, Object> dataMap = new HashMap();
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (IsObjectNullUtils.is(xydParents.getChildId())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "您还没有完善儿童信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                if (IsObjectNullUtils.is(xydChild) || !"1".equals(xydChild.getPerfection())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "您还没有完善儿童信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
                xydSystemStatistics.setUserId(xydParents.getChildId());
                xydSystemStatistics.setStates("1");
                xydSystemStatistics.setModule(module);
                List<XydSystemStatistics> statisticsList = systemStatisticsService.selectByList(xydSystemStatistics);
                if (!IsObjectNullUtils.is(statisticsList) && statisticsList.size() > 0){
                    if (Integer.parseInt(module) > Integer.parseInt(statisticsList.get(0).getModule())){
                        resultMap.put("code", ResultStant.RESULT_CODE_NULL);
                        resultMap.put("msg", "该模块还没有训练记录,");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_NULL);
                    resultMap.put("msg", "该模块还没有训练记录,");
                    resultMap.put("data", "");
                    return resultMap;
                }
                //获取通关进度和总学习时长
                dataMap.put("countTime", statisticsList.get(0).getLearningTime());
                dataMap.put("schedule", statisticsList.get(0).getRate1());
                //获取近4周的记录
                List<Map> mapList = new ArrayList<>();
                Date time =new Date();
                for(int i = 0; i < 4; i++){
                    List<Object[]> timeList = xydTrainingResultService.selectWeekStayResultList(xydParents.getChildId(), DateUtil.getWeekFirstDay(time), DateUtil.getWeekLastDay(time), null, module);
                    List<Map<String, Object>> accuracyList1 = xydTrainingResultService.selectWeekAccuracyList(xydParents.getChildId(), DateUtil.getWeekFirstDay(time), DateUtil.getWeekLastDay(time), scene,"1", module);
                    if (!IsObjectNullUtils.is(accuracyList1) && accuracyList1.size() > 0 && accuracyList1.get(0) == null){
                        accuracyList1 = new ArrayList<>();
                    }
                    //按固定格式封装
                    Map<String, Object> map = new HashMap<>();
                    map.put("accuracyList", accuracyList1);
                    map.put("timeList", timeList);
                    map.put("weekFirstDay", DateUtil.getWeekFirstDay(time));
                    map.put("weekLastDay", DateUtil.getWeekLastDay(time));
                    //如果当前没有数据 则不传
                    if ((IsObjectNullUtils.is(timeList) && IsObjectNullUtils.is(accuracyList1)) || (timeList.size() <1 && accuracyList1.size() < 1)){

                    }else {
                        mapList.add(map);
                    }
                    time = DateUtil.getLastWeekDate(time);
                }
                dataMap.put("resultList", mapList);
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("data", dataMap);
                resultMap.put("msg", "");
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                resultMap.put("msg", "无法获取用户信息，请重新登录!");
                resultMap.put("data", "");
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统异常，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }



    /**
     *  训练档案、按月查询统计学习情况记录
     * @param token
     * @param module 1 名词 2 动词 3 句子成组 4句子分解'
     * @param scene 1训练 2测试 3意义
     * @return
     */
    @RequestMapping(value = "/noun/monthInfo")
    public Map getNounMonthInfo(@RequestParam(value = "token")String token, @RequestParam(value = "module")String module,
                               @RequestParam(value = "scene")String scene){
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(module) || IsObjectNullUtils.is(scene)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法相关信息，请稍后重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                Map<String, Object> dataMap = new HashMap();
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (IsObjectNullUtils.is(xydParents.getChildId())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "您还没有完善儿童信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                if (IsObjectNullUtils.is(xydChild) || !"1".equals(xydChild.getPerfection())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "您还没有完善儿童信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
                xydSystemStatistics.setUserId(xydParents.getChildId());
                xydSystemStatistics.setStates("1");
                xydSystemStatistics.setModule(module);
                List<XydSystemStatistics> statisticsList = systemStatisticsService.selectByList(xydSystemStatistics);
                if (!IsObjectNullUtils.is(statisticsList) && statisticsList.size() > 0){
                    if (Integer.parseInt(module) > Integer.parseInt(statisticsList.get(0).getModule())){
                        resultMap.put("code", ResultStant.RESULT_CODE_NULL);
                        resultMap.put("msg", "该模块还没有训练记录,");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_NULL);
                    resultMap.put("msg", "该模块还没有训练记录,");
                    resultMap.put("data", "");
                    return resultMap;
                }
                //获取通关进度和总学习时长
                dataMap.put("countTime", statisticsList.get(0).getLearningTime());
                dataMap.put("schedule", statisticsList.get(0).getRate1());
                //获取当前的月份
                int month = DateUtil.getMonth(new Date());
                Date date = new Date();
                List monthResultList = new ArrayList<>();
                for (int j = 0; j < month; j++){
                    int flag=0;
                    //获取当前月天数
                    int monthDay = DateUtil.getCurrentMonthDay(date);
                    //获取下个月的第一天
                    Date nextMonthOneDaytime = DateUtil.getFirstDayByNextMonth(date);
                    int countWeek = monthDay%7 > 0 ? (monthDay/7+1): monthDay/7;
                    //获取当前月的第一天
                    Date monthOneDaytime = DateUtil.getFirstDayByDate(date);
                    Date nextWeekTime = DateUtil.getDayZeroClock(monthOneDaytime, -7);
                    List<Map> mapList = new ArrayList<>();
                    //获取近4周的记录
                    for(int i = 0; i < countWeek; i++){
                        if (i == 4){
                            nextWeekTime =  nextMonthOneDaytime;    //最后不够七天的情况
                        }
                        List<Map<String, Object>> timeList = xydTrainingResultService.selectWeekByMonthStayResultList(xydParents.getChildId(), monthOneDaytime, nextWeekTime, null, module);
                        if (IsObjectNullUtils.is(timeList) || timeList.get(0) == null){
                            timeList = new ArrayList<>();
                        }
                        List<Map<String, Object>> accuracyList = xydTrainingResultService.selectWeekAccuracyList(xydParents.getChildId(), monthOneDaytime, nextWeekTime, scene,"1", module);
                        if ((IsObjectNullUtils.is(timeList) && IsObjectNullUtils.is(accuracyList)) || (timeList.size() <1 && accuracyList.size() < 1)){
                            flag++;
                        }
                        Map<String, Object> map = new HashMap<>();
                        map.put("timeList", timeList);
                        map.put("weekFirstDay", DateUtil.getDate(monthOneDaytime));
                        map.put("weekLastDay", DateUtil.getDate(nextWeekTime));
                        monthOneDaytime = nextWeekTime;
                        nextWeekTime = DateUtil.getDayZeroClock(monthOneDaytime, -7);
                        map.put("accuracyList", accuracyList);
                        mapList.add(map);
                    }
                    Map<String, Object> listMap = new HashMap<>();
                    listMap.put("list", mapList);
                    int mon = DateUtil.getMonth(date);
                    listMap.put("month", mon);
                    if ((mon == 2 && flag == 4) || ( mon != 2 && flag == 5)){

                    }else {
                        monthResultList.add(listMap);
                    }

                    //获取当前的月份
                    date = DateUtil.getLastMonthByDate(date);

                }
                dataMap.put("resultList", monthResultList);
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("data", dataMap);
                resultMap.put("msg", "");
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                resultMap.put("data", "");
                resultMap.put("msg", "无法获取用户信息，请重新登录!");
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统异常，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }


    /**
     *  训练档案、总测试通关学习情况记录
     * @param token
     * @return
     */
    @RequestMapping(value = "/noun/totalInfo")
    public Map getNounMonthInfo(@RequestParam(value = "token")String token){
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("data", "");
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            return resultMap;
        }

        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                Map<String, Object> dataMap = new HashMap();
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (IsObjectNullUtils.is(xydParents.getChildId())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "您还没有完善儿童信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                if (IsObjectNullUtils.is(xydChild) || !"1".equals(xydChild.getPerfection())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "您还没有完善儿童信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                //获取通关进度和总学习时长
                XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
                xydSystemStatistics.setUserId(xydParents.getChildId());
                xydSystemStatistics.setStates("1");
                Double  sumRate = systemStatisticsService.selectSumRate(xydSystemStatistics);
                dataMap.put("sumRate", sumRate == null ? 0 : sumRate/4);
                //获取进度、学习时长和量分表
                String [] type = {"1","2"};
                List list = xydAnswerRecordService.selectBystudyRateList(xydParents.getId(), xydParents.getChildId(),"1", null, type);
                dataMap.put("list", list);
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("data", dataMap);
                resultMap.put("msg", "");
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                resultMap.put("msg", "无法获取用户信息，请重新登录!");
                resultMap.put("data", "");
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统异常，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    public static void main(String[] arges) throws ParseException {
        Date ti = DateUtil.parseDate("2018-02-20");
        Date date= DateUtil.getFirstDayByDate(ti);
        System.out.println(DateUtil.getDate(date));
        date = DateUtil.getDayZeroClock(date, -7);
        System.out.println(DateUtil.getDate(date));
        BigDecimal a = new BigDecimal(1);
        BigDecimal c = a.divide(new BigDecimal(4));
        System.out.print(c);
    }

}
