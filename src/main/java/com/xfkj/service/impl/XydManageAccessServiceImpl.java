package com.xfkj.service.impl;

import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.mapper.XydManageAccessMapper;
import com.xfkj.model.XydAccessRole;
import com.xfkj.model.XydManageAccess;
import com.xfkj.model.XydManageAccount;
import com.xfkj.service.XydManageAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/30.
 */
@Service
public class XydManageAccessServiceImpl implements XydManageAccessService {
    @Autowired
    private XydManageAccessMapper xydManageAccessMapper;
    @Override
    public List<XydManageAccess> selectByRoleAccessList(XydAccessRole xydAccessRole) {
        return xydManageAccessMapper.selectByRoleAccessList(xydAccessRole);
    }

    @Override
    public List<XydManageAccess> selectByEntityList(XydManageAccess xydManageAccess) {
        List<XydManageAccess> accessList = xydManageAccessMapper.selectByEntityList(xydManageAccess);
        if (!IsObjectNullUtils.is(accessList)){
            for (XydManageAccess manageAccess : accessList) {
                //获取每个菜单的子菜单
                XydManageAccess access = new XydManageAccess();
                access.setIsDeleted(0);
                access.setStatus("0");
                access.setParentId(manageAccess.getId());
                List<XydManageAccess> list = xydManageAccessMapper.selectByEntityList(access);
                if (!IsObjectNullUtils.is(list) && list.size() > 0){
                    for (XydManageAccess xydManageAccess1 : list) {
                        XydManageAccess nAccess = new XydManageAccess();
                        nAccess.setIsDeleted(0);
                        nAccess.setStatus("0");
                        nAccess.setParentId(xydManageAccess1.getId());
                        xydManageAccess1.setSubmenu(xydManageAccessMapper.selectByEntityList(nAccess));
                    }
                }
                manageAccess.setSubmenu(list);
            }
        }
        return accessList;
    }

    @Override
    public List<XydManageAccount> selectByUserAccessList(int[] roleids) {
        return xydManageAccessMapper.selectByUserAccessList(roleids);
    }
}
