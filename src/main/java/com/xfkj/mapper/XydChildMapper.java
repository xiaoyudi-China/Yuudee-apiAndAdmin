package com.xfkj.mapper;

import com.xfkj.model.XydChild;
import org.springframework.stereotype.Repository;

@Repository
public interface XydChildMapper extends BaseMapper<XydChild> {
    int updateByPrimaryKey(XydChild record);
}