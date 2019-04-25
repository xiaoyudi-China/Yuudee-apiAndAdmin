package com.dkd.mapper;

import com.dkd.model.XydPcidType;

import java.util.List;

public interface XydPcidTypeMapper extends BaseMapper<XydPcidType>{
    int updateByPrimaryKey(XydPcidType record);

    List<XydPcidType> selectByTypeSortList(XydPcidType xydPcidType);
}