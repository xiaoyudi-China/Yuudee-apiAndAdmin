package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydSystemImages;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/12.
 */
public interface XydSystemImagesService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(XydSystemImages record);

    XydSystemImages selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydSystemImages record);

    List<XydSystemImages> selectList(XydSystemImages record);

    int count(GenericQueryParam queryParam);

    List<XydSystemImages> select(GenericQueryParam queryParam);

    XydSystemImages selectByType(String s);
}
