package com.dkd.mapper;

import com.dkd.model.XydManageRole;
import com.dkd.model.XydUserRole;

import java.util.List;

public interface XydManageRoleMapper extends BaseMapper<XydManageRole>{

    int updateByPrimaryKey(XydManageRole record);

    List<XydManageRole> selectByUserRoleList(XydUserRole xydUserRole);

    List<XydManageRole> selectByRoleList(XydManageRole xydManageRole);
}