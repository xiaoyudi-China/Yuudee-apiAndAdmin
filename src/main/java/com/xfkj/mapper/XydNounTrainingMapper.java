package com.xfkj.mapper;

import com.xfkj.model.XydNounTraining;

import java.util.List;

public interface XydNounTrainingMapper extends BaseMapper<XydNounTraining>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydNounTraining record);

    int insertSelective(XydNounTraining record);

    XydNounTraining selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydNounTraining record);

    int updateByPrimaryKey(XydNounTraining record);

    List<XydNounTraining> selectAllRand();
}