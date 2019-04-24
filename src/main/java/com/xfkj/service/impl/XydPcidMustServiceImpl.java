package com.xfkj.service.impl;

import com.xfkj.common.emun.PCDIMustType;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.mapper.XydPcidMustMapper;
import com.xfkj.mapper.XydPcidMustVocabularyMapper;
import com.xfkj.model.*;
import com.xfkj.service.XydPcidMustService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/8.
 */
@Service
public class XydPcidMustServiceImpl implements XydPcidMustService  {
    @Autowired
    private XydPcidMustMapper xydPcidMustMapper;
    @Autowired
    private XydPcidMustVocabularyMapper xydPcidMustVocabulary;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydPcidMustMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(XydPcidMust record) {
        return xydPcidMustMapper.insertSelective(record);
    }

    @Override
    public XydPcidMust selectByPrimaryKey(Integer id) {
        return xydPcidMustMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydPcidMust record) {
        return xydPcidMustMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydPcidMust> selectByTypeLis(XydPcidMust xydPcidMust) {
        return xydPcidMustMapper.selectByTypeLis(xydPcidMust);
    }

    @Override
    public List selectByTypeAndOutLis(XydPcidType pcidType, XydAnswerRecord xydAnswerRecord, XydParents xydParents) {
        List resultList = new ArrayList<>();
        if (pcidType.getNameEnum() == PCDIMustType.MUST_NAME_E.getValue()){
            //词汇
          List<XydPcidMustVocabulary>  list = xydPcidMustVocabulary.selectByTypeAndOutLis(pcidType.getId(), xydAnswerRecord.getId());
            if (!IsObjectNullUtils.is(list)){
                for (XydPcidMustVocabulary pcidMustVocabulary : list) {
                    if (IsObjectNullUtils.is(pcidMustVocabulary)){
                        continue;
                    }else {
                        pcidMustVocabulary.setTopicResult(new ArrayList(Arrays.asList(pcidMustVocabulary.getTopicResultOne().split("\\|"))));
                    }
                }
            }
            resultList = list;
            return resultList;
        }else {
            //其他
            List<XydPcidMust> list = xydPcidMustMapper.selectByTypeAndOutLis(xydAnswerRecord.getId(), pcidType.getId());
            if (!IsObjectNullUtils.is(list)){
                for (XydPcidMust xydPcidMust : list) {
                    if (IsObjectNullUtils.is(xydPcidMust)){
                        continue;
                    }else {
                        xydPcidMust.setTopicResult(new ArrayList(Arrays.asList(xydPcidMust.getTopicResultOne().split("\\|"))));
                        if (pcidType.getNameEnum() == PCDIMustType.MUST_NAME_C.getValue()){
                          List<String> reList =new ArrayList<>();
                          reList.add(xydPcidMust.getAnswerOne());
                          reList.add(xydPcidMust.getAnswerTwo());
                          reList.add(xydPcidMust.getAnswerThree());
                          xydPcidMust.setAnswers(reList);
                        }
                    }
                }
            }
            resultList = list;
            return resultList;
        }
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydPcidMustMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydPcidMust> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydPcidMustMapper.selectByParamList(queryParam, rowBounds);
    }

}
