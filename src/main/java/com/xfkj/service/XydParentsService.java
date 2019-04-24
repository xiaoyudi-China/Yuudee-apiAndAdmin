package com.xfkj.service;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.model.XydParents;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/21.
 */
public interface XydParentsService {
    int insertSelective(XydParents xydParents);

    List<XydParents> selectByEntityList(XydParents xydParents);

    int updateByPrimaryKeySelective(XydParents xydParents);

    XydParents selectByPrimaryKey(Integer id);

    int selectByParamCount(GenericQueryParam queryParam);

    List<XydParents> selectByParamList(GenericQueryParam queryParam);

    int manageAddParents(String phone, String password, Integer qcellcoreId);

    int selectRegisterCount(XydParents xydParents);

    List<Map<String, Object>> selectDayCount(Date date, Date date1);

    List<Map<String, Object>> selectTotalCount(Date date, Date date1);
}
