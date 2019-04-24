package com.xfkj.mapper;

import com.xfkj.model.XydPhoneQcellcore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XydPhoneQcellcoreMapper extends BaseMapper<XydPhoneQcellcore> {

    int updateByPrimaryKey(XydPhoneQcellcore record);

    List<XydPhoneQcellcore> selectByTypeList();
}