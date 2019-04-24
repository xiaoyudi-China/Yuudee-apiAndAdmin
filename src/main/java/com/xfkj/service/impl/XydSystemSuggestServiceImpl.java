package com.xfkj.service.impl;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydSystemSuggestMapper;
import com.xfkj.model.XydSystemSuggest;
import com.xfkj.service.XydSystemSuggestService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/26.
 */
@Service
public class XydSystemSuggestServiceImpl implements XydSystemSuggestService {
    @Autowired
    private XydSystemSuggestMapper xydSystemSuggestMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydSystemSuggestMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(XydSystemSuggest record) {
        return xydSystemSuggestMapper.insert(record);
    }

    @Override
    public int insertSelective(XydSystemSuggest record) {
        return xydSystemSuggestMapper.insertSelective(record);
    }

    @Override
    public XydSystemSuggest selectByPrimaryKey(Integer id) {
        return xydSystemSuggestMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydSystemSuggest record) {
        return xydSystemSuggestMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydSystemSuggest> selectList(XydSystemSuggest xydSystemSuggest) {
        return xydSystemSuggestMapper.selectList(xydSystemSuggest);
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydSystemSuggestMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydSystemSuggest> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydSystemSuggestMapper.selectByParamList(queryParam, rowBounds);
    }
}
