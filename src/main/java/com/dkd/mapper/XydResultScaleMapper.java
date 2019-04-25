package com.dkd.mapper;

import com.dkd.model.XydResultScale;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydResultScaleMapper extends BaseMapper<XydResultScale>{

    int updateByPrimaryKey(XydResultScale record);

    List<XydResultScale> selectByAbcList(@Param("xydResultScale")XydResultScale xydResultScale, @Param("score")Integer score);
}