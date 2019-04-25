package com.dkd.service.impl;

import com.dkd.mapper.XydAnswerResultsMapper;
import com.dkd.model.XydAnswerResults;
import com.dkd.service.XydAnswerResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/24.
 */
@Service
public class XydAnswerResultsServiceImpl implements XydAnswerResultsService {
    @Autowired
    private XydAnswerResultsMapper xydAnswerResultsMapper;
    @Override
    public int insertSelective(XydAnswerResults xydAnswerResults) {
        return xydAnswerResultsMapper.insertSelective(xydAnswerResults);
    }

    @Override
    public List<XydAnswerResults> selectByList(XydAnswerResults xydAnswerResults) {
        return xydAnswerResultsMapper.selectList(xydAnswerResults);
    }

    @Override
    public XydAnswerResults selectByPrimaryKey(Integer id) {
        return xydAnswerResultsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydAnswerResults xydAnswerResults) {
        return xydAnswerResultsMapper.updateByPrimaryKeySelective(xydAnswerResults);
    }
}
