package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydNounTraining;

import java.util.List;
import java.util.Map;

/**
 * Created by King on 2018/9/29.
 */
public interface XydNounTrainingService {

    List<XydNounTraining> selectAllRand();

    int count(GenericQueryParam queryParam);

    List<XydNounTraining> select(GenericQueryParam queryParam);

    int insert(XydNounTraining nounTraining);

    int delete(Integer id);

    int update(XydNounTraining nounTraining);

    XydNounTraining selectById(Integer id);
}
