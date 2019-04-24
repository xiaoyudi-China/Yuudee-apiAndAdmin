package com.xfkj.service;

import com.xfkj.model.XydPcidType;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/8.
 * pcdi问卷类型接口
 */
public interface XydPcdiTypeService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(XydPcidType record);

    XydPcidType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydPcidType record);

    List<XydPcidType> selectByList(XydPcidType record);

    List<XydPcidType> selectByTypeSortList(XydPcidType xydPcidType);
}
