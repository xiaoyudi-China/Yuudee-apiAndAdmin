package com.xfkj.mapper;

import com.xfkj.model.XydToy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XydToyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(XydToy record);

    int insertSelective(XydToy record);

    XydToy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydToy record);

    int updateByPrimaryKey(XydToy record);

    List<XydToy> selectByEntity(XydToy xydToy);

    int updateByUserId(XydToy xydToy1);

    int updateUseToy(XydToy toy);

    int insertList(@Param(value = "toy") List<XydToy> xydToy1, @Param(value = "module") String module, @Param(value = "userId") Integer childId,@Param(value = "status") String s);

    int emptyUpdateUseToy(XydToy xydToy);
}