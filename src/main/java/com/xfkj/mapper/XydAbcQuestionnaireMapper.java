package com.xfkj.mapper;

import com.xfkj.model.XydAbcQuestionnaire;

import java.util.List;

public interface XydAbcQuestionnaireMapper extends BaseMapper<XydAbcQuestionnaire>{

    int updateByPrimaryKey(XydAbcQuestionnaire record);

    List<XydAbcQuestionnaire> selectByListAndResult(Integer answerRecordId);

    List<XydAbcQuestionnaire> selectByEntityList(XydAbcQuestionnaire xydAbcQuestionnaire);
}