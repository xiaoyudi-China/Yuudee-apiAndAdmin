package com.xfkj.service;

import com.xfkj.model.XydTrainingHelptime;

import java.util.List;

/**
 * Created by King on 2018/9/29.
 */
public interface XydTrainingHelpService {
    XydTrainingHelptime selectByEntity(XydTrainingHelptime helptime1);

    int insert(XydTrainingHelptime helptime2);

    int update(XydTrainingHelptime helptime);

    List<XydTrainingHelptime> selectByEntityList(XydTrainingHelptime helptime1);
}
