package com.xfkj.manage;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.utils.DateUtil;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.model.XydExperienceRecord;
import com.xfkj.model.XydGroupTraining;
import com.xfkj.model.XydParents;
import com.xfkj.model.XydSystemStatistics;
import com.xfkj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by mai xiaogang on 2018/10/16.
 * 工作台
 */
@Controller
@RequestMapping(value = "/manage/workbench")
public class WorkBenchController {
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    private XydSystemRemindService xydSystemRemindService;
    @Autowired
    private XydAnswerRecordService xydAnswerRecordService;
    @Autowired
    private XydSystemStatisticsService xydSystemStatisticsService;
    @Autowired
    private XydGroupTrainingService xydGroupTrainingService;
    @Autowired
    private XydExperienceRecordService xydExperienceRecordService;

    /**
     * 跳转到统计页
     * @return
     */
    @RequestMapping(value = "/tostatisticsPage")
    public String tostatisticsPage(){
        return "/demo/workbench/statistics";
    }
    /**
     * 跳转到工作台页面
     * @return
     */
    @RequestMapping(value = "/toWorkBenchPage")
    public String toWorkBenchPage(Model model){
        Map<String, Object> data = new HashMap<>();
        //获取总注册人数registerCount
        XydParents xydParents = new XydParents();
        int registerCount = xydParentsService.selectRegisterCount(xydParents);
        data.put("registerCount", registerCount);
        //获取名词测试体验人数testCount
        XydExperienceRecord experienceRecord = new XydExperienceRecord();
        experienceRecord.setModule("1");
        experienceRecord.setChildModule("2");
        experienceRecord.setStates("1");
        int testCount = xydExperienceRecordService.selectRegisterCount(experienceRecord);
        data.put("testCount", testCount);
        //获取训练体验人数TrainingCount
        experienceRecord.setChildModule("1");
        int TrainingCount = xydExperienceRecordService.selectRegisterCount(experienceRecord);
        data.put("TrainingCount", TrainingCount);
        //获取完善儿童个人信息人数prefect
        int prefect = xydSystemRemindService.selecPrefectCount();
        data.put("prefect", prefect);
        //pcdi必填问卷人数pcdiMustCount
        String type = "1";
        int pcdiMustCount = xydAnswerRecordService.selectTypeCount(type);
        data.put("pcdiMustCount", pcdiMustCount);
        //pcdi选填问卷人数pcdiOpCount
        type = "2";
        int pcdiOpCount = xydAnswerRecordService.selectTypeCount(type);
        data.put("pcdiOpCount", pcdiOpCount);
        //abc问卷人数abcCount
        type = "3";
        int abcCount = xydAnswerRecordService.selectTypeCount(type);
        data.put("abcCount", abcCount);
        //学习人数nonuTrainingCount
        XydGroupTraining xydGroupTraining = new XydGroupTraining();
        xydGroupTraining.setModule("1");
        xydGroupTraining.setScene("1");
        int nonuTrainingCount = xydGroupTrainingService.selectStatisticsCount(xydGroupTraining);
        data.put("nonuTrainingCount", nonuTrainingCount);
        //名词通关人数nounPlayerCount
        XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
        xydSystemStatistics.setStates("1");
        xydSystemStatistics.setModule("1");
        xydSystemStatistics.setRate1(new BigDecimal("1"));
        int nounPlayerCount = xydSystemStatisticsService.selectPlayerCount(xydSystemStatistics);
        data.put("nounPlayerCount", nounPlayerCount);
        //动词学习人数vebTrainingCount
        xydGroupTraining.setModule("2");
        xydGroupTraining.setScene("1");
        int vebTrainingCount = xydGroupTrainingService.selectStatisticsCount(xydGroupTraining);
        data.put("vebTrainingCount", vebTrainingCount);
        //动词通关人数vebPlayerCount
        xydSystemStatistics.setModule("2");
        xydSystemStatistics.setRate1(new BigDecimal("1"));
        int vebPlayerCount = xydSystemStatisticsService.selectPlayerCount(xydSystemStatistics);
        data.put("vebPlayerCount", vebPlayerCount);
        //句子成组学习人数senGroupTrainCount
        xydGroupTraining.setModule("3");
        xydGroupTraining.setScene("1");
        int senGroupTrainCount = xydGroupTrainingService.selectStatisticsCount(xydGroupTraining);
        data.put("senGroupTrainCount", senGroupTrainCount);
        //句子成组通关人数senGroupPlayerCount
        xydSystemStatistics.setModule("3");
        xydSystemStatistics.setRate1(new BigDecimal("1"));
        int senGroupPlayerCount = xydSystemStatisticsService.selectPlayerCount(xydSystemStatistics);
        data.put("senGroupPlayerCount", senGroupPlayerCount);
        //通关人数sumPlayerCount
        xydSystemStatistics.setModule("4");
        xydSystemStatistics.setRate1(new BigDecimal("1"));
        int sumPlayerCount = xydSystemStatisticsService.selectPlayerCount(xydSystemStatistics);
        data.put("sumPlayerCount", sumPlayerCount);
        model.addAttribute("data", data);
        return "/demo/workbench/Workbench";
    }
    /**
     * 工作台 总人数信息统计
     * @param response
     * @return
     */
    @RequestMapping(value = "/getWorkCount")
    @ResponseBody
    public Map getWorkCount(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> result = new HashMap();
        Map<String, Object> data = new HashMap<>();
        //获取总注册人数registerCount
        XydParents xydParents = new XydParents();
        int registerCount = xydParentsService.selectRegisterCount(xydParents);
        data.put("registerCount1", registerCount);
        //获取测试体验人数testCount
        xydParents.setFeel("2");
        int testCount = xydParentsService.selectRegisterCount(xydParents);
        data.put("testCount", testCount);
        //获取训练体验人数TrainingCount
        xydParents.setFeel("1");
        int TrainingCount = xydParentsService.selectRegisterCount(xydParents);
        data.put("TrainingCount", TrainingCount);
        //获取完善儿童个人信息人数prefect
        int prefect = xydSystemRemindService.selecPrefectCount();
        data.put("prefect", prefect);
        //pcdi必填问卷人数pcdiMustCount
        String type = "1";
        int pcdiMustCount = xydAnswerRecordService.selectTypeCount(type);
        data.put("pcdiMustCount", pcdiMustCount);
        //pcdi选填问卷人数pcdiOpCount
         type = "2";
        int pcdiOpCount = xydAnswerRecordService.selectTypeCount(type);
        data.put("pcdiOpCount", pcdiOpCount);
        //abc问卷人数abcCount
         type = "3";
        int abcCount = xydAnswerRecordService.selectTypeCount(type);
        data.put("abcCount", abcCount);
        //学习人数nonuTrainingCount
        XydGroupTraining xydGroupTraining = new XydGroupTraining();
        xydGroupTraining.setModule("1");
        xydGroupTraining.setScene("1");
        int nonuTrainingCount = xydGroupTrainingService.selectStatisticsCount(xydGroupTraining);
        data.put("nonuTrainingCount", nonuTrainingCount);
        //名词通关人数nounPlayerCount
        XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
        xydSystemStatistics.setModule("1");
        xydSystemStatistics.setRate(new BigDecimal("1"));
        int nounPlayerCount = xydSystemStatisticsService.selectPlayerCount(xydSystemStatistics);
        data.put("nounPlayerCount", nounPlayerCount);
        //动词学习人数vebTrainingCount
        xydGroupTraining.setModule("2");
        xydGroupTraining.setScene("1");
        int vebTrainingCount = xydGroupTrainingService.selectStatisticsCount(xydGroupTraining);
        data.put("vebTrainingCount", vebTrainingCount);
        //动词通关人数vebPlayerCount
        xydSystemStatistics.setModule("2");
        xydSystemStatistics.setRate(new BigDecimal("1"));
        int vebPlayerCount = xydSystemStatisticsService.selectPlayerCount(xydSystemStatistics);
        data.put("vebPlayerCount", vebPlayerCount);
        //句子成组学习人数senGroupTrainCount
        xydGroupTraining.setModule("3");
        xydGroupTraining.setScene("1");
        int senGroupTrainCount = xydGroupTrainingService.selectStatisticsCount(xydGroupTraining);
        data.put("senGroupTrainCount", senGroupTrainCount);
        //句子成组通关人数senGroupPlayerCount
        xydSystemStatistics.setModule("3");
        xydSystemStatistics.setRate(new BigDecimal("1"));
        int senGroupPlayerCount = xydSystemStatisticsService.selectPlayerCount(xydSystemStatistics);
        data.put("senGroupPlayerCount", senGroupPlayerCount);
        //通关人数sumPlayerCount
        xydSystemStatistics.setModule("4");
        xydSystemStatistics.setRate(new BigDecimal("1"));
        int sumPlayerCount = xydSystemStatisticsService.selectPlayerCount(xydSystemStatistics);
        data.put("sumPlayerCount", sumPlayerCount);
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("data", data);
        result.put("msg", "查询成功!");
        return result;
    }

    /**
     * 跳转到查看信息
     * @return
     */
    @RequestMapping(value = "/toUserDetail")
    public String toUserDetail(Model model){
        return "/demo/workbench/userDetail";
    }

    /**
     * 获取工作台总人数累计数据
     * @param response
     * @return
     */
    @RequestMapping(value = "/getWorkSum")
    @ResponseBody
    public Map getWorkSum(HttpServletResponse response, @RequestParam(value = "startTime", required = false)String startTime,
                             @RequestParam(value = "endTime", required = false)String endTime) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        Map<String, Object> result = new HashMap();
        Map<String, Object> data = new HashMap<>();
        if (IsObjectNullUtils.is(startTime) || IsObjectNullUtils.is(endTime)){
            startTime = DateUtil.getDate(DateUtil.getMonthFirstDayByDate(new Date()));
            endTime =DateUtil.getDate(DateUtil.getMonthLastDayByDate(new Date()));
        }
        List<Date> dateList = DateUtil.findDates(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime));
        //注册人数regisList
        List<Map<String, Object>> regisList = xydParentsService.selectTotalCount(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime));
        for (int i = 0; i < regisList.size(); i++) {
            if ((long)regisList.get(i).get("total") == 0 && i > 0){
                regisList.get(i).put("total", (long)regisList.get(i-1).get("total"));
            }
        }
        data.put("regisList", regisList);
        //学习人数stayList
        List<Map<String, Object>> stayList = xydGroupTrainingService.selectTotalCount(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime) ,"1", "2");
        for (int i = 0; i < stayList.size(); i++) {
            if ((long)stayList.get(i).get("total") == 0 && i > 0){
                stayList.get(i).put("total", (long)stayList.get(i-1).get("total"));
            }
        }
        data.put("stayList", stayList);
        //通关人数playerList
        List<Map<String, Object>> playerList = xydSystemStatisticsService.selectTotalCount(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime));
        for (int i = 0; i < playerList.size(); i++) {
            if ((long)playerList.get(i).get("total") == 0 && i > 0){
                playerList.get(i).put("total", (long)playerList.get(i-1).get("total"));
            }
        }
        data.put("playerList", playerList);
        result.put("msg", "查询成功！");
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("data", data);
        return result;
    }
    /**
     * 获取工作台每日人数累计数据
     * @param response
     * @return
     */
    @RequestMapping(value = "/getWorkday")
    @ResponseBody
    public Map getWorkday(HttpServletResponse response, @RequestParam(value = "startTime", required = false)String startTime,
                             @RequestParam(value = "endTime", required = false)String endTime) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> result = new HashMap();
        Map<String, Object> data = new HashMap<>();
        if (IsObjectNullUtils.is(startTime) || IsObjectNullUtils.is(endTime)){
            startTime = DateUtil.getDate(DateUtil.getMonthFirstDayByDate(new Date()));
            endTime =DateUtil.getDate(DateUtil.getMonthLastDayByDate(new Date()));
        }
        //注册人数
        List<Map<String, Object>> regisList = xydParentsService.selectDayCount(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime));
        data.put("regisList", regisList);
        //学习人数
        List<Map<String, Object>> stayList = xydGroupTrainingService.selectDayCount(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime) ,null, null);
        data.put("stayList", stayList);
        //通关人数
        List<Map<String, Object>> playerList = xydSystemStatisticsService.selectDayCount(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime));
        data.put("playerList", playerList);

        result.put("msg", "查询成功！");
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("data", data);
        return result;
    }
    /**
     * 获取工作台个学习模块数据
     * @param response
     * @return
     */
    @RequestMapping(value = "/getWorkModule")
    @ResponseBody
    public Map getWorkResult(HttpServletResponse response, @RequestParam(value = "startTime", required = false)String startTime,
                             @RequestParam(value = "endTime", required = false)String endTime) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> result = new HashMap();
        Map<String, Object> data = new HashMap<>();
        if (IsObjectNullUtils.is(startTime) || IsObjectNullUtils.is(endTime)){
            startTime = DateUtil.getDate(DateUtil.getMonthFirstDayByDate(new Date()));
            endTime =DateUtil.getDate(DateUtil.getMonthLastDayByDate(new Date()));
        }
        //名词nonuList
        List<Map<String, Object>> nonuList = xydGroupTrainingService.selectDayCount(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime),"1",null);
        data.put("nonuList", nonuList);
        //动词vebList
        List<Map<String, Object>> vebList = xydGroupTrainingService.selectDayCount(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime), "2", null);
        data.put("vebList", vebList);
        //句子分解sentResoList
        List<Map<String, Object>> sentResoList = xydGroupTrainingService.selectDayCount(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime),"4", null);
        data.put("sentResoList", sentResoList);
        //句子成组sentGrepList
        List<Map<String, Object>> sentGrepList = xydGroupTrainingService.selectDayCount(DateUtil.parseDate(startTime), DateUtil.parseDate(endTime),"3", null);
        data.put("sentGrepList", sentGrepList);
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg", "查询成功！");
        result.put("data", data);
        return result;
    }

}
