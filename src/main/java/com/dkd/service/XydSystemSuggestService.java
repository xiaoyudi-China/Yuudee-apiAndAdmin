package com.dkd.service;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.model.XydSystemSuggest;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/26.
 */
public interface XydSystemSuggestService {
    int deleteByPrimaryKey(Integer id);

    int insert(XydSystemSuggest record);

    int insertSelective(XydSystemSuggest record);

    XydSystemSuggest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydSystemSuggest record);

    List<XydSystemSuggest> selectList(XydSystemSuggest xydSystemSuggest);

    int selectByParamCount(GenericQueryParam queryParam);

    List<XydSystemSuggest> selectByParamList(GenericQueryParam queryParam);
}
