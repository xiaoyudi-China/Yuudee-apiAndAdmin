package com.xfkj.service;

import com.xfkj.model.XydAccessRole;
import com.xfkj.model.XydManageAccess;
import com.xfkj.model.XydManageAccount;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/30.
 */
public interface XydManageAccessService {
    List<XydManageAccess> selectByRoleAccessList(XydAccessRole xydAccessRole);
    //获取菜单列表
    List<XydManageAccess> selectByEntityList(XydManageAccess xydManageAccess);

    List<XydManageAccount> selectByUserAccessList(int[] roleids);
}
