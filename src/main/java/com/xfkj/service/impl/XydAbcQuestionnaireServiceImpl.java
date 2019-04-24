package com.xfkj.service.impl;

import com.github.pagehelper.PageHelper;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydAbcQuestionnaireMapper;
import com.xfkj.model.XydAbcQuestionnaire;
import com.xfkj.model.XydPcidType;
import com.xfkj.service.XydAbcQuestionnaireService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/9.
 */
@Service
public class XydAbcQuestionnaireServiceImpl implements XydAbcQuestionnaireService {
    @Autowired
    private XydAbcQuestionnaireMapper xydAbcQuestionnaireMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydAbcQuestionnaireMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(XydAbcQuestionnaire record) {
        return xydAbcQuestionnaireMapper.insertSelective(record);
    }

    @Override
    public XydAbcQuestionnaire selectByPrimaryKey(Integer id) {
        return xydAbcQuestionnaireMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydAbcQuestionnaire record) {
        return xydAbcQuestionnaireMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydAbcQuestionnaire> selectByList(XydAbcQuestionnaire record) {
        return xydAbcQuestionnaireMapper.selectList(record);
    }

    @Override
    public List<XydAbcQuestionnaire> selectByListAndResult(Integer AnswerRecordId) {
        return xydAbcQuestionnaireMapper.selectByListAndResult(AnswerRecordId);
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydAbcQuestionnaireMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydAbcQuestionnaire> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydAbcQuestionnaireMapper.selectByParamList(queryParam, rowBounds);
    }

    @Override
    public List<XydAbcQuestionnaire> selectByEntityList(XydAbcQuestionnaire xydAbcQuestionnaire) {
        return xydAbcQuestionnaireMapper.selectByEntityList(xydAbcQuestionnaire);
    }
}
