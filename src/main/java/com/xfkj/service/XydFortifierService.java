package com.xfkj.service;

import com.xfkj.model.XydFortifier;

import java.util.List;

/**
 * Created by King on 2018/11/13.
 */
public interface XydFortifierService {

    List<XydFortifier> getList(Integer childId);

    XydFortifier selectOne(Integer childId, String module);

    int insert(XydFortifier fortifier1);

    int update(XydFortifier fortifier1);
}
