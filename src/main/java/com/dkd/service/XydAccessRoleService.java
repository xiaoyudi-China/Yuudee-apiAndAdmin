package com.dkd.service;

import com.dkd.model.XydAccessRole;

/**
 * Created by mai xiaogang on 2018/10/30.
 */
public interface XydAccessRoleService {
    int deleteByEntityList(XydAccessRole eecAccessRole);

    int insertSelective(XydAccessRole xydAccessRole);
}
