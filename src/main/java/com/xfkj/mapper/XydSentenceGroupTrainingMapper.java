package com.xfkj.mapper;

import com.xfkj.model.XydSentenceGroupTraining;

import java.util.List;

public interface XydSentenceGroupTrainingMapper extends BaseMapper<XydSentenceGroupTraining>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydSentenceGroupTraining record);

    int insertSelective(XydSentenceGroupTraining record);

    XydSentenceGroupTraining selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydSentenceGroupTraining record);

    int updateByPrimaryKey(XydSentenceGroupTraining record);

    List<XydSentenceGroupTraining> selectAllByRand();
}