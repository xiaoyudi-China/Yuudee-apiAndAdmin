package com.dkd.service;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.model.XydManageRole;
import com.dkd.model.XydUserRole;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/29.
 */
public interface XydManageRoleService {
    //查询用户对应的角色列表
    List<XydManageRole> selectByUserRoleList(XydUserRole xydUserRole);

    XydManageRole selectByPrimaryKey(Integer id);

    List<XydManageRole> selectByRoleList(XydManageRole xydManageRole);
    
    int updateByPrimaryKeySelective(XydManageRole xydManageRole);

    int insertSelective(XydManageRole xydManageRole);

    int selectByParamCount(GenericQueryParam queryParam);

    List<XydManageRole> selectByParamList(GenericQueryParam queryParam);
}
