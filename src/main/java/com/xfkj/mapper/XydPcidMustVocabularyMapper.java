package com.xfkj.mapper;

import com.xfkj.model.XydPcidMust;
import com.xfkj.model.XydPcidMustVocabulary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydPcidMustVocabularyMapper extends BaseMapper<XydPcidMustVocabulary>{

    int updateByPrimaryKey(XydPcidMustVocabulary record);

    List<XydPcidMustVocabulary> selectByList(XydPcidMustVocabulary record);

    List<XydPcidMustVocabulary> selectByTypeLis(XydPcidMustVocabulary xydPcidMustVocabulary);

    List<XydPcidMustVocabulary> selectByTypeAndOutLis(@Param("pcidTypeId")Integer pcidTypeId, @Param("answerRecordId")Integer answerRecordId);
}