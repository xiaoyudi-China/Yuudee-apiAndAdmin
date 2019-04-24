package com.xfkj.service;

import com.xfkj.model.XydSystemStatistics;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/10/9.
 */
public interface XydSystemStatisticsService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(XydSystemStatistics record);

    XydSystemStatistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydSystemStatistics record);

    List<XydSystemStatistics> selectByList(XydSystemStatistics record);

    //获取通过记录
    List<XydSystemStatistics> selectPlayerResultList(XydSystemStatistics xydSystemStatistics);

    //获取通过记录
    List<XydSystemStatistics> selectPlayerResultList1(XydSystemStatistics xydSystemStatistics);

    //获取总通关进度
    Double selectSumRate(XydSystemStatistics xydSystemStatistics);
    //获取总学习时长
    Long selectSumStudyTime(Integer id);

    int selectPlayerCount(XydSystemStatistics xydSystemStatistics);

    List<Map<String, Object>> selectDayCount(Date date, Date date1);

    List<Map<String, Object>> selectTotalCount(Date date, Date date1);

    XydSystemStatistics selectNew(Integer childId);
}
