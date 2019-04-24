package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydAbcQuestionnaire;
import com.xfkj.model.XydPcidType;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/9.
 */
public interface XydAbcQuestionnaireService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(XydAbcQuestionnaire record);

    XydAbcQuestionnaire selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydAbcQuestionnaire record);

    List<XydAbcQuestionnaire> selectByList(XydAbcQuestionnaire record);
    //获取abc问卷加答案
    List<XydAbcQuestionnaire> selectByListAndResult(Integer id);

    int selectByParamCount(GenericQueryParam queryParam);

    List<XydAbcQuestionnaire> selectByParamList(GenericQueryParam queryParam);


    List<XydAbcQuestionnaire> selectByEntityList(XydAbcQuestionnaire xydAbcQuestionnaire);
}
