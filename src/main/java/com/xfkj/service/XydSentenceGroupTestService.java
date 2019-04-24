package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydSentenceGroupTest;

import java.util.List;

/**
 * Created by King on 2018/10/8.
 */
public interface XydSentenceGroupTestService {
    List<XydSentenceGroupTest> selectAllByRand();

    int count(GenericQueryParam queryParam);

    List<XydSentenceGroupTest> select(GenericQueryParam queryParam);

    int insert(XydSentenceGroupTest verbTest);

    int delete(Integer id);

    int update(XydSentenceGroupTest verbTest);

    XydSentenceGroupTest selectById(Integer id);
}
