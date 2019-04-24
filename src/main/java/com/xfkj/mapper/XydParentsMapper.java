package com.xfkj.mapper;

import com.xfkj.model.XydParents;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface XydParentsMapper extends BaseMapper<XydParents>{
    int updateByPrimaryKey(XydParents record);

    int selectRegisterCount(XydParents xydParents);

    List<Map<String, Object>> selectDayCount(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Map<String, Object>> selectTotalCount(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}