package com.xfkj.api;

import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.utils.DateUtil;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.utils.PrintToFile;
import com.xfkj.common.utils.TokenProccessor;
import com.xfkj.model.*;
import com.xfkj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by King on 2018/9/29.
 * 儿童训练测试题库
 */
@Controller
@RequestMapping("/app/trainTest")
public class TrainTestController {

    @Autowired
    private XydNounTrainingService nounTrainingService;

    @Autowired
    private XydNounTestService nounTestService;

    @Autowired
    private XydNounSenseService nounSenseService;

    @Autowired
    private XydTrainingHelpService trainingHelpService;

    @Autowired
    private XydVerbTrainingService verbTrainingService;

    @Autowired
    private XydVerbTestService verbTestService;

    @Autowired
    private XydSentenceGroupTestService sentenceGroupTestService;

    @Autowired
    private XydSentenceGroupTrainingService sentenceGroupTrainingService;

    @Autowired
    private XydSentenceResolveTestService sentenceResolveTestService;

    @Autowired
    private XydSentenceResolveTrainingService sentenceResolveTrainingService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private XydParentsService xydParentsService;

    @Autowired
    private XydSystemStatisticsService systemStatisticsService;

    @Autowired
    private XydGroupTrainingService groupTrainingService;

    @Autowired
    private XydFortifierService fortifierService;

    @Autowired
    private XydChildService childService;

    /**
     * 获取强化物
     *
     * @return
     */
    @RequestMapping(value = "/getFortifier")
    @ResponseBody
    public Map getFortifier(HttpServletRequest request, @RequestParam(value = "token", required = false) String token) {
        Map<String, Object> result = new HashMap<String, Object>();
        TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
        Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
        if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
            XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
            if (!IsObjectNullUtils.is(xydParents)) {
                if (!IsObjectNullUtils.is(xydParents.getChildId())) {
                    List<XydFortifier> list = fortifierService.getList(xydParents.getChildId());
                    result.put("data", list);
                    result.put("code", 200);
                    result.put("msg", "查询成功！");
                } else {
                    result.put("code", 205);
                    result.put("msg", "没有完善儿童信息！");
                }
            } else {
                result.put("code", 205);
                result.put("msg", "获取用户信息失败！");
            }
        } else {
            result.put("code", 205);
            result.put("msg", "获取用户信息失败！");
        }
        return result;
    }

    /**
     * 添加强化物金币
     *
     * @return
     */
    @RequestMapping(value = "/addFortifier")
    @ResponseBody
    public Map addFortifier(HttpServletRequest request, @RequestParam(value = "token", required = false) String token,
                            @RequestParam(value = "module", required = false) String module,
                            @RequestParam(value = "state", required = false) String state) {
        Map<String, Object> result = new HashMap<String, Object>();
        String url = request.getRequestURI();
        PrintToFile.print("调用addFortifier方法url："+url+"module:"+module+"state:"+state+"token:"+token);
        if (IsObjectNullUtils.is(token) || IsObjectNullUtils.is(module) || IsObjectNullUtils.is(state)) {
            result.put("code", 206);
            result.put("msg", "缺少参数！");
            return result;
        }
        TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
        Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
        if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
            XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
            if (!IsObjectNullUtils.is(xydParents)) {
                if (!IsObjectNullUtils.is(xydParents.getChildId())) {
                    Integer gold = 10;
                    XydFortifier fortifier = fortifierService.selectOne(xydParents.getChildId(), module);
                    if (!IsObjectNullUtils.is(fortifier)) {
                        XydFortifier fortifier1 = new XydFortifier();
                        if (state.equals("1")) {
                            if (fortifier.getGold() < gold) {
                                result.put("code", ResultStant.RESULT_CODE_ERROR);
                                result.put("msg", "扣除失败，金币不够！");
                                return result;
                            }
                            fortifier1.setGold(fortifier.getGold() - gold);
                        } else {
                            fortifier1.setGold(fortifier.getGold() + 1);
                        }
                        fortifier1.setUpdateTime(new Date());
                        fortifier1.setModule(module);
                        fortifier1.setUserId(fortifier.getUserId());
                        fortifier1.setId(fortifier.getId());
                        int flag = fortifierService.update(fortifier1);
                        if (flag != 0) {
                            result.put("code", 200);
                            result.put("msg", "添加成功！");
                        } else {
                            result.put("code", 206);
                            result.put("msg", "添加失败！");
                        }
                        return result;
                    } else {
                        if ("1".equals(state)) {
                            result.put("code", ResultStant.RESULT_CODE_ERROR);
                            result.put("msg", "扣除失败，金币不够！");
                            return result;
                        } else {
                            XydFortifier fortifier1 = new XydFortifier();
                            fortifier1.setCreateTime(new Date());
                            fortifier1.setModule(module);
                            fortifier1.setGold(1);
                            fortifier1.setUserId(xydParents.getChildId());
                            int flag = fortifierService.insert(fortifier1);
                            if (flag != 0) {
                                result.put("code", 200);
                                result.put("msg", "添加成功！");
                            } else {
                                result.put("code", 206);
                                result.put("msg", "添加失败！");
                            }
                            return result;
                        }
                    }
                } else {
                    result.put("code", 205);
                    result.put("msg", "没有完善儿童信息！");
                }
            } else {
                result.put("code", 205);
                result.put("msg", "获取用户信息失败！");
            }
        } else {
            result.put("code", 205);
            result.put("msg", "获取用户信息失败！");
        }
        return result;
    }

    /**
     * 获取做题进度
     *
     * @return
     */
    @RequestMapping(value = "/getSystemStatistics")
    @ResponseBody
    public Map getSystemStatistics(HttpServletRequest request, @RequestParam(value = "token", required = false) String token) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (!IsObjectNullUtils.is(xydParents)) {
                    if (!IsObjectNullUtils.is(xydParents.getChildId())) {
                        XydChild xydChild = childService.selectByPrimaryKey(xydParents.getChildId());
                        if (IsObjectNullUtils.is(xydChild)){
                            result.put("code", 205);
                            result.put("msg", "没有儿童信息！");
                            return result;
                        }
                        //1、查询最新的做题进度
                        XydSystemStatistics xydSystemStatistics = systemStatisticsService.selectNew(xydParents.getChildId());
                        if (!IsObjectNullUtils.is(xydSystemStatistics)) {
                            //如果全部通关后将module修改为5
                            if ("4".equals(xydSystemStatistics.getModule()) && new BigDecimal(1).compareTo(xydSystemStatistics.getRate1()) == 0){
                                xydSystemStatistics.setModule("5");
                            }
                            result.put("data", xydSystemStatistics);
                        } else {
                            result.put("data", 0);
                        }
                        //判断是否通关了
                        Map<String, Object> mapT = new HashMap<>();
                        mapT.put("module1", "0");
                        mapT.put("module2", "0");
                        mapT.put("module3", "0");
                        mapT.put("module4", "0");
                        XydGroupTraining xydGroupTraining = new XydGroupTraining();
                        xydGroupTraining.setUserId(xydParents.getChildId());
                        xydGroupTraining.setScene("2");
                        xydGroupTraining.setValid("1");
                        for (int j = 1; j < 5; j++){
                            xydGroupTraining.setModule(j+"");
                            List<XydGroupTraining>  list1 = groupTrainingService.selectByList(xydGroupTraining);
                            int a = 0;
                            if (!IsObjectNullUtils.is(list1) && list1.size() > 0){
                                for (int i = 0; i < list1.size(); i++) {
                                    if ("1".equals(list1.get(i).getPass())){
                                        a++;
                                    }else {
                                        break;
                                    }
                                }
                                boolean flag = true;
                                XydSystemStatistics statistics2 = new XydSystemStatistics();
                                statistics2.setUserId(xydParents.getChildId());
                                statistics2.setModule(xydGroupTraining.getModule());
                                List<XydSystemStatistics> playerlist1 = systemStatisticsService.selectByList(statistics2);
                                if (!IsObjectNullUtils.is(playerlist1) && playerlist1.size() > 0){
                                    double rate = playerlist1.get(0).getRate().doubleValue();
                                    if (rate < 1 && rate > 0)
                                        flag = false;
                                }


                                if (a%3 == 0 && a != 0 && flag){
                                    mapT.put("module"+j, "1");
                                }
                            }
                        }
                        result.put("againModule", mapT);

                        //是否再次通关判断
                        Map<String, Object> isMap = new HashMap<>();
                        isMap.put("player1", "0");  //1 为再次通关
                        isMap.put("player2", "0");
                        isMap.put("player3", "0");
                        isMap.put("player4", "0");
                        XydSystemStatistics statistics = new XydSystemStatistics();
                        statistics.setUserId(xydParents.getChildId());
                        List<XydSystemStatistics> playerlist = systemStatisticsService.selectByList(statistics);
                        if (!IsObjectNullUtils.is(playerlist) && playerlist.size() > 0){
                            for (XydSystemStatistics systemStatistics : playerlist) {
                                if (!IsObjectNullUtils.is(systemStatistics) && !IsObjectNullUtils.is(systemStatistics.getPlayer()) && systemStatistics.getPlayer() > 1){
                                    isMap.put("player"+systemStatistics.getModule(), "1");
                                }
                            }
                        }
                        result.put("playerModule", isMap);

                        //2、查询做未完成课件的小进度
                        List<XydGroupTraining> list = groupTrainingService.selectNearTest(xydParents.getChildId());
                        result.put("groupTraining", list);
                        //3、查询次用户改做哪个模块的课件
                        Map map = groupTrainingService.selectNearmodule(xydParents.getChildId());
                        result.put("list", map);

                        //召鹏让恩祥写的 该做句子成组还是句子分解
                        //句子成组进度
                     /*   List<XydGroupTraining> groupTrainingcz = groupTrainingService.selectnearJuziChengzu(xydParents.getChildId());
                        if(groupTrainingcz.size() < 3){
                            //句子成组没有做过
                            result.put("sentence", 3);
                        }else{
                            //句子分解进度
                            List<XydGroupTraining> groupTrainingfj = groupTrainingService.selectnearJuziFenjie(xydParents.getChildId());
                            //句子分解没有做完 继续做分解
                            if(groupTrainingfj.size()<3){
                                result.put("sentence", 4);
                            }
                            boolean cz = true;
                            boolean fj = true;
                            Date czdate= new Date();
                            Date fjdate = new Date();
                            for(XydGroupTraining groupTraining : groupTrainingcz){
                                czdate =groupTraining.getUpdateTime();
                                break;
                            }
                            for(XydGroupTraining groupTraining : groupTrainingfj){
                                fjdate =groupTraining.getUpdateTime();
                                break;
                            }

                            for(XydGroupTraining groupTraining : groupTrainingcz){
                                if(!groupTraining.getPass().equals("1")){
                                    cz = false;
                                    break;
                                }
                            }
                            //如果句子成组未通关
                            if(cz == false){
                                result.put("sentence", 3);
                            }else{
                                for(XydGroupTraining groupTraining : groupTrainingfj){
                                    if(!groupTraining.getPass().equals("1")){
                                        fj = false;
                                        break;
                                    }
                                }
                                //如果句子分解未通关
                                if(fj == false) {
                                    result.put("sentence", 4);
                                }else{
                                    //如果都通关了 成组时间晚于分解
                                    if(czdate.getTime() > fjdate.getTime()){
                                        result.put("sentence", 4);
                                    }else{
                                        result.put("sentence", 3);
                                    }
                                }
                            }
                        }*/
                        //小麦改
                        XydSystemStatistics statistics1 = new XydSystemStatistics();
                        statistics1.setUserId(xydParents.getChildId());
                        statistics1.setStates("1");
                        List<XydSystemStatistics> statisticsList = systemStatisticsService.selectByList(statistics1);
                        int czcount = 0;
                        int fjcount = 0;
                        for (XydSystemStatistics systemStatistics : statisticsList) {
                            if (!IsObjectNullUtils.is(systemStatistics) && "3".equals(systemStatistics.getModule())){
                                 czcount = systemStatistics.getPlayer();
                            }
                            if (!IsObjectNullUtils.is(systemStatistics) && "4".equals(systemStatistics.getModule())){
                                fjcount = systemStatistics.getPlayer();
                            }
                        }
                        if (czcount > fjcount){
                            result.put("sentence", 4);
                        }else {
                            result.put("sentence", 3);
                        }
                        System.out.println("通过通关次数获取的sentence="+result.get("sentence"));
                    } else {
                        result.put("code", 205);
                        result.put("msg", "没有儿童信息！");
                    }
                } else {
                    result.put("code", ResultStant.RESULT_CODE_LOGIN);
                    result.put("msg", "用户信息获取失败！");
                    return result;
                }
            } else {
                result.put("code", ResultStant.RESULT_CODE_LOGIN);
                result.put("msg", "用户信息获取失败！");
                return result;
            }
            result.put("code", 200);
            result.put("msg", "查询成功！");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", ResultStant.RESULT_CODE_SERVICE);
            result.put("msg", "系统繁忙，请稍后重试！");
            return result;
        }
    }

    /**
     * 随机十条 名词
     *
     * @return
     */
    @RequestMapping(value = "/getNoun")
    @ResponseBody
    public Map getNounTraining(HttpServletRequest request, @RequestParam(value = "token", required = false) String token) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            XydTrainingHelptime helptime1 = new XydTrainingHelptime();
            helptime1.setTopicType(1);
            helptime1.setStates("1");
            //设定名词训练的时间 如果没有设置名词的时间 reutrn
            XydTrainingHelptime helptime = trainingHelpService.selectByEntity(helptime1);
            if (IsObjectNullUtils.is(helptime)) {
                result.put("code", "205");
                result.put("msg1", "查询失败！没有设置名词训练时间");
            } else {
                result.put("time", helptime);
            }
            List<XydNounTraining> nounTraining = nounTrainingService.selectAllRand();
            result.put("nounTraining", nounTraining);
            //名词测试
            List<XydNounTest> nounTest = nounTestService.selectAllRand();
            result.put("nounTest", nounTest);

            //名词意义
            if (!IsObjectNullUtils.is(token)) {
                Integer childId = null;
                TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
                Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
                if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {

                    XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                    if (!IsObjectNullUtils.is(xydParents)) {
                        if(!IsObjectNullUtils.is(xydParents.getChildId())){
                            childId = xydParents.getChildId();
                        }else{
                            result.put("code", 205);
                            result.put("msg", "儿童信息未完善！");
                            return result;
                        }
                    } else {
                        result.put("code", ResultStant.RESULT_CODE_LOGIN);
                        result.put("msg", "用户信息获取失败！");
                        return result;
                    }
                } else {
                    result.put("code", ResultStant.RESULT_CODE_LOGIN);
                    result.put("msg", "用户信息获取失败！");
                    return result;
                }

                XydSystemStatistics systemStatistics = new XydSystemStatistics();
                systemStatistics.setUserId(childId);
                List<XydSystemStatistics> xydSystemStatisticss = systemStatisticsService.selectByList(systemStatistics);
                boolean b = false;
                if (xydSystemStatisticss.size() > 0) {
                    for (XydSystemStatistics systemStatistics1 : xydSystemStatisticss) {
                        if (systemStatistics1.getModule().equals("1") && systemStatistics1.getChildModule().equals("3")) {
                            List<XydNounSense> nounSense = nounSenseService.selectAllRand();
                            result.put("nounSense", nounSense);
                            break;
                        } else if (!systemStatistics1.getModule().equals("1")) {
                            List<XydNounSense> nounSense = nounSenseService.selectAllRand();
                            result.put("nounSense", nounSense);
                            break;
                        }
                    }
                }
            }
            result.put("code", 200);
            result.put("msg", "查询成功！");
            return result;
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "系统繁忙，请稍后重试！");
            e.printStackTrace();
            return result;
        }
    }

    /**
     * 随机10条 动词
     *
     * @return
     */
    @RequestMapping(value = "/getVerb")
    @ResponseBody
    public Map getVerbTraining(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            XydTrainingHelptime helptime1 = new XydTrainingHelptime();
            helptime1.setTopicType(2);
            helptime1.setStates("1");
            //设定动词的时间 如果没有设置名词的时间 reutrn
            XydTrainingHelptime helptime = trainingHelpService.selectByEntity(helptime1);
            if (IsObjectNullUtils.is(helptime)) {
                result.put("code", "205");
                result.put("msg1", "查询失败！没有设置动词训练时间");
            } else {
                result.put("helptime", helptime);
            }
            //动词训练
            List<XydVerbTraining> verbTraining = verbTrainingService.selectAllRand();
            result.put("verbTraining", verbTraining);
            //动词测试
            List<XydVerbTest> verbTest = verbTestService.selectAllByRand();
            result.put("verbTest", verbTest);
            result.put("code", 200);
            result.put("msg", "查询成功！");
            return result;
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "系统繁忙，请稍后重试！");
            return result;
        }
    }

    /**
     * 随机10条 句子成组
     *
     * @return
     */
    @RequestMapping(value = "/getSentencegroup")
    @ResponseBody
    public Map getSentencegroupTraining(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            XydTrainingHelptime helptime1 = new XydTrainingHelptime();
            helptime1.setTopicType(3);
            helptime1.setStates("1");
            //设定名词的时间 如果没有设置句子成组的时间 reutrn
            List<XydTrainingHelptime> helptime = trainingHelpService.selectByEntityList(helptime1);
            if (IsObjectNullUtils.is(helptime)) {
                result.put("code", "205");
                result.put("msg1", "查询失败！没有设置句子成组训练时间");
            } else {
                result.put("helptime", helptime);
            }
            //句子成组训练
            List<XydSentenceGroupTraining> sentenceGroupTraining = sentenceGroupTrainingService.selectAllByRand();
            result.put("sentenceGroupTraining", sentenceGroupTraining);
            //句子成组测试
            List<XydSentenceGroupTest> sentenceGroupTest = sentenceGroupTestService.selectAllByRand();
            result.put("sentenceGroupTest", sentenceGroupTest);
            result.put("code", 200);
            result.put("msg", "查询成功！");
            return result;
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "系统繁忙，请稍后重试！");
            return result;
        }
    }

    /**
     * 随机10条 句子分解
     *
     * @return
     */
    @RequestMapping(value = "/getSentenceResolve")
    @ResponseBody
    public Map getSentenceResolveTraining(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            XydTrainingHelptime helptime1 = new XydTrainingHelptime();
            helptime1.setTopicType(4);
            helptime1.setStates("1");
            //设定名词的时间 如果没有设置句子分解的时间 reutrn
            List<XydTrainingHelptime> helptime = trainingHelpService.selectByEntityList(helptime1);
            if (IsObjectNullUtils.is(helptime)) {
                result.put("code", "205");
                result.put("msg1", "查询失败！没有设置句子分解时间");
            } else {
                result.put("helptime", helptime);
            }
            //句子分解训练
            List<XydSentenceResolveTraining> sentenceResolveTraining = sentenceResolveTrainingService.selectAllByRand();
            result.put("sentenceResolveTraining", sentenceResolveTraining);
            //句子分解测试
            List<XydSentenceResolveTest> sentenceResolveTest = sentenceResolveTestService.selectAllByRand();
            System.out.println("句子分解训练"+sentenceResolveTraining.toString());
            System.out.println("句子分解测试"+sentenceResolveTest.toString());
            result.put("cosentenceResolveTestde", sentenceResolveTest);
            result.put("code", 200);
            result.put("msg", "查询成功！");
            return result;
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "系统繁忙，请稍后重试！");
            return result;
        }
    }


}