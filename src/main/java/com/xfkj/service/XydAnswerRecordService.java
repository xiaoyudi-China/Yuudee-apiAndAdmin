package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydAnswerRecord;
import com.xfkj.model.XydAnswerResults;
import com.xfkj.model.XydParents;
import com.xfkj.model.model_extends.XyydAnswerRecordExtends;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/28.
 */
public interface XydAnswerRecordService {
    int insertSelective(XydAnswerRecord xydAnswerRecord);

    List<XydAnswerRecord> selectByList(XydAnswerRecord xydAnswerRecord);

    XydAnswerRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydAnswerRecord xydAnswerRecord);

    //查询指定到当前时间填写问卷次数
    List<XydAnswerRecord> selectByThreeCountList(Integer parentsId, Integer childId, Date createTime, String states, String[] types);

    //查询pcdi量分与学习时长、总通管率问题。
    List selectBystudyRateList(Integer parentsId, Integer childId,String isValid, String states, String[] types);

    //添加abc问卷答题记录
    XydAnswerRecord insertAbcResult(XydParents xydParents, Double score, List resultList) throws Exception;
    //添加pcdi问卷答题记录
    XydAnswerRecord insertPcdiResult(XydParents xydParents,Integer nounCount,Integer verbCount,Integer adjCount, Double score, List list) throws Exception;

    XydAnswerRecord selectByReportInfoList(XydParents xydParents, String type);
    //添加pcdi全部问卷答题记录
    XydAnswerRecord insertPcdiAllResult(XydParents xydParents, XydAnswerRecord xydAnswerRecord,List<Map<String, Object>> list, int count) throws Exception;

    int selectByParamCount(GenericQueryParam queryParam);

    List<XyydAnswerRecordExtends> selectByParamList(GenericQueryParam queryParam);

    List selectByPcdiOPRateList(Integer parentsId, Integer childId, String isValid, String states, String[] types);

    List selectByPcdiMustRateList(Integer parentsId, Integer childId, String isValid, String states, String[] types);

    List selectByAbcRateList(Integer parentsId, Integer childId, String isValid, String states, String[] types);

    int selectTypeCount(String type);

    List<XydAnswerRecord> selectByTypeList(XydAnswerRecord xydAnswerRecord);
}
