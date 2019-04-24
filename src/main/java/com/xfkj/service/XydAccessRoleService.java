package com.xfkj.service;

import com.xfkj.model.XydAccessRole;

/**
 * Created by mai xiaogang on 2018/10/30.
 */
public interface XydAccessRoleService {
    int deleteByEntityList(XydAccessRole eecAccessRole);

    int insertSelective(XydAccessRole xydAccessRole);
}
