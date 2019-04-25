package com.dkd.mapper;

import com.dkd.model.XydExperienceRecord;

import java.util.List;

public interface XydExperienceRecordMapper extends BaseMapper<XydExperienceRecord>{

    int updateByPrimaryKey(XydExperienceRecord record);

    List<XydExperienceRecord> selectByList(XydExperienceRecord xydExperienceRecord);

    int selectRegisterCount(XydExperienceRecord experienceRecord);
}