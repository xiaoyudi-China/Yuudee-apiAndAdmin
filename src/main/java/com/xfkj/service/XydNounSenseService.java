package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydNounSense;

import java.util.List;

/**
 * Created by King on 2018/9/29.
 */
public interface XydNounSenseService {

    List<XydNounSense> selectAllRand();

    int count(GenericQueryParam queryParam);

    List<XydNounSense> select(GenericQueryParam queryParam);

    int insert(XydNounSense nounTest);

    int delete(Integer id);

    int update(XydNounSense nounTest);

    XydNounSense selectById(Integer id);
}
