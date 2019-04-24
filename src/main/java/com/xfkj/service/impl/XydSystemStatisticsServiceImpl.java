package com.xfkj.service.impl;

import com.xfkj.mapper.XydSystemStatisticsMapper;
import com.xfkj.model.XydSystemStatistics;
import com.xfkj.service.XydSystemStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/10/9.
 */
@Service
public class XydSystemStatisticsServiceImpl implements XydSystemStatisticsService {
    @Autowired
    private XydSystemStatisticsMapper xydSystemStatisticsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xydSystemStatisticsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(XydSystemStatistics record) {
        return xydSystemStatisticsMapper.insertSelective(record);
    }

    @Override
    public XydSystemStatistics selectByPrimaryKey(Integer id) {
        return xydSystemStatisticsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydSystemStatistics record) {
        return xydSystemStatisticsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<XydSystemStatistics> selectByList(XydSystemStatistics record) {
        return xydSystemStatisticsMapper.selectList(record);
    }

    @Override
    public List<XydSystemStatistics> selectPlayerResultList(XydSystemStatistics xydSystemStatistics) {
        //获取当前通过的记录
        List<XydSystemStatistics> list = xydSystemStatisticsMapper.selectPlayerResultList(xydSystemStatistics);

        if (list != null ){
            int a = list.size();
            BigDecimal count = new BigDecimal(0);
            //添加总通关进度
            for (int j = 0; j < list.size(); j++){
              count = count.add(list.get(j).getRate1());
            }
            for (int i = 0; i < 4-a; i++){
                XydSystemStatistics addStatistics = new XydSystemStatistics();
                addStatistics.setUserId(xydSystemStatistics.getUserId());
                addStatistics.setRate1(new BigDecimal(0));
                addStatistics.setModule(a+1+i+"");
                list.add(addStatistics);
            }
            XydSystemStatistics addStatisticsSum = new XydSystemStatistics();
            addStatisticsSum.setUserId(xydSystemStatistics.getUserId());
            addStatisticsSum.setRate1(count.divide(new BigDecimal(4)));
            addStatisticsSum.setModule("5");
            list.add(addStatisticsSum);
        }
        return list;
    }

    @Override
    public List<XydSystemStatistics> selectPlayerResultList1(XydSystemStatistics xydSystemStatistics) {
        //获取当前通过的记录
        List<XydSystemStatistics> list = xydSystemStatisticsMapper.selectPlayerResultList(xydSystemStatistics);

     /*   if (list.size()>0){
            int a = list.size();
            BigDecimal count = new BigDecimal(0);
            //添加总通关进度
            for (int j = 0; j < list.size(); j++){
                count = count.add(list.get(j).getRate1());
            }
            for (int i = 0; i < 4-a; i++){
                XydSystemStatistics addStatistics = new XydSystemStatistics();
                addStatistics.setUserId(xydSystemStatistics.getUserId());
                addStatistics.setRate1(new BigDecimal(0));
                addStatistics.setModule(a+1+i+"");
                list.add(addStatistics);
            }
            XydSystemStatistics addStatisticsSum = new XydSystemStatistics();
            addStatisticsSum.setUserId(xydSystemStatistics.getUserId());
            addStatisticsSum.setRate1(count.divide(new BigDecimal(4)));
            addStatisticsSum.setModule("5");
            list.add(addStatisticsSum);
        }*/
        return list;
    }

    @Override
    public Double selectSumRate(XydSystemStatistics xydSystemStatistics) {
        return xydSystemStatisticsMapper.selectSumRate(xydSystemStatistics);
    }

    @Override
    public Long selectSumStudyTime(Integer id) {
        return xydSystemStatisticsMapper.selectSumStudyTime(id);
    }

    @Override
    public int selectPlayerCount(XydSystemStatistics xydSystemStatistics) {
        return xydSystemStatisticsMapper.selectPlayerCount(xydSystemStatistics);
    }

    @Override
    public List<Map<String, Object>> selectDayCount(Date date, Date date1) {
        return xydSystemStatisticsMapper.selectDayCount(date,date1);
    }

    @Override
    public List<Map<String, Object>> selectTotalCount(Date date, Date date1) {
        return xydSystemStatisticsMapper.selectTotalCount(date,date1);
    }

    @Override
    public XydSystemStatistics selectNew(Integer childId) {
        return xydSystemStatisticsMapper.selectNew(childId);
    }
}
