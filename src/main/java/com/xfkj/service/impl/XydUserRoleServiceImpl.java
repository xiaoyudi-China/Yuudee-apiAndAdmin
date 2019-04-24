package com.xfkj.service.impl;

import com.xfkj.mapper.XydUserRoleMapper;
import com.xfkj.model.XydUserRole;
import com.xfkj.service.XydUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mai xiaogang on 2018/10/29.
 */
@Service
public class XydUserRoleServiceImpl implements XydUserRoleService {
    @Autowired
    private XydUserRoleMapper xydUserRoleMapper;
    @Override
    public int deleteByUserRoleList(XydUserRole xydUserRole) {
        return xydUserRoleMapper.deleteByUserRoleList(xydUserRole);
    }

    @Override
    public int insertSelective(XydUserRole xydUserRole) {
        return xydUserRoleMapper.insertSelective(xydUserRole);
    }
}
