package com.dkd.service;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.model.XydAnswerRecord;
import com.dkd.model.XydParents;
import com.dkd.model.XydPcidMust;
import com.dkd.model.XydPcidType;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/8.
 */
public interface XydPcidMustService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(XydPcidMust record);

    XydPcidMust selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydPcidMust record);

    List<XydPcidMust> selectByTypeLis(XydPcidMust xydPcidMust);

    //获取pcdi必做每种类型题加对应的答案
    List<XydPcidMust> selectByTypeAndOutLis(XydPcidType pcidType, XydAnswerRecord xydAnswerRecord, XydParents xydParents);

    //后台列表总数
    int selectByParamCount(GenericQueryParam queryParam);
    //后台列表
    List<XydPcidMust> selectByParamList(GenericQueryParam queryParam);
}
