package com.xfkj.mapper;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydTrainingResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface XydTrainingResultMapper extends BaseMapper<XydTrainingResult>{
    int deleteByPrimaryKey(Integer id);

    int insert(XydTrainingResult record);

    int insertSelective(XydTrainingResult record);

    XydTrainingResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydTrainingResult record);

    int updateByPrimaryKey(XydTrainingResult record);

    Integer selectPassCount(@Param(value = "userId") Integer childId, @Param(value = "module") String module);

    List<XydTrainingResult> selectTestPersonCount(@Param(value = "module") String module, @Param(value = "scene") String scene);

    List<XydTrainingResult> selectByGroupId(Integer groupId);

    int selectByParamStatCount(GenericQueryParam queryParam);

    List<XydTrainingResult> selectByParamStatList(GenericQueryParam queryParam, RowBounds rowBounds);
}