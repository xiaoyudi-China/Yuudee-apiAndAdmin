package com.xfkj.service.impl;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydNounTrainingMapper;
import com.xfkj.model.XydNounTraining;
import com.xfkj.service.XydNounTrainingService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by King on 2018/9/29.
 */
@Service
public class XydNounTrainingServiceImpl implements XydNounTrainingService {

    @Autowired
    private XydNounTrainingMapper nounTrainingMapper;

    @Override
    public List<XydNounTraining> selectAllRand() {
        return nounTrainingMapper.selectAllRand();
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return nounTrainingMapper.selectByParamCount(queryParam);
    }


    @Override
    public List<XydNounTraining> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return nounTrainingMapper.selectByParamList(queryParam,rowBounds);
    }

    @Override
    public int insert(XydNounTraining nounTraining) {
        return nounTrainingMapper.insertSelective(nounTraining);
    }

    @Override
    public int delete(Integer id) {
        return nounTrainingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(XydNounTraining nounTraining) {
        return nounTrainingMapper.updateByPrimaryKeySelective(nounTraining);
    }

    @Override
    public XydNounTraining selectById(Integer id) {
        return nounTrainingMapper.selectByPrimaryKey(id);
    }
}
