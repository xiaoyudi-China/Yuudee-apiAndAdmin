package com.xfkj.service;

import com.xfkj.model.XydIntroduce;
import com.xfkj.model.XydSystemRemind;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/27.
 * 用户提醒表
 */
public interface XydSystemRemindService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(XydSystemRemind record);

    XydSystemRemind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydSystemRemind record);

    List<XydSystemRemind> selectList(XydSystemRemind record);

    int selecPrefectCount();

    int updateRemind(Integer parentsId);
}
