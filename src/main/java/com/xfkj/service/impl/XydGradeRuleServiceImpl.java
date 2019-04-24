package com.xfkj.service.impl;

import com.xfkj.mapper.XydGradeRuleMapper;
import com.xfkj.model.XydGradeRule;
import com.xfkj.model.XydPcidMust;
import com.xfkj.service.XydGradeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/8.
 */
@Service
public class XydGradeRuleServiceImpl implements XydGradeRuleService {
    @Autowired
    private XydGradeRuleMapper xydGradeRuleMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydGradeRuleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(XydGradeRule record) {
        return xydGradeRuleMapper.insertSelective(record);
    }

    @Override
    public XydGradeRule selectByPrimaryKey(Integer id) {
        return xydGradeRuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydGradeRule record) {
        return xydGradeRuleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydGradeRule> selectByTypeList(XydGradeRule xydGradeRule) {
        return xydGradeRuleMapper.selectList(xydGradeRule);
    }

}
