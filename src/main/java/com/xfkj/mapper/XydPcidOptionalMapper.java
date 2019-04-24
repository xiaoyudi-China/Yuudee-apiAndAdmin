package com.xfkj.mapper;

import com.xfkj.model.XydPcidMust;
import com.xfkj.model.XydPcidOptional;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydPcidOptionalMapper extends BaseMapper<XydPcidOptional> {

    int updateByPrimaryKey(XydPcidOptional record);

    List<XydPcidOptional> selectByTypeLis(XydPcidOptional xydPcidOptional);

    //获取pcdi选做题加答案
    List<XydPcidOptional> selectByTypeAndOutLis(@Param("pcidTypeId")Integer pcidTypeId, @Param("answerRecordId")Integer answerRecordId);
}