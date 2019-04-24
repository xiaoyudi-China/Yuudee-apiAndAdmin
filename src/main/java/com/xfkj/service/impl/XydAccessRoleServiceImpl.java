package com.xfkj.service.impl;

import com.xfkj.mapper.XydAccessRoleMapper;
import com.xfkj.model.XydAccessRole;
import com.xfkj.service.XydAccessRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mai xiaogang on 2018/10/30.
 */
@Service
public class XydAccessRoleServiceImpl implements XydAccessRoleService{
    @Autowired
    private XydAccessRoleMapper xydAccessRoleMapper;
    @Override
    public int deleteByEntityList(XydAccessRole eecAccessRole) {
        return xydAccessRoleMapper.deleteByEntityList(eecAccessRole);
    }

    @Override
    public int insertSelective(XydAccessRole xydAccessRole) {
        return xydAccessRoleMapper.insertSelective(xydAccessRole);
    }
}
