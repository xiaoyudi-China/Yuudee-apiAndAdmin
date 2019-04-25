package com.dkd.mapper;

import com.dkd.model.XydSystemVersions;

public interface XydSystemVersionsMapper extends BaseMapper<XydSystemVersions>{

    int updateByPrimaryKey(XydSystemVersions record);

    XydSystemVersions selectByVersion(String versionsId);
}