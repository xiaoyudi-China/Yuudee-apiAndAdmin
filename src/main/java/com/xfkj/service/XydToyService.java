package com.xfkj.service;

import com.xfkj.model.XydToy;

import java.util.List;

/**
 * Created by King on 2018/11/14.
 */
public interface XydToyService {

    List<XydToy> selectByEntity(XydToy xydToy);

    int updateByUserId(XydToy xydToy1);

    int updateUseToy(XydToy toy);

    int insertList(List<XydToy> xydToy1, String module, Integer childId, String s);

    int emptyUpdateUseToy(XydToy xydToy);
}
