package com.xfkj.service.impl;

import com.xfkj.common.emun.PCDIMustType;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.mapper.XydPcidOptionalMapper;
import com.xfkj.model.*;
import com.xfkj.service.XydPcidOptionalService;
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
public class XydPcidOptionalServiceImpl implements XydPcidOptionalService {
    @Autowired
    private XydPcidOptionalMapper xydPcidOptionalMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydPcidOptionalMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(XydPcidOptional record) {
        return xydPcidOptionalMapper.insertSelective(record);
    }

    @Override
    public XydPcidOptional selectByPrimaryKey(Integer id) {
        return xydPcidOptionalMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydPcidOptional record) {
        return xydPcidOptionalMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydPcidOptional> selectByTypeLis(XydPcidOptional xydPcidOptional) {
        return xydPcidOptionalMapper.selectByTypeLis(xydPcidOptional);
    }

    @Override
    public List<XydPcidOptional> selectByTypeAndOutLis(XydPcidType pcidType, XydAnswerRecord xydAnswerRecord, XydParents xydParents) {
        List<XydPcidOptional> list = xydPcidOptionalMapper.selectByTypeAndOutLis(pcidType.getId(), xydAnswerRecord.getId());
        if (!IsObjectNullUtils.is(list)){
            for (XydPcidOptional xydPcidOptional : list) {
                if (IsObjectNullUtils.is(xydPcidOptional)){
                    continue;
                }else {
                    List list1 = new ArrayList<>();
                    list1.add(xydPcidOptional.getTopicResultOne());
                    list1.add(xydPcidOptional.getTopicResultTwo());
                    xydPcidOptional.setTopicResult(list1);
                }
            }
        }
       return list;
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydPcidOptionalMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydPcidOptional> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydPcidOptionalMapper.selectByParamList(queryParam, rowBounds);
    }

    @Override
    public List<XydPcidOptional> selectList(XydPcidOptional xydPcidOptional) {
        return xydPcidOptionalMapper.selectList(xydPcidOptional);
    }

}
