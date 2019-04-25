package com.dkd.service.impl;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.mapper.XydAreaMapper;
import com.dkd.model.XydArea;
import com.dkd.service.XydAreaService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/26.
 */
@Service
public class XydAreaServiceImpl implements XydAreaService{
    @Autowired
    private XydAreaMapper xydAreaMapper;

    @Override
    public int deleteByPrimaryKey(Integer areaid) {
        return xydAreaMapper.deleteByPrimaryKey(areaid);
    }

    @Override
    public int insertSelective(XydArea record) {
        return xydAreaMapper.insertSelective(record);
    }

    @Override
    public XydArea selectByPrimaryKey(Integer areaid) {
        return xydAreaMapper.selectByPrimaryKey(areaid);
    }

    @Override
    public int updateByPrimaryKeySelective(XydArea record) {
        return xydAreaMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydArea> selectByList(XydArea xydArea) {
        return xydAreaMapper.selectList(xydArea);
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return xydAreaMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydArea> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydAreaMapper.selectByParamList(queryParam, rowBounds);
    }

    @Override
    public int deleteChilCity(Integer parentid) {
        return xydAreaMapper.deleteChilCity(parentid);
    }
}
