package com.dkd.service;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.model.XydManageAccount;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/29.
 */
public interface XydManageAccountService {
    XydManageAccount selectByPrimaryKey(Integer id);

    List<XydManageAccount> selectByAccount(String account);

    int updateByPrimaryKeySelective(XydManageAccount xydManageAccount);

    int insertSelective(XydManageAccount xydManageAccount);

    int selectByParamCount(GenericQueryParam queryParam);

    List<XydManageAccount> selectByParamList(GenericQueryParam queryParam);
}
