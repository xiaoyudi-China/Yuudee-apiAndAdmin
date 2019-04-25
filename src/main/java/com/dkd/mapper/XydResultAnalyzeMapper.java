package com.dkd.mapper;

import com.dkd.model.XydResultAnalyze;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydResultAnalyzeMapper extends BaseMapper<XydResultAnalyze>{
    int updateByPrimaryKey(XydResultAnalyze record);

    List<XydResultAnalyze> selectByCountList(@Param("xydResultAnalyze")XydResultAnalyze xydResultAnalyze, @Param("count")int count);
}