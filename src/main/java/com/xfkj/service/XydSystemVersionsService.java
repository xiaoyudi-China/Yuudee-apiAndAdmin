package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydSystemVersions;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/26.
 */
public interface XydSystemVersionsService  {
    int deleteByPrimaryKey(Integer id);

    int insert(XydSystemVersions record);

    int insertSelective(XydSystemVersions record);

    XydSystemVersions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydSystemVersions record);

    List<XydSystemVersions> selectList(XydSystemVersions xydSystemVersions);

    int selectByParamCount(GenericQueryParam queryParam);

    List<XydSystemVersions> selectByParamList(GenericQueryParam queryParam);

    XydSystemVersions selectByVersion(String versionsId);
}
