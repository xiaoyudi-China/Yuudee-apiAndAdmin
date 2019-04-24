package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydSentenceResolveTest;

import java.util.List;

/**
 * Created by King on 2018/10/10.
 */
public interface XydSentenceResolveTestService {
    List<XydSentenceResolveTest> selectAllByRand();

    int count(GenericQueryParam queryParam);

    List<XydSentenceResolveTest> select(GenericQueryParam queryParam);

    int insert(XydSentenceResolveTest sentenceResolveTest);

    int delete(Integer id);

    int update(XydSentenceResolveTest sentenceResolveTest);

    XydSentenceResolveTest selectById(Integer id);
}
