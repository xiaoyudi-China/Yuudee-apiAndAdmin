package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydGroupTraining;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/11/2.
 */
public interface XydGroupTrainingService {
    int insertSelective(XydGroupTraining xydGroupTraining);

    List<XydGroupTraining> selectByList(XydGroupTraining xydGroupTraining);

    XydGroupTraining selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydGroupTraining xydGroupTraining);
    //查询儿童个人当天的学习情况
    void selectByDayResultList(Integer id, Map<String, Object> dayResult, Date date);
    //查询儿童个人一周的学习情况
    void selectByWeekResultList(Integer id, Date startTime, Date endTime, Map<String, Object> dayResult);
    //查询儿童某月的学习情况
    void selectByMonthResultList(Integer id, Date date, Map<String, Object> monthResult);

    int selectStatisticsCount(XydGroupTraining xydGroupTraining);

    List<Map<String, Object>> selectDayCount(Date date, Date date1,String module, String scene);

    List<Map<String, Object>> selectTotalCount(Date date, Date date1,String module, String scene);

    List<XydGroupTraining> selectNearTest(Integer childId);

    XydGroupTraining selectById(Integer groupId_1);

    Map selectNearmodule(Integer childId);

    List<XydGroupTraining> selectnearJuziChengzu(Integer childId);

    List<XydGroupTraining> selectnearJuziFenjie(Integer childId);
}
