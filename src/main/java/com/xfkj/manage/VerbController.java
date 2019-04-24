package com.xfkj.manage;

import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.model_custom.DataTableReturnData;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.FileUtils;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.web.BaseController;
import com.xfkj.model.XydNounSense;
import com.xfkj.model.XydTrainingHelptime;
import com.xfkj.model.XydVerbTest;
import com.xfkj.model.XydVerbTraining;
import com.xfkj.service.XydTrainingHelpService;
import com.xfkj.service.XydVerbTestService;
import com.xfkj.service.XydVerbTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by King on 2018/10/18.
 * 动词管理
 */
@Controller
@RequestMapping("/manage/verb")
public class VerbController extends BaseController{
    @Autowired
    private XydTrainingHelpService trainingHelpService;

    @Autowired
    private XydVerbTrainingService verbTrainingService;

    @Autowired
    private XydVerbTestService verbTestService;

    @RequestMapping(value = "/toVerbTrainingPage")
    public String toVerbTrainingPage(Model model){
        XydTrainingHelptime helptime1 = new XydTrainingHelptime();
        helptime1.setTopicType(2);
        helptime1.setStates("1");
        XydTrainingHelptime helptime = trainingHelpService.selectByEntity(helptime1);

        model.addAttribute("helptime", helptime);
        return "/demo/testpaper/verbTrainingList";
    }
    @RequestMapping(value = "/toVerbTestPage")
    public String toVerbTestPage(Model model){
         return "/demo/testpaper/verbTestList";
    }

    @RequestMapping(value = "/toAddVerbTraining")
    public String toAddVerbTraining(Model model,@RequestParam(value = "id",required = false)Integer id){
        if(!IsObjectNullUtils.is(id)){
            XydVerbTraining verbTraining = verbTrainingService.selectById(id);
            model.addAttribute("verbTraining",verbTraining);
        }
        return "/demo/testpaper/addVerbTraining";
    }

    @RequestMapping(value = "/toAddVerbTest")
    public String toAddVerbTest(Model model,@RequestParam(value = "id",required = false) Integer id){
        if(!IsObjectNullUtils.is(id)){
            XydVerbTest verbTest = verbTestService.selectById(id);
            model.addAttribute("verbTest",verbTest);
        }
        return "/demo/testpaper/addVerbTest";
    }

    public static boolean jiaoyan(List<String> stringList){
        boolean b = false;
        if(!IsObjectNullUtils.is(stringList) ){
            return true;
        }
        return b;
    }
    /**
     * 获取动词短语 测试
     * @param params 组合文字 搜索
     * @return
     */
    @RequestMapping(value = "/getVerbTestList")
    @ResponseBody
    public DataTableReturnData getVerbTestList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydVerbTest> dataTableReturnData = new DataTableReturnData<XydVerbTest>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        XydTrainingHelptime helptime1 = new XydTrainingHelptime();
        helptime1.setTopicType(1);
        helptime1.setStates("1");
        XydTrainingHelptime helptime = trainingHelpService.selectByEntity(helptime1);

        if (!IsObjectNullUtils.is(params.get("groupWord"))) {
            queryParam.fill("groupChar", "%"+params.get("groupWord")+"%");
        }
        if (!IsObjectNullUtils.is(params.get("disturbType"))) {
            queryParam.fill("disturbType", params.get("disturbType"));
        }
        int count = verbTestService.count(queryParam);
        List<XydVerbTest> list = verbTestService.select(queryParam);
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    @RequestMapping(value = "/addVerbTest")
    @ResponseBody
    public Map addVerbTest(HttpServletRequest request, @RequestParam(required = false,value = "verbType")String verbType,
                           @RequestParam(required = false,value = "verbChar")String verbChar,
                           @RequestParam(required = false,value = "cardChar")String cardChar,
                           @RequestParam(required = false,value = "groupChar")String groupChar,
                           @RequestParam(required = false,value = "cardOneTime")Integer cardOneTime,
                           @RequestParam(required = false,value = "cardTwoTime")Integer cardTwoTime){
        Map<String,Object> result = new HashMap<String,Object>();
        if(IsObjectNullUtils.is(verbType)||IsObjectNullUtils.is(verbChar)
                ||IsObjectNullUtils.is(cardChar)||IsObjectNullUtils.is(groupChar)){
            result.put("code","205");
            result.put("msg","参数获取失败");
            return result;
        }
        XydVerbTest verbTest = new XydVerbTest();
        verbTest.setVerbType(verbType);
        verbTest.setCardOneTime(cardOneTime);
        verbTest.setCardTwoTime(cardTwoTime);
        verbTest.setVerbChar(verbChar);
        verbTest.setCardChar(cardChar);
        verbTest.setGroupChar(groupChar);
        Map<String, Object> verbImages = FileUtils.verbimages(request);
        if(verbImages.size()>0){
            List<String> verbImage = (List<String>) verbImages.get("verbImage");
            if(jiaoyan(verbImage)){
                for(String str : verbImage){
                    verbTest.setVerbImage(str);
                }
            }
            List<String> verbRecord = (List<String>) verbImages.get("verbRecord");
            if(jiaoyan(verbRecord)){
                for(String str : verbRecord){
                    verbTest.setVerbRecord(str);
                }
            }
            List<String> cardImage = (List<String>) verbImages.get("cardImage");
            if(jiaoyan(cardImage)){
                for(String str : cardImage){
                    verbTest.setCardImage(str);
                }
            }
            List<String> cardRecord = (List<String>) verbImages.get("cardRecord");
            if(jiaoyan(cardRecord)){
                for(String str : cardRecord){
                    verbTest.setCardRecord(str);
                }
            }
            List<String> groupImage = (List<String>) verbImages.get("groupImage");
            if(jiaoyan(groupImage)){
                for(String str : groupImage){
                    verbTest.setGroupImage(str);
                }
            }
            List<String> startSlideshow = (List<String>) verbImages.get("startSlideshow");
            if(jiaoyan(startSlideshow)){
                String s = "";
                for(String str : startSlideshow){
                    s += str+",";
                }

                verbTest.setStartSlideshow(s);
            }
            List<String> endSlideshow = (List<String>) verbImages.get("endSlideshow");
            if(jiaoyan(endSlideshow)){
                String s = "";
                for(String str : endSlideshow){
                    s += str+",";
                }
                verbTest.setEndSlideshow(s);
            }
            List<String> groupRecord = (List<String>) verbImages.get("groupRecord");
            if(jiaoyan(groupRecord)){
                for(String str : groupRecord){
                    verbTest.setGroupRecord(str);
                }
            }
        }
        verbTest.setCreateTime(new Date());
        verbTest.setStates("1");
        int flag = verbTestService.insert(verbTest);
        if(flag != 0){
            result.put("code","200");
            result.put("msg","添加成功");
        }else{
            result.put("code","206");
            result.put("msg","添加失败");
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "http://yuudee.oss-cn-beijing.aliyuncs.com/1d57215f1a7141d4bf5ae7fede86bfb8.png,http://yuudee.oss-cn-beijing.aliyuncs.com/ca4a309bbee54626b745ccf9f726dc4f.png,http://yuudee.oss-cn-beijing.aliyuncs.com/09211adb914e4c0da445f9b1d8be667b.png,";
        
    }
    @RequestMapping(value = "/deleteVerbTest")
    @ResponseBody
    public Map deleteVerbTest(HttpServletRequest request, @RequestParam(required = false,value = "id") Integer id){
        Map<String,Object> result = new HashMap<String,Object>();
        if(IsObjectNullUtils.is(id)){
            result.put("code","205");
            result.put("msg","id 获取失败");
            return result;
        }
        int flag = verbTestService.delete(id);
        if(flag != 0){
            result.put("code","200");
            result.put("msg","删除成功");
        }else{
            result.put("code","206");
            result.put("msg","删除失败");
        }
        return result;
    }

    @RequestMapping(value = "/updateVerbTest")
    @ResponseBody
    public Map updateVerbTest(HttpServletRequest request,@RequestParam(required = false,value = "verbType")String verbType,
                              @RequestParam(required = false,value = "verbChar")String verbChar,
                              @RequestParam(required = false,value = "cardChar")String cardChar,
                              @RequestParam(required = false,value = "groupChar")String groupChar,
                              @RequestParam(required = false,value = "id")Integer id,
                              @RequestParam(required = false,value = "cardOneTime")Integer cardOneTime,
                              @RequestParam(required = false,value = "cardTwoTime")Integer cardTwoTime){
        Map<String,Object> result = new HashMap<String,Object>();
        XydVerbTest verbTest = new XydVerbTest();
        if(IsObjectNullUtils.is(id)){
            result.put("code","205");
            result.put("msg","id 获取失败");
            return result;
        }
        verbTest.setId(id);
        if (cardOneTime != null ){
            verbTest.setCardOneTime(cardOneTime);
        }
        if (cardTwoTime != null ){
            verbTest.setCardTwoTime(cardTwoTime);
        }
        if(!IsObjectNullUtils.is(verbType)){
            verbTest.setVerbType(verbType);
        }
        if(!IsObjectNullUtils.is(verbChar)){
            verbTest.setVerbChar(verbChar);
        }
        if(!IsObjectNullUtils.is(cardChar)){
            verbTest.setCardChar(cardChar);
        }
        if(!IsObjectNullUtils.is(groupChar)){
            verbTest.setGroupChar(groupChar);
        }
        Map<String, Object> verbImages = FileUtils.verbimages(request);
        if(verbImages.size()>0){
            List<String> verbImage = (List<String>) verbImages.get("verbImage");
            if(jiaoyan(verbImage)){
                for(String str : verbImage){
                    verbTest.setVerbImage(str);
                }
            }
            List<String> verbRecord = (List<String>) verbImages.get("verbRecord");
            if(jiaoyan(verbRecord)){
                for(String str : verbRecord){
                    verbTest.setVerbRecord(str);
                }
            }
            List<String> cardImage = (List<String>) verbImages.get("cardImage");
            if(jiaoyan(cardImage)){
                for(String str : cardImage){
                    verbTest.setCardImage(str);
                }
            }
            List<String> cardRecord = (List<String>) verbImages.get("cardRecord");
            if(jiaoyan(cardRecord)){
                for(String str : cardRecord){
                    verbTest.setCardRecord(str);
                }
            }
            List<String> groupImage = (List<String>) verbImages.get("groupImage");
            if(jiaoyan(groupImage)){
                for(String str : groupImage){
                    verbTest.setGroupImage(str);
                }
            }
            List<String> startSlideshow = (List<String>) verbImages.get("startSlideshow");
            if(jiaoyan(startSlideshow)){
                String s = null;
                for(String str : startSlideshow){
                    s += str+",";
                }
                verbTest.setStartSlideshow(s);
            }
            List<String> endSlideshow = (List<String>) verbImages.get("endSlideshow");
            if(jiaoyan(endSlideshow)){
                String s = null;
                for(String str : endSlideshow){
                    s += str+",";
                }
                verbTest.setEndSlideshow(s);
            }
            List<String> groupRecord = (List<String>) verbImages.get("groupRecord");
            if(jiaoyan(groupRecord)){
                for(String str : groupRecord){
                    verbTest.setGroupRecord(str);
                }
            }
        }
        verbTest.setUpdateTime(new Date());
        int flag = verbTestService.update(verbTest);
        if(flag != 0){
            result.put("code","200");
            result.put("msg","修改成功");
        }else{
            result.put("code","206");
            result.put("msg","修改失败");
        }
        return result;
    }

    @RequestMapping(value = "/verbTestDetail")
    @ResponseBody
    public Map verbTestDetail(HttpServletRequest request, @RequestParam(required = false,value = "id") Integer id){
        Map<String,Object> result = new HashMap<String,Object>();
        if(IsObjectNullUtils.is(id)){
            result.put("code","205");
            result.put("msg","id 获取失败");
            return result;
        }
        XydVerbTest verbTest = verbTestService.selectById(id);
        result.put("data",verbTest);
        result.put("status",200);
        return result;
    }

    /**
     * 获取动词短语 训练
     * @param params 组合文字 搜索
     * @return
     */
    @RequestMapping(value = "/getVerbTrainingList")
    @ResponseBody
    public DataTableReturnData getVerbTrainingList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydVerbTraining> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        if (!IsObjectNullUtils.is(params.get("groupWord"))) {
            queryParam.fill("groupChar", "%"+params.get("groupWord")+"%");
        }
        int count = verbTrainingService.count(queryParam);
        List<XydVerbTraining> list = verbTrainingService.select(queryParam);

        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }
    @RequestMapping(value = "/getHelpTime")
    @ResponseBody
    public Map getHelpTime(HttpServletRequest request, @RequestParam (value = "topic",required = false)Integer topicType) {
        Map<String, Object> result = new HashMap<String, Object>();
        XydTrainingHelptime helptime1 = new XydTrainingHelptime();
        helptime1.setTopicType(topicType);
        helptime1.setStates("1");
        //设定动词的时间 如果没有设置名词的时间 reutrn
        List<XydTrainingHelptime> helptime = trainingHelpService.selectByEntityList(helptime1);
        if (IsObjectNullUtils.is(helptime)) {
            result.put("code", "205");
            result.put("msg1", "查询失败！没有设置动词训练时间");
        } else {
            result.put("helptime", helptime);
        }
        return result;
    }


    @RequestMapping(value = "/addVerbTraining")
    @ResponseBody
    public Map addVerbTraining(HttpServletRequest request, @RequestParam(required = false,value = "verbType")String verbType,
                               @RequestParam(required = false,value = "verbChar")String verbChar,
                               @RequestParam(required = false,value = "cardChar")String cardChar,
                               @RequestParam(required = false,value = "groupChar")String groupChar){
        Map<String,Object> result = new HashMap<String,Object>();
        if(IsObjectNullUtils.is(verbType) ||IsObjectNullUtils.is(verbChar)
                ||IsObjectNullUtils.is(cardChar) ||IsObjectNullUtils.is(groupChar)){
            result.put("code","205");
            result.put("msg","参数 获取失败");
            return result;
        }
        XydVerbTraining verbTraining = new XydVerbTraining();
        verbTraining.setVerbType(verbType);
        verbTraining.setVerbChar(verbChar);
        verbTraining.setCardChar(cardChar);
        verbTraining.setGroupChar(groupChar);
        Map<String, Object> verbImages = FileUtils.verbimages(request);
        if(verbImages.size()>0){

            List<String> verbRecord = (List<String>) verbImages.get("verbRecord");
            if(jiaoyan(verbRecord)){
                for(String str : verbRecord){
                    verbTraining.setVerbRecord(str);
                }
            }
            List<String> cardImage = (List<String>) verbImages.get("cardImage");
            if(jiaoyan(cardImage)){
                for(String str : cardImage){
                    verbTraining.setCardImage(str);
                }
            }
            List<String> cardRecord = (List<String>) verbImages.get("cardRecord");
            if(jiaoyan(cardRecord)){
                for(String str : cardRecord){
                    verbTraining.setCardRecord(str);
                }
            }
            List<String> groupImage = (List<String>) verbImages.get("groupImage");
            if(jiaoyan(groupImage)){
                for(String str : groupImage){
                    verbTraining.setGroupImage(str);
                }
            }//                                                         startSlideshow
            List<String> startSlideshow = (List<String>) verbImages.get("startSlideshow");
            if(jiaoyan(startSlideshow)){
                String str1 = "";
                for(String str : startSlideshow){
                    str1 += str+",";
                }
                verbTraining.setStartSlideshow(str1);
            }
            List<String> endSlideshow = (List<String>) verbImages.get("endSlideshow");
            if(jiaoyan(endSlideshow)){
                for(String str : endSlideshow){
                    verbTraining.setEndSlideshow(str);
                }
            }
            List<String> groupRecord = (List<String>) verbImages.get("groupRecord");
            if(jiaoyan(groupRecord)){
                for(String str : groupRecord){
                    verbTraining.setGroupRecord(str);
                }
            }
        }
        verbTraining.setCreateTime(new Date());
        verbTraining.setStates("1");
        int flag = verbTrainingService.insert(verbTraining);
        if(flag != 0){
            result.put("code","200");
            result.put("msg","添加成功");
        }else{
            result.put("code","206");
            result.put("msg","添加失败");
        }
        return result;
    }

    @RequestMapping(value = "/deleteVerbTraining")
    @ResponseBody
    public Map deleteVerbTraining(HttpServletRequest request, @RequestParam(required = false,value = "id") Integer id){
        Map<String,Object> result = new HashMap<String,Object>();
        if(IsObjectNullUtils.is(id)){
            result.put("code","205");
            result.put("msg","id 获取失败");
            return result;
        }
        int flag = verbTrainingService.delete(id);
        if(flag != 0){
            result.put("code","200");
            result.put("msg","删除成功");
        }else{
            result.put("code","206");
            result.put("msg","删除失败");
        }
        return result;
    }

    @RequestMapping(value = "/updateVerbTraining")
    @ResponseBody
    public Map updateVerbTraining(HttpServletRequest request,@RequestParam(required = false,value = "verbType")String verbType,
                                  @RequestParam(required = false,value = "verbChar")String verbChar,
                                  @RequestParam(required = false,value = "cardChar")String cardChar,
                                  @RequestParam(required = false,value = "groupChar")String groupChar,
                                  @RequestParam(required = false,value = "id")Integer id){
        Map<String,Object> result = new HashMap<String,Object>();
        if(IsObjectNullUtils.is(id)){
            result.put("code","205");
            result.put("msg","id 获取失败");
            return result;
        }

        Map<String, Object> verbImages = FileUtils.verbimages(request);
        XydVerbTraining verbTraining = new XydVerbTraining();
        verbTraining.setId(id);
        if(!IsObjectNullUtils.is(verbChar)){
            verbTraining.setVerbChar(verbChar);
        }
        if(!IsObjectNullUtils.is(verbType)){
            verbTraining.setVerbType(verbType);
        }
        if(!IsObjectNullUtils.is(cardChar)){
            verbTraining.setCardChar(cardChar);
        }if(!IsObjectNullUtils.is(groupChar)){
            verbTraining.setGroupChar(groupChar);
        }

        if(verbImages.size()>0){

            List<String> verbRecord = (List<String>) verbImages.get("verbRecord");
            if(jiaoyan(verbRecord)){
                for(String str : verbRecord){
                    verbTraining.setVerbRecord(str);
                }
            }
            List<String> cardImage = (List<String>) verbImages.get("cardImage");
            if(jiaoyan(cardImage)){
                for(String str : cardImage){
                    verbTraining.setCardImage(str);
                }
            }
            List<String> cardRecord = (List<String>) verbImages.get("cardRecord");
            if(jiaoyan(cardRecord)){
                for(String str : cardRecord){
                    verbTraining.setCardRecord(str);
                }
            }
            List<String> groupImage = (List<String>) verbImages.get("groupImage");
            if(jiaoyan(groupImage)){
                for(String str : groupImage){
                    verbTraining.setGroupImage(str);
                }
            }
            List<String> startSlideshow = (List<String>) verbImages.get("startSlideshow");
            if(jiaoyan(startSlideshow)){
                if(jiaoyan(startSlideshow)){
                    String str1 = "";
                    for(String str : startSlideshow){
                        str1 += str+",";
                    }
                    verbTraining.setStartSlideshow(str1);
                }
            }
            List<String> endSlideshow = (List<String>) verbImages.get("endSlideshow");
            if(jiaoyan(endSlideshow)){
                for(String str : endSlideshow){
                    verbTraining.setEndSlideshow(str);
                }
            }
            List<String> groupRecord = (List<String>) verbImages.get("groupRecord");
            if(jiaoyan(groupRecord)){
                for(String str : groupRecord){
                    verbTraining.setGroupRecord(str);
                }
            }
        }
        verbTraining.setUpdateTime(new Date());
        int flag = verbTrainingService.update(verbTraining);
        if(flag != 0){
            result.put("code","200");
            result.put("msg","修改成功");
        }else{
            result.put("code","206");
            result.put("msg","修改失败");
        }
        return result;
    }

    @RequestMapping(value = "/verbTrainingDetail")
    @ResponseBody
    public Map verbTrainingDetail(HttpServletRequest request, @RequestParam(required = false,value = "id") Integer id){
        Map<String,Object> result = new HashMap<String,Object>();
        if(IsObjectNullUtils.is(id)){
            result.put("code","205");
            result.put("msg","id 获取失败");
            return result;
        }
        XydVerbTraining verbTraining = verbTrainingService.selectById(id);
        result.put("data",verbTraining);
        result.put("status",200);
        result.put("msg","查询成功");
        return result;
    }
}