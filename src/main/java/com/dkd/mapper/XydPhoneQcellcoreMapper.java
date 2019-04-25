package com.dkd.mapper;

import com.dkd.model.XydPhoneQcellcore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XydPhoneQcellcoreMapper extends BaseMapper<XydPhoneQcellcore> {

    int updateByPrimaryKey(XydPhoneQcellcore record);

    List<XydPhoneQcellcore> selectByTypeList();
}