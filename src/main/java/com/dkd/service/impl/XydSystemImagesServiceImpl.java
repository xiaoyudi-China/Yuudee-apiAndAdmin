package com.dkd.service.impl;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.mapper.XydSystemImagesMapper;
import com.dkd.model.XydSystemImages;
import com.dkd.service.XydSystemImagesService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/12.
 */
@Service
public class XydSystemImagesServiceImpl implements XydSystemImagesService {
    @Autowired
    private XydSystemImagesMapper xydSystemImagesMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydSystemImagesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(XydSystemImages record) {
        return xydSystemImagesMapper.insertSelective(record);
    }

    @Override
    public XydSystemImages selectByPrimaryKey(Integer id) {
        return xydSystemImagesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydSystemImages record) {
        return xydSystemImagesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydSystemImages> selectList(XydSystemImages record) {
        return xydSystemImagesMapper.selectList(record);
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return xydSystemImagesMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydSystemImages> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydSystemImagesMapper.selectByParamList(queryParam, rowBounds);
    }

    @Override
    public XydSystemImages selectByType(String s) {
        return xydSystemImagesMapper.selectByType(s);
    }
}
