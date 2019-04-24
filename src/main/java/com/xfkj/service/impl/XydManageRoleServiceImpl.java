package com.xfkj.service.impl;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydManageRoleMapper;
import com.xfkj.model.XydManageAccount;
import com.xfkj.model.XydManageRole;
import com.xfkj.model.XydUserRole;
import com.xfkj.service.XydManageRoleService;
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
