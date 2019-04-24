package com.xfkj.service.impl;

import com.xfkj.mapper.XydSystemRemindMapper;
import com.xfkj.model.XydIntroduce;
import com.xfkj.model.XydSystemRemind;
import com.xfkj.service.XydSystemRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/27.
 */
@Service
public class XydSystemRemindServiceImpl implements XydSystemRemindService {
    @Autowired
    private XydSystemRemindMapper xydSystemRemindMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydSystemRemindMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(XydSystemRemind record) {
        return xydSystemRemindMapper.insertSelective(record);
    }

    @Override
    public XydSystemRemind selectByPrimaryKey(Integer id) {
        return xydSystemRemindMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydSystemRemind record) {
        return xydSystemRemindMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydSystemRemind> selectList(XydSystemRemind record) {
        return xydSystemRemindMapper.selectList(record);
    }

    @Override
    public int selecPrefectCount() {
        return xydSystemRemindMapper.selecPrefectCount();
    }

    @Override
    public int updateRemind(Integer parentsId) {
        return xydSystemRemindMapper.updateRemind(parentsId);
    }
}
