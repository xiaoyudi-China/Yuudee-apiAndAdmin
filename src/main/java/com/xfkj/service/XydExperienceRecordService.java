package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydChild;
import com.xfkj.model.XydExperienceRecord;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/11/15.
 */
public interface XydExperienceRecordService {
    int insertSelective(XydExperienceRecord xydExperienceRecord);

    List<XydExperienceRecord> selectByList(XydExperienceRecord xydExperienceRecord);

    XydExperienceRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydExperienceRecord xydExperienceRecord);
    //统计名词体验人数
    int selectRegisterCount(XydExperienceRecord experienceRecord);
}
