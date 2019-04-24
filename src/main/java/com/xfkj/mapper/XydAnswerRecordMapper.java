package com.xfkj.mapper;


import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydAnswerRecord;
import com.xfkj.model.model_extends.XyydAnswerRecordExtends;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;

public interface XydAnswerRecordMapper extends BaseMapper<XydAnswerRecord>{

    int updateByPrimaryKey(XydAnswerRecord record);

    List<XydAnswerRecord> selectByThreeCountList(@Param("parentsId") Integer parentsId, @Param("childId")Integer childId,@Param("timeDate")Date timeDate,@Param("states")String states, @Param("types") String[] types);

    List<XydAnswerRecord> selectBystudyRateList(@Param("parentsId") Integer parentsId, @Param("childId")Integer childId, @Param("isValid")String isValid, @Param("states")String states, @Param("types")String[] types);
    //pcdi必做30个句子与学习时长、总测试进度list
    List<Object[]> selectBystudyRateOutList(@Param("parentsId") Integer parentsId, @Param("childId")Integer childId, @Param("isValid")String isValid, @Param("states")String states, @Param("types")String[] types);

    List<XyydAnswerRecordExtends> selectExtendsByParamList(GenericQueryParam queryParam, RowBounds rowBounds);

    //pcdi选做词汇与学习时长、总测试进度list
    List<Object[]> selectByPcdiOPRateList(@Param("parentsId") Integer parentsId, @Param("childId")Integer childId, @Param("isValid")String isValid, @Param("states")String states, @Param("types")String[] types);

    //pcdi必做词汇与学习时长、总测试进度list
    List<Object[]> selectByPcdiMustRateList(@Param("parentsId") Integer parentsId, @Param("childId")Integer childId, @Param("isValid")String isValid, @Param("states")String states, @Param("types")String[] types);

    //abc与学习时长、总测试进度list
    List<Object[]> selectByAbcRateList(@Param("parentsId") Integer parentsId, @Param("childId")Integer childId, @Param("isValid")String isValid, @Param("states")String states, @Param("types")String[] types);

    int selectTypeCount(@Param("type")String type);

    List<XydAnswerRecord> selectByTypeList(XydAnswerRecord xydAnswerRecord);
    //更具类型查询问卷
    List<XydAnswerRecord> selectByArrTypeList(@Param("parentsId") Integer parentsId, @Param("types")String[] types);
}