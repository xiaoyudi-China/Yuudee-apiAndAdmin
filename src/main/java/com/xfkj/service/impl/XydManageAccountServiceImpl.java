package com.xfkj.service.impl;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydManageAccountMapper;
import com.xfkj.model.XydManageAccount;
import com.xfkj.service.XydManageAccountService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/29.
 */
@Service
public class XydManageAccountServiceImpl implements XydManageAccountService {
    @Autowired
    private XydManageAccountMapper xydManageAccountMapper;
    @Override
    public XydManageAccount selectByPrimaryKey(Integer id) {
        return xydManageAccountMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<XydManageAccount> selectByAccount(String account) {
        return xydManageAccountMapper.selectByAccount(account);
    }

    @Override
    public int updateByPrimaryKeySelective(XydManageAccount xydManageAccount) {
        return xydManageAccountMapper.updateByPrimaryKeySelective(xydManageAccount);
    }

    @Override
    public int insertSelective(XydManageAccount xydManageAccount) {
        return xydManageAccountMapper.insertSelective(xydManageAccount);
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydManageAccountMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydManageAccount> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydManageAccountMapper.selectByParamList(queryParam, rowBounds);
    }
}
