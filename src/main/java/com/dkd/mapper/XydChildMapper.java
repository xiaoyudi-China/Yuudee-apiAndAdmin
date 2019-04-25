package com.dkd.mapper;

import com.dkd.model.XydChild;
import org.springframework.stereotype.Repository;

@Repository
public interface XydChildMapper extends BaseMapper<XydChild> {
    int updateByPrimaryKey(XydChild record);
}