package com.dkd.service;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.model.XydResultScale;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/31.
 */
public interface XydResultScaleService {
    int selectByParamCount(GenericQueryParam queryParam);

    List<XydResultScale> selectByParamList(GenericQueryParam queryParam);

    XydResultScale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydResultScale resultScale);

    int insertSelective(XydResultScale resultScale);
}
