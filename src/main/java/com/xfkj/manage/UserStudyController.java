package com.xfkj.manage;

import com.xfkj.common.config.Global;
import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.utils.DateUtil;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.model.XydChild;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/11/1.
 * 儿童个人学习统计
 */
@Controller
@RequestMapping(value = "/manage/study")
public class UserStudyController {
    @Autowired
    private XydChildService xydChildService;
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    private XydSystemStatisticsService xydSystemStatisticsService;
    @Autowired
    private XydAnswerRecordService xydAnswerRecordService;
    @Autowired
    private XydGroupTrainingService xydGroupTrainingService;


    /**
     * 跳转到儿童个人信息详情界面
     */
    @RequestMapping(value = "/toUserStudyInfo")
    public String toUserStudyInfo(Model model,  @RequestParam(value = "id")Integer id){
        Map<String, Object> result = new HashMap();
        Map<String, Object> data = new HashMap<>();
        XydChild xydChild = new XydChild();
        if (!IsObjectNullUtils.is(id)){
            xydChild = xydChildService.selectByPrimaryKey(id);
        }
        //获取儿童个人信息
        data.put("child", xydChild);
        model.addAttribute("child", xydChild);
        XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
        xydSystemStatistics.setUserId(xydChild.getId());
        xydSystemStatistics.setStates("1");
        //获取学习进度
        List<XydSystemStatistics> statisticsList = xydSystemStatisticsService.selectPlayerResultList(xydSystemStatistics);
        data.put("statisticsList", statisticsList);
        if (!IsObjectNullUtils.is(statisticsList) && statisticsList.size() > 4){
            data.put("statisticsList0", statisticsList.get(0));
            data.put("statisticsList1", statisticsList.get(1));
            data.put("statisticsList2", statisticsList.get(2));
            data.put("statisticsList3", statisticsList.get(3));
            BigDecimal sum = statisticsList.get(4).getRate1();
            BigDecimal f1 = sum == null ? new BigDecimal(0) : sum.setScale(4, BigDecimal.ROUND_DOWN);
            System.out.println(f1.toString());
            data.put("statisticsList4", Double.parseDouble(f1.toString())*100);

        }
        //获取总学习时长
        Long sumStudyTime = xydSystemStatisticsService.selectSumStudyTime(xydChild.getId());
        double sumTime = sumStudyTime == null ? 0.0 : (double)sumStudyTime/60;
        sumTime = (double) Math.round(sumTime * 100) / 100;
        data.put("sumStudyTime", sumTime);
        //获取pcdi量分信息
        model.addAttribute("data", data);
        return "/demo/workbench/userDetail";
    }

    /**
     * 获取儿童个人数据状况
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUserDataInfo")
    @ResponseBody
    public Map getUserDataInfo(HttpServletResponse response, @RequestParam(value = "id")Integer id){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> result = new HashMap();
        Map<String, Object> data = new HashMap<>();
        if (IsObjectNullUtils.is(id)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "无法获取相关信息！");
            return result;
        }
        XydChild xydChild = xydChildService.selectByPrimaryKey(id);
        if (IsObjectNullUtils.is(xydChild)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "无法获取到该儿童学习信息！");
            return result;
        }
        //获取儿童个人信息
        data.put("child", xydChild);

        XydSystemStatistics xydSystemStatistics = new XydSystemStatistics();
        xydSystemStatistics.setUserId(xydChild.getId());
        xydSystemStatistics.setStates("1");
        //获取学习进度
        List<XydSystemStatistics> statisticsList = xydSystemStatisticsService.selectPlayerResultList(xydSystemStatistics);
        data.put("statisticsList", statisticsList);
        //获取总学习时长
        Long sumStudyTime = xydSystemStatisticsService.selectSumStudyTime(xydChild.getId());
        data.put("sumStudyTime",sumStudyTime == null ? 0 : sumStudyTime);
        //获取pcdi量分信息

        //获取进度、学习时长和量分表
        String [] type = {"1","2"};
        List list = xydAnswerRecordService.selectBystudyRateList(xydChild.getParentsId(), xydChild.getId(),"1", null, type);
        data.put("pcdiList", list);
        //获取pcid选做词汇列表
        List pcdiOpList = xydAnswerRecordService.selectByPcdiOPRateList(xydChild.getParentsId(), xydChild.getId(),"1", null, new String[]{"2"});
        data.put("pcdiOpList", pcdiOpList);
        //获取pcid必做词汇列表
        List pcdiMustList = xydAnswerRecordService.selectByPcdiMustRateList(xydChild.getParentsId(), xydChild.getId(),"1", null, type);
        data.put("pcdiMustList", pcdiMustList);
        //获取abc数据列表
        List abcList = xydAnswerRecordService.selectByAbcRateList(xydChild.getParentsId(), xydChild.getId(),"1", null, new String[]{"3"});
        data.put("abcList", abcList);

        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg", "查询成功");
        result.put("data", data);
        return result;
    }

    /**
     * 获取儿童个人学习训练
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUserStudyInfo")
    @ResponseBody
    public Map getUserStudyInfo(HttpServletResponse response, @RequestParam(value = "id")Integer id, @RequestParam(value = "timeType")String timeType,
                                @RequestParam(value = "month", required = false)String month,
                                @RequestParam(value = "dayTime", required = false)String dayTime,
                                @RequestParam(value = "startTime", required = false)String startTime,
                                @RequestParam(value = "endTime", required = false)String endTime ) throws ParseException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> result = new HashMap();
        if (IsObjectNullUtils.is(id)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "无法获取相关信息！");
            return result;
        }
        XydChild xydChild = xydChildService.selectByPrimaryKey(id);
        if (IsObjectNullUtils.is(xydChild)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "无法获取到该儿童学习信息！");
            return result;
        }
        Map<String, Object> dayResult = new HashMap<>();
        Map<String, Object> weekResult = new HashMap<>();
        Map<String, Object> monthResult = new HashMap<>();
        switch (timeType){
            case "1":  //天
                Date dayDate = new Date();
                if (!IsObjectNullUtils.is(dayTime)){
                    dayDate = DateUtil.getDate(dayTime, "yyyy-MM-dd");
                }
                xydGroupTrainingService.selectByDayResultList(xydChild.getId(), dayResult, dayDate);
                result.put("data", dayResult);
                break;
            case "2":  //周
                Date startDate = null;
                Date endDate = null;
                if (IsObjectNullUtils.is(startTime) || IsObjectNullUtils.is(endTime)){
                    startDate = DateUtil.getWeekFirstDay(new Date());
                    endDate = DateUtil.getWeekLastDay(new Date());
                }else {
                    startDate = DateUtil.getDate(startTime);
                    endDate = DateUtil.getDate(endTime);
                }
                xydGroupTrainingService.selectByWeekResultList(xydChild.getId(),startDate, endDate, weekResult);
                result.put("data", weekResult);
                break;
            case "3":  //月
                if (IsObjectNullUtils.is(month)){
                    month = DateUtil.getStrDate(new Date(), "yyyy-MM");
                }
                Date monthDate = DateUtil.getDate(month,"yyyy-MM");
                xydGroupTrainingService.selectByMonthResultList(xydChild.getId(), monthDate, monthResult);
                result.put("data", monthResult);
                break;
            default:;
        }
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg", "查询成功！");
        return result;
    }
}
