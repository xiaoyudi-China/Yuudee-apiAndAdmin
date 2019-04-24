package com.xfkj.service.impl;

import com.xfkj.mapper.XydPcidMustVocabularyMapper;
import com.xfkj.model.XydPcidMust;
import com.xfkj.model.XydPcidMustVocabulary;
import com.xfkj.service.XydPcidMustVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/8.
 */
@Service
public class XydPcidMustVocabularyServiceImpl implements XydPcidMustVocabularyService {
    @Autowired
    private XydPcidMustVocabularyMapper xydPcidMustVocabularyMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydPcidMustVocabularyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(XydPcidMustVocabulary record) {
        return xydPcidMustVocabularyMapper.insertSelective(record);
    }

    @Override
    public XydPcidMustVocabulary selectByPrimaryKey(Integer id) {
        return xydPcidMustVocabularyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydPcidMustVocabulary record) {
        return xydPcidMustVocabularyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydPcidMustVocabulary> selectByTypeLis(Integer id) {

        XydPcidMustVocabulary xydPcidMustVocabulary = new XydPcidMustVocabulary();
        xydPcidMustVocabulary.setStates("1");
        xydPcidMustVocabulary.setPcdiTypeId(id);
        //type : 1名称 2动词 3形容词
        //xydPcidMustVocabulary.setType("1");
        return xydPcidMustVocabularyMapper.selectByTypeLis(xydPcidMustVocabulary);
    }
}
