package com.xfkj.mapper;

import com.xfkj.model.XydPcidMust;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydPcidMustMapper extends BaseMapper<XydPcidMust> {
    int updateByPrimaryKey(XydPcidMust record);

    List<XydPcidMust> selectByTypeLis(XydPcidMust xydPcidMust);

    List<XydPcidMust> selectByTypeAndOutLis(@Param("answerRecordId")Integer answerRecordId, @Param("pcidTypeId")Integer pcidTypeId);
}