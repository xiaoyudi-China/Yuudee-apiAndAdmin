package com.dkd.service;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.model.XydIntroduce;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/26.
 */
public interface XydIntroduceService {
    XydIntroduce selectByPrimaryKey(Integer id);

    List<XydIntroduce> selectList(XydIntroduce xydIntroduce);

    int selectByParamCount(GenericQueryParam queryParam);

    List<XydIntroduce> selectByParamList(GenericQueryParam queryParam);

    int insertSelective(XydIntroduce xydIntroduce);

    int updateByPrimaryKeySelective(XydIntroduce xydIntroduce);

    XydIntroduce selectByType(String s);
}
