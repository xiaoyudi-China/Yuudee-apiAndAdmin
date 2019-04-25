package com.dkd.service;

import com.dkd.model.XydAccessRole;
import com.dkd.model.XydManageAccess;
import com.dkd.model.XydManageAccount;

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
