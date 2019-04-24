package com.xfkj.api;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.xfkj.common.config.Constants;
import com.xfkj.common.utils.DateUtil;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.utils.TokenProccessor;
import com.xfkj.mapper.XydGroupTrainingMapper;
import com.xfkj.model.*;
import com.xfkj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * 儿童训练测试记录
 * Created by King on 2018/10/9.
 */
@RestController
@RequestMapping("/app/trainingResult")
public class TrainingResultController {

    @Autowired
    private XydTrainingResultService trainingResultService;

    @Autowired
    private XydSystemStatisticsService systemStatisticsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private XydParentsService xydParentsService;

    @Autowired
    private XydChildService xydChildService;

    @Autowired
    private XydGroupTrainingService groupTrainingService;

    @Autowired
    private XydGroupTrainingMapper groupTrainingMapper;

    @Autowired
    private Constants constants;

    @Autowired
    private XydFortifierService fortifierService;

    @Autowired
    private XydSystemRemindService systemRemindService;

    @Autowired
    private XydAnswerRecordService xydAnswerRecordService;

    /**
     * 添加儿童训练测试课件结果
     *
     * @param token 用户唯一标识
     * @return result
     */
    @RequestMapping(value = "addTrainingResult", method = RequestMethod.POST)
    @ResponseBody
    public Map addTrainingResult(HttpServletRequest request, @RequestParam(required = false, value = "token") String token,
                                 @RequestParam(required = false, value = "coursewareId") Integer coursewareId,
                                 @RequestParam(required = false, value = "groupId") Integer groupId,
                                 @RequestParam(required = false, value = "scene") String scene,
                                 @RequestParam(required = false, value = "module") String module,
                                 @RequestParam(required = false, value = "startTime") String startTime,
                                 @RequestParam(required = false, value = "pass") String pass,
                                 @RequestParam(required = false, value = "stayTime") Integer stayTime,
                                 @RequestParam(required = false, value = "stayTimeList") String stayTimeList,
                                 @RequestParam(required = false, value = "disTurbName") String disTurbName,
                                 @RequestParam(required = false, value = "errorType") String errorType,
                                 @RequestParam(required = false, value = "name") String name) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (IsObjectNullUtils.is(token)) {
                result.put("code", 205);
                result.put("msg", "用户信息获取失败！");
                return result;
            }
            if (IsObjectNullUtils.is(coursewareId) || IsObjectNullUtils.is(scene) ||
                    IsObjectNullUtils.is(module) || IsObjectNullUtils.is(startTime)
                    || IsObjectNullUtils.is(stayTime) ||
                    IsObjectNullUtils.is(pass) || IsObjectNullUtils.is(name)) {
                result.put("code", 205);
                result.put("msg", "参数获取失败！");
                return result;
            }
            System.out.println("前端传过来的module:"+module);
            if (Integer.parseInt(module) > 4) {
                result.put("code", 205);
                result.put("msg", "参数有误，！");
                return result;
            }
            System.out.println("转之前"+startTime);
            Date parse = DateUtil.parse(startTime);
            System.out.println("转之后"+parse);
            //儿童id
            Integer childId = null;
            Integer xydParentsId = null;
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (!IsObjectNullUtils.is(xydParents)) {
                     xydParentsId = xydParents.getId();
                    childId = xydParents.getChildId();
                } else {
                    result.put("code", 205);
                    result.put("msg", "用户信息获取失败！");
                    return result;
                }
            } else {
                result.put("code", 205);
                result.put("msg", "用户信息获取失败！");
                return result;
            }
            if (IsObjectNullUtils.is(childId)) {
                result.put("code", 205);
                result.put("msg", "儿童信息获取失败！");
                return result;
            }
            Integer learnTime = 0;
            boolean b = false;
            Integer groupId_1 = null;
            //-------------start------------
            // 如果组的id为空 添加记录 否则在组上增加单个记录id(修改)
            if (IsObjectNullUtils.is(groupId)) {
                XydGroupTraining xydGroupTraining = new XydGroupTraining();
                xydGroupTraining.setScene(scene);
                xydGroupTraining.setUserId(childId);
                xydGroupTraining.setModule(module);
                xydGroupTraining.setStartTime(parse);
                xydGroupTraining.setTrainingIdlist(coursewareId + "");
                xydGroupTraining.setEndTime(new Date());
                if (stayTime < constants.getCountTime()){
                    xydGroupTraining.setStayTime(stayTime);
                }else {
                    xydGroupTraining.setStayTime(0);
                }

                xydGroupTraining.setCreateTime(new Date());
                int i = trainingResultService.insertGroupTraining(xydGroupTraining);
                groupId_1 = xydGroupTraining.getId();
                result.put("groupId", groupId_1);
            } else {
                result.put("groupId", groupId);
                XydGroupTraining groupTraining = trainingResultService.selectGroupTrainingById(groupId);
                if (!IsObjectNullUtils.is(groupTraining)) {
                    groupTraining.setId(groupId);
                    if (stayTime < constants.getCountTime()){
                        groupTraining.setStayTime(groupTraining.getStayTime() + stayTime);
                    }
                    learnTime = groupTraining.getStayTime();
                    groupTraining.setTrainingIdlist(groupTraining.getTrainingIdlist() + "," + coursewareId);
                    //如果回答10道题 这组课件记录设为有效  获取答题的个数
                    if (groupTraining.getTrainingIdlist().split(",").length == 10) {
                        b = true;
                        groupTraining.setValid("1");
                        result.put("groupId", "");
                    }
                    groupTraining.setEndTime(new Date());
                    groupTraining.setUpdateTime(new Date());
                    int ic = trainingResultService.updateGroupTraining(groupTraining);
                    if (ic == 0) {
                        result.put("code", 206);
                        result.put("msg", "修改失败！");
                        return result;
                    }
                }
            }
            //------------------------ end ------------------------\\

            XydTrainingResult xydTrainingResult = new XydTrainingResult();
            xydTrainingResult.setCoursewareId(coursewareId);
            //设置课件名称
            xydTrainingResult.setName(name);
            //如果组id不是创建的 使用前台传过来的
            if (!IsObjectNullUtils.is(groupId)) {
                xydTrainingResult.setGroupId(groupId);
            } else {
                xydTrainingResult.setGroupId(groupId_1);
            }
            if (!IsObjectNullUtils.is(stayTimeList)) {
                xydTrainingResult.setStayTimeList(stayTimeList);
            }
            if (!IsObjectNullUtils.is(disTurbName)) {
                xydTrainingResult.setDisturbName(disTurbName);
            }
            if (!IsObjectNullUtils.is(errorType)) {
                xydTrainingResult.setErrorType(errorType);
            }
            xydTrainingResult.setScene(scene);
            xydTrainingResult.setModule(module);
            xydTrainingResult.setStartTime(parse);
            xydTrainingResult.setEndTime(new Date());
            xydTrainingResult.setStayTime(stayTime);
            xydTrainingResult.setCreateTime(new Date());
            xydTrainingResult.setUserId(childId);
            xydTrainingResult.setPass(pass);
            xydTrainingResult.setStates("1");

            int flag = trainingResultService.insert(xydTrainingResult);
            if (flag != 0) {
                XydGroupTraining groupTraining = new XydGroupTraining();
                if (IsObjectNullUtils.is(groupId)) {
                    groupTraining.setId(groupId_1);
                } else {
                    groupTraining.setId(groupId);
                }
                //查询组的记录
                XydGroupTraining groupTraining2 = new XydGroupTraining();
                List<XydTrainingResult> trainingResult = null;
                if (IsObjectNullUtils.is(groupId)) {
                    groupTraining2 = groupTrainingService.selectById(groupId_1);
                    trainingResult = trainingResultService.selectByGroupId(groupId_1);
                } else {
                    groupTraining2 = groupTrainingService.selectById(groupId);
                    trainingResult = trainingResultService.selectByGroupId(groupId);
                }
                //查询这组课件所有正确的题 计算正确率 c
                int c = 0;
                if (trainingResult.size() > 0) {
                    for (XydTrainingResult result1 : trainingResult) {
                        if (result1.getPass().equals("1")) {
                            c++;
                        }
                    }
                }
                //如果通关 重新计算正确率
                if (pass.equals("1")) {
                    BigDecimal multiply = new BigDecimal(c + "").divide(new BigDecimal("10"));
                    groupTraining.setAccuracy(multiply);
                }

                //如果是名词意义的话
                if(module.equals("1") && scene.equals("3")){
                    List<XydGroupTraining> list = groupTrainingMapper.selectSense2(childId);
                    int a = 0;
                    for(XydGroupTraining groupTraining1: list){
                        if(groupTraining1.getScene().equals("3")){
                            a++;
                        }
                    }
                    if(a==2){
                        XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
                        xydSystemStatistics.setUserId(childId);
                        xydSystemStatistics.setModule(module);
                        List<XydSystemStatistics> xydSystemStatisticss = systemStatisticsService.selectByList(xydSystemStatistics);
                        if (!IsObjectNullUtils.is(xydSystemStatisticss) && xydSystemStatisticss.size() > 0){
                            for(XydSystemStatistics xydSystemStatistics1 : xydSystemStatisticss){
                                XydSystemStatistics systemStatistics = new XydSystemStatistics();
                                systemStatistics.setId(xydSystemStatistics1.getId());
                                systemStatistics.setRate(new BigDecimal("0.00"));
                                systemStatistics.setPassNumber(0);
                                systemStatistics.setUpdateTime(new Date());
                                systemStatisticsService.updateByPrimaryKeySelective(systemStatistics);
                            }
                        }
                    }
                }

                //如果在添加【测试课件记录】 添加通关逻辑 XydSystemStatistics
                if (scene.equals("2")) {
                    System.out.println("正确率：" + c);
                    //如果通关 就在 XydSystemStatistics 连续通关次数+1 否则重置为0
                    XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
                    xydSystemStatistics.setUserId(childId);
                    xydSystemStatistics.setModule(module);
                    List<XydSystemStatistics> xydSystemStatisticss = systemStatisticsService.selectPlayerResultList1(xydSystemStatistics);
                    //c1 第十道题 --> 正确率大于80% 则本关通过
                    int size = trainingResult.size();
                    if (size == 10) {
                        if(c >= 8){
                        groupTraining.setPass("1");
                        if (xydSystemStatisticss.size() > 0) {
                            for (XydSystemStatistics systemStatistics : xydSystemStatisticss) {
                                if (systemStatistics.getModule().equals(module)) {
                                   // systemStatistics.setLearningTime(systemStatistics.getLearningTime()+stayTime);
                                    systemStatistics.setChildModule(scene);
                                    //连续作对
                                    if (systemStatistics.getPassNumber() > 0 && systemStatistics.getCount()==0) {
//                                        Integer count = trainingResultService.selectPassCount(childId, module);
                                        //第一次做 且连续作对
                                        if (systemStatistics.getPassNumber() == 0 && systemStatistics.getCount() == 0) {
                                            systemStatistics.setRate(new BigDecimal("0.33"));
                                            systemStatistics.setPassNumber(1);
                                            if(!systemStatistics.getIsPass().equals("1")){
                                                systemStatistics.setRate1(new BigDecimal("0.33"));
                                            }
                                            //第二次做 且连续作对
                                        } else if (systemStatistics.getPassNumber() == 1) {
                                            systemStatistics.setRate(new BigDecimal("0.66"));
                                            systemStatistics.setPassNumber(2);
                                            if(!systemStatistics.getIsPass().equals("1")){
                                                systemStatistics.setRate1(new BigDecimal("0.66"));
                                            }
                                            //第三次做 且作对
                                        } else if (systemStatistics.getPassNumber() == 2 ) {
                                            if(module.equals("1")){
                                                systemStatistics.setChildModule("3");
                                                systemStatistics.setRate1(new BigDecimal("1.00"));
                                                systemStatistics.setRate(new BigDecimal("1.00"));
                                                systemStatistics.setPassNumber(3);
                                                systemStatistics.setPlayer(systemStatistics.getPlayer()+1);
                                            }else{
                                                systemStatistics.setRate(new BigDecimal("0"));
                                                systemStatistics.setPassNumber(0);
                                                systemStatistics.setRate1(new BigDecimal("1.00"));
                                                systemStatistics.setPlayer(systemStatistics.getPlayer()+1);
                                            }
                                            systemStatistics.setIsPass("1");
                                            //systemStatistics.setCount(systemStatistics.getCount() + 1);
                                            systemStatistics.setUpdateTime(new Date());
                                            //如果所有关卡没有通关完 添加下一关的学习模块
                                            if (!"4".equals(module)) {
                                                XydSystemStatistics systemStatistics2 = new XydSystemStatistics();
                                                systemStatistics2.setModule(String.valueOf(Integer.valueOf(module) + 1));
                                                systemStatistics2.setUserId(childId);
                                                List<XydSystemStatistics> xydSystemStatisticss1 = systemStatisticsService.selectByList(systemStatistics2);
                                                //如果下个关卡没记录 则添加关卡记录
                                                if(xydSystemStatisticss1.size()<1){
                                                    XydSystemStatistics systemStatistics1 = new XydSystemStatistics();
                                                    systemStatistics1.setUserId(childId);
 													//systemStatistics1.setLearningTime(0);
 													systemStatistics1.setModule(String.valueOf(Integer.valueOf(module) + 1));
                                                    systemStatistics1.setChildModule("1");
                                                    systemStatistics1.setCreateTime(new Date());
                                                    systemStatistics1.setPassNumber(0);
                                                    System.out.println("---------添加通关进度1---"+systemStatistics1);
                                                    systemStatisticsService.insertSelective(systemStatistics1);
                                                }
                                            }
                                            //暂时不要后台处理这个逻辑，前端控制通关弹框一次，
                                            // 如果多次弹框需要后台控制时，才需要修改提醒，并且用starts区分弹框类型。
                                            if ("4".equals(module)){
                                                //第四个模块通关 修改提醒
                                                if (!IsObjectNullUtils.is(xydParentsId)){
                                                    XydAnswerRecord xydAnswerRecord = new XydAnswerRecord();
                                                    xydAnswerRecord.setParentsId(xydParentsId);
                                                    List<XydAnswerRecord> list= xydAnswerRecordService.selectByList(xydAnswerRecord);
                                                    if (!IsObjectNullUtils.is(list) && list.size() > 1){
                                                        systemRemindService.updateRemind(xydParentsId);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        BigDecimal subtract = new BigDecimal("1").subtract(systemStatistics.getRate());
                                        BigDecimal divide = subtract.divide(new BigDecimal("3"), 2,ROUND_HALF_DOWN);
                                        BigDecimal add = systemStatistics.getRate().add(divide);
                                        if (systemStatistics.getPassNumber() == 0) {
                                            if(!systemStatistics.getIsPass().equals("1")){
                                                systemStatistics.setRate1(add);
                                            }
                                            systemStatistics.setPassNumber(1);
                                            systemStatistics.setRate(add);
                                            //第二次做 且作对
                                        } else if (systemStatistics.getPassNumber() == 1) {
                                            systemStatistics.setRate(add);
                                            systemStatistics.setPassNumber(2);
                                            if(!systemStatistics.getIsPass().equals("1")){
                                                systemStatistics.setRate1(add);
                                            }
                                            //第三次做 且作对
                                        } else if (systemStatistics.getPassNumber() == 2) {
                                            if(module.equals("1")){
                                                systemStatistics.setChildModule("3");
                                                systemStatistics.setRate1(new BigDecimal("1.00"));
                                                systemStatistics.setRate(new BigDecimal("1.00"));
                                                systemStatistics.setPlayer(systemStatistics.getPlayer()+1);
                                                systemStatistics.setPassNumber(3);
                                            }else{
                                                systemStatistics.setRate(new BigDecimal("0"));
                                                systemStatistics.setPassNumber(0);
                                                systemStatistics.setRate1(new BigDecimal("1.00"));
                                                systemStatistics.setPlayer(systemStatistics.getPlayer()+1);
                                            }
                                            systemStatistics.setCount(0);
                                            systemStatistics.setIsPass("1");
                                            systemStatistics.setUpdateTime(new Date());
                                            XydSystemStatistics systemStatistics2 = new XydSystemStatistics();
                                            if ("4".equals(module)){
                                                systemStatistics2.setModule("4");
                                            }else {
                                                systemStatistics2.setModule(String.valueOf(Integer.valueOf(module) + 1));
                                            }
                                            systemStatistics2.setUserId(childId);
                                            List<XydSystemStatistics> xydSystemStatisticss1 = systemStatisticsService.selectByList(systemStatistics2);
                                            System.out.println("module:"+module);
                                            //如果下个关卡没记录 则添加关卡记录
                                            if(xydSystemStatisticss1.size()<1){
                                                if (!"4".equals(module)) {
                                                    XydSystemStatistics systemStatistics1 = new XydSystemStatistics();
                                                    systemStatistics1.setUserId(childId);
                                                    systemStatistics1.setModule(String.valueOf(Integer.valueOf(module) + 1));
                                                    systemStatistics1.setChildModule("1");
                                                    systemStatistics1.setCreateTime(new Date());
                                                    systemStatistics1.setPassNumber(0);
                                                    System.out.println("---------添加通关进度2---"+systemStatistics1);
                                                    systemStatisticsService.insertSelective(systemStatistics1);
                                                }
                                            }
                                            if ("4".equals(module)){
                                                //第四个模块通关 修改提醒
                                                if (!IsObjectNullUtils.is(xydParentsId)){
                                                    XydAnswerRecord xydAnswerRecord = new XydAnswerRecord();
                                                    xydAnswerRecord.setParentsId(xydParentsId);
                                                    List<XydAnswerRecord> list= xydAnswerRecordService.selectByList(xydAnswerRecord);
                                                    if (!IsObjectNullUtils.is(list) && list.size() > 1){
                                                        systemRemindService.updateRemind(xydParentsId);
                                                    }
                                                }
                                            }

                                        }
                                    }
                                    systemStatistics.setCount(0);
                                    int i = systemStatisticsService.updateByPrimaryKeySelective(systemStatistics);
                                    if (i == 0) {
                                        result.put("code", 206);
                                        result.put("msg", "添加开始下一关时候失败！");
                                        return result;
                                    }
                                    break;
                                }
                            }
                        } else {
                            //儿童第一次做题
                            XydSystemStatistics systemStatistics = new XydSystemStatistics();
                            systemStatistics.setUserId(childId);
                            systemStatistics.setModule(module);
                            systemStatistics.setChildModule(scene);
                            BigDecimal bigDecimal = new BigDecimal("0.33");
                            systemStatistics.setRate(bigDecimal);
                            systemStatistics.setPassNumber(1);
                            systemStatistics.setRate1(bigDecimal);
                          //  systemStatistics.setLearningTime(stayTime);
                            systemStatistics.setCreateTime(new Date());
                            systemStatistics.setStates("1");
                            int i = systemStatisticsService.insertSelective(systemStatistics);
                            if (i == 0) {
                                result.put("code", 207);
                                result.put("msg", "添加儿童第一次做题失败！");
                                return result;
                            }
                        }
                    }else {
                            //通关失败 如果有记录 连续通关次数重置为0
                            if (xydSystemStatisticss.size() > 0) {
                                for (XydSystemStatistics systemStatistics : xydSystemStatisticss) {
                                    if (systemStatistics.getModule().equals(module)) {
                                        systemStatistics.setPassNumber(0);
                                        systemStatistics.setCount(systemStatistics.getCount()+1);
                                        systemStatisticsService.updateByPrimaryKeySelective(systemStatistics);
                                    }else{
                                        //儿童第一次做题
                                        XydSystemStatistics systemStatistics1 = new XydSystemStatistics();
                                        systemStatistics1.setUserId(childId);
                                        systemStatistics1.setModule(module);
                                        systemStatistics1.setChildModule(scene);
                                       // systemStatistics1.setLearningTime(stayTime);
                                        systemStatistics1.setCreateTime(new Date());
                                        systemStatistics1.setStates("1");
                                        int i = systemStatisticsService.insertSelective(systemStatistics1);
                                        if (i == 0) {
                                            result.put("code", 207);
                                            result.put("msg", "添加儿童第一次做题失败！");
                                            return result;
                                        }
                                    }
                                    break;
                                }
                            }
                            groupTraining.setPass("0");
                        }
                    }
                }else{
                    XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
                    xydSystemStatistics.setUserId(childId);
                    xydSystemStatistics.setModule(module);
                    List<XydSystemStatistics> xydSystemStatisticss = systemStatisticsService.selectPlayerResultList1(xydSystemStatistics);
                    for (XydSystemStatistics systemStatistics : xydSystemStatisticss) {
                        if (systemStatistics.getModule().equals(module)) {
                            //systemStatistics.setLearningTime(systemStatistics.getLearningTime()+stayTime);
                            systemStatisticsService.updateByPrimaryKeySelective(systemStatistics);
                        }
                        break;
                    }
                }
                groupTraining.setUpdateTime(new Date());
                int i = trainingResultService.updateGroupTraining(groupTraining);
                if (i == 0) {
                    result.put("code", 208);
                    result.put("msg", "修改组记录失败！");
                    return result;
                }
                // 添加答题时长、（体验时间不累加，以及如果答题时长超过五分钟则不记录学习时长）
                if (stayTime < constants.getCountTime()){
                    XydSystemStatistics statistics = new XydSystemStatistics();
                    statistics.setModule(module);
                    statistics.setStates("1");
                    statistics.setUserId(childId);
                    List<XydSystemStatistics> statisticsList = systemStatisticsService.selectByList(statistics);
                    if (!IsObjectNullUtils.is(statisticsList) && statisticsList.size() > 0 && module.equals(statisticsList.get(0).getModule())){
                        statistics.setId(statisticsList.get(0).getId());
                        statistics.setLearningTime(statisticsList.get(0).getLearningTime() + stayTime);
                        systemStatisticsService.updateByPrimaryKeySelective(statistics);
                    }else {
                        XydChild xydChild = xydChildService.selectByPrimaryKey(childId);
                        if (!IsObjectNullUtils.is(xydChild) && "1".equals(xydChild.getPerfection())){
                            statistics.setChildModule(scene);
                            statistics.setLearningTime(stayTime);
                            statistics.setCreateTime(new Date());
                            statistics.setStates("1");
                            systemStatisticsService.insertSelective(statistics);
                        }
                    }
                }

                result.put("code", 200);
                result.put("msg", "添加成功！");
            } else {
                result.put("code", 206);
                result.put("msg", "添加失败！");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "系统繁忙, 请稍后重试！");
            return result;
        }
    }

}
