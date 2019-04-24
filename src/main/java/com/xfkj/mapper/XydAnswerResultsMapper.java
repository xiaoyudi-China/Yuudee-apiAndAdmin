package com.xfkj.mapper;

import com.xfkj.model.XydAnswerRecord;
import com.xfkj.model.XydAnswerResults;
import com.xfkj.model.XydResultScale;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface XydAnswerResultsMapper extends BaseMapper<XydAnswerResults> {
    int updateByPrimaryKey(XydAnswerResults record);

    int insertResultList(@Param("list")List<XydAnswerResults> list, @Param("answerId")Integer answerId,@Param("states")String states,@Param("createTime")Date createTime);

    int insertPcdiQuestionResult(@Param("list")List<XydAnswerResults> list, @Param("answerId")Integer answerId,@Param("states")String states,@Param("createTime")Date createTime);

    int insertABCResultList(@Param("list")List<XydAnswerResults> list, @Param("answerId")Integer answerId,@Param("states")String states,@Param("createTime")Date createTime);
}