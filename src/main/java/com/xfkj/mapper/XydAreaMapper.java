package com.xfkj.mapper;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydArea;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XydAreaMapper {

    int updateByPrimaryKey(XydArea record);

    int deleteChilCity(Integer parentid);

    int deleteByPrimaryKey(Integer areaid);

    int insertSelective(XydArea xydAreaa);

    XydArea selectByPrimaryKey(Integer areaid);

    int updateByPrimaryKeySelective(XydArea xydAreaa);

    List<XydArea> selectList(XydArea xydArea);

    int selectByParamCount(GenericQueryParam queryParam);

    List<XydArea> selectByParamList(GenericQueryParam queryParam, RowBounds rowBounds);
}