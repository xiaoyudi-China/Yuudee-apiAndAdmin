package com.xfkj.mapper;

import com.xfkj.model.XydSentenceResolveTraining;

import java.util.List;

public interface XydSentenceResolveTrainingMapper extends BaseMapper<XydSentenceResolveTraining>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydSentenceResolveTraining record);

    int insertSelective(XydSentenceResolveTraining record);

    XydSentenceResolveTraining selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydSentenceResolveTraining record);

    int updateByPrimaryKey(XydSentenceResolveTraining record);

    List<XydSentenceResolveTraining> selectAllByRand();
}