package com.xfkj.service;

import com.xfkj.model.XydPhoneQcellcore;

import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/25.
 */
public interface XydPhoneQcellcoreService {
    XydPhoneQcellcore selectByPrimaryKey(Integer id);

    List<XydPhoneQcellcore> selectByEntityList(XydPhoneQcellcore xydPhoneQcellcore);
    //根据首字符不同以及热门城市分组查询出集合
    List<XydPhoneQcellcore> selectByTypeList();

}
