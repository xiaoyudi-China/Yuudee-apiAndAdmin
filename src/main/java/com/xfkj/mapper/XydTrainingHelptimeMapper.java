package com.xfkj.mapper;

import com.xfkj.model.XydTrainingHelptime;

import java.util.List;

public interface XydTrainingHelptimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(XydTrainingHelptime record);

    int insertSelective(XydTrainingHelptime record);

    XydTrainingHelptime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydTrainingHelptime record);

    int updateByPrimaryKey(XydTrainingHelptime record);

    XydTrainingHelptime selectByEntity(XydTrainingHelptime helptime1);

    List<XydTrainingHelptime> selectByEntityList(XydTrainingHelptime helptime1);
}