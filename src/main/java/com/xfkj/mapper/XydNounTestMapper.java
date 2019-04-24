package com.xfkj.mapper;

import com.xfkj.model.XydNounTest;

import java.util.List;

public interface XydNounTestMapper extends BaseMapper<XydNounTest>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydNounTest record);

    int insertSelective(XydNounTest record);

    XydNounTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydNounTest record);

    int updateByPrimaryKey(XydNounTest record);

    List<XydNounTest> selectAllRand();
}