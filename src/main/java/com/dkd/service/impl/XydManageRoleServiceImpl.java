package com.dkd.service.impl;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.mapper.XydManageRoleMapper;
import com.dkd.model.XydManageRole;
import com.dkd.model.XydUserRole;
import com.dkd.service.XydManageRoleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/29.
 */
@Service
public class XydManageRoleServiceImpl implements XydManageRoleService {
    @Autowired
    private XydManageRoleMapper xydManageRoleMapper;
    @Override
    public List<XydManageRole> selectByUserRoleList(XydUserRole xydUserRole) {
        return xydManageRoleMapper.selectByUserRoleList(xydUserRole);
    }

    @Override
    public XydManageRole selectByPrimaryKey(Integer id) {
        return xydManageRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<XydManageRole> selectByRoleList(XydManageRole xydManageRole) {
        return xydManageRoleMapper.selectByRoleList(xydManageRole);
    }

    @Override
    public int updateByPrimaryKeySelective(XydManageRole xydManageRole) {
        return xydManageRoleMapper.updateByPrimaryKeySelective(xydManageRole);
    }

    @Override
    public int insertSelective(XydManageRole xydManageRole) {
        return xydManageRoleMapper.insertSelective(xydManageRole);
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydManageRoleMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydManageRole> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydManageRoleMapper.selectByParamList(queryParam, rowBounds);
    }
}
