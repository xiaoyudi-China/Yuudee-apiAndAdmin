package com.dkd.service.impl;

import com.dkd.mapper.XydPhoneQcellcoreMapper;
import com.dkd.model.XydPhoneQcellcore;
import com.dkd.service.XydPhoneQcellcoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/25.
 */
@Service
public class XydPhoneQcellcoreServiceImpl implements XydPhoneQcellcoreService{
    @Autowired
    private XydPhoneQcellcoreMapper xydPhoneQcellcoreMapper;

    @Override
    public XydPhoneQcellcore selectByPrimaryKey(Integer id) {
        return xydPhoneQcellcoreMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<XydPhoneQcellcore> selectByEntityList(XydPhoneQcellcore xydPhoneQcellcore) {
        return xydPhoneQcellcoreMapper.selectList(xydPhoneQcellcore);
    }

    @Override
    public List<XydPhoneQcellcore> selectByTypeList() {
        return xydPhoneQcellcoreMapper.selectByTypeList();
    }
}
