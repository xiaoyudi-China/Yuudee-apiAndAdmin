package com.dkd.service;

import com.dkd.model.XydUserRole;

/**
 * Created by mai xiaogang on 2018/10/29.
 */
public interface XydUserRoleService {
    int deleteByUserRoleList(XydUserRole eecUserRole);

    int insertSelective(XydUserRole eecUserRole);
}
