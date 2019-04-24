package com.xfkj.mapper;

import com.xfkj.model.XydNounSense;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydNounSenseMapper extends BaseMapper<XydNounSense>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydNounSense record);

    int insertSelective(XydNounSense record);

    XydNounSense selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydNounSense record);

    int updateByPrimaryKey(XydNounSense record);

    List<XydNounSense> selectAllRand();

    XydNounSense selectByType(@Param(value = "disturbType") String disturbType,@Param(value = "id") Integer id, @Param(value = "adjectiveChar") String adjectiveChar, @Param(value = "noneChar") String noneChar);
}