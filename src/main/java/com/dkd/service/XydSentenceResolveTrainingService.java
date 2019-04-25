package com.dkd.service;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.model.XydSentenceResolveTraining;

import java.util.List;

/**
 * Created by King on 2018/10/10.
 */
public interface XydSentenceResolveTrainingService {
    List<XydSentenceResolveTraining> selectAllByRand();

    int count(GenericQueryParam queryParam);

    List<XydSentenceResolveTraining> select(GenericQueryParam queryParam);

    int insert(XydSentenceResolveTraining sentenceResolveTraining);

    int delete(Integer id);

    int update(XydSentenceResolveTraining sentenceResolveTraining);

    XydSentenceResolveTraining selectById(Integer id);
}
