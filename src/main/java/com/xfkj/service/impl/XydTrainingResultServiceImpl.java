package com.xfkj.service.impl;

import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.mapper.XydGroupTrainingMapper;
import com.xfkj.mapper.XydTrainingResultMapper;
import com.xfkj.model.XydGroupTraining;
import com.xfkj.model.XydTrainingResult;
import com.xfkj.service.XydTrainingResultService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by King on 2018/10/9.
 */
@Service
public class XydTrainingResultServiceImpl implements XydTrainingResultService {

    @Autowired
    private XydTrainingResultMapper trainingResultMapper;

    @Autowired
    private XydGroupTrainingMapper groupTrainingMapper;

    @Override
    public int insert(XydTrainingResult xydTrainingResult) {
        return trainingResultMapper.insertSelective(xydTrainingResult);
    }

    @Override
    public int insertGroupTraining(XydGroupTraining xydGroupTraining) {
        return groupTrainingMapper.insertSelective(xydGroupTraining);
    }

    @Override
    public XydGroupTraining selectGroupTrainingById(Integer groupId) {
        return groupTrainingMapper.selectByPrimaryKey(groupId);
    }

    @Override
    public int updateGroupTraining(XydGroupTraining groupTraining1) {
        return groupTrainingMapper.updateByPrimaryKeySelective(groupTraining1);
    }

    @Override
    public XydTrainingResult selectTrainingResultById(Integer integer) {
        return trainingResultMapper.selectByPrimaryKey(integer);
    }

    @Override
    public Integer selectPassCount(Integer childId, String module) {
        return trainingResultMapper.selectPassCount(childId,module);
    }

    @Override
    public List<XydGroupTraining> selectDayResultList(Integer childId, Date time, String scene, String module) {
        return groupTrainingMapper.selectDayResultList(childId, time, scene, module);
    }

    @Override
    public Integer selectDayCountTime(Integer childId, Date time,  String module) {
        return groupTrainingMapper.selectDayCountTime(childId, time, module);
    }

    @Override
    public List<Object[]> selectWeekStayResultList(Integer childId, Date weekFirstDay, Date weekLastDay, String scene, String module) {
        return groupTrainingMapper.selectWeekStayResultList(childId, weekFirstDay, weekLastDay, scene, module);
    }


    @Override
    public List<Map<String, Object>> selectWeekByMonthStayResultList(Integer childId, Date weekFirstDay, Date weekLastDay, String scene, String module) {
        return groupTrainingMapper.selectWeekByMonthStayResultList(childId, weekFirstDay, weekLastDay, scene, module);
    }

    @Override
    public List<Map<String,Object>> selectWeekAccuracyList(Integer childId, Date weekFirstDay, Date weekLastDay, String scene, String valid, String module) {
        return groupTrainingMapper.selectWeekAccuracyList(childId, weekFirstDay, weekLastDay, scene,valid, module);
    }

    @Override
    public int count(GenericQueryParam queryParam) {
        return trainingResultMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XydTrainingResult> select(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return trainingResultMapper.selectByParamList(queryParam,rowBounds);
    }

    @Override
    public List<XydTrainingResult> selectTestPersonCount(String module, String scene) {
        return trainingResultMapper.selectTestPersonCount(module,scene);
    }

    @Override
    public List<XydTrainingResult> selectByGroupId(Integer groupId) {
        return trainingResultMapper.selectByGroupId(groupId);
    }

    @Override
    public int selectByParamStatCount(GenericQueryParam queryParam) {
        return trainingResultMapper.selectByParamStatCount(queryParam);
    }

    @Override
    public List<XydTrainingResult> selectByParamStatList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return trainingResultMapper.selectByParamStatList(queryParam,rowBounds);
    }
}
