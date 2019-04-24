package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydSentenceGroupTraining;

import java.util.List;

/**
 * Created by King on 2018/10/8.
 */
public interface XydSentenceGroupTrainingService {

    List<XydSentenceGroupTraining> selectAllByRand();

    int count(GenericQueryParam queryParam);

    List<XydSentenceGroupTraining> select(GenericQueryParam queryParam);

    int insert(XydSentenceGroupTraining verbTest);

    int delete(Integer id);

    int update(XydSentenceGroupTraining verbTest);

    XydSentenceGroupTraining selectById(Integer id);
}
