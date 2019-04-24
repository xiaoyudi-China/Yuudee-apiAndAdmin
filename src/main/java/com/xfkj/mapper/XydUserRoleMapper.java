package com.xfkj.mapper;

import com.xfkj.model.XydUserRole;

public interface XydUserRoleMapper extends BaseMapper<XydUserRole> {

    int updateByPrimaryKey(XydUserRole record);

    int deleteByUserRoleList(XydUserRole eecUserRole);
}