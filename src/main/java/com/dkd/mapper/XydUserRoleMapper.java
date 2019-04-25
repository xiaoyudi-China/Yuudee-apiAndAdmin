package com.dkd.mapper;

import com.dkd.model.XydUserRole;

public interface XydUserRoleMapper extends BaseMapper<XydUserRole> {

    int updateByPrimaryKey(XydUserRole record);

    int deleteByUserRoleList(XydUserRole eecUserRole);
}