package com.xfkj.service;

import com.xfkj.model.XydGradeRule;
import com.xfkj.model.XydPcidMust;

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
