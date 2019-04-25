package com.dkd.service;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.model.XydChild;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/20.
 */

public interface XydChildService {
    int insertSelective(XydChild xydChild);

    List<XydChild> selectByList(XydChild xydChild);

    XydChild selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydChild xydChild);

    int selectByParamCount(GenericQueryParam queryParam);

    List<XydChild> selectByParamList(GenericQueryParam queryParam);
}
