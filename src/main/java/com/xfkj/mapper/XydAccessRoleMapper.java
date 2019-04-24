package com.xfkj.mapper;

import com.xfkj.model.XydAccessRole;

public interface XydAccessRoleMapper extends BaseMapper<XydAccessRole>{

    int updateByPrimaryKey(XydAccessRole record);

    int deleteByEntityList(XydAccessRole eecAccessRole);
}