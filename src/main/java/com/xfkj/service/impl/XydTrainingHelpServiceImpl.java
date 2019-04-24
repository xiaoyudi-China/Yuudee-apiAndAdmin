package com.xfkj.service.impl;

import com.xfkj.mapper.XydTrainingHelptimeMapper;
import com.xfkj.model.XydTrainingHelptime;
import com.xfkj.service.XydTrainingHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by King on 2018/9/29.
 */
@Service
public class XydTrainingHelpServiceImpl implements XydTrainingHelpService {

    @Autowired
    private XydTrainingHelptimeMapper helptimeMapper;

    @Override
    public XydTrainingHelptime selectByEntity(XydTrainingHelptime helptime1) {
        return helptimeMapper.selectByEntity(helptime1);
    }

    @Override
    public int insert(XydTrainingHelptime helptime2) {
        return helptimeMapper.insertSelective(helptime2);
    }

    @Override
    public int update(XydTrainingHelptime helptime) {
        return helptimeMapper.updateByPrimaryKeySelective(helptime);
    }

    @Override
    public List<XydTrainingHelptime> selectByEntityList(XydTrainingHelptime helptime1) {
        return helptimeMapper.selectByEntityList(helptime1);
    }
}
