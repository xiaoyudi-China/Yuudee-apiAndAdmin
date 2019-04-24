package com.xfkj.service.impl;

import com.xfkj.common.config.Global;
import com.xfkj.common.emun.PCDIMustType;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.DateUtil;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.utils.JpushTemplateUtil;
import com.xfkj.common.utils.TemplateUtils;
import com.xfkj.mapper.*;
import com.xfkj.model.*;
import com.xfkj.model.model_extends.XyydAnswerRecordExtends;
import com.xfkj.service.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by mai xiaogang on 2018/9/28.
 */
@Service
public class XydAnswerRecordServiceImpl implements XydAnswerRecordService{
    @Autowired
    private XydAnswerRecordMapper xydAnswerRecordMapper;
    @Autowired
    private XydResultScaleMapper xydResultScaleMapper;
    @Autowired
    private XydSystemStatisticsMapper xydSystemStatisticsMapper;
    @Autowired
    private XydChildMapper xydChildMapper;
    @Autowired
    private XydSystemRemindMapper xydSystemRemindMapper;
    @Autowired
    private XydAnswerResultsMapper xydAnswerResultsMapper;
    @Autowired
    private XydResultAnalyzeMapper resultAnalyzeMapper;
    @Autowired
    private XydPhoneQcellcoreMapper phoneQcellcoreMapper;
    @Autowired
    private XydChildService xydChildService;
    @Autowired
    private XydSystemRemindService xydSystemRemindService;
    @Autowired
    private XydPhoneQcellcoreService xydPhoneQcellcoreService;
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    Global global;


    @Override
    public int insertSelective(XydAnswerRecord xydAnswerRecord) {
        return xydAnswerRecordMapper.insertSelective(xydAnswerRecord);
    }

    @Override
    public List<XydAnswerRecord> selectByList(XydAnswerRecord xydAnswerRecord) {
        return xydAnswerRecordMapper.selectList(xydAnswerRecord);
    }

    @Override
    public XydAnswerRecord selectByPrimaryKey(Integer id) {
        return xydAnswerRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XydAnswerRecord xydAnswerRecord) {
        return xydAnswerRecordMapper.updateByPrimaryKeySelective(xydAnswerRecord);
    }

    @Override
    public List<XydAnswerRecord> selectByThreeCountList(Integer parentsId, Integer childId, Date createTime, String states, String[] types) {
        return xydAnswerRecordMapper.selectByThreeCountList(parentsId, childId, createTime, states, types);
    }

    @Override
    public List selectBystudyRateList(Integer parentsId, Integer childId, String isValid, String states, String[] types) {

        return xydAnswerRecordMapper.selectBystudyRateOutList(parentsId, childId, isValid, states, types);
    }

    @Transactional
    @Override
    public XydAnswerRecord insertAbcResult(XydParents xydParents, Double score, List resultList) throws Exception {
        TemplateUtils templateUtils = new TemplateUtils();
        JpushTemplateUtil jpushTemplateUtil = new JpushTemplateUtil();
        templateUtils.setXydParentsService(xydParentsService);
        templateUtils.setXydChildService(xydChildService);
        templateUtils.setXydPhoneQcellcoreService(xydPhoneQcellcoreService);
        templateUtils.setXydSystemRemindService(xydSystemRemindService);
        templateUtils.setGlobal(global);
        XydAnswerRecord xydAnswerRecord = new XydAnswerRecord();
        if (IsObjectNullUtils.is(xydParents.getChildId())){
            throw new Exception();
        }
        XydChild xydChild = xydChildMapper.selectByPrimaryKey(xydParents.getChildId());
        if (IsObjectNullUtils.is(xydChild))
            throw new Exception();
        //获取文案
        XydResultScale xydResultScale = new XydResultScale();
        xydResultScale.setType("2");
        xydResultScale.setStates("1");
        List<XydResultScale> list = xydResultScaleMapper.selectByAbcList(xydResultScale, score.intValue());
        if (IsObjectNullUtils.is(list) || list.size() < 1){
            xydAnswerRecord.setTitle("");
        }else {
            xydAnswerRecord.setTitle(list.get(0).getContent());
        }
        xydAnswerRecord.setParentsId(xydParents.getId());
        xydAnswerRecord.setUserId(xydParents.getChildId());
        //问卷类型 1 pcdi问卷必填结果页 2：pcdi问卷全部结果页 : 3 abc问卷结果页
        xydAnswerRecord.setType("3");
        xydAnswerRecord.setScore(new BigDecimal(score));
        //是否有效   1：有效 2 无效
        xydAnswerRecord.setIsValid("1");
        xydAnswerRecord.setCreateTime(new Date());
        xydAnswerRecord.setStates("1");
        //获取学习时长
        Long sumTime = xydSystemStatisticsMapper.selectSumStudyTime(xydChild.getId());
        xydAnswerRecord.setLearningTime(sumTime == null ? 0:sumTime);
        //获取总通关进度
        XydSystemStatistics sumStatistics = new XydSystemStatistics();
        sumStatistics.setUserId(xydChild.getId());
        sumStatistics.setStates("1");
        Double rate = xydSystemStatisticsMapper.selectSumRate(sumStatistics);
        Double rateAll = rate == null ? 0 : rate/4;
        xydAnswerRecord.setRateAll(new BigDecimal(rateAll));
        //获取提醒类型
        XydSystemRemind xydSystemRemind = new XydSystemRemind();
        xydSystemRemind.setParentsId(xydParents.getId());
        // 1:pcdi问卷、2: 儿童信息、3: abc问卷提醒',
        xydSystemRemind.setType("3");
        xydSystemRemind.setIsRemind("1");
        List<XydSystemRemind> systemRemindList = xydSystemRemindMapper.selectList(xydSystemRemind);
        if (!IsObjectNullUtils.is(systemRemindList) && systemRemindList.size() > 0){
            xydAnswerRecord.setStates(systemRemindList.get(0).getStates());
            //修改提醒状态为不提醒
            xydSystemRemind.setIsRemind("2");
            xydSystemRemind.setId(systemRemindList.get(0).getId());
            xydSystemRemind.setUserId(xydParents.getChildId());
            xydSystemRemindMapper.updateByPrimaryKeySelective(xydSystemRemind);
            XydPhoneQcellcore phoneQcellcore = phoneQcellcoreMapper.selectByPrimaryKey(xydParents.getQcellcoreId());
            if (!IsObjectNullUtils.is(phoneQcellcore)){
                //触发定时任务
                templateUtils.sendTemplate(DateUtil.getThreeMonthTimeLong(new Date()), "1111", phoneQcellcore.getPhonePrefix(), xydParents.getPhoneNumber(), xydSystemRemind.getId());
                jpushTemplateUtil.sendTemplate(DateUtil.getThreeMonthMornTimeLong(new Date()), "", phoneQcellcore.getPhonePrefix(), xydParents.getPhoneNumber(), xydSystemRemind.getId());
            }
            //是否可以重新评估
            xydAnswerRecord.setAnew("1");
        }else {
            //将上一次测试的记录设置为无效
            XydAnswerRecord oldRecord = new XydAnswerRecord();
            oldRecord.setParentsId(xydParents.getId());
            oldRecord.setType("3");
            List<XydAnswerRecord> recordList = xydAnswerRecordMapper.selectList(oldRecord);
            if (!IsObjectNullUtils.is(recordList) && recordList.size() > 0){
                oldRecord.setId(recordList.get(0).getId());
                oldRecord.setIsValid("2");
                xydAnswerRecordMapper.updateByPrimaryKeySelective(oldRecord);
            }
            xydAnswerRecord.setStates("1");
            xydAnswerRecord.setAnew("2");
        }
        if (xydAnswerRecordMapper.insertSelective(xydAnswerRecord) < 1){
            throw new Exception();
        }
        //批量的添加答题记录
        if ( xydAnswerResultsMapper.insertABCResultList(resultList, xydAnswerRecord.getId(), "1", new Date()) < 1){
            throw new Exception();
        }
        return xydAnswerRecord;
    }

    @Transactional
    @Override
    public XydAnswerRecord insertPcdiResult(XydParents xydParents, Integer nounCount,Integer verbCount,Integer adjCount, Double score, List resultList) throws Exception {
        TemplateUtils templateUtils = new TemplateUtils();
        JpushTemplateUtil jpushTemplateUtil = new JpushTemplateUtil();
        templateUtils.setXydParentsService(xydParentsService);
        templateUtils.setXydChildService(xydChildService);
        templateUtils.setXydPhoneQcellcoreService(xydPhoneQcellcoreService);
        templateUtils.setXydSystemRemindService(xydSystemRemindService);
        templateUtils.setGlobal(global);
        XydAnswerRecord xydAnswerRecord = new XydAnswerRecord();
        if (IsObjectNullUtils.is(xydParents.getChildId())){
            throw new Exception();
        }
        XydChild xydChild = xydChildMapper.selectByPrimaryKey(xydParents.getChildId());
        if (IsObjectNullUtils.is(xydChild) || IsObjectNullUtils.is(xydChild.getBirthdate()))
            throw new Exception();
        //更具儿童不同的月龄获取30个句子文案

        int childMonthAge = DateUtil.getMonthAge(xydChild.getBirthdate(), new Date());
        int monthAge = childMonthAge;
        XydResultAnalyze xydResultAnalyze = new XydResultAnalyze();
        //孩子大于28月龄按计算 如果小于16个月按16个月计算
        if (monthAge < global.getDefaultAgeMin()){
            monthAge = global.getDefaultAgeMin();
        }
        xydResultAnalyze.setMonthAge(monthAge > global.getDefaultAgeMax() ? global.getDefaultAgeMax() : monthAge);
        xydResultAnalyze.setStates("1");
        xydResultAnalyze.setIsOptional("1");
        xydResultAnalyze.setSex(xydChild.getSex());
        List<XydResultAnalyze> analyzeList = resultAnalyzeMapper.selectByCountList(xydResultAnalyze, score.intValue());
        if (!IsObjectNullUtils.is(analyzeList) && analyzeList.size() > 0){
            double stateScore = analyzeList.get(0).getStateScore().doubleValue();
            double endScore = analyzeList.get(0).getEndScore().doubleValue();
            double statePercent = analyzeList.get(0).getStatePercent().doubleValue();
            double endPercent = analyzeList.get(0).getEndPercent().doubleValue();
            if ((endScore - stateScore) == 0){
                score = (statePercent + endPercent)/2;
            }else {
                if (score == stateScore){
                    score = statePercent;
                }else {
                    score = (endPercent - statePercent)*(score - stateScore)/(endScore - stateScore)+statePercent;
                }
            }
        }
        int scoreInt = (int) Math.round(score);
        XydResultScale xydResultScale = new XydResultScale();
        xydResultScale.setType("1");
        xydResultScale.setStates("1");
        xydResultScale.setIsOptional("1");
        xydResultScale.setTopicType(PCDIMustType.MUST_NAME_D.getValue()+"");
        xydResultScale.setMore(getMore(childMonthAge, global.getDefaultAgeMin(), global.getDefaultAgeMax()));
        List<XydResultScale> listD = xydResultScaleMapper.selectByAbcList(xydResultScale, scoreInt);
        if (IsObjectNullUtils.is(listD) || listD.size() < 1){
            xydAnswerRecord.setTitle("");
        }else {
            xydAnswerRecord.setTitle(listD.get(0).getContent());
        }
        //获取词汇文案
        Integer count = nounCount+verbCount+adjCount;
        xydResultScale.setMore(null);
        xydResultScale.setTopicType(PCDIMustType.MUST_NAME_E.getValue()+"");
        List<XydResultScale> listE = xydResultScaleMapper.selectByAbcList(xydResultScale, count);
        if (IsObjectNullUtils.is(listE) || listD.size() < 1){
            xydAnswerRecord.setVocabulary("");
        }else {
            xydAnswerRecord.setVocabulary(listE.get(0).getContent());
        }
        xydAnswerRecord.setNounScore(nounCount);
        xydAnswerRecord.setVerbScore(verbCount);
        xydAnswerRecord.setAdjectiveScore(adjCount);
        xydAnswerRecord.setParentsId(xydParents.getId());
        xydAnswerRecord.setUserId(xydParents.getChildId());
        //问卷类型 1 pcdi问卷必填结果页 2：pcdi问卷全部结果页 : 3 abc问卷结果页
        xydAnswerRecord.setType("1");
        xydAnswerRecord.setScore(new BigDecimal(scoreInt));
        //是否有效   1：有效 2 无效
        xydAnswerRecord.setIsValid("1");
        xydAnswerRecord.setCreateTime(new Date());
        xydAnswerRecord.setStates("1");
        //获取学习时长
        Long sumTime = xydSystemStatisticsMapper.selectSumStudyTime(xydChild.getId());
        xydAnswerRecord.setLearningTime(sumTime == null ? 0L : sumTime);
        //获取总通关进度
        XydSystemStatistics sumStatistics = new XydSystemStatistics();
        sumStatistics.setUserId(xydChild.getId());
        sumStatistics.setStates("1");
        Double sum =  xydSystemStatisticsMapper.selectSumRate(sumStatistics);
        Double rateAll = sum == null ? 0:sum/4;
        xydAnswerRecord.setRateAll(new BigDecimal(rateAll));
        //获取提醒类型
        XydSystemRemind xydSystemRemind = new XydSystemRemind();
        xydSystemRemind.setParentsId(xydParents.getId());
        // 1:pcdi问卷、2: 儿童信息、3: abc问卷提醒',
        xydSystemRemind.setType("1");
        xydSystemRemind.setIsRemind("1");
        List<XydSystemRemind> systemRemindList = xydSystemRemindMapper.selectList(xydSystemRemind);
        if (!IsObjectNullUtils.is(systemRemindList) && systemRemindList.size() > 0){
            xydAnswerRecord.setStates(systemRemindList.get(0).getStates());
            //修改提醒状态为不提醒
            xydSystemRemind.setIsRemind("2");
            xydSystemRemind.setUserId(xydParents.getChildId());
            xydSystemRemind.setId(systemRemindList.get(0).getId());
            xydSystemRemindMapper.updateByPrimaryKeySelective(xydSystemRemind);
            XydPhoneQcellcore phoneQcellcore = phoneQcellcoreMapper.selectByPrimaryKey(xydParents.getQcellcoreId());
            if (!IsObjectNullUtils.is(phoneQcellcore)){
                //触发定时任务
                templateUtils.sendTemplate(DateUtil.getThreeMonthTimeLong(new Date()), "1111", phoneQcellcore.getPhonePrefix(), xydParents.getPhoneNumber(), xydSystemRemind.getId());
                jpushTemplateUtil.sendTemplate(DateUtil.getThreeMonthMornTimeLong(new Date()), "", phoneQcellcore.getPhonePrefix(), xydParents.getPhoneNumber(), xydSystemRemind.getId());
            }
            //是否可以重新评估
            xydAnswerRecord.setAnew("1");
        }else {
            //将上一次测试的记录设置为无效
            XydAnswerRecord oldRecord = new XydAnswerRecord();
            oldRecord.setParentsId(xydParents.getId());
            String[] types = {"1", "2"};
            List<XydAnswerRecord> recordList = xydAnswerRecordMapper.selectBystudyRateList(xydParents.getId(), xydChild.getId(), null, null, types);
            if (!IsObjectNullUtils.is(recordList) && recordList.size() > 0){
                oldRecord.setId(recordList.get(0).getId());
                oldRecord.setIsValid("2");
                xydAnswerRecordMapper.updateByPrimaryKeySelective(oldRecord);
            }
            xydAnswerRecord.setStates("1");
            xydAnswerRecord.setAnew("2");
        }
        if (xydAnswerRecordMapper.insertSelective(xydAnswerRecord) < 1){
            throw new Exception();
        }
        //批量的添加答题记录
        if ( xydAnswerResultsMapper.insertResultList(resultList, xydAnswerRecord.getId(), "1", new Date()) < 1){
            throw new Exception();
        }
        return xydAnswerRecord;
    }

    @Override
    public XydAnswerRecord selectByReportInfoList(XydParents xydParents, String type) {
        XydAnswerRecord xydAnswerRecord = new XydAnswerRecord();
        xydAnswerRecord.setType(type);
        xydAnswerRecord.setIsValid("1");
        xydAnswerRecord.setParentsId(xydParents.getId());
        xydAnswerRecord.setUserId(xydParents.getChildId());
        List<XydAnswerRecord> xydAnswerRecordList = xydAnswerRecordMapper.selectList(xydAnswerRecord);
        if (!IsObjectNullUtils.is(xydAnswerRecord) && xydAnswerRecordList.size() > 0){
            return xydAnswerRecordList.get(0);
        }
        return null;
    }

    @Override
    public int selectByParamCount(GenericQueryParam queryParam) {
        return xydAnswerRecordMapper.selectByParamCount(queryParam);
    }

    @Override
    public List<XyydAnswerRecordExtends> selectByParamList(GenericQueryParam queryParam) {
        int page = queryParam.getPage();
        int pageSize = queryParam.getPageSize();
        int offset = (page - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        return xydAnswerRecordMapper.selectExtendsByParamList(queryParam, rowBounds);
    }

    @Override
    public List selectByPcdiOPRateList(Integer parentsId, Integer childId, String isValid, String states, String[] types) {
        return xydAnswerRecordMapper.selectByPcdiOPRateList(parentsId, childId, isValid, states, types);
    }

    @Override
    public List selectByPcdiMustRateList(Integer parentsId, Integer childId, String isValid, String states, String[] types) {
        return xydAnswerRecordMapper.selectByPcdiMustRateList(parentsId, childId, isValid, states, types);
    }

    @Override
    public List selectByAbcRateList(Integer parentsId, Integer childId, String isValid, String states, String[] types) {
        return xydAnswerRecordMapper.selectByAbcRateList(parentsId, childId, isValid, states, types);
    }

    @Override
    public int selectTypeCount(String type) {
        return xydAnswerRecordMapper.selectTypeCount(type);
    }

    @Override
    public List<XydAnswerRecord> selectByTypeList(XydAnswerRecord xydAnswerRecord) {
        return xydAnswerRecordMapper.selectByTypeList(xydAnswerRecord);
    }

    @Transactional
    @Override
    public XydAnswerRecord insertPcdiAllResult(XydParents xydParents, XydAnswerRecord xydAnswerRecord, List resultList, int count) throws Exception {
        TemplateUtils templateUtils = new TemplateUtils();
        if (IsObjectNullUtils.is(xydParents.getChildId())){
            throw new Exception();
        }
        XydChild xydChild = xydChildMapper.selectByPrimaryKey(xydParents.getChildId());
        if (IsObjectNullUtils.is(xydChild))
            throw new Exception();
        int childMonthAge = DateUtil.getMonthAge(xydChild.getBirthdate(), new Date());
        int monthAge = childMonthAge;
        if (monthAge < global.getDefaultAgeMin()){
            monthAge = global.getDefaultAgeMin();
        }
        XydResultAnalyze xydResultAnalyze = new XydResultAnalyze();
        //孩子大于28个月按28月龄计算
        xydResultAnalyze.setMonthAge(monthAge > global.getDefaultAgeMax() ? global.getDefaultAgeMax() : monthAge);
        xydResultAnalyze.setStates("1");
        xydResultAnalyze.setIsOptional("2");
        xydResultAnalyze.setSex(xydChild.getSex());
        Integer percent = new Integer(0);
        List<XydResultAnalyze> analyzeList = resultAnalyzeMapper.selectByCountList(xydResultAnalyze, count);
        if (!IsObjectNullUtils.is(analyzeList) && analyzeList.size() > 0){
            int stateScore = analyzeList.get(0).getStateScore();
            int endScore = analyzeList.get(0).getEndScore();
            int statePercent = analyzeList.get(0).getStatePercent();
            int endPercent = analyzeList.get(0).getEndPercent();
            if ((endScore - stateScore) == 0){
                percent = (int)((statePercent + endPercent)/2);
            }else {
                if (stateScore == count){
                    percent = statePercent;
                }else {
                    percent = (int)((endPercent - statePercent)*(count - stateScore)/(endScore - stateScore)+statePercent);
                }
            }
        }
        XydResultScale xydResultScale = new XydResultScale();
        xydResultScale.setType("1");
        xydResultScale.setStates("1");
        xydResultScale.setIsOptional("2");
        xydResultScale.setMore(getMore(childMonthAge, global.getDefaultAgeMin(), global.getDefaultAgeMax()));
        List<XydResultScale> list = xydResultScaleMapper.selectByAbcList(xydResultScale, percent);
        xydAnswerRecord.setPercent(new BigDecimal(percent));
        if (IsObjectNullUtils.is(list) || list.size() < 1){
            xydAnswerRecord.setVocabulary("");
        }else {
            //词汇建议
            xydAnswerRecord.setVocabulary(list.get(0).getContent());
        }
        xydAnswerRecord.setCount(count);
        //将必填结果页变为全部结果页
        xydAnswerRecord.setType("2");
        if (xydAnswerRecordMapper.updateByPrimaryKeySelective(xydAnswerRecord) < 1){
            throw new Exception();
        }
        //批量的添加答题记录
        if ( xydAnswerResultsMapper.insertPcdiQuestionResult(resultList, xydAnswerRecord.getId(), "1", new Date()) < 1){
            throw new Exception();
        }
        return xydAnswerRecord;
    }

    public String getMore(int age, int minAge, int maxAge){
        if (age < minAge){
            return "1";
        }else if (age > maxAge){
            return "3";
        }else {
            return "2";
        }
    }
}
