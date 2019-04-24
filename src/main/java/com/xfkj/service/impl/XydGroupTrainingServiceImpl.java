package com.xfkj.service.impl;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.DateUtil;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.mapper.XydGroupTrainingMapper;
import com.xfkj.mapper.XydSystemStatisticsMapper;
import com.xfkj.model.XydGroupTraining;
import com.xfkj.model.XydSystemStatistics;
import com.xfkj.service.XydGroupTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by mai xiaogang on 2018/11/2.
 */
@Service
public class XydGroupTrainingServiceImpl implements XydGroupTrainingService {
    @Autowired
    private XydGroupTrainingMapper xydGroupTrainingMapper;

    @Autowired
    private XydSystemStatisticsMapper systemStatisticsMapper;

    @Override
    public int insertSelective(XydGroupTraining xydGroupTraining) {
        return xydGroupTrainingMapper.insertSelective(xydGroupTraining);
    }

    @Override
    public List<XydGroupTraining> selectByList(XydGroupTraining xydGroupTraining) {
        return xydGroupTrainingMapper.selectByList(xydGroupTraining);
    }

    @Override
    public XydGroupTraining selectByPrimaryKey(Integer id) {
        return xydGroupTrainingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydGroupTraining xydGroupTraining) {
        return xydGroupTrainingMapper.updateByPrimaryKeySelective(xydGroupTraining);
    }

    @Override
    public void selectByDayResultList(Integer id, Map<String, Object> dayResult, Date dayDate) {
        //名词  学习场景  1训练 2测试 3意义     训练测试模块：1名词 2动词 3句子组成 4句子分解 5名词意义级别',
        List<Object[]> nounList = xydGroupTrainingMapper.selectByDayResultList(id, "2", "1", "1", dayDate);
        dayResult.put("nonuList", nounList);
        //动词
        List<Object[]> vebList = xydGroupTrainingMapper.selectByDayResultList(id, "2", "2", "1", dayDate);
        dayResult.put("vebList", vebList);
        //句子成组
        List<Object[]> sentenceGroupList = xydGroupTrainingMapper.selectByDayResultList(id, "2", "3", "1", dayDate);
        dayResult.put("sentenceGroupList", sentenceGroupList);
        //句子分解
        List<Object[]> sentenceResolve = xydGroupTrainingMapper.selectByDayResultList(id, "2", "4", "1", dayDate);
        dayResult.put("sentenceResolve", sentenceResolve);
    }

    @Override
    public void selectByWeekResultList(Integer id, Date startTime, Date endTime, Map<String, Object> dayResult) {
        //名词  学习场景  1训练 2测试 3意义     训练测试模块：1名词 2动词 3句子组成 4句子分解 5名词意义级别',
        List<Map<String, Object>> nounList = xydGroupTrainingMapper.selectWeekAccuracyList(id, startTime, endTime, null, "1", "1");
        dayResult.put("nonuList", nounList);
        List<Map<String, Object>> nounTimeList = xydGroupTrainingMapper.selectWeekAccuracyList(id, startTime, endTime, null, null, "1");
        dayResult.put("nounTimeList", nounTimeList);
        //动词
        List<Map<String, Object>> vebList = xydGroupTrainingMapper.selectWeekAccuracyList(id, startTime, endTime, null, "1", "2");
        dayResult.put("vebList", vebList);
        List<Map<String, Object>> vebTimeList = xydGroupTrainingMapper.selectWeekAccuracyList(id, startTime, endTime, null, null, "2");
        dayResult.put("vebTimeList", vebTimeList);
        //句子成组
        List<Map<String, Object>> sentenceGroupList = xydGroupTrainingMapper.selectWeekAccuracyList(id, startTime, endTime, null, "1", "3");
        dayResult.put("sentenceGroupList", sentenceGroupList);
        List<Map<String, Object>> sentenceTimeList = xydGroupTrainingMapper.selectWeekAccuracyList(id, startTime, endTime, null, null, "3");
        dayResult.put("sentenceTimeList", sentenceTimeList);
        //句子分解
        List<Map<String, Object>> sentenceResolve = xydGroupTrainingMapper.selectWeekAccuracyList(id, startTime, endTime, null, "1","4");
        dayResult.put("sentenceResolve", sentenceResolve);
        List<Map<String, Object>> sentenceResolveTimeList = xydGroupTrainingMapper.selectWeekAccuracyList(id, startTime, endTime, null, null,"4");
        dayResult.put("sentenceResolveTimeList", sentenceResolveTimeList);
    }

    @Override
    public void selectByMonthResultList(Integer id, Date date, Map<String, Object> monthResult) {
        //名词  学习场景  1训练 2测试 3意义     训练测试模块：1名词 2动词 3句子组成 4句子分解 5名词意义级别',
       /* List<Object[]> nounList = xydGroupTrainingMapper.selectMonthAccuracyList(id, date, null,"1", "1");
        monthResult.put("nonuList", nounList);
        List<Object[]> nounTimeList = xydGroupTrainingMapper.selectMonthAccuracyList(id, date, null,null, "1");
        monthResult.put("nounTimeList", nounTimeList);*/
        monthResult.put("nounResult", getList( date, id, "1", "2"));
        //动词
       /* List<Object[]> vebList = xydGroupTrainingMapper.selectMonthAccuracyList(id, date, null, "1", "2");
        monthResult.put("vebList", vebList);
        List<Object[]> vebTimeList = xydGroupTrainingMapper.selectMonthAccuracyList(id, date, null, null, "2");
        monthResult.put("vebTimeList", vebTimeList);*/
        monthResult.put("vebResult", getList( date, id, "2", "2"));
        //句子成组
        /*List<Object[]> sentenceGroupList = xydGroupTrainingMapper.selectMonthAccuracyList(id, date, null, "1", "3");
        monthResult.put("sentenceGroupList", sentenceGroupList);
        List<Object[]> sentenceTimeList = xydGroupTrainingMapper.selectMonthAccuracyList(id, date, null, null, "3");
        monthResult.put("sentenceTimeList", sentenceTimeList);*/
        monthResult.put("sentenceGroupResult", getList( date, id, "3", "2"));
        //句子分解
       /* List<Object[]> sentenceResolve = xydGroupTrainingMapper.selectMonthAccuracyList(id, date, null, "1", "4");
        monthResult.put("sentenceResolve", sentenceResolve);
        List<Object[]> sentenceResolveTimeList = xydGroupTrainingMapper.selectMonthAccuracyList(id, date, null, null, "4");
        monthResult.put("sentenceResolveTimeList", sentenceResolveTimeList);
        monthResult.put("time", DateUtil.getStrDate(date, "yyyy-MM"));*/
        monthResult.put("sentenceResolveResult", getList( date, id, "4", "2"));

    }

    public List getList(Date date, Integer childId, String module, String scene){
            Map<String, Object> dataMap = new HashMap<>();
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
                List<Map<String, Object>> timeList = xydGroupTrainingMapper.selectWeekByMonthStayResultList(childId, monthOneDaytime, nextWeekTime, null, module);
                if (IsObjectNullUtils.is(timeList) || timeList.get(0) == null){
                    timeList = new ArrayList<>();
                }
                Map<String, Object> map = new HashMap<>();
                map.put("timeList", timeList);
                List<Map<String, Object>> accuracyList = xydGroupTrainingMapper.selectWeekAccuracyList(childId, monthOneDaytime, nextWeekTime, scene,"1", module);
                monthOneDaytime = nextWeekTime;
                nextWeekTime = DateUtil.getDayZeroClock(monthOneDaytime, -7);
                map.put("accuracyList", accuracyList);
                mapList.add(map);
            }

          return mapList;
    }

    @Override
    public int selectStatisticsCount(XydGroupTraining xydGroupTraining) {
        return xydGroupTrainingMapper.selectStatisticsCount(xydGroupTraining);
    }

    @Override
    public List<Map<String, Object>> selectDayCount(Date date, Date date1, String moudle, String scene) {
        return xydGroupTrainingMapper.selectDayCount(date, date1, moudle, scene);
    }

    @Override
    public List<Map<String, Object>> selectTotalCount(Date date, Date date1, String module, String scene) {
        return xydGroupTrainingMapper.selectTotalCount(date, date1, module, scene);
    }

    @Override
    public List<XydGroupTraining> selectNearTest(Integer childId) {
        List<XydGroupTraining> xydGroupTrainings = xydGroupTrainingMapper.selectNearTest(childId);
        if (xydGroupTrainings.size() > 0) {
            for (XydGroupTraining groupTraining : xydGroupTrainings) {
                String[] split = groupTraining.getTrainingIdlist().split(",");
                groupTraining.setLength(split.length);
                if (groupTraining.getScene().equals("2")) {
                    BigDecimal accuracy = groupTraining.getAccuracy().multiply(new BigDecimal("10"));
                    int i = accuracy.intValue();
                    groupTraining.setGold(i );
                }
            }
        }
        return xydGroupTrainings;
    }

    @Override
    public XydGroupTraining selectById(Integer groupId_1) {
        return xydGroupTrainingMapper.selectByPrimaryKey(groupId_1);
    }

    @Override
    public Map selectNearmodule(Integer childId) {
        HashMap<String, String> map = new HashMap<String, String>();
        XydGroupTraining groupTraining = xydGroupTrainingMapper.selectNearmodule(childId, "1");
        if (!IsObjectNullUtils.is(groupTraining)) {
            if (Integer.parseInt(groupTraining.getScene()) == 1) {
                map.put("noun", "2");
            } else if (Integer.parseInt(groupTraining.getScene()) == 2) {
                //查询是否通关
                XydSystemStatistics systemStatistics = systemStatisticsMapper.selectJinDU(childId, "1");
                if (!IsObjectNullUtils.is(systemStatistics)) {
                    if (systemStatistics.getRate().intValue() == 1) {
                        map.put("noun", "3");
                    } else {
                        map.put("noun", "1");
                    }
                } else {
                    map.put("noun", "1");
                }
            } else if (Integer.parseInt(groupTraining.getScene()) == 3) {
                //selectSense2 这个接口是专门查询最近做过的名词记录
                List<XydGroupTraining> list = xydGroupTrainingMapper.selectSense2(childId);
                int a = 0;
                for(XydGroupTraining groupTraining1: list){
                    if(groupTraining1.getScene().equals("3")){
                        a++;
                    }
                }
                if(a==2){
                    map.put("noun", "1");
                }else{
                    map.put("noun", "3");
                }
//                if (list.size() > 1) {
//                    map.put("noun", "1");
//                } else {
//                    map.put("noun", "3");
//                }
            }
        } else {
            map.put("noun", "1");
        }
        XydGroupTraining groupTraining2 = xydGroupTrainingMapper.selectNearmodule(childId, "2");
        if (!IsObjectNullUtils.is(groupTraining2)) {
            if (Integer.parseInt(groupTraining2.getScene()) == 1) {
                map.put("verb", "2");
            } else if (Integer.parseInt(groupTraining2.getScene()) == 2) {
                map.put("verb", "1");
//                XydSystemStatistics systemStatistics = systemStatisticsMapper.selectJinDU(childId, "2");
//                if(!IsObjectNullUtils.is(systemStatistics)){
//                    if (systemStatistics.getRate().intValue() == 100) {
//                        map.put("verb", "2");
//                    } else {
//                        map.put("verb", "1");
//                    }
//                }else{
//                    map.put("verb", "1");
//                }
            } else {
                map.put("verb", "1");
            }
        } else {
            map.put("verb", "1");
        }

        XydGroupTraining groupTraining3 = xydGroupTrainingMapper.selectNearmodule(childId, "3");
        if (!IsObjectNullUtils.is(groupTraining3)) {
            if (Integer.parseInt(groupTraining3.getScene()) == 1) {
                map.put("group", "2");
            } else if (Integer.parseInt(groupTraining3.getScene()) == 2) {
                map.put("group", "1");
//                XydSystemStatistics systemStatistics = systemStatisticsMapper.selectJinDU(childId, "3");
//                if(!IsObjectNullUtils.is(systemStatistics)){
//                    if (systemStatistics.getRate().intValue() == 100) {
//                        map.put("group", "2");
//                    } else {
//                        map.put("group", "1");
//                    }
//                }else{
//                    map.put("group", "1");
//                }
            }
        } else {
            map.put("group", "1");
        }

        XydGroupTraining groupTraining4 = xydGroupTrainingMapper.selectNearmodule(childId, "4");
        if (!IsObjectNullUtils.is(groupTraining4)) {
            if (Integer.parseInt(groupTraining4.getScene()) == 1) {
                map.put("sentence", "2");
            } else if (Integer.parseInt(groupTraining4.getScene()) == 2) {
                map.put("sentence", "1");
//                XydSystemStatistics systemStatistics = systemStatisticsMapper.selectJinDU(childId, "4");
//                if(!IsObjectNullUtils.is(systemStatistics)){
//                    if (systemStatistics.getRate().intValue() == 100) {
//                        map.put("sentence", "1");
//                    } else {
//                        map.put("sentence", "1");
//                    }
//                }else{
//                    map.put("sentence", "1");
//                }
            }
        } else {
            map.put("sentence", "1");
        }
        return map;
    }

    @Override
    public List<XydGroupTraining> selectnearJuziChengzu(Integer childId) {
        return xydGroupTrainingMapper.selectnearJuziChengzu(childId);
    }

    @Override
    public List<XydGroupTraining> selectnearJuziFenjie(Integer childId) {
        return xydGroupTrainingMapper.selectnearJuziFenjie(childId);
    }
}
