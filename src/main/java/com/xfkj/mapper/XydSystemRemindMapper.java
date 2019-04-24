package com.xfkj.mapper;

import com.xfkj.model.XydSystemRemind;

public interface XydSystemRemindMapper extends BaseMapper<XydSystemRemind>{

    int updateByPrimaryKey(XydSystemRemind record);

    int selecPrefectCount();

    int updateRemind(Integer parentsId);
}