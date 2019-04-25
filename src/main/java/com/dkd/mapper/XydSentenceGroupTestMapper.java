package com.dkd.mapper;

import com.dkd.model.XydSentenceGroupTest;

import java.util.List;

public interface XydSentenceGroupTestMapper extends BaseMapper<XydSentenceGroupTest>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydSentenceGroupTest record);

    int insertSelective(XydSentenceGroupTest record);

    XydSentenceGroupTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydSentenceGroupTest record);

    int updateByPrimaryKey(XydSentenceGroupTest record);

    List<XydSentenceGroupTest> selectAllByRand();
}