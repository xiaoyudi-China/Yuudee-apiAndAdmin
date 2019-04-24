package com.xfkj.service.impl;

import com.xfkj.mapper.XydPcidTypeMapper;
import com.xfkj.model.XydPcidType;
import com.xfkj.service.XydPcdiTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/8.
 */
@Service
public class XydPcdiTypeServiceImpl implements XydPcdiTypeService {
    @Autowired
    private XydPcidTypeMapper xydPcidTypeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydPcidTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(XydPcidType record) {
        return xydPcidTypeMapper.insertSelective(record);
    }

    @Override
    public XydPcidType selectByPrimaryKey(Integer id) {
        return xydPcidTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydPcidType record) {
        return xydPcidTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydPcidType> selectByList(XydPcidType record) {
        return xydPcidTypeMapper.selectList(record);
    }

    @Override
    public List<XydPcidType> selectByTypeSortList(XydPcidType xydPcidType) {
        return  xydPcidTypeMapper.selectByTypeSortList(xydPcidType);
    }
}
