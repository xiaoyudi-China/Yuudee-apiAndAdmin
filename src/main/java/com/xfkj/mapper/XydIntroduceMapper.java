package com.xfkj.mapper;

import com.xfkj.model.XydIntroduce;
import org.apache.ibatis.annotations.Param;

public interface XydIntroduceMapper extends BaseMapper<XydIntroduce>{

    int updateByPrimaryKey(XydIntroduce record);

    XydIntroduce selectByType(@Param(value = "type") String s);
}