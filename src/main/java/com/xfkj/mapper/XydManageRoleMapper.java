package com.xfkj.mapper;

import com.xfkj.model.XydManageRole;
import com.xfkj.model.XydUserRole;

import java.util.List;

public interface XydManageRoleMapper extends BaseMapper<XydManageRole>{

    int updateByPrimaryKey(XydManageRole record);

    List<XydManageRole> selectByUserRoleList(XydUserRole xydUserRole);

    List<XydManageRole> selectByRoleList(XydManageRole xydManageRole);
}