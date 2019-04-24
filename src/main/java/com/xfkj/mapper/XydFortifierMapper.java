package com.xfkj.mapper;

import com.xfkj.model.XydFortifier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydFortifierMapper extends BaseMapper<XydFortifier>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydFortifier record);

    int insertSelective(XydFortifier record);

    XydFortifier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydFortifier record);

    int updateByPrimaryKey(XydFortifier record);

    List<XydFortifier> getList(@Param(value = "userId") Integer childId);

    XydFortifier selectOne(@Param(value = "userId") Integer childId, @Param(value = "module") String module);
}