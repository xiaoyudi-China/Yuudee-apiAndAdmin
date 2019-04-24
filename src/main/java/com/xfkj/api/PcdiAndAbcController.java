package com.xfkj.api;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.xfkj.common.config.Constants;
import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.emun.PCDIMustType;
import com.xfkj.common.emun.PCDIOptionalType;
import com.xfkj.common.utils.DateUtil;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.utils.RedisService;
import com.xfkj.common.utils.TokenProccessor;
import com.xfkj.model.*;
import com.xfkj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by mai xiaogang on 2018/9/28.
 * 关于问卷操作
 */
@RestController
@RequestMapping("/app/question")
public class PcdiAndAbcController {
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    private XydPcdiTypeService xydPcdiTypeService;
    @Autowired
    private XydPcidMustService xydPcidMustService;
    @Autowired
    private XydPcidMustVocabularyService xydPcidMustVocabularyService;
    @Autowired
    private XydPcidOptionalService xydPcidOptionalService;
    @Autowired
    private XydAnswerRecordService xydAnswerRecordService;
    @Autowired
    private XydAnswerResultsService xydAnswerResultsService;
    @Autowired
    private XydGradeRuleService xydGradeRuleService;
    @Autowired
    private XydAbcQuestionnaireService xydAbcQuestionnaireService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private XydChildService xydChildService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private Constants constants;

    /**
     * 获取pcdi打分规则
     * @param type 问卷类型：1 pcdi 2 abc
     * @param isOptional 是否必做 1 必做 2 选做 （abc则不传）
     * @param topicType 每种题的题型（abc则不传）
     * @return
     */
    @RequestMapping(value = "/getPcdi/gradeRule")
    public Map getGradeRule(HttpServletResponse response, @RequestParam(value = "type", required = false)String type,
                            @RequestParam(value = "isOptional", required = false)String isOptional,
                            @RequestParam(value = "topicType", required = false)Integer topicType){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> resultMap = new HashMap();
        XydGradeRule xydGradeRule = new XydGradeRule();
        if (!IsObjectNullUtils.is(type))
            xydGradeRule.setType(type);
        if (!IsObjectNullUtils.is(isOptional))
            xydGradeRule.setIsOptional(isOptional);
        if (!IsObjectNullUtils.is(topicType) && !IsObjectNullUtils.is(isOptional)){
            if ("1".equals(isOptional)){   //必做
                if (!IsObjectNullUtils.is(PCDIMustType.getEnum(topicType)))
                    xydGradeRule.setTopicType(topicType);
            }else {   //选做
                if (!IsObjectNullUtils.is(PCDIOptionalType.getEnum(topicType)))
                    xydGradeRule.setTopicType(topicType);
            }
        }
        List<XydGradeRule> list = xydGradeRuleService.selectByTypeList(xydGradeRule);
        if (!IsObjectNullUtils.is(list) && list.size() > 0){
            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
            resultMap.put("data", list.get(0));
            resultMap.put("msg", "获取成功！");
            return resultMap;
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "没有获取到相关信息");
            resultMap.put("data", "");
            return resultMap;
        }
    }

    /**
     * 获取pcdi问卷必做题
     */
    @RequestMapping(value = "/getPcdi/must")
    public Map toPcdiMustPage(HttpServletResponse response, @RequestParam(value = "token")String token){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> resultMap = new HashMap();
        Map<String, Object> map = new HashMap<>();
        System.out.println("token ++++++++:"+token);
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
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents)object).getId());
                if (!IsObjectNullUtils.is(xydParents)){
                    XydPcidType xydPcidType = new XydPcidType();
                    xydPcidType.setStates("1");
                    xydPcidType.setIsOptional("1");
                    //获取所有pcdi必做问卷类型
                    List<XydPcidType> typeList = xydPcdiTypeService.selectByTypeSortList(xydPcidType);
                    if (IsObjectNullUtils.is(typeList) || typeList.size() < 1){
                        resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                        resultMap.put("msg", "没有你想要的内容!");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                    //封装需要的数据
                    List<Map<String, Object>> dataList = new ArrayList<>();
                    for (XydPcidType pcidType : typeList) {
                        Map<String, Object> dataMap = new HashMap<>();
                        if (pcidType.getNameEnum() == PCDIMustType.MUST_NAME_E.getValue()){
                            //获取必做词汇
                            List<XydPcidMustVocabulary> list = xydPcidMustVocabularyService.selectByTypeLis(pcidType.getId());
                            dataMap.put("pcidType", pcidType);
                            dataMap.put("pcidList", list);
                            dataList.add(dataMap);
                        }else {
                            //获取其他类型
                            XydPcidMust xydPcidMust = new XydPcidMust();
                            xydPcidMust.setStates("1");
                            xydPcidMust.setPcdiTypeId(pcidType.getId());
                            List<XydPcidMust> list = xydPcidMustService.selectByTypeLis(xydPcidMust);
                            dataMap.put("pcidType", pcidType);
                            dataMap.put("pcidList", list);
                            dataList.add(dataMap);
                        }
                    }
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("data", dataList);
                    resultMap.put("msg", "");
                    return resultMap;
                }
            }
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 用户重新评估，获取上次pcdi问卷必做题加答案
     * @param token
     * @param answerId  问卷记录表id
     * @return
     */
    @RequestMapping(value = "/getPcdi/mustAndResult")
    public Map mustAndResultPage(HttpServletResponse response, @RequestParam(value = "token")String token, @RequestParam(value = "answerId")Integer answerId){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> resultMap = new HashMap();
        Map<String, Object> map = new HashMap<>();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(answerId)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
           TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents)object).getId());
                XydAnswerRecord xydAnswerRecord = xydAnswerRecordService.selectByPrimaryKey(answerId);
                if (IsObjectNullUtils.is(xydAnswerRecord)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "无法获取相关信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                if (!IsObjectNullUtils.is(xydParents)){
                    XydPcidType xydPcidType = new XydPcidType();
                    xydPcidType.setStates("1");
                    xydPcidType.setIsOptional("1");
                    //获取所有pcdi必做问卷类型
                    List<XydPcidType> typeList = xydPcdiTypeService.selectByTypeSortList(xydPcidType);
                    if (IsObjectNullUtils.is(typeList) || typeList.size() < 1){
                        resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                        resultMap.put("msg", "没有你想要的内容!");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                    //封装需要的数据
                    List<XydAnswerResults> dataList = new ArrayList<>();
                    XydAnswerResults xydAnswerResults = new XydAnswerResults();
                    xydAnswerResults.setStates("1");
                    xydAnswerResults.setAnswerId(answerId);
                    List<XydAnswerResults> list = new ArrayList<>();
                    for (XydPcidType pcidType : typeList) {
                        if (IsObjectNullUtils.is(pcidType)){
                            continue;
                        }
                        xydAnswerResults.setTopicType(pcidType.getId());
                        list = xydAnswerResultsService.selectByList(xydAnswerResults);
                        dataList.addAll(list);
                    }
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("data", dataList);
                    return resultMap;
                }
            }
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 获取pcdi问卷选做题
     * @param token
     * @return
     */
    @RequestMapping(value = "/getPcdi/optional")
    public Map toPcdiOptionalPage(HttpServletResponse response, @RequestParam(value = "token")String token){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("data", "");
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents)object).getId());
                if (!IsObjectNullUtils.is(xydParents)){
                    XydPcidType xydPcidType = new XydPcidType();
                    xydPcidType.setStates("1");
                    xydPcidType.setIsOptional("2");
                    //获取所有pcdi选做问卷类型
                    List<XydPcidType> typeList = xydPcdiTypeService.selectByTypeSortList(xydPcidType);
                    if (IsObjectNullUtils.is(typeList) || typeList.size() < 1){
                        resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                        resultMap.put("msg", "没有你想要的内容!");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                    //封装每种类型的数据
                    List<Map<String, Object>> dataList = new ArrayList<>();
                    for (XydPcidType pcidType : typeList) {
                        Map<String, Object> dataMap = new HashMap<>();
                        if (!IsObjectNullUtils.is(pcidType)){
                            XydPcidOptional xydPcidOptional = new XydPcidOptional();
                            xydPcidOptional.setStates("1");
                            xydPcidOptional.setPcdiTypeId(pcidType.getId());
                            List<XydPcidOptional> list = xydPcidOptionalService.selectByTypeLis(xydPcidOptional);
                            dataMap.put("pcidType", pcidType);
                            dataMap.put("pcidList", list);
                            dataList.add(dataMap);
                        }
                    }
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("data", dataList);
                    resultMap.put("msg", "");
                    return resultMap;
                }
            }
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 用户重新评估，获取上次pcdi问卷选做题加答案
     * @param token
     * @param answerId 问卷记录id
     * @return
     */
    @RequestMapping(value = "/getPcdi/optionalAndResult")
    public Map optionalAndResult(HttpServletResponse response, @RequestParam(value = "token")String token, @RequestParam(value = "answerId")Integer answerId){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(answerId)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents)object).getId());
                XydAnswerRecord xydAnswerRecord = xydAnswerRecordService.selectByPrimaryKey(answerId);
                if (IsObjectNullUtils.is(xydAnswerRecord)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "无法获取相关信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                if (!IsObjectNullUtils.is(xydParents) && xydParents.getId().equals(xydAnswerRecord.getParentsId())){
                    XydPcidType xydPcidType = new XydPcidType();
                    xydPcidType.setStates("1");
                    xydPcidType.setIsOptional("2");
                    //获取所有pcdi选做问卷类型
                    List<XydPcidType> typeList = xydPcdiTypeService.selectByTypeSortList(xydPcidType);
                    if (IsObjectNullUtils.is(typeList) || typeList.size() < 1){
                        resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                        resultMap.put("msg", "没有你想要的内容!");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                    //封装每种类型的数据
                    List dataList = new ArrayList<>();
                    List<XydAnswerResults> list = new ArrayList<>();
                    XydAnswerResults xydAnswerResults = new XydAnswerResults();
                    xydAnswerResults.setStates("1");
                    xydAnswerResults.setAnswerId(answerId);
                    for (XydPcidType pcidType : typeList) {
                        if (!IsObjectNullUtils.is(pcidType)){
                            xydAnswerResults.setTopicType(pcidType.getId());
                            list = xydAnswerResultsService.selectByList(xydAnswerResults);
                            dataList.addAll(list);
                        }
                    }
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("data", dataList);
                    resultMap.put("msg", "");
                    return resultMap;
                }
            }
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }


    /**
     * ABC问卷获取
     * @return
     */
    @RequestMapping(value = "/getABC")
    public Map getABC(HttpServletResponse response, @RequestParam(required = false, value = "token")String token
                      ){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        System.out.println("token ++++++++:"+token);
        Map<String, Object> resultMap = new HashMap();
        XydAbcQuestionnaire xydAbcQuestionnaire = new XydAbcQuestionnaire();
        xydAbcQuestionnaire.setStates("1");
        List<XydAbcQuestionnaire> questionnaireList = xydAbcQuestionnaireService.selectByList(xydAbcQuestionnaire);
        if (!IsObjectNullUtils.is(questionnaireList) && questionnaireList.size() > 0){
            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
            resultMap.put("data", questionnaireList);
            resultMap.put("msg", "");
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取到您想要的资源！");
            resultMap.put("data", "");
        }
        return resultMap;
    }

    /**
     * 获取个人ABC问卷题以及答案
     * @param token
     * @param answerId  问卷答题记录id
     * @return
     */
    @RequestMapping(value = "/getABC/outResult")
    public Map getABCoutResult(HttpServletResponse response, @RequestParam(value = "token")String token,
                               @RequestParam(value = "answerId")Integer answerId){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(answerId)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息!");
            resultMap.put("data", "");
            return resultMap;
        }
        //获取答题记录表信息
        XydAnswerRecord xydAnswerRecord = xydAnswerRecordService.selectByPrimaryKey(answerId);
        if (IsObjectNullUtils.is(xydAnswerRecord)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (IsObjectNullUtils.is(xydParents)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "无法获取用户信息，请重新登录!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydAnswerResults xydAnswerResults = new XydAnswerResults();
                xydAnswerResults.setAnswerId(answerId);
                xydAnswerResults.setStates("1");
                List<XydAnswerResults> xydAnswerResultsList = xydAnswerResultsService.selectByList(xydAnswerResults);
                if (!IsObjectNullUtils.is(xydAnswerResultsList) && xydAnswerResultsList.size() > 0){
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("data", xydAnswerResultsList);
                    resultMap.put("msg", "获取成功！");
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("data", "");
                    resultMap.put("msg", "无法获取到您想要的资源！");
                }
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * pcdi必填问卷结果记录添加
     */
    @RequestMapping(value = "/addPcdiResult", method = RequestMethod.POST)
    public Map addPcdiResult(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "token")String token, @RequestParam(value = "score", required = false)Double score,
                             @RequestParam(value = "nounCount", required = false)Integer nounCount,
                             @RequestParam(value = "verbCount", required = false)Integer verbCount,
                             @RequestParam(value = "adjCount", required = false)Integer adjCount,
                             @RequestParam(value = "pcdiCache", required = false)Boolean pcdiCache,
                             @RequestParam String resultList){

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        List<Map<String ,Object>> list = new ArrayList<>();
        try {
            Gson gson=new Gson();
            list=gson.fromJson(resultList,new TypeToken<List<Map<String ,Object>>>(){}.getType());
            System.out.print(list.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(resultList)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取答题记录，请重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (IsObjectNullUtils.is(xydParents)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "无法获取用户信息，请重新登录!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                System.out.println("开始缓存处理"+pcdiCache);
                if (pcdiCache){
                    String key = xydParents.getId()+"pcdi";
                    //系统默认为7天
                    redisService.set(key, list, constants.getCacheTime());
                    System.out.println("缓存pcdi成功！"+list);
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "缓存成功！");
                    resultMap.put("data", "");
                    return resultMap;
                }
                if (nounCount == null || verbCount == null || adjCount == null || score == null){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                    resultMap.put("msg", "无法获取答题结果!");
                    resultMap.put("data", "");
                    return resultMap;
                }
               XydAnswerRecord xydAnswerRecord = xydAnswerRecordService.insertPcdiResult(xydParents,nounCount, verbCount, adjCount, score, list);
               resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
               resultMap.put("data", xydAnswerRecord);
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                resultMap.put("msg", "无法获取用户信息");
                resultMap.put("data", "");
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * pcdi选做问卷答题记录提交
     * @param token
     * @param resultList
     * @param mustId 填完必做返回的id
     * @param count 词汇个数
     * @return
     */
    @RequestMapping(value = "/addPcdiOutResult", method = RequestMethod.POST)
    public Map addPcdiOutResult(HttpServletResponse response, @RequestParam(value = "token")String token,
                                @RequestParam(value = "resultList")String resultList,
                                @RequestParam(value = "mustId")Integer mustId,
                                @RequestParam(value = "count", required = false)Integer count){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(resultList)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取答题记录，请重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        List<Map<String ,Object>> list = new ArrayList<>();
        try {
            Gson gson=new Gson();
            list=gson.fromJson(resultList,new TypeToken<List<Map<String ,Object>>>(){}.getType());
            System.out.print(list.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        if (IsObjectNullUtils.is(mustId) || IsObjectNullUtils.is(list) || list.size() < 1){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取答题记录，请重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (IsObjectNullUtils.is(xydParents)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "无法获取用户信息，请重新登录!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydAnswerRecord xydmustRecord = xydAnswerRecordService.selectByPrimaryKey(mustId);
                if(IsObjectNullUtils.is(xydmustRecord)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                    resultMap.put("msg", "无法相关信息!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydAnswerRecord xydAnswerRecord = xydAnswerRecordService.insertPcdiAllResult(xydParents, xydmustRecord, list, count);
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("data", xydAnswerRecord);
                resultMap.put("msg", "");
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * abc问卷结果记录添加
     * @param token
     * @param score
     * @return
     */
    @RequestMapping(value = "/addABCResult", method = RequestMethod.POST)
    public Map addABCResult(HttpServletResponse response, @RequestParam(value = "token")String token, @RequestParam(value = "score")Double score,
                            @RequestParam String resultList,
                            @RequestParam(value = "pcdiCache", required = false)Boolean pcdiCache){

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("data", "");
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            return resultMap;
        }
        if (IsObjectNullUtils.is(resultList)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取答题记录!");
            resultMap.put("data", "");
            return resultMap;
        }
        List<Map<String ,Object>> list = new ArrayList<>();
        try {
            Gson gson=new Gson();
            list=gson.fromJson(resultList,new TypeToken<List<Map<String ,Object>>>(){}.getType());
            System.out.print(list.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (IsObjectNullUtils.is(xydParents)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "无法获取用户信息，请重新登录!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                if (pcdiCache){
                    String key = xydParents.getId()+"abc";
                    redisService.set(key, list, constants.getCacheTime());
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "缓存成功！");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydAnswerRecord xydAnswerRecord = xydAnswerRecordService.insertAbcResult(xydParents, score, list);
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("data", xydAnswerRecord);
                resultMap.put("msg", "");
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
     * 查看评估报告
     * @param token
     * @param type  1 pcdi问卷必填结果页 2：pcdi问卷全部结果页 : 3 abc问卷结果页',
     * @return          (？？？？？pcdi 获取哪个)
     */
    @RequestMapping(value = "/getReportInfo")
    public Map getRePortInfo(HttpServletResponse response,@RequestParam(value = "token")String token, @RequestParam(value = "type")String type){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> resultMap = new HashMap<>();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(type)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息，请稍后重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                Map<String, Object> map = new HashMap<>();
                if (IsObjectNullUtils.is(xydParents)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "无法获取用户信息，请重新登录!");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                if (!IsObjectNullUtils.is(xydChild)){
                    map.put("name", xydChild.getName());
                    //性别 0男 1女
                    map.put("sex", xydChild.getSex());
                    //获取儿童的月龄
                    int monthage =  DateUtil.getMonthAge(xydChild.getBirthdate(), new Date());
                    map.put("monthAge", monthage <= 0 ? 1 : monthage);
                }
                XydAnswerRecord xydAnswerRecord = xydAnswerRecordService.selectByReportInfoList(xydParents, type);
                if (!IsObjectNullUtils.is(xydAnswerRecord)){
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    map.put("resultData", xydAnswerRecord);
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                    resultMap.put("msg", "无法获取报告相关信息！");
                }
                resultMap.put("data", map);
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 用户获取缓存的答题记录
     * @param response
     * @param token
     * @param type 1：pcdi必做问卷 3 abc 问卷
     * @return
     */
    @RequestMapping(value = "/getCache", method = RequestMethod.GET)
    public Map getList(HttpServletResponse response, @RequestParam(value = "token")String token, @RequestParam(value = "type")String type){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> resultMap = new HashMap<>();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取用户信息，请重新登录!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(type)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("data", "");
            resultMap.put("msg", "无法获取相关信息!");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (IsObjectNullUtils.is(xydParents)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("data", "");
                    resultMap.put("msg", "无法获取用户信息，请重新登录!");
                    return resultMap;
                }
                System.out.println("开始获取缓存:"+type);
                List<Object> resultList = new ArrayList<>();
                if ("1".equals(type)){
                    String key = xydParents.getId()+"pcdi";
                    resultList = (List<Object>) redisService.get(key);
                    redisService.remove(key);
                }
                if ("3".equals(type)){
                    String key = xydParents.getId()+"abc";
                    resultList = (List<Object>) redisService.get(key);
                    redisService.remove(key);
                }
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("data", resultList);
                resultMap.put("msg", "");
                return resultMap;
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
            return resultMap;
        }
        resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
        resultMap.put("msg", "无法获取用户信息");
        resultMap.put("data", "");
        return resultMap;
    }
}
