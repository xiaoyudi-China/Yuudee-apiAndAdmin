package com.xfkj.service.impl;

import com.xfkj.mapper.XydExperienceRecordMapper;
import com.xfkj.model.XydExperienceRecord;
import com.xfkj.service.XydExperienceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/11/15.
 */
@Service
public class XydExperienceRecordServiceImpl implements XydExperienceRecordService {
    @Autowired
    private XydExperienceRecordMapper xydExperienceRecordMapper;
    @Override
    public int insertSelective(XydExperienceRecord xydExperienceRecord) {
        return xydExperienceRecordMapper.insertSelective(xydExperienceRecord);
    }

    @Override
    public List<XydExperienceRecord> selectByList(XydExperienceRecord xydExperienceRecord) {
        return  xydExperienceRecordMapper.selectByList(xydExperienceRecord);
    }

    @Override
    public XydExperienceRecord selectByPrimaryKey(Integer id) {
        return  xydExperienceRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydExperienceRecord xydExperienceRecord) {
        return xydExperienceRecordMapper.updateByPrimaryKeySelective(xydExperienceRecord);
    }

    @Override
    public int selectRegisterCount(XydExperienceRecord experienceRecord) {
        return xydExperienceRecordMapper.selectRegisterCount(experienceRecord);
    }
}
