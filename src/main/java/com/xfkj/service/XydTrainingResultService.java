package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydGroupTraining;
import com.xfkj.model.XydTrainingResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by King on 2018/10/9.
 */
public interface XydTrainingResultService {

    int insert(XydTrainingResult xydTrainingResult);

    int insertGroupTraining(XydGroupTraining xydGroupTraining);

    XydGroupTraining selectGroupTrainingById(Integer groupId);

    int updateGroupTraining(XydGroupTraining groupTraining1);

    XydTrainingResult selectTrainingResultById(Integer integer);


    Integer selectPassCount(Integer childId, String module);

    //按天获取测试记录
    List<XydGroupTraining> selectDayResultList(Integer childId, Date time, String scene, String module);
    //按天获取学习时长
    Integer selectDayCountTime(Integer childId, Date time, String module);
    //按周获取学习时长集合
    List<Object[]> selectWeekStayResultList(Integer childId, Date weekFirstDay, Date weekLastDay, String scene, String module);
    //按周获取测试正确率集合
    List<Map<String, Object>> selectWeekAccuracyList(Integer childId, Date weekFirstDay, Date weekLastDay, String scene,String valid, String module);

    int count(GenericQueryParam queryParam);

    List<XydTrainingResult> select(GenericQueryParam queryParam);

    List<XydTrainingResult> selectTestPersonCount(String module, String scene);

    List<XydTrainingResult> selectByGroupId(Integer groupId);

    int selectByParamStatCount(GenericQueryParam queryParam);

    List<XydTrainingResult> selectByParamStatList(GenericQueryParam queryParam);

    List<Map<String, Object>> selectWeekByMonthStayResultList(Integer childId, Date weekFirstDay, Date weekLastDay, String scene, String module);
}
