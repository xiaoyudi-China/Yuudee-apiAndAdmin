package com.dkd.service.impl;

import com.dkd.common.query.GenericQueryParam;
import com.dkd.common.utils.MyMD5Util;
import com.dkd.mapper.XydParentsMapper;
import com.dkd.mapper.XydSystemRemindMapper;
import com.dkd.model.XydParents;
import com.dkd.model.XydSystemRemind;
import com.dkd.service.XydParentsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/21.
 */
@Service
public class XydParentsServiceImpl implements XydParentsService {
    @Autowired
    private XydParentsMapper xydParentsMapper;
    @Autowired
    private XydSystemRemindMapper xydSystemRemindMapper;

    @Override
    @Transactional
    public int insertSelective(XydParents xydParents) {
        return xydParentsMapper.insertSelective(xydParents);
    }

    @Override
    public List<XydParents> selectByEntityList(XydParents xydParents) {
        return xydParentsMapper.selectList(xydParents);
    }

    @Override
    public int updateByPrimaryKeySelective(XydParents xydParents) {
        return xydParentsMapper.updateByPrimaryKeySelective(xydParents);
    }

    @Override
    public XydParents selectByPrimaryKey(Integer id) {
        return xydParentsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydParentsMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydParents> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydParentsMapper.selectByParamList(queryParam, rowBounds);
    }

    @Override
    public int manageAddParents(String phone, String password, Integer qcellcoreId) {
        //添加
        XydParents xydParents = new XydParents();
        xydParents.setPhoneNumber(phone);
        try {
            xydParents.setPassword(MyMD5Util.getEncryptedPwd(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        xydParents.setQcellcoreId(qcellcoreId);
        xydParents.setStatus("1");
        xydParents.setCreateTime(new Date());
        if ( xydParentsMapper.insertSelective(xydParents) > 0){
            //添加儿童个人信息提醒记录
            XydSystemRemind xydSystemRemind = new XydSystemRemind();
            xydSystemRemind.setStates("1");
            xydSystemRemind.setIsRemind("1");
            xydSystemRemind.setCreateTime(new Date());
            xydSystemRemind.setUpdateTime(new Date());
            xydSystemRemind.setParentsId(xydParents.getId());
            xydSystemRemind.setType("2");
            xydSystemRemind.setTitle("完善儿童个人信息提醒");
            xydSystemRemindMapper.insertSelective(xydSystemRemind);
            //添加pcdi问卷提醒
            xydSystemRemind.setType("1");
            xydSystemRemind.setTitle("pcid问卷提醒");
            xydSystemRemindMapper.insertSelective(xydSystemRemind);
            //添加abc问卷提醒
            xydSystemRemind.setType("3");
            xydSystemRemind.setTitle("abc问卷提醒");
            xydSystemRemindMapper.insertSelective(xydSystemRemind);
            return 1;
        }
        return 0;
    }

    @Override
    public int selectRegisterCount(XydParents xydParents) {
        return xydParentsMapper.selectRegisterCount(xydParents);
    }

    @Override
    public List<Map<String, Object>> selectDayCount(Date date, Date date1) {
        return xydParentsMapper.selectDayCount(date, date1);
    }

    @Override
    public List<Map<String, Object>> selectTotalCount(Date date, Date date1) {
        return xydParentsMapper.selectTotalCount(date, date1);
    }
}
