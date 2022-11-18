package com.dkd.api;

import com.dkd.common.config.Constants;
import com.dkd.common.constant.ResultStant;
import com.dkd.common.utils.IsObjectNullUtils;
import com.dkd.common.utils.RedisService;
import com.dkd.common.utils.TokenProccessor;
import com.dkd.model.*;
import com.dkd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/27.
 * 家长中心
 */
@RestController
@RequestMapping(value = "/app/parents")
public class ParentsCenterController {
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    private XydChildService xydChildService;
    @Autowired
    private XydSystemRemindService xydSystemRemindService;
    @Autowired
    private XydAnswerRecordService xydAnswerRecordService;
    @Autowired
    private XydSystemStatisticsService xydSystemStatisticsService;
    @Autowired
    private XydExperienceRecordService xydExperienceRecordService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;
    @Autowired
    private Constants constants;

    @RequestMapping(value = "/test")
    public Map mxgTest(){
        Map<String, Object> map = new HashMap<>();
        XydParents xydParents = xydParentsService.selectByPrimaryKey(1);
        map.put("code", 200);
        map.put("data", xydParents);
        return map;
    }

    /**
     * 评估评测界面
     * 三个判断 ：是否完善儿童个人信息、是否pcdi问卷提醒、abc问卷提醒
     */
    @RequestMapping(value = "/toAssess")
    public Map ParentsToAssess(HttpServletResponse response, @RequestParam(value = "token",required = false) String token, @RequestParam(value = "state",required = false) String state, @RequestParam(value = "pcdiCount",required = false) String pcdiCount, @RequestParam(value = "pcdiState",required = false) String pcdiState, @RequestParam(value = "userId",required = false) String userId, @RequestParam(value = "abcCount",required = false) String abcCount, @RequestParam(value = "abcState",required = false) String abcState) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");
        Map<String, Object> resultMap = new HashMap();
        Map<String, Object> map = new HashMap<>();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("data", "");
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            return resultMap;
        }
        try {
            if (pcdiCount != null) {
                String key = userId + "pcdiCount";
                System.out.println("key=" + key);
                this.redisService.set(key, pcdiCount, constants.getStateTime());
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("msg", "pcdiCount缓存成功！");
                resultMap.put("data", "");
            } else {
                String key = userId + "pcdiCount";
                TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
                Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(key);
                if (IsObjectNullUtils.is(object)) {
                    pcdiCount = "0";
                } else {
                    pcdiCount = (String) object;
                }

                map.put("pcdiCount", pcdiCount);
                resultMap.put("data", map);
            }

            if (pcdiState != null) {
                String key = userId + "pcdiState";
                this.redisService.set(key, pcdiState, constants.getStateTime());
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("msg", "pcdiState缓存成功！");
                resultMap.put("data", "");
            } else {
                String key = userId + "pcdiState";
                TokenProccessor.getInstance().setRedisTemplate(this.redisTemplate);
                Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(key);
                if (IsObjectNullUtils.is(object)) {
                    pcdiState = "0";
                } else {
                    pcdiState = (String) object;
                }

                map.put("pcdiState", pcdiState);
                resultMap.put("data", map);
            }
        } catch (Exception exception) {
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "pcdi提示信息有误！");
            resultMap.put("data", "");
            exception.printStackTrace();
        }

        try {
            if (abcCount != null) {
                String key = userId + "abcCount";
                this.redisService.set(key, abcCount, this.constants.getStateTime());
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("msg", "abcCount缓存成功！");
                resultMap.put("data", "");
            } else {
                String key = userId + "abcCount";
                TokenProccessor.getInstance().setRedisTemplate(this.redisTemplate);
                Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(key);
                if (IsObjectNullUtils.is(object)) {
                    abcCount = "0";
                } else {
                    abcCount = (String) object;
                }

                map.put("abcCount", abcCount);
                resultMap.put("data", map);
            }

            if (abcState != null) {
                String key = userId + "abcState";
                this.redisService.set(key, abcState, this.constants.getStateTime());
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("msg", "abcState缓存成功！");
                resultMap.put("data", "");
            } else {
                String key = userId + "abcState";
                TokenProccessor.getInstance().setRedisTemplate(this.redisTemplate);
                Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(key);
                if (IsObjectNullUtils.is(object)) {
                    abcState = "0";
                } else {
                    abcState = (String) object;
                }

                map.put("abcState", abcState);
                resultMap.put("data", map);
            }
        } catch (Exception exception) {
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "abc提示信息有误！");
            resultMap.put("data", "");
            exception.printStackTrace();
        }

        try {
            TokenProccessor.getInstance().setRedisTemplate(this.redisTemplate);
            Object tokenObject = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(tokenObject) && tokenObject instanceof XydParents) {
                XydParents xydParents = this.xydParentsService.selectByPrimaryKey(((XydParents) tokenObject).getId());
                if (!IsObjectNullUtils.is(state) && "1".equals(state)) {
                    XydAnswerRecord xydAnswerRecord = this.xydAnswerRecordService.selectByReportInfoList(xydParents, "3");
                    xydAnswerRecord.setAnew("0");
                    xydAnswerRecordService.updateAnewByPrimary(xydAnswerRecord);
                }

                if (!IsObjectNullUtils.is(xydParents)) {
                    XydSystemRemind xydSystemRemind = new XydSystemRemind();
                    xydSystemRemind.setIsRemind("1");
                    xydSystemRemind.setParentsId(xydParents.getId());
                    //判断是否需要完善儿童个人信息提醒
                    xydSystemRemind.setType("2");
                    List<XydSystemRemind> list = xydSystemRemindService.selectList(xydSystemRemind);
                    if (!IsObjectNullUtils.is(list) && list.size() > 0){
                        map.put("IsRemind", "1"); //未完善儿童个人信息
                    }else {
                        map.put("IsRemind", "2");//已完善儿童个人信息
                    }
                    String pcdiStatus = "";
                    String abcStatus = "";

                    if (redisService.get(xydParents.getId() + "pcdiOptional") != null) {
                        map.put("pcdiIsRemind", "5");
                    }

                    //判断是否需要填写pcdi
                    xydSystemRemind.setType("1");
                    xydSystemRemind.setIsRemind("1");
                    List<XydSystemRemind> pcdiList = xydSystemRemindService.selectList(xydSystemRemind);
                    if (!IsObjectNullUtils.is(pcdiList) && pcdiList.size() > 0 && "1".equals(pcdiList.get(0).getIsRemind())){
                        map.put("pcdiIsRemind", "1");   //提醒、该做pcdi问卷
                        pcdiStatus = pcdiList.get(0).getStates();
                    }else {
                        XydAnswerRecord xydAnswerRecord = new XydAnswerRecord();
                        xydAnswerRecord.setParentsId(xydParents.getId());
                        List<XydAnswerRecord> recordList = xydAnswerRecordService.selectByTypeList(xydAnswerRecord);
                        if (!IsObjectNullUtils.is(recordList) && recordList.size() >0){
                            if ("1".equals(recordList.get(0).getType())){
                                map.put("pcdiIsRemind", "2");   //不提醒该做pcdi问卷,展示必填报告页
                            }else {
                                map.put("pcdiIsRemind", "4");   //不提醒该做pcdi问卷,展示全部报告页
                            }
                        }else {
                            map.put("pcdiIsRemind", "2");   //不提醒该做pcdi问卷,展示必填报告页
                        }
                    }
                    if (redisService.get(xydParents.getId()+"pcdi") != null){
                        map.put("pcdiIsRemind", "3");
                    }

                    //判断是否需要填写abc
                    xydSystemRemind.setType("3");
                    xydSystemRemind.setIsRemind("1");
                    List<XydSystemRemind> abcList = xydSystemRemindService.selectList(xydSystemRemind);
                    if (!IsObjectNullUtils.is(abcList) && abcList.size() > 0 && "1".equals(abcList.get(0).getIsRemind())){
                        map.put("abcIsRemind", "1");   //提醒、该做abc问卷
                        abcStatus = abcList.get(0).getStates();
                    }else {
                        map.put("abcIsRemind", "2");   //不提醒该做abc问卷
                    }
                    if (redisService.get(xydParents.getId()+"abc") != null){
                        map.put("abcIsRemind", "3");
                    }
                    boolean flag = false;
                    //判断这个家长是否做过pcdi问卷
                    XydAnswerRecord xydAnswerRecord = new XydAnswerRecord();
                    xydAnswerRecord.setParentsId(xydParents.getId());
                    xydAnswerRecord.setType("3");
                    List<XydAnswerRecord> isList3 = xydAnswerRecordService.selectByList(xydAnswerRecord);
                    xydAnswerRecord.setType("1");
                    List<XydAnswerRecord> isList1 = xydAnswerRecordService.selectByList(xydAnswerRecord);
                    xydAnswerRecord.setType("2");
                    List<XydAnswerRecord> isList2 = xydAnswerRecordService.selectByList(xydAnswerRecord);
                    if (isList3.size() > 0){
                        if (isList2.size() > 0 || isList1.size() > 0){
                            flag = true;
                        }
                    }
                    map.put("isRecord", flag == true  ? "1" : "0");
                    //1正常定时提醒 2：通关提醒',
                    if ("1".equals(pcdiStatus) || "1".equals(abcStatus)){
                        map.put("remindType", "1");
                    }else {
                        map.put("remindType", "2");
                    }
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "ok!");
                    resultMap.put("data", map);
                    return resultMap;
                }
            }
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统异常，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 训练档案一级页面
     */
    @RequestMapping(value = "/training/records")
    public Map trainingRecords(@RequestParam(value = "token")String token){
        Map<String, Object> resultMap = new HashMap<>();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
           TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (IsObjectNullUtils.is(xydParents)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                    resultMap.put("data", "");
                    resultMap.put("msg", "无法获取用户信息，请重新登录!");
                    return resultMap;
                }
                if (IsObjectNullUtils.is(xydParents.getChildId())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "您还没有完善儿童信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                if (IsObjectNullUtils.is(xydChild) || !"1".equals(xydChild.getPerfection())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "您还没有完善儿童信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
                xydSystemStatistics.setUserId(xydChild.getId());
                xydSystemStatistics.setStates("1");
                List<XydSystemStatistics> statisticsList = xydSystemStatisticsService.selectPlayerResultList(xydSystemStatistics);
                Map<String, Object> map = new HashMap<>();
                map.put("statisticsList", statisticsList);
                map.put("childName", xydChild.getName());
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("data", map);
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                resultMap.put("msg", "无法获取用户信息，请重新登录!");
                resultMap.put("data", "");
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统异常，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 添加家长产品体验记录
     * @param token
     * @param type 1训练 2测试
     * @return
     */
    @RequestMapping(value = "/addRecord")
    public Map addRecord(@RequestParam(value = "token")String token, @RequestParam(value = "type")String type){
        Map<String, Object> result = new HashMap<>();
        if (IsObjectNullUtils.is(token)){
            result.put("code", ResultStant.RESULT_CODE_LOGIN);
            result.put("msg", "无法获取用户信息，请重新登录!");
            result.put("data", "");
            return result;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (!IsObjectNullUtils.is(xydParents)){
                    XydExperienceRecord xydExperienceRecord = new XydExperienceRecord();
                    xydExperienceRecord.setModule("1");
                    xydExperienceRecord.setChildModule(type);
                    xydExperienceRecord.setParentsId(xydParents.getId());
                    List<XydExperienceRecord> list = xydExperienceRecordService.selectByList(xydExperienceRecord);
                    if (!IsObjectNullUtils.is(list) && list.size() > 0){
                        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                        result.put("msg", "操作成功");
                        result.put("data", "");
                        return result;
                    }
                    xydExperienceRecord.setStates("1");
                    xydExperienceRecord.setCreateTime(new Date());
                    if (xydExperienceRecordService.insertSelective(xydExperienceRecord) > 0){
                        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                        result.put("msg", "操作成功");
                        result.put("data", "");
                    }else {
                        result.put("code", ResultStant.RESULT_CODE_ERROR);
                        result.put("msg", "操作失败");
                        result.put("data", "");
                    }
                    return result;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", ResultStant.RESULT_CODE_SERVICE);
            result.put("msg", "系统繁忙，请稍后重试！");
            result.put("data", "");
            return result;
        }
        result.put("code", ResultStant.RESULT_CODE_LOSE);
        result.put("msg", "无法获取到用户信息！");
        result.put("data", "");
        return result;
    }

}
