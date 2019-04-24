package com.xfkj.service.impl;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydIntroduceMapper;
import com.xfkj.model.XydIntroduce;
import com.xfkj.service.XydIntroduceService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/26.
 */
@Service
public class XydIntroduceServiceImpl implements XydIntroduceService {
    @Autowired
    private XydIntroduceMapper xydIntroduceMapper;

    @Override
    public XydIntroduce selectByPrimaryKey(Integer id) {
        return xydIntroduceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<XydIntroduce> selectList(XydIntroduce xydIntroduce) {
        return xydIntroduceMapper.selectList(xydIntroduce);
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydIntroduceMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydIntroduce> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydIntroduceMapper.selectByParamList(queryParam, rowBounds);
    }

    @Override
    public int insertSelective(XydIntroduce xydIntroduce) {
        return xydIntroduceMapper.insertSelective(xydIntroduce);
    }

    @Override
    public int updateByPrimaryKeySelective(XydIntroduce xydIntroduce) {
        return xydIntroduceMapper.updateByPrimaryKeySelective(xydIntroduce);
    }

    @Override
    public XydIntroduce selectByType(String s) {
        return xydIntroduceMapper.selectByType(s);
    }
}
