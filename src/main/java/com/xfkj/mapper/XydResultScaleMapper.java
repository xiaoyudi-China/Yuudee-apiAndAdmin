package com.xfkj.mapper;

import com.xfkj.model.XydResultScale;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface XydResultScaleMapper extends BaseMapper<XydResultScale>{

    int updateByPrimaryKey(XydResultScale record);

    List<XydResultScale> selectByAbcList(@Param("xydResultScale")XydResultScale xydResultScale, @Param("score")Integer score);
}