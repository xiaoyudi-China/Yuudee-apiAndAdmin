package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydArea;
import com.xfkj.model.XydSystemImages;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/26.
 */
public interface XydAreaService {
    int deleteByPrimaryKey(Integer areaid);

    int insertSelective(XydArea record);

    XydArea selectByPrimaryKey(Integer areaid);

    int updateByPrimaryKeySelective(XydArea record);

     List<XydArea> selectByList(XydArea xydArea);

    int count(GenericQueryParam queryParam);

    List<XydArea> select(GenericQueryParam queryParam);

    int deleteChilCity(Integer parentid);
}
