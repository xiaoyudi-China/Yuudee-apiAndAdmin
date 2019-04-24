package com.xfkj.service.impl;

import com.xfkj.mapper.XydToyMapper;
import com.xfkj.model.XydToy;
import com.xfkj.service.XydToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by King on 2018/11/14.
 */
@Service
public class XydToyServiceImpl implements XydToyService {

    @Autowired
    private XydToyMapper toyMapper;

    @Override
    public List<XydToy> selectByEntity(XydToy xydToy) {
        return toyMapper.selectByEntity(xydToy);
    }

    @Override
    public int updateByUserId(XydToy xydToy1) {
        return toyMapper.updateByUserId(xydToy1);
    }

    @Override
    public int updateUseToy(XydToy toy) {
        return toyMapper.updateUseToy(toy);
    }

    @Override
    public int insertList(List<XydToy> xydToy1, String module, Integer childId, String s) {
        return toyMapper.insertList(xydToy1,module,childId,s);
    }

    @Override
    public int emptyUpdateUseToy(XydToy xydToy) {
        return toyMapper.emptyUpdateUseToy(xydToy);
    }
}
