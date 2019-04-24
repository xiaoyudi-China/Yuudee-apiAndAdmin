package com.xfkj.service.impl;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydChildMapper;
import com.xfkj.model.XydChild;
import com.xfkj.service.XydChildService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/20.
 */
@Service
public class XydChildServiceImpl implements XydChildService {
    @Autowired
    private XydChildMapper xydChildMapper;
    @Override
    public int insertSelective(XydChild xydChild) {
        return xydChildMapper.insertSelective(xydChild);
    }

    @Override
    public List<XydChild> selectByList(XydChild xydChild) {
        return xydChildMapper.selectList(xydChild);
    }

    @Override
    public XydChild selectByPrimaryKey(Integer id) {
        return xydChildMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydChild xydChild) {
        return xydChildMapper.updateByPrimaryKeySelective(xydChild);
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydChildMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydChild> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydChildMapper.selectByParamList(queryParam, rowBounds);
    }

}
