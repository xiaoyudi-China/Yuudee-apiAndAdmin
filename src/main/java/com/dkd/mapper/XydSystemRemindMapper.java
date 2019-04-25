package com.dkd.mapper;

import com.dkd.model.XydSystemRemind;

public interface XydSystemRemindMapper extends BaseMapper<XydSystemRemind>{

    int updateByPrimaryKey(XydSystemRemind record);

    int selecPrefectCount();

    int updateRemind(Integer parentsId);
}