package com.dkd.mapper;

import com.dkd.model.XydSystemImages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydSystemImagesMapper extends BaseMapper<XydSystemImages>{

    int updateByPrimaryKey(XydSystemImages record);

    List<XydSystemImages> selectList(XydSystemImages record);

    XydSystemImages selectByType(@Param(value = "type") String s);
}