package com.xfkj.mapper;

import com.xfkj.model.XydSentenceResolveTest;

import java.util.List;

public interface XydSentenceResolveTestMapper extends BaseMapper<XydSentenceResolveTest>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydSentenceResolveTest record);

    int insertSelective(XydSentenceResolveTest record);

    XydSentenceResolveTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydSentenceResolveTest record);

    int updateByPrimaryKey(XydSentenceResolveTest record);

    List<XydSentenceResolveTest> selectAllByRand();
}