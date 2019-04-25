package com.dkd.service.impl;

import com.dkd.mapper.XydFortifierMapper;
import com.dkd.model.XydFortifier;
import com.dkd.service.XydFortifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by King on 2018/11/13.
 */
@Service
public class XydFortifierServiceImpl implements XydFortifierService {

    @Autowired
    private XydFortifierMapper fortifierMapper;

    @Override
    public List<XydFortifier> getList(Integer childId) {
        return fortifierMapper.getList(childId);
    }

    @Override
    public XydFortifier selectOne(Integer childId, String module) {
        return fortifierMapper.selectOne(childId,module);
    }

    @Override
    public int insert(XydFortifier fortifier1) {
        return fortifierMapper.insertSelective(fortifier1);
    }

    @Override
    public int update(XydFortifier fortifier1) {
        return fortifierMapper.updateByPrimaryKeySelective(fortifier1);
    }
}
