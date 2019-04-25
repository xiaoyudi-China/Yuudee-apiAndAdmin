package com.dkd.service;

import com.dkd.model.XydGradeRule;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/8.
 * 问卷打分接口
 */
public interface XydGradeRuleService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(XydGradeRule record);

    XydGradeRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydGradeRule record);

    List<XydGradeRule> selectByTypeList(XydGradeRule xydGradeRule);
}
