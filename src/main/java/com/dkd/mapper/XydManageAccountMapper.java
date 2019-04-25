package com.dkd.mapper;

import com.dkd.model.XydManageAccount;

import java.util.List;

public interface XydManageAccountMapper extends BaseMapper<XydManageAccount>{

    int updateByPrimaryKey(XydManageAccount record);

    List<XydManageAccount> selectByAccount(String account);
}