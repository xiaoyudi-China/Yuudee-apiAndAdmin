package com.dkd.manage;

import com.dkd.common.constant.ResultStant;
import com.dkd.common.model_custom.DataTableReturnData;
import com.dkd.common.query.GenericQueryParam;
import com.dkd.common.utils.IsObjectNullUtils;
import com.dkd.common.web.BaseController;
import com.dkd.model.XydArea;
import com.dkd.model.XydChild;
import com.dkd.model.XydTrainingResult;
import com.dkd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


/**
 * Created by King on 2018/10/17.
 * 工作台
 */
@Controller
@RequestMapping("/manage/trainTest")
public class TrainingController extends BaseController {

    @Autowired
    private XydTrainingHelpService trainingHelpService;

    @Autowired
    private XydSentenceGroupTestService sentenceGroupTestService;

    @Autowired
    private XydSentenceGroupTrainingService sentenceGroupTrainingService;

    @Autowired
    private XydSentenceResolveTestService sentenceResolveTestService;

    @Autowired
    private XydSentenceResolveTrainingService sentenceResolveTrainingService;

    @Autowired
    private XydTrainingResultService trainingResultService;

    @Autowired
    private XydChildService childService;

    @Autowired
    private XydAreaService areaService;

    //去课件统计列表
    @RequestMapping(value = "/toCoursewaretListPage")
    public String toCoursewaretListPage(Model model) {
        return "/demo/workbench/coursewaretList";
    }

    //用户详情统计
    @RequestMapping(value = "/touserDetailList")
    public String touserDetailList(Model model) {
        return "/demo/workbench/userDetailList";
    }
    //getTrainingResultList
    @RequestMapping(value = "/toTrainingResults")
    public String toGetTrainingResultList(Model model,@RequestParam(value = "userId",required = false)Integer id) {
        if(!IsObjectNullUtils.is(id)){
            model.addAttribute("userId",id);
        }
        return "/demo/workbench/trainingResults";
    }
    /**
     * 查看学习历史
     * @return
     */
    @RequestMapping(value = "/getTrainingResultList")
    @ResponseBody
    public DataTableReturnData getTrainingResultList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydTrainingResult> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        if (!IsObjectNullUtils.is(params.get("name"))) {
            queryParam.fill("name", params.get("name"));
        }
        if (!IsObjectNullUtils.is(params.get("userId"))) {
            queryParam.fill("userId", params.get("userId"));
        }
        if (!IsObjectNullUtils.is(params.get("module"))) {
            queryParam.fill("module", params.get("module"));
        }
        if (!IsObjectNullUtils.is(params.get("scene"))) {
            queryParam.fill("scene", params.get("scene"));
        }
        if (!IsObjectNullUtils.is(params.get("pass"))) {
            queryParam.fill("pass", params.get("pass"));
        }
        if (!IsObjectNullUtils.is(params.get("errorType"))) {
            queryParam.fill("errorType", params.get("errorType"));
        }
        int count = trainingResultService.count(queryParam);
        List<XydTrainingResult> list = trainingResultService.select(queryParam);
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     * 课件统计列表(暂时不用)
     * @return
     */
    @RequestMapping(value = "/getCoursewaretList1")
    @ResponseBody
    public DataTableReturnData getCoursewaretList1(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydTrainingResult> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        if (!IsObjectNullUtils.is(params.get("name"))) {
            queryParam.fill("name", params.get("name"));
        }
        if (!IsObjectNullUtils.is(params.get("scene"))) {
            queryParam.fill("scene", params.get("scene"));
        }else{
            queryParam.fill("scene", "2");
        }
        if (!IsObjectNullUtils.is(params.get("module"))) {
            queryParam.fill("module", params.get("module"));
        }
        queryParam.fill("sentence", "1");
        List<XydTrainingResult> list = trainingResultService.select(queryParam);
        if (list.size() > 0) {
            GenericQueryParam queryParam1 = new GenericQueryParam(1, 99999999);
            for (XydTrainingResult trainingResult : list) {
                queryParam1.fill("scene", trainingResult.getScene());
                queryParam1.fill("module", trainingResult.getModule());
                int count = trainingResultService.count(queryParam1);
                trainingResult.setTestCount(count);//训练次数

                queryParam1.fill("pass", "1");
                int count1 = trainingResultService.count(queryParam1);//正确场数

                double b = count1;
                double b1 = count;
                String s = formatDouble3(b / b1);
                if(s.equals("1")){
                    double bb = 100.00;
                    trainingResult.setPassCount(bb);
                }else{
                    String[] split = s.split("\\.");
                    String s1 = split[1];
                    trainingResult.setPassCount(Double.parseDouble(s1));
                }

                List<XydTrainingResult> list2 = trainingResultService.selectTestPersonCount(trainingResult.getModule(), trainingResult.getScene());
                trainingResult.setTestPersonCount(list2.size());//测试人数

                queryParam1.fill("pass", "1");
                List<XydTrainingResult> list1 = trainingResultService.select(queryParam1);
                if (list1.size() > 0) {
                    boolean t = false;
                    Integer a1 = 0;
                    Integer a2 = 0;
                    Integer a3 = 0;
                    Integer a4 = 0;
                    for(XydTrainingResult trainingResult1 : list1){
                        if(trainingResult1.getModule().equals("1")||trainingResult1.equals("2")||trainingResult1.equals("3")){
                            if(!IsObjectNullUtils.is(trainingResult1.getStayTimeList())) {
                                    a1 += Integer.parseInt(trainingResult1.getStayTimeList().split(",")[0]);
                                    if (trainingResult1.getStayTimeList().split(",").length > 1) {
                                        a2 += Integer.parseInt(trainingResult1.getStayTimeList().split(",")[1]);
                                    }
                            }
                            t=true;
                        }else if(trainingResult1.getModule().equals("4")){
                            if(!IsObjectNullUtils.is(trainingResult1.getStayTimeList())) {

                                    a1 += Integer.parseInt(trainingResult1.getStayTimeList().split(",")[0]);
                                    if (trainingResult1.getStayTimeList().split(",").length > 1) {
                                        a2 += Integer.parseInt(trainingResult1.getStayTimeList().split(",")[1]);
                                    }
                                    if (trainingResult1.getStayTimeList().split(",").length > 2) {
                                        a3 += Integer.parseInt(trainingResult1.getStayTimeList().split(",")[2]);
                                    }
                                    if (trainingResult1.getStayTimeList().split(",").length > 3) {
                                        a4 += Integer.parseInt(trainingResult1.getStayTimeList().split(",")[3]);
                                    }
                            }
                        }
                    }
                    if(t){
                        trainingResult.setAvgTestTime(a1 / count + "," + a2 / count);
                    }else{
                        trainingResult.setAvgTestTime(a1 / count + "," + a2 / count + "," + a4 / count + "," + a4 / count );
                    }
                }
            }
        }
        Collections.sort(list,new SortByY());
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(list.size());
        dataTableReturnData.setRecordsTotal(list.size());
        return dataTableReturnData;
    }

    //根据Y排序
    class SortByY implements Comparator{
        public int compare(Object obj1,Object obj2){
            XydTrainingResult point1=(XydTrainingResult)obj1;
            XydTrainingResult point2=(XydTrainingResult)obj2;
            if(point1.getPassCount() < point2.getPassCount())
                return 1;
            else
                return 0;
        }
    }

    /**
     * 课件统计列表 mxg
     * @return
     */
    @RequestMapping(value = "/getCoursewaretList")
    @ResponseBody
    public DataTableReturnData getCoursewaretList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydTrainingResult> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        if (!IsObjectNullUtils.is(params.get("name"))) {
            queryParam.fill("name", "%"+params.get("name")+"%");
        }
        if (!IsObjectNullUtils.is(params.get("scene"))) {
            queryParam.fill("scene", params.get("scene"));
        }else{
            queryParam.fill("scene", "2");
        }
        if (!IsObjectNullUtils.is(params.get("module"))) {
            queryParam.fill("module", params.get("module"));
        }
        int count = trainingResultService.selectByParamStatCount(queryParam);

        List<XydTrainingResult> list = trainingResultService.selectByParamStatList(queryParam);
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }


    /**
     * 用户详情统计
     * @return
     */
    @RequestMapping(value = "/getUserDetailList")
    @ResponseBody
    public DataTableReturnData getUserDetailList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydChild> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("name"))) {
            queryParam.fill("name", params.get("name"));
        }
        if (!IsObjectNullUtils.is(params.get("birthdate"))) {
            queryParam.fill("birthdate", params.get("birthdate"));
        }
        if (!IsObjectNullUtils.is(params.get("sex"))) {
            queryParam.fill("sex", params.get("sex"));
        }
        if (!IsObjectNullUtils.is(params.get("phoneNumber"))) {
            queryParam.fill("phoneNumber", params.get("phoneNumber"));
        }
        if (!IsObjectNullUtils.is(params.get("countiy"))) {
            XydArea countiy = areaService.selectByPrimaryKey(Integer.parseInt(params.get("countiy")));
            if(!IsObjectNullUtils.is(countiy)){
                queryParam.fill("countiy", countiy.getAreaname());
            }
        }
        if (!IsObjectNullUtils.is(params.get("province"))) {
            XydArea countiy = areaService.selectByPrimaryKey(Integer.parseInt(params.get("province")));
            if(!IsObjectNullUtils.is(countiy)){
                queryParam.fill("province", countiy.getAreaname());
            }
//            queryParam.fill("province", params.get("province"));
        }
        if (!IsObjectNullUtils.is(params.get("city"))) {
            XydArea countiy = areaService.selectByPrimaryKey(Integer.parseInt(params.get("city")));
            if(!IsObjectNullUtils.is(countiy)){
                queryParam.fill("city", countiy.getAreaname());
            }
//            queryParam.fill("city", params.get("city"));
        }
        System.out.println("queryParam="+queryParam);
        int count = childService.selectByParamCount(queryParam);
        List<XydChild> xydChildren = childService.selectByParamList(queryParam);
        dataTableReturnData.setData(xydChildren);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }



    public static String formatDouble3(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留n位小数
        nf.setMaximumFractionDigits(2);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);
        return nf.format(d);
    }

}
