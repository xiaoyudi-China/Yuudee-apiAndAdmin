package com.xfkj.service;

import com.xfkj.model.XydAnswerResults;
import com.xfkj.model.XydChild;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/10/24.
 */
public interface XydAnswerResultsService {
    int insertSelective(XydAnswerResults xydAnswerResults);

    List<XydAnswerResults> selectByList(XydAnswerResults xydAnswerResults);

    XydAnswerResults selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XydAnswerResults xydAnswerResults);

}
