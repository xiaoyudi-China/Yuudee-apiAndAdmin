package com.xfkj.mapper;

import com.xfkj.model.XydGroupTraining;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface XydGroupTrainingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(XydGroupTraining record);

    int insertSelective(XydGroupTraining record);

    XydGroupTraining selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydGroupTraining record);

    int updateByPrimaryKey(XydGroupTraining record);

    List<XydGroupTraining> selectDayResultList(@Param("userId") Integer userId, @Param("time") Date time, @Param("scene") String scene, @Param("module") String module);

    Integer selectDayCountTime(@Param("userId") Integer userId, @Param("time") Date time, @Param("module") String module);

    List<Object[]> selectWeekStayResultList(@Param("userId")Integer userId, @Param("weekFirstDay")Date weekFirstDay, @Param("weekLastDay")Date weekLastDay, @Param("scene")String scene, @Param("module")String module);

    List<Map<String,Object>> selectWeekAccuracyList(@Param("userId")Integer userId, @Param("weekFirstDay")Date weekFirstDay, @Param("weekLastDay")Date weekLastDay, @Param("scene")String scene, @Param("valid")String valid, @Param("module")String module);

    List<XydGroupTraining> selectByList(XydGroupTraining xydGroupTraining);

    List<Object[]> selectByDayResultList(@Param("userId") Integer userId, @Param("scene") String scene, @Param("module") String module, @Param("valid")String valid, @Param("dayDate")Date dayDate);

    List<Object[]> selectMonthAccuracyList(@Param("userId")Integer userId, @Param("endTime")Date endTime, @Param("scene")String scene, @Param("valid")String valid, @Param("module")String module);

    int selectStatisticsCount(XydGroupTraining xydGroupTraining);

    List<Map<String, Object>> selectDayCount(@Param("startTime")Date startTime,@Param("endTime")Date endTime, @Param("module")String module, @Param("scene")String scene);

    List<Map<String, Object>> selectTotalCount(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("module")String module, @Param("scene")String scene);

    List<XydGroupTraining> selectNearTest(Integer childId);

    XydGroupTraining selectNearmodule(@Param(value = "userId") Integer childId, @Param(value = "module") String s);

    List<XydGroupTraining> selectSense2(@Param(value = "userId")Integer childId);

    List<XydGroupTraining> selectnearJuziFenjie(@Param(value = "userId")Integer childId);

    List<XydGroupTraining> selectnearJuziChengzu(@Param(value = "userId")Integer childId);

    List<Map<String, Object>> selectWeekByMonthStayResultList(@Param("userId")Integer userId, @Param("weekFirstDay")Date weekFirstDay, @Param("weekLastDay")Date weekLastDay, @Param("scene")String scene, @Param("module")String module);
}