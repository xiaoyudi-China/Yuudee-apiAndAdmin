package com.xfkj.mapper;

import com.xfkj.model.XydManageAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydManageAccountMapper extends BaseMapper<XydManageAccount>{

    int updateByPrimaryKey(XydManageAccount record);

    List<XydManageAccount> selectByAccount(String account);
}