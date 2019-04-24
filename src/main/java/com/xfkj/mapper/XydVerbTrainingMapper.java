package com.xfkj.mapper;

import com.xfkj.model.XydVerbTraining;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydVerbTrainingMapper extends BaseMapper<XydVerbTraining>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydVerbTraining record);

    int insertSelective(XydVerbTraining record);

    XydVerbTraining selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydVerbTraining record);

    int updateByPrimaryKey(XydVerbTraining record);

    List<XydVerbTraining> selectAllRand();

    XydVerbTraining selectByType(@Param(value = "verbType") String verbType,@Param(value = "cardChar") String cardChar);

    List<XydVerbTraining> selectByFiveType(@Param(value = "type") String s);

    List<XydVerbTraining> selectByFiveType1();
}