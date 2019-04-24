package com.xfkj.service.impl;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydResultScaleMapper;
import com.xfkj.model.XydResultScale;
import com.xfkj.service.XydResultScaleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/31.
 */
@Service
public class XydResultScaleServiceImpl implements XydResultScaleService {
    @Autowired
    private XydResultScaleMapper xydResultScaleMapper;
    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydResultScaleMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydResultScale> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydResultScaleMapper.selectByParamList(queryParam, rowBounds);
    }

    @Override
    public XydResultScale selectByPrimaryKey(Integer id) {
        return xydResultScaleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydResultScale resultScale) {
        return xydResultScaleMapper.updateByPrimaryKeySelective(resultScale);
    }

    @Override
    public int insertSelective(XydResultScale resultScale) {
        return xydResultScaleMapper.insertSelective(resultScale);
    }
}
