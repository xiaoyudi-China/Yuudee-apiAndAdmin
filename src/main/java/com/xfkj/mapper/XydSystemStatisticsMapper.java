package com.xfkj.mapper;

import com.xfkj.model.XydSystemStatistics;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface XydSystemStatisticsMapper extends BaseMapper<XydSystemStatistics>{

    int updateByPrimaryKey(XydSystemStatistics record);

    //获取总学习时长
    Long selectSumStudyTime(Integer userId);

    List<XydSystemStatistics> selectPlayerResultList(XydSystemStatistics xydSystemStatistics);
    //获取总通关进度
    Double selectSumRate(XydSystemStatistics xydSystemStatistics);

    int selectPlayerCount(XydSystemStatistics xydSystemStatistics);

    List<Map<String, Object>> selectDayCount(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

    List<Map<String, Object>> selectTotalCount(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

    XydSystemStatistics selectNew(Integer childId);

    XydSystemStatistics selectJinDU(@Param(value = "userId") Integer childId, @Param(value = "module") String s);
}