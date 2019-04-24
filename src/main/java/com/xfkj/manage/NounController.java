package com.xfkj.manage;

import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.model_custom.DataTableReturnData;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.FileUtils;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.web.BaseController;
import com.xfkj.model.XydNounSense;
import com.xfkj.model.XydNounTest;
import com.xfkj.model.XydNounTraining;
import com.xfkj.model.XydTrainingHelptime;
import com.xfkj.service.XydNounSenseService;
import com.xfkj.service.XydNounTestService;
import com.xfkj.service.XydNounTrainingService;
import com.xfkj.service.XydTrainingHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
 * 名词管理
 */
@Controller
@RequestMapping("/manage/noun")
public class NounController extends BaseController {
    @Autowired
    private XydNounTrainingService nounTrainingService;

    @Autowired
    private XydNounTestService nounTestService;

    @Autowired
    private XydNounSenseService nounSenseService;

    @Autowired
    private XydTrainingHelpService trainingHelpService;

    @RequestMapping(value = "/toNoumTrainingPage")
    public String toNoumTrainingPage(Model model) {
        XydTrainingHelptime helptime1 = new XydTrainingHelptime();
        helptime1.setTopicType(1);
        helptime1.setStates("1");
        XydTrainingHelptime helptime = trainingHelpService.selectByEntity(helptime1);
        model.addAttribute("helptime", helptime);
        return "/demo/testpaper/nounTrainingList";
    }

    @RequestMapping(value = "/toNoumTestPage")
    public String toNoumTestPage(Model model) {
        return "/demo/testpaper/nounTestList";
    }


    @RequestMapping(value = "/toNounSensePage")
    public String toNounSenseList(Model model) {
        return "/demo/testpaper/nounSenseList";
    }

    /**
     * 跳转到名词训练添加、修改页面
     */
    @RequestMapping(value = "/toAddNounTrainingPage")
    public String toAddNounTrainingPage(Model model, @RequestParam(value = "id", required = false) Integer id) {
        if (!IsObjectNullUtils.is(id)) {
            XydNounTraining xydNounTraining = nounTrainingService.selectById(id);
            model.addAttribute("nonuTraining", xydNounTraining);
        }
        return "/demo/testpaper/addNounTraining";
    }

    @RequestMapping(value = "/toAddNounTestPage")
    public String toAddNounTestPage(Model model, @RequestParam(value = "id", required = false) Integer id) {
        if (!IsObjectNullUtils.is(id)) {
            XydNounTest xydNounTraining = nounTestService.selectById(id);
            model.addAttribute("nonuTest", xydNounTraining);
        }
        return "/demo/testpaper/addNounTest";
    }

    @RequestMapping(value = "/getImg.html")
    public String getImg(Model model, @RequestParam(value = "data", required = false) String data) {
        model.addAttribute("images",data);
        return "/demo/testpaper/getImage";
    }

    @RequestMapping(value = "/toAddNounSensePage")
    public String toAddNounSensePage(Model model, @RequestParam(value = "id", required = false) Integer id) {
        if (!IsObjectNullUtils.is(id)) {
            XydNounSense nounSense = nounSenseService.selectById(id);
            model.addAttribute("nounSense", nounSense);
        }
        return "/demo/testpaper/addNounSense";
    }


    @RequestMapping(value = "/setHelpTimes")
    @ResponseBody
    public Map setHelpTimes(HttpServletRequest request, @RequestParam(required = false, value = "states1") Integer states1,
                            @RequestParam(required = false, value = "states2") Integer states2,
                            @RequestParam(required = false, value = "states3") Integer states3,
                            @RequestParam(required = false, value = "states4") Integer states4,
                            @RequestParam(required = false, value = "topicType") Integer topicType) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(topicType)) {
            result.put("status", "205");
            result.put("msg", "缺少参数");
            return result;
        }
        if (topicType.equals(1)||topicType.equals(2)) {
            if (null == states1) {
                result.put("status", "205");
                result.put("msg", "缺少参数");
                return result;
            }
            XydTrainingHelptime trainingHelptime = new XydTrainingHelptime();
            trainingHelptime.setSort(1);
            trainingHelptime.setHelpTime(states1);
            trainingHelptime.setStates("1");
            trainingHelptime.setTopicType(topicType);

            XydTrainingHelptime trainingHelptime1 = trainingHelpService.selectByEntity(trainingHelptime);
            if(IsObjectNullUtils.is(trainingHelptime1)){
                trainingHelptime.setCreateTime(new Date());
                int insert = trainingHelpService.insert(trainingHelptime);
                if (insert != 0) {
                    result.put("status", 200);
                    result.put("msg", "添加成功");
                } else {
                    result.put("status", 206);
                    result.put("msg", "添加失败");
                }
            }else{
                trainingHelptime.setUpdateTime(new Date());
                trainingHelptime.setId(trainingHelptime1.getId());
                int update = trainingHelpService.update(trainingHelptime);
                if (update != 0) {
                    result.put("status", 200);
                    result.put("msg", "修改成功");
                } else {
                    result.put("status", 206);
                    result.put("msg", "修改失败");
                }
            }
            return result;
        } else if (topicType.equals(3)) {
            if (null == states1 ) {
                result.put("status", "205");
                result.put("msg", "缺少参数");
                return result;
            }
            int insentcount = 0;
            XydTrainingHelptime trainingHelptime = new XydTrainingHelptime();
            trainingHelptime.setStates("1");
            trainingHelptime.setTopicType(topicType);
            trainingHelptime.setSort(1);
            List<XydTrainingHelptime> xydTrainingHelptimes = trainingHelpService.selectByEntityList(trainingHelptime);
            if(xydTrainingHelptimes.size()>0){
                trainingHelptime.setId(xydTrainingHelptimes.get(0).getId()) ;
                trainingHelptime.setHelpTime(states1);
                trainingHelptime.setUpdateTime(new Date());
                if (trainingHelpService.update(trainingHelptime) > 0)
                    insentcount++;
            }else {
                trainingHelptime.setCreateTime(new Date());
                trainingHelptime.setHelpTime(states1);
                if (trainingHelpService.insert(trainingHelptime) > 0)
                    insentcount++;
            }
            if (null == states2 ) {
                result.put("status", "205");
                result.put("msg", "缺少参数");
                return result;
            }
            XydTrainingHelptime trainingHelptime2 = new XydTrainingHelptime();
            trainingHelptime2.setStates("1");
            trainingHelptime2.setTopicType(topicType);
            trainingHelptime2.setSort(2);
            List<XydTrainingHelptime> xydTrainingHelptimes2 = trainingHelpService.selectByEntityList(trainingHelptime2);
            if(xydTrainingHelptimes2.size()>0){
                trainingHelptime2.setId(xydTrainingHelptimes2.get(0).getId()) ;
                trainingHelptime2.setHelpTime(states2);
                trainingHelptime2.setUpdateTime(new Date());
                if (trainingHelpService.update(trainingHelptime2) > 0)
                    insentcount++;
            }else {
                trainingHelptime2.setCreateTime(new Date());
                trainingHelptime2.setHelpTime(states2);
                if (trainingHelpService.insert(trainingHelptime2) > 0)
                    insentcount++;
            }
            if (insentcount > 1){
                result.put("status", "200");
                result.put("msg", "操作成功");
            }else {
                result.put("status", "205");
                result.put("msg", "操作失败");
            }
            return result;

        } else if (topicType.equals(4)) {
            if (null == states1 || null == states2 || null == states3 || null == states4) {
                result.put("status", "205");
                result.put("msg", "缺少参数");
                return result;
            }
            XydTrainingHelptime trainingHelptime1 = new XydTrainingHelptime();
            trainingHelptime1.setStates("1");
            trainingHelptime1.setTopicType(topicType);
            List<XydTrainingHelptime> xydTrainingHelptimes = trainingHelpService.selectByEntityList(trainingHelptime1);
            if(xydTrainingHelptimes.size()>0){
                for(XydTrainingHelptime trainingHelptime : xydTrainingHelptimes){
                    //如果辅助时长1存在而且不等于设置辅助时长 则修改
                    if(trainingHelptime.getSort()==1&&trainingHelptime.getHelpTime()!=states1){
                        trainingHelptime1.setUpdateTime(new Date());
                        trainingHelptime1.setSort(1);
                        trainingHelptime1.setHelpTime(states1);
                        trainingHelptime1.setId(trainingHelptime.getId());
                        trainingHelpService.update(trainingHelptime1);
                    }
                    if(trainingHelptime.getSort()==2&&trainingHelptime.getHelpTime()!=states1){
                        trainingHelptime1.setUpdateTime(new Date());
                        trainingHelptime1.setSort(2);
                        trainingHelptime1.setHelpTime(states2);
                        trainingHelptime1.setId(trainingHelptime.getId());
                        trainingHelpService.update(trainingHelptime1);
                    }
                    if(trainingHelptime.getSort()==3&&trainingHelptime.getHelpTime()!=states1){
                        trainingHelptime1.setUpdateTime(new Date());
                        trainingHelptime1.setSort(3);
                        trainingHelptime1.setHelpTime(states3);
                        trainingHelptime1.setId(trainingHelptime.getId());
                        trainingHelpService.update(trainingHelptime1);
                    }
                    if(trainingHelptime.getSort()==4&&trainingHelptime.getHelpTime()!=states1){
                        trainingHelptime1.setUpdateTime(new Date());
                        trainingHelptime1.setSort(4);
                        trainingHelptime1.setHelpTime(states4);
                        trainingHelptime1.setId(trainingHelptime.getId());
                        trainingHelpService.update(trainingHelptime1);
                    }
                }
                result.put("status", 200);
                result.put("msg", "修改成功");
            }else{
                XydTrainingHelptime trainingHelptime = new XydTrainingHelptime();
                trainingHelptime.setCreateTime(new Date());
                trainingHelptime.setSort(1);
                trainingHelptime.setHelpTime(states1);
                trainingHelptime.setStates("1");
                trainingHelptime.setTopicType(topicType);
                int insert = trainingHelpService.insert(trainingHelptime);

                trainingHelptime.setSort(2);
                trainingHelptime.setHelpTime(states2);
                int insert2 = trainingHelpService.insert(trainingHelptime);

                trainingHelptime.setSort(3);
                trainingHelptime.setHelpTime(states3);
                int insert3 = trainingHelpService.insert(trainingHelptime);

                trainingHelptime.setSort(4);
                trainingHelptime.setHelpTime(states4);
                int insert4 = trainingHelpService.insert(trainingHelptime);

                if (insert != 0 && insert2 != 0 && insert3 != 0 && insert4 != 0) {
                    result.put("status", 200);
                    result.put("msg", "添加成功");
                } else {
                    result.put("status", 206);
                    result.put("msg", "添加失败");
                }
            }
            return result;
        }
        return result;
    }

    /**
     * 获取名词短语结构 训练
     *
     * @param params 组合文字 搜索
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getNounTrainingList")
    public DataTableReturnData getNounTrainingList(@RequestParam Map<String, String> params) {
        DataTableReturnData<XydNounTraining> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("groupWord"))) {
            queryParam.fill("groupWord", params.get("groupWord"));
        }
        int count = nounTrainingService.count(queryParam);
        List<XydNounTraining> list = nounTrainingService.select(queryParam);
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }


    @RequestMapping(value = "/addNounTraining")
    @ResponseBody
    public Map addNounTraining(HttpServletRequest request, @RequestParam(required = false, value = "wireChar") String wireChar,
                               @RequestParam(required = false, value = "colorPenChar") String colorPenChar,
                               @RequestParam(required = false, value = "groupWord") String groupWord) {
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, Object> upload = FileUtils.nounImages(request);
        if (IsObjectNullUtils.is(wireChar) || IsObjectNullUtils.is(colorPenChar) || IsObjectNullUtils.is(groupWord)) {
            result.put("code", "205");
            result.put("msg", "添加失败 参数有误");
            return result;
        }
        XydNounTraining nounTraining = new XydNounTraining();
        nounTraining.setWireChar(wireChar);
        nounTraining.setColorPenChar(colorPenChar);
        nounTraining.setGroupWord(groupWord);
        if (upload.size() > 0) {
            List<String> wireImage = (List<String>) upload.get("wireImage");
            if (null != wireImage && wireImage.size() > 0) {
                for (String str : wireImage) {
                    nounTraining.setWireImage(str);
                }
            }

            List<String> wireRecord = (List<String>) upload.get("wireRecord");
            if (null != wireRecord && wireRecord.size() > 0) {
                for (String str : wireRecord) {
                    nounTraining.setWireRecord(str);
                }
            }
            List<String> groupImage = (List<String>) upload.get("groupImage");
            if (null != groupImage && groupImage.size() > 0) {
                for (String str : groupImage) {
                    nounTraining.setGroupImage(str);
                }
            }
            List<String> colorPenRecord = (List<String>) upload.get("colorPenRecord");
            if (null != colorPenRecord && colorPenRecord.size() > 0) {
                for (String str : colorPenRecord) {
                    nounTraining.setColorPenRecord(str);
                }
            }

            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (null != groupRecord && groupRecord.size() > 0) {
                if (groupRecord.size() > 0) {
                    for (String str : groupRecord) {
                        nounTraining.setGroupRecord(str);
                    }
                }
            }
        }

        if (IsObjectNullUtils.is(nounTraining)) {
            result.put("code", "205");
            result.put("msg", "对象获取失败");
            return result;
        }
        nounTraining.setCreateTime(new Date());
        nounTraining.setStates("1");
        int flag = nounTrainingService.insert(nounTraining);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "添加成功");
        } else {
            result.put("code", "206");
            result.put("msg", "添加失败");
        }
        return result;
    }

    @RequestMapping(value = "/updateNounTraining")
    @ResponseBody
    public Map updateNounTraining(HttpServletRequest request, @RequestParam(required = false, value = "wireChar") String wireChar,
                                  @RequestParam(required = false, value = "colorPenChar") String colorPenChar,
                                  @RequestParam(required = false, value = "groupWord") String groupWord,
                                  @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "206");
            result.put("msg", "修改失败 id获取失败");
            return result;
        }
        XydNounTraining nounTraining = new XydNounTraining();
        nounTraining.setId(id);
        if (!IsObjectNullUtils.is(wireChar)) {
            nounTraining.setWireChar(wireChar);
        }
        if (!IsObjectNullUtils.is(colorPenChar)) {
            nounTraining.setColorPenChar(colorPenChar);
        }
        if (!IsObjectNullUtils.is(groupWord)) {
            nounTraining.setGroupWord(groupWord);
        }
        Map<String, Object> upload = FileUtils.nounImages(request);
        if (upload.size() > 0) {
            List<String> wireImage = (List<String>) upload.get("wireImage");
            if (!IsObjectNullUtils.is(wireImage)) {
                for (String str : wireImage) {
                    nounTraining.setWireImage(str);
                }
            }
            List<String> wireRecord = (List<String>) upload.get("wireRecord");
            if (!IsObjectNullUtils.is(wireRecord)) {
                for (String str : wireRecord) {
                    nounTraining.setWireRecord(str);
                }
            }
            List<String> colorPenRecord = (List<String>) upload.get("colorPenRecord");
            if (!IsObjectNullUtils.is(colorPenRecord)) {
                for (String str : colorPenRecord) {
                    nounTraining.setColorPenRecord(str);
                }
            }
            List<String> groupImage = (List<String>) upload.get("groupImage");
            if (!IsObjectNullUtils.is(groupImage)) {
                for (String str : groupImage) {
                    nounTraining.setGroupImage(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (!IsObjectNullUtils.is(groupRecord)) {
                if (groupRecord.size() > 0) {
                    for (String str : groupRecord) {
                        nounTraining.setGroupRecord(str);
                    }
                }
            }
        }
        nounTraining.setUpdateTime(new Date());
        int flag = nounTrainingService.update(nounTraining);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "修改成功");
        } else {
            result.put("code", "206");
            result.put("msg", "修改失败");
        }
        return result;
    }

    @RequestMapping(value = "/NounTrainingDetail")
    @ResponseBody
    public Map NounTrainingDetail(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }

        XydNounTraining nounTraining = nounTrainingService.selectById(id);
        result.put("data", nounTraining);
        result.put("status", 200);
        return result;
    }


    @RequestMapping(value = "/deleteNounTraining")
    @ResponseBody
    public Map deleteNounTraining(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydNounTraining nounTraining = nounTrainingService.selectById(id);
        int flag = nounTrainingService.delete(id);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "删除成功");
        } else {
            result.put("code", "206");
            result.put("msg", "删除失败");
        }
        if (!IsObjectNullUtils.is(nounTraining)){
            FileUtils.deleteFile(nounTraining.getGroupImage());
            FileUtils.deleteFile(nounTraining.getWireImage());
            FileUtils.deleteFile(nounTraining.getColorPenRecord());
        }
        return result;
    }

    /**
     * 获取名词短语测试
     *
     * @param params 组合文字 搜索
     * @return
     */
    @RequestMapping(value = "/getNounTestList")
    @ResponseBody
    public DataTableReturnData getNounTestList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydNounTest> dataTableReturnData = new DataTableReturnData<XydNounTest>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        if (!IsObjectNullUtils.is(params.get("groupChar"))) {
            queryParam.fill("groupChar", params.get("groupChar"));
        }
        int count = nounTestService.count(queryParam);
        List<XydNounTest> list = nounTestService.select(queryParam);
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    @RequestMapping(value = "/addNounTest", method = RequestMethod.POST)
    @ResponseBody
    public Map addNounTest(HttpServletRequest request, @RequestParam(required = false, value = "cardColorChar") String cardColorChar,
                           @RequestParam(required = false, value = "fristAssistTime") Integer fristAssistTime,
                           @RequestParam(required = false, value = "cardWireChar") String cardWireChar,
                           @RequestParam(required = false, value = "secondAssistTime") Integer secondAssistTime,
                           @RequestParam(required = false, value = "groupChar") String groupChar) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(cardColorChar) || IsObjectNullUtils.is(fristAssistTime) || IsObjectNullUtils.is(cardWireChar)
                || IsObjectNullUtils.is(secondAssistTime) || IsObjectNullUtils.is(groupChar)) {
            result.put("code", "205");
            result.put("msg", "参数 获取失败");
            return result;
        }
        XydNounTest nounTest = new XydNounTest();
        nounTest.setCardColorChar(cardColorChar);
        nounTest.setFristAssistTime(fristAssistTime);
        nounTest.setCardWireChar(cardWireChar);
        nounTest.setSecondAssistTime(secondAssistTime);
        nounTest.setGroupChar(groupChar);
        Map<String, Object> upload = FileUtils.sentenceGroup(request);
        if (upload.size() > 0) {
            List<String> groupImage = (List<String>) upload.get("groupImage");
            if (null != groupImage && groupImage.size() > 0) {
                for (String str : groupImage) {
                    nounTest.setGroupImage(str);
                }
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (null != cardOneImage && cardOneImage.size() > 0) {
                for (String str : cardOneImage) {
                    nounTest.setCardColorImage(str);
                }
            }
            List<String> cardoneRecord = (List<String>) upload.get("cardOneRecord");
            if (null != cardoneRecord && cardoneRecord.size() > 0) {
                for (String str : cardoneRecord) {
                    nounTest.setCardColorRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (null != cardTwoImage && cardTwoImage.size() > 0) {
                if (cardTwoImage.size() > 0) {
                    for (String str : cardTwoImage) {
                        nounTest.setCardWireImage(str);
                    }
                }
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (null != cardTwoRecord && cardTwoRecord.size() > 0) {
                if (cardTwoRecord.size() > 0) {
                    for (String str : cardTwoRecord) {
                        nounTest.setCardWireRecord(str);
                    }
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (null != groupRecord && groupRecord.size() > 0) {
                if (groupRecord.size() > 0) {
                    for (String str : groupRecord) {
                        nounTest.setGroupRecord(str);
                    }
                }
            }
        }

        nounTest.setCreateTime(new Date());
        nounTest.setStates("1");

        int flag = nounTestService.insert(nounTest);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "添加成功");
        } else {
            result.put("code", "206");
            result.put("msg", "添加失败");
        }
        return result;
    }

    @RequestMapping(value = "/updateNounTest")
    @ResponseBody
    public Map updateNounTest(HttpServletRequest request, @RequestParam(required = false, value = "cardColorChar") String cardColorChar,
                              @RequestParam(required = false, value = "fristAssistTime") Integer fristAssistTime,
                              @RequestParam(required = false, value = "cardWireChar") String cardWireChar,
                              @RequestParam(required = false, value = "secondAssistTime") Integer secondAssistTime,
                              @RequestParam(required = false, value = "groupChar") String groupChar,
                              @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydNounTest nounTest = new XydNounTest();
        nounTest.setId(id);
        if (!IsObjectNullUtils.is(cardColorChar)) {
            nounTest.setCardColorChar(cardColorChar);
        }
        if (!IsObjectNullUtils.is(fristAssistTime)) {
            nounTest.setFristAssistTime(fristAssistTime);
        }
        if (!IsObjectNullUtils.is(cardWireChar)) {
            nounTest.setCardWireChar(cardWireChar);
        }
        if (!IsObjectNullUtils.is(secondAssistTime)) {
            nounTest.setSecondAssistTime(secondAssistTime);
        }
        if (!IsObjectNullUtils.is(groupChar)) {
            nounTest.setGroupChar(groupChar);
        }
        Map<String, Object> upload = FileUtils.sentenceGroup(request);
        if (upload.size() > 0) {
            List<String> groupImage = (List<String>) upload.get("groupImage");
            if (null != groupImage && groupImage.size() > 0) {
                for (String str : groupImage) {
                    nounTest.setGroupImage(str);
                }
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (null != cardOneImage && cardOneImage.size() > 0) {
                for (String str : cardOneImage) {
                    nounTest.setCardColorImage(str);
                }
            }
            List<String> cardoneRecord = (List<String>) upload.get("cardOneRecord");
            if (null != cardoneRecord && cardoneRecord.size() > 0) {
                for (String str : cardoneRecord) {
                    nounTest.setCardColorRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (null != cardTwoImage && cardTwoImage.size() > 0) {
                if (cardTwoImage.size() > 0) {
                    for (String str : cardTwoImage) {
                        nounTest.setCardWireImage(str);
                    }
                }
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (null != cardTwoRecord && cardTwoRecord.size() > 0) {
                if (cardTwoRecord.size() > 0) {
                    for (String str : cardTwoRecord) {
                        nounTest.setCardWireRecord(str);
                    }
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (null != groupRecord && groupRecord.size() > 0) {
                if (groupRecord.size() > 0) {
                    for (String str : groupRecord) {
                        nounTest.setGroupRecord(str);
                    }
                }
            }
        }

        if (IsObjectNullUtils.is(nounTest)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        nounTest.setUpdateTime(new Date());
        int flag = nounTestService.update(nounTest);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "修改成功");
        } else {
            result.put("code", "206");
            result.put("msg", "修改失败");
        }
        return result;
    }

    @RequestMapping(value = "/NounTestDetail")
    @ResponseBody
    public Map NounTestDetail(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }

        XydNounTest nounTest = nounTestService.selectById(id);
        result.put("data", nounTest);
        result.put("status", 200);
        return result;
    }


    @RequestMapping(value = "/deleteNounTest")
    @ResponseBody
    public Map deleteNounTest(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        int flag = nounTestService.delete(id);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "删除成功");
        } else {
            result.put("code", "206");
            result.put("msg", "删除失败");
        }
        return result;
    }

    /**
     * 获取名词短语结构测试
     *
     * @param params 组合文字 搜索
     * @return
     */
    @RequestMapping(value = "/getNounSenseList")
    @ResponseBody
    public DataTableReturnData getNounSenseList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydNounSense> dataTableReturnData = new DataTableReturnData<XydNounSense>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        if (!IsObjectNullUtils.is(params.get("groupChar"))) {
            queryParam.fill("groupChar", params.get("groupChar"));
        }
        if (!IsObjectNullUtils.is(params.get("disturbType"))) {
            queryParam.fill("disturbType", params.get("disturbType"));
        }
        int count = nounSenseService.count(queryParam);
        List<XydNounSense> list = nounSenseService.select(queryParam);
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    @RequestMapping(value = "/addNounSense")
    @ResponseBody
    public Map addNounSense(HttpServletRequest request, @RequestParam(required = false, value = "cardAdjectiveChar") String cardAdjectiveChar,
                            @RequestParam(required = false, value = "fristAssistTime") Integer fristAssistTime,
                            @RequestParam(required = false, value = "cardNounChar") String cardNounChar,
                            @RequestParam(required = false, value = "secondAssistTime") Integer secondAssistTime,
                            @RequestParam(required = false, value = "groupChar") String groupChar,
                            @RequestParam(required = false, value = "disturbType") String disturbType) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(cardAdjectiveChar) || IsObjectNullUtils.is(fristAssistTime) || IsObjectNullUtils.is(cardNounChar)
                || IsObjectNullUtils.is(secondAssistTime) || IsObjectNullUtils.is(groupChar)
                || IsObjectNullUtils.is(disturbType)) {
            result.put("code", "205");
            result.put("msg", "参数获取失败");
            return result;
        }
        XydNounSense nounSense = new XydNounSense();
        nounSense.setCardAdjectiveChar(cardAdjectiveChar);
        nounSense.setFristAssistTime(fristAssistTime);
        nounSense.setCardNounChar(cardNounChar);
        nounSense.setSecondAssistTime(secondAssistTime);
        nounSense.setGroupChar(groupChar);
        nounSense.setDisturbType(disturbType);
        nounSense.setIdioType(disturbType);

        Map<String, Object> upload = FileUtils.sentenceGroup(request);
        if (upload.size() > 0) {
            List<String> startSlideshow = (List<String>) upload.get("groupImage");
            if (!IsObjectNullUtils.is(startSlideshow)) {
                for (String str : startSlideshow) {
                    nounSense.setGroupImage(str);
                }
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (!IsObjectNullUtils.is(startSlideshow)) {
                for (String str : cardOneImage) {
                    nounSense.setCardAdjectiveImage(str);
                }
            }
            List<String> cardoneRecord = (List<String>) upload.get("cardOneRecord");
            if (!IsObjectNullUtils.is(cardoneRecord)) {
                for (String str : cardoneRecord) {
                    nounSense.setCardAdjectiveRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (!IsObjectNullUtils.is(cardTwoImage)) {
                for (String str : cardTwoImage) {
                    nounSense.setCardNounImage(str);
                }
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (!IsObjectNullUtils.is(cardTwoRecord)) {
                for (String str : cardTwoRecord) {
                    nounSense.setCardNounRecord(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (!IsObjectNullUtils.is(groupRecord)) {
                for (String str : groupRecord) {
                    nounSense.setGroupRecord(str);
                }
            }
        }

        nounSense.setCreateTime(new Date());
        nounSense.setStates("1");

        int flag = nounSenseService.insert(nounSense);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "添加成功");
        } else {
            result.put("code", "206");
            result.put("msg", "添加失败");
        }
        return result;
    }

    @RequestMapping(value = "/deleteNounSense")
    @ResponseBody
    public Map deleteNounSense(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        int flag = nounSenseService.delete(id);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "删除成功");
        } else {
            result.put("code", "206");
            result.put("msg", "删除失败");
        }
        return result;
    }

    @RequestMapping(value = "/updateNounSense")
    @ResponseBody
    public Map updateNounSense(HttpServletRequest request, @RequestParam(required = false, value = "cardAdjectiveChar") String cardAdjectiveChar,
                               @RequestParam(required = false, value = "fristAssistTime") Integer fristAssistTime,
                               @RequestParam(required = false, value = "cardNounChar") String cardNounChar,
                               @RequestParam(required = false, value = "secondAssistTime") Integer secondAssistTime,
                               @RequestParam(required = false, value = "groupChar") String groupChar,
                               @RequestParam(required = false, value = "disturbType") String disturbType,
                               @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydNounSense nounSense = new XydNounSense();
        nounSense.setUpdateTime(new Date());
        nounSense.setId(id);
        if (!IsObjectNullUtils.is(cardAdjectiveChar)) {
            nounSense.setCardAdjectiveChar(cardAdjectiveChar);
        }
        if (!IsObjectNullUtils.is(fristAssistTime)) {
            nounSense.setFristAssistTime(fristAssistTime);
        }
        if (!IsObjectNullUtils.is(cardNounChar)) {
            nounSense.setCardNounChar(cardNounChar);
        }
        if (!IsObjectNullUtils.is(secondAssistTime)) {
            nounSense.setSecondAssistTime(secondAssistTime);
        }
        if (!IsObjectNullUtils.is(groupChar)) {
            nounSense.setGroupChar(groupChar);
        }
        if (!IsObjectNullUtils.is(cardAdjectiveChar)) {
            nounSense.setDisturbType(disturbType);
            nounSense.setIdioType(disturbType);
        }

        Map<String, Object> upload = FileUtils.sentenceGroup(request);
        if (upload.size() > 0) {
            List<String> startSlideshow = (List<String>) upload.get("groupImage");
            if (!IsObjectNullUtils.is(startSlideshow)) {
                for (String str : startSlideshow) {
                    nounSense.setGroupImage(str);
                }
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (!IsObjectNullUtils.is(cardOneImage)) {
                for (String str : cardOneImage) {
                    nounSense.setCardAdjectiveImage(str);
                }
            }
            List<String> cardoneRecord = (List<String>) upload.get("cardOneRecord");
            if (!IsObjectNullUtils.is(cardoneRecord)) {
                for (String str : cardoneRecord) {
                    nounSense.setCardAdjectiveRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (!IsObjectNullUtils.is(cardTwoImage)) {
                for (String str : cardTwoImage) {
                    nounSense.setCardNounImage(str);
                }
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (!IsObjectNullUtils.is(cardTwoRecord)) {
                for (String str : cardTwoRecord) {
                    nounSense.setCardNounRecord(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (!IsObjectNullUtils.is(groupRecord)) {
                for (String str : groupRecord) {
                    nounSense.setGroupRecord(str);
                }
            }
        }

        nounSense.setUpdateTime(new Date());
        int flag = nounSenseService.update(nounSense);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "修改成功");
        } else {
            result.put("code", "206");
            result.put("msg", "修改失败");
        }
        return result;
    }

    @RequestMapping(value = "/NounSenseDetail")
    @ResponseBody
    public Map NounSenseDetail(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }

        XydNounSense nounTest = nounSenseService.selectById(id);
        result.put("data", nounTest);
        result.put("status", 200);
        return result;
    }

}
