package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydVerbTraining;

import java.util.List;

/**
 * Created by King on 2018/10/8.
 */
public interface XydVerbTrainingService {

    List<XydVerbTraining> selectAllRand();

    int count(GenericQueryParam queryParam);

    List<XydVerbTraining> select(GenericQueryParam queryParam);

    int insert(XydVerbTraining verbTraining);

    int delete(Integer id);

    int update(XydVerbTraining verbTraining);

    XydVerbTraining selectById(Integer id);
}
