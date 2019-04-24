package com.xfkj.service.impl;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydSystemVersionsMapper;
import com.xfkj.model.XydSystemVersions;
import com.xfkj.service.XydSystemVersionsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/26.
 */
@Service
public class XydSystemVersionsServiceImpl implements XydSystemVersionsService{
    @Autowired
    private XydSystemVersionsMapper xydSystemVersionsMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydSystemVersionsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(XydSystemVersions record) {
        return xydSystemVersionsMapper.insert(record);
    }

    @Override
    public int insertSelective(XydSystemVersions record) {
        return  xydSystemVersionsMapper.insertSelective(record);
    }

    @Override
    public XydSystemVersions selectByPrimaryKey(Integer id) {
        return xydSystemVersionsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydSystemVersions record) {
        return xydSystemVersionsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydSystemVersions> selectList(XydSystemVersions xydSystemVersions) {
        return xydSystemVersionsMapper.selectList(xydSystemVersions);
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydSystemVersionsMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydSystemVersions> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydSystemVersionsMapper.selectByParamList(queryParam, rowBounds);
    }

    @Override
    public XydSystemVersions selectByVersion(String versionsId) {
        return xydSystemVersionsMapper.selectByVersion(versionsId);
    }
}
