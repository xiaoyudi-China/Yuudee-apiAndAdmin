package com.xfkj.mapper;

import com.xfkj.model.XydExperienceRecord;

import java.util.List;

public interface XydExperienceRecordMapper extends BaseMapper<XydExperienceRecord>{

    int updateByPrimaryKey(XydExperienceRecord record);

    List<XydExperienceRecord> selectByList(XydExperienceRecord xydExperienceRecord);

    int selectRegisterCount(XydExperienceRecord experienceRecord);
}