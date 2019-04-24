package com.xfkj.mapper;

import com.xfkj.model.XydAccessRole;
import com.xfkj.model.XydManageAccess;
import com.xfkj.model.XydManageAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydManageAccessMapper extends BaseMapper<XydManageAccess>{

    int updateByPrimaryKey(XydManageAccess record);

    List<XydManageAccess> selectByRoleAccessList(XydAccessRole xydAccessRole);

    List<XydManageAccess> selectByEntityList(XydManageAccess xydManageAccess);

    List<XydManageAccount> selectByUserAccessList(@Param("roleIds") int[] roleIdss);
}