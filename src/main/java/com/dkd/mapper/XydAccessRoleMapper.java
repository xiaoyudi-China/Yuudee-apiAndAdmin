package com.dkd.mapper;

import com.dkd.model.XydAccessRole;

public interface XydAccessRoleMapper extends BaseMapper<XydAccessRole>{

    int updateByPrimaryKey(XydAccessRole record);

    int deleteByEntityList(XydAccessRole eecAccessRole);
}