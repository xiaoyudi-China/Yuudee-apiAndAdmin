package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydVerbTest;

import java.util.List;

/**
 * Created by King on 2018/10/8.
 */
public interface XydVerbTestService {

    List<XydVerbTest> selectAllByRand();

    int count(GenericQueryParam queryParam);

    List<XydVerbTest> select(GenericQueryParam queryParam);

    int insert(XydVerbTest verbTest);

    int delete(Integer id);

    int update(XydVerbTest verbTest);

    XydVerbTest selectById(Integer id);
}
