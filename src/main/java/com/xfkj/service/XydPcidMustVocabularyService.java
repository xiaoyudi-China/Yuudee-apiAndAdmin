package com.xfkj.service;

import com.xfkj.model.XydPcidMust;
import com.xfkj.model.XydPcidMustVocabulary;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/8.
 */
public interface XydPcidMustVocabularyService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(XydPcidMustVocabulary record);

    XydPcidMustVocabulary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydPcidMustVocabulary record);

    List<XydPcidMustVocabulary> selectByTypeLis(Integer id);
}
