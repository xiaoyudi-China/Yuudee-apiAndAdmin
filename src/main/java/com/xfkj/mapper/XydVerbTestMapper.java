package com.xfkj.mapper;

import com.xfkj.model.XydVerbTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydVerbTestMapper extends BaseMapper<XydVerbTest>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydVerbTest record);

    int insertSelective(XydVerbTest record);

    XydVerbTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydVerbTest record);

    int updateByPrimaryKey(XydVerbTest record);

    List<XydVerbTest> selectAllByRand();

    XydVerbTest selectByType(@Param(value = "verbType") String verbType, @Param(value = "cardChar") String cardChar);
}