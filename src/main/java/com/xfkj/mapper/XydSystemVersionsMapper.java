package com.xfkj.mapper;

import com.xfkj.model.XydSystemVersions;

public interface XydSystemVersionsMapper extends BaseMapper<XydSystemVersions>{

    int updateByPrimaryKey(XydSystemVersions record);

    XydSystemVersions selectByVersion(String versionsId);
}