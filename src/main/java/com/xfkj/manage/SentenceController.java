package com.xfkj.manage;

import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.model_custom.DataTableReturnData;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.FileUtils;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.web.BaseController;
import com.xfkj.model.*;
import com.xfkj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by King on 2018/10/18.
 * 复杂句子管理
 */
@Controller
@RequestMapping("/manage/sentence")
public class SentenceController extends BaseController {
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

    @RequestMapping(value = "/toSentenceResolveTrainingPage")
    public String toNoumTrainingPage(Model model) {
        XydTrainingHelptime helptime1 = new XydTrainingHelptime();
        helptime1.setTopicType(4);
        helptime1.setStates("1");
        XydTrainingHelptime helptime = trainingHelpService.selectByEntity(helptime1);
        model.addAttribute("helptime", helptime);
        return "/demo/testpaper/sentenceResolveTrainingList";
    }

    @RequestMapping(value = "/toSentenceGroupTrainingPage")
    public String toSentencegroupTrainingPage(Model model) {
        XydTrainingHelptime helptime1 = new XydTrainingHelptime();
        helptime1.setTopicType(3);
        helptime1.setStates("1");
        XydTrainingHelptime helptime = trainingHelpService.selectByEntity(helptime1);
        model.addAttribute("helptime", helptime);
        return "/demo/testpaper/sentenceGroupTrainingList";
    }

    @RequestMapping(value = "/toSentenceResolveTestPage")
    public String toVerbTestPage(Model model) {
        return "/demo/testpaper/sentenceResolveTestList";
    }

    @RequestMapping(value = "/toSentenceGroupTestPage")
    public String sentenceGroupTrainingPage(Model model) {
        return "/demo/testpaper/sentenceGroupTestList";
    }

    public static boolean jiaoyan(List<String> stringList) {
        boolean b = false;
        if (!IsObjectNullUtils.is(stringList)) {
            return true;
        }
        return b;
    }

    @RequestMapping(value = "/toAddSentenceGroupTraining")
    public String toAddSentenceGroupTraining(Model model, Integer id) {
        if (!IsObjectNullUtils.is(id)) {
            XydSentenceGroupTraining sentenceGroupTraining = sentenceGroupTrainingService.selectById(id);
            model.addAttribute("sentenceGroupTraining", sentenceGroupTraining);
        }
        return "/demo/testpaper/addSentenceGroupTraining";
    }

    @RequestMapping(value = "/toAddSentenceGroupTest")
    public String toAddSentenceGroupTest(Model model, Integer id) {
        if (!IsObjectNullUtils.is(id)) {
            XydSentenceGroupTest sentenceGroupTest = sentenceGroupTestService.selectById(id);
            model.addAttribute("sentenceGroupTest", sentenceGroupTest);
        }
        return "/demo/testpaper/addSentenceGroupTest";
    }

    @RequestMapping(value = "/toCheckSentenceResolveTest")
    public String toCheckSentenceResolveTest(Model model, Integer id) {
        if (!IsObjectNullUtils.is(id)) {
            XydSentenceResolveTest sentenceResolveTest = sentenceResolveTestService.selectById(id);
            if(!IsObjectNullUtils.is(sentenceResolveTest.getStartSlideshow())){
                ArrayList<String> strings = new ArrayList<>();
                String[] split = sentenceResolveTest.getStartSlideshow().split(",");
                for(String s : split){
                    if(!IsObjectNullUtils.is(s)){
                        strings.add(s);
                    }
                }
                sentenceResolveTest.setStartSlideshowList(strings);
            }
            if(!IsObjectNullUtils.is(sentenceResolveTest.getCardThreeImage())){
                ArrayList<String> strings = new ArrayList<>();
                String[] split = sentenceResolveTest.getCardThreeImage().split(",");
                for(String s : split){
                    if(!IsObjectNullUtils.is(s)){
                        strings.add(s);
                    }
                }
                sentenceResolveTest.setCardThreeImageList(strings);
            }
            model.addAttribute("sentenceResolveTest", sentenceResolveTest);
        }
        return "/demo/testpaper/checkSentenceResolveTest";
    }
    //去查看 训练
    @RequestMapping(value = "/toCheckSentenceResolveTraining")
    public String toCheckSentenceResolveTraining(Model model, Integer id) {
        if (!IsObjectNullUtils.is(id)) {
            XydSentenceResolveTraining sentenceResolveTraining = sentenceResolveTrainingService.selectById(id);
            if(!IsObjectNullUtils.is(sentenceResolveTraining.getStartSlideshow())){
                ArrayList<String> strings = new ArrayList<>();
                String[] split = sentenceResolveTraining.getStartSlideshow().split(",");
                for(String s : split){
                    if(!IsObjectNullUtils.is(s)){
                        strings.add(s);
                    }
                }
                sentenceResolveTraining.setStartSlideshowList(strings);
            }
            if(!IsObjectNullUtils.is(sentenceResolveTraining.getCardThreeImage())){
                ArrayList<String> strings = new ArrayList<>();
                String[] split = sentenceResolveTraining.getCardThreeImage().split(",");
                for(String s : split){
                    if(!IsObjectNullUtils.is(s)){
                        strings.add(s);
                    }
                }
                sentenceResolveTraining.setCardThreeImageList(strings);
            }
            model.addAttribute("sentenceResolveTraining", sentenceResolveTraining);
        }
        return "/demo/testpaper/checkSentenceResolveTraining";
    }

    @RequestMapping(value = "/toAddSentenceResolveTest")
    public String toAddSentenceResolveTest(Model model, Integer id) {
        if (!IsObjectNullUtils.is(id)) {
            XydSentenceResolveTest sentenceResolveTest = sentenceResolveTestService.selectById(id);
            if(!IsObjectNullUtils.is(sentenceResolveTest.getStartSlideshow())){
                ArrayList<String> strings = new ArrayList<>();
                String[] split = sentenceResolveTest.getStartSlideshow().split(",");
                for(String s : split){
                    if(!IsObjectNullUtils.is(s)){
                        strings.add(s);
                    }
                }
                sentenceResolveTest.setStartSlideshowList(strings);
            }
            if(!IsObjectNullUtils.is(sentenceResolveTest.getCardThreeImage())){
                ArrayList<String> strings = new ArrayList<>();
                String[] split = sentenceResolveTest.getCardThreeImage().split(",");
                for(String s : split){
                    if(!IsObjectNullUtils.is(s)){
                        strings.add(s);
                    }
                }
                sentenceResolveTest.setCardThreeImageList(strings);
            }
            model.addAttribute("sentenceResolveTest", sentenceResolveTest);
        }
        return "/demo/testpaper/addSentenceResolveTest";
    }

    @RequestMapping(value = "/toAddSentenceResolveTraining")
    public String toAddSentenceResolveTraining(Model model, Integer id) {
        if (!IsObjectNullUtils.is(id)) {
            XydSentenceResolveTraining sentenceResolveTraining = sentenceResolveTrainingService.selectById(id);
            if(!IsObjectNullUtils.is(sentenceResolveTraining.getStartSlideshow())){
                ArrayList<String> strings = new ArrayList<>();
                String[] split = sentenceResolveTraining.getStartSlideshow().split(",");
                for(String s : split){
                    if(!IsObjectNullUtils.is(s)){
                        strings.add(s);
                    }
                }
                sentenceResolveTraining.setStartSlideshowList(strings);
            }
            model.addAttribute("sentenceResolveTraining", sentenceResolveTraining);
        }
        return "/demo/testpaper/addSentenceResolveTraining";
    }

    /**
     * 句子分解 训练
     *
     * @return dataTableReturnData
     */
    @RequestMapping(value = "/getSentenceResolveTrainingList")
    @ResponseBody
    public DataTableReturnData getSentenceResolveTrainingList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydSentenceResolveTraining> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        if (!IsObjectNullUtils.is(params.get("groupWord"))) {
            queryParam.fill("groupChar", "%"+params.get("groupWord")+"%");
        }

        int count = sentenceResolveTrainingService.count(queryParam);
        List<XydSentenceResolveTraining> list = sentenceResolveTrainingService.select(queryParam);
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    @RequestMapping(value = "/addSentenceResolveTraining")
    @ResponseBody
    public Map addSentenceResolveTraining(HttpServletRequest request, @RequestParam(value = "cardOneChar", required = false) String cardOneChar,
                                          @RequestParam(value = "cardTwoChar", required = false) String cardTwoChar,
                                          @RequestParam(value = "cardThreeChar", required = false) String cardThreeChar,
                                          @RequestParam(value = "cardFourChar", required = false) String cardFourChar,
                                          @RequestParam(value = "groupChar", required = false) String groupChar) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(cardOneChar) || IsObjectNullUtils.is(cardTwoChar) || IsObjectNullUtils.is(cardThreeChar)
                || IsObjectNullUtils.is(cardFourChar) || IsObjectNullUtils.is(groupChar)) {
            result.put("code", "205");
            result.put("msg", "参数 获取失败");
            return result;
        }
        XydSentenceResolveTraining sentenceResolveTraining = new XydSentenceResolveTraining();
        sentenceResolveTraining.setCardOneChar(cardOneChar);
        sentenceResolveTraining.setCardTwoChar(cardTwoChar);
        sentenceResolveTraining.setCardThreeChar(cardThreeChar);
        sentenceResolveTraining.setCardFourChar(cardFourChar);
        sentenceResolveTraining.setGroupChar(groupChar);
        Map<String, Object> upload = FileUtils.sentenceReslove(request);
        if (upload.size() > 0) {
            List<String> startSlideshow = (List<String>) upload.get("startSlideshow");
            if (jiaoyan(startSlideshow)) {
                String str1 = "";
                for (String str : startSlideshow) {
                    str1 += str+",";
                }
                sentenceResolveTraining.setStartSlideshow(str1);
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (jiaoyan(cardOneImage)) {
                for (String str : cardOneImage) {
                    sentenceResolveTraining.setCardOneImage(str);
                }
            }
            List<String> cardOneRecord = (List<String>) upload.get("cardOneRecord");
            if (jiaoyan(cardOneRecord)) {
                for (String str : cardOneRecord) {
                    sentenceResolveTraining.setCardOneRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (jiaoyan(cardTwoImage)) {
                for (String str : cardTwoImage) {
                    sentenceResolveTraining.setCardTwoImage(str);
                }
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (jiaoyan(cardTwoRecord)) {
                for (String str : cardTwoRecord) {
                    sentenceResolveTraining.setCardTwoRecord(str);
                }
            }
            List<String> cardThreeImage = (List<String>) upload.get("cardThreeImage");
            if (jiaoyan(cardThreeImage)) {
                String str1 = "";
                for (String str : cardThreeImage) {
                    str1 += str+",";
                }
                sentenceResolveTraining.setCardThreeImage(str1);
            }
            List<String> cardThreeRecord = (List<String>) upload.get("cardThreeRecord");
            if (jiaoyan(cardThreeRecord)) {
                for (String str : cardThreeRecord) {
                    sentenceResolveTraining.setCardThreeRecord(str);
                }
            }
            List<String> cardFourImage = (List<String>) upload.get("cardFourImage");
            if (jiaoyan(cardFourImage)) {
                for (String str : cardFourImage) {
                    sentenceResolveTraining.setCardFourImage(str);
                }
            }
            List<String> cardFourRecord = (List<String>) upload.get("cardFourRecord");
            if (jiaoyan(cardFourRecord)) {
                for (String str : cardFourRecord) {
                    sentenceResolveTraining.setCardFourRecord(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (jiaoyan(groupRecord)) {
                for (String str : groupRecord) {
                    sentenceResolveTraining.setGroupRecord(str);
                }
            }
        }
        sentenceResolveTraining.setCreateTime(new Date());
        sentenceResolveTraining.setStates("1");
        int flag = sentenceResolveTrainingService.insert(sentenceResolveTraining);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "添加成功");
        } else {
            result.put("code", "206");
            result.put("msg", "添加失败");
        }
        return result;
    }

    @RequestMapping(value = "/deleteSentenceResolveTraining")
    @ResponseBody
    public Map deleteSentenceResolveTraining(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        int flag = sentenceResolveTrainingService.delete(id);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "删除成功");
        } else {
            result.put("code", "206");
            result.put("msg", "删除失败");
        }
        return result;
    }

    @RequestMapping(value = "/updateSentenceResolveTraining")
    @ResponseBody
    public Map updateSentenceResolveTraining(HttpServletRequest request, @RequestParam(value = "cardOneChar", required = false) String cardOneChar,
                                             @RequestParam(value = "cardTwoChar", required = false) String cardTwoChar,
                                             @RequestParam(value = "cardThreeChar", required = false) String cardThreeChar,
                                             @RequestParam(value = "cardFourChar", required = false) String cardFourChar,
                                             @RequestParam(value = "groupChar", required = false) String groupChar,
                                             @RequestParam(value = "id", required = false) Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydSentenceResolveTraining sentenceResolveTraining = new XydSentenceResolveTraining();
        sentenceResolveTraining.setId(id);

        if (!IsObjectNullUtils.is(cardOneChar)) {
            sentenceResolveTraining.setCardOneChar(cardOneChar);
        }
        if (!IsObjectNullUtils.is(cardTwoChar)) {
            sentenceResolveTraining.setCardTwoChar(cardTwoChar);
        }
        if (!IsObjectNullUtils.is(cardThreeChar)) {
            sentenceResolveTraining.setCardThreeChar(cardThreeChar);
        }
        if (!IsObjectNullUtils.is(cardFourChar)) {
            sentenceResolveTraining.setCardFourChar(cardFourChar);
        }
        if (!IsObjectNullUtils.is(groupChar)) {
            sentenceResolveTraining.setGroupChar(groupChar);
        }
        Map<String, Object> upload = FileUtils.sentenceReslove(request);
        if (upload.size() > 0) {
            List<String> startSlideshow = (List<String>) upload.get("startSlideshow");
            if (jiaoyan(startSlideshow)) {
                String str1 = "";
                for (String str : startSlideshow) {
                    str1 += str+",";
                }
                sentenceResolveTraining.setStartSlideshow(str1);
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (jiaoyan(cardOneImage)) {
                for (String str : cardOneImage) {
                    sentenceResolveTraining.setCardOneImage(str);
                }
            }
            List<String> cardOneRecord = (List<String>) upload.get("cardOneRecord");
            if (jiaoyan(cardOneRecord)) {
                for (String str : cardOneRecord) {
                    sentenceResolveTraining.setCardOneRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (jiaoyan(cardTwoImage)) {
                for (String str : cardTwoImage) {
                    sentenceResolveTraining.setCardTwoImage(str);
                }
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (jiaoyan(cardTwoRecord)) {
                for (String str : cardTwoRecord) {
                    sentenceResolveTraining.setCardTwoRecord(str);
                }
            }
            List<String> cardThreeImage = (List<String>) upload.get("cardThreeImage");
            if (jiaoyan(cardThreeImage)) {
                String str1 = "";
                for (String str : cardThreeImage) {
                    str1 += str+",";
                }
                sentenceResolveTraining.setCardThreeImage(str1);
            }
            List<String> cardThreeRecord = (List<String>) upload.get("cardThreeRecord");
            if (jiaoyan(cardThreeRecord)) {
                for (String str : cardThreeRecord) {
                    sentenceResolveTraining.setCardThreeRecord(str);
                }
            }
            List<String> cardFourImage = (List<String>) upload.get("cardFourImage");
            if (jiaoyan(cardFourImage)) {
                for (String str : cardFourImage) {
                    sentenceResolveTraining.setCardFourImage(str);
                }
            }
            List<String> cardFourRecord = (List<String>) upload.get("cardFourRecord");
            if (jiaoyan(cardFourRecord)) {
                for (String str : cardFourRecord) {
                    sentenceResolveTraining.setCardFourRecord(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (jiaoyan(groupRecord)) {
                for (String str : groupRecord) {
                    sentenceResolveTraining.setGroupRecord(str);
                }
            }
        }
        sentenceResolveTraining.setUpdateTime(new Date());
        int flag = sentenceResolveTrainingService.update(sentenceResolveTraining);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "修改成功");
        } else {
            result.put("code", "206");
            result.put("msg", "修改失败");
        }
        return result;
    }

    @RequestMapping(value = "/SentenceResolveTrainingDetail")
    @ResponseBody
    public Map SentenceResolveTrainingDetail(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydSentenceResolveTraining sentenceResolveTraining = sentenceResolveTrainingService.selectById(id);
        result.put("data", sentenceResolveTraining);
        result.put("status", 200);
        result.put("msg", "查询成功");
        return result;
    }

    /**
     * 句子分解 测试
     *
     * @return dataTableReturnData
     */
    @RequestMapping(value = "/getSentenceResolveTestList")
    @ResponseBody
    public DataTableReturnData getSentenceResolveTestList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydSentenceResolveTest> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        if (!IsObjectNullUtils.is(params.get("groupWord"))) {
            queryParam.fill("groupChar", "%"+params.get("groupWord")+"%");
        }
        int count = sentenceResolveTestService.count(queryParam);
        List<XydSentenceResolveTest> list = sentenceResolveTestService.select(queryParam);
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    @RequestMapping(value = "/addSentenceResolveTest")
    @ResponseBody
    public Map addSentenceResolveTest(HttpServletRequest request, @RequestParam(required = false, value = "cardOneChar") String cardOneChar,
                                      @RequestParam(required = false, value = "cardTwoChar") String cardTwoChar,
                                      @RequestParam(required = false, value = "cardThreeChar") String cardThreeChar,
                                      @RequestParam(required = false, value = "cardFourChar") String cardFourChar,
                                      @RequestParam(required = false, value = "groupChar") String groupChar,
                                      @RequestParam(required = false, value = "cardOneTime") Integer cardOneTime,
                                      @RequestParam(required = false, value = "cardTwoTime") Integer cardTwoTime,
                                      @RequestParam(required = false, value = "cardThreeTime") Integer cardThreeTime,
                                      @RequestParam(required = false, value = "cardFourTime") Integer cardFourTime) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(cardOneChar) || IsObjectNullUtils.is(cardTwoChar) || IsObjectNullUtils.is(cardThreeChar)
                || IsObjectNullUtils.is(cardFourChar) || IsObjectNullUtils.is(groupChar) || IsObjectNullUtils.is(cardOneTime)
                || IsObjectNullUtils.is(cardTwoTime) || IsObjectNullUtils.is(cardThreeTime) || IsObjectNullUtils.is(cardFourTime)) {
            result.put("code", "205");
            result.put("msg", "参数获取失败");
            return result;
        }
        XydSentenceResolveTest sentenceResolveTest = new XydSentenceResolveTest();
        sentenceResolveTest.setCardOneChar(cardOneChar);
        sentenceResolveTest.setCardOneTime(cardOneTime);
        sentenceResolveTest.setCardTwoTime(cardTwoTime);
        sentenceResolveTest.setCardThreeTime(cardThreeTime);
        sentenceResolveTest.setCardFourTime(cardFourTime);
        sentenceResolveTest.setCardTwoChar(cardTwoChar);
        sentenceResolveTest.setCardThreeChar(cardThreeChar);
        sentenceResolveTest.setCardFourChar(cardFourChar);
        sentenceResolveTest.setGroupChar(groupChar);
        Map<String, Object> upload = FileUtils.sentenceReslove(request);
        if (upload.size() > 0) {
            List<String> startSlideshow = (List<String>) upload.get("startSlideshow");
            if (jiaoyan(startSlideshow)) {
                String str1 = "";
                for (String str : startSlideshow) {
                    str1 += str+",";
                }
                sentenceResolveTest.setStartSlideshow(str1);
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (jiaoyan(cardOneImage)) {
                for (String str : cardOneImage) {
                    sentenceResolveTest.setCardOneImage(str);
                }
            }
            List<String> cardOneRecord = (List<String>) upload.get("cardOneRecord");
            if (jiaoyan(cardOneRecord)) {
                for (String str : cardOneRecord) {
                    sentenceResolveTest.setCardOneRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (jiaoyan(cardTwoImage)) {
                for (String str : cardTwoImage) {
                    sentenceResolveTest.setCardTwoImage(str);
                }
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (jiaoyan(cardTwoRecord)) {
                for (String str : cardTwoRecord) {
                    sentenceResolveTest.setCardTwoRecord(str);
                }
            }
            List<String> cardThreeImage = (List<String>) upload.get("cardThreeImage");
            if (jiaoyan(cardThreeImage)) {
                String str1 = "";
                for (String str : cardThreeImage) {
                    str1 += str+",";
                }
                sentenceResolveTest.setCardThreeImage(str1);
            }
            List<String> cardThreeRecord = (List<String>) upload.get("cardThreeRecord");
            if (jiaoyan(cardThreeRecord)) {
                for (String str : cardThreeRecord) {
                    sentenceResolveTest.setCardThreeRecord(str);
                }
            }
            List<String> cardFourImage = (List<String>) upload.get("cardFourImage");
            if (jiaoyan(cardFourImage)) {
                for (String str : cardFourImage) {
                    sentenceResolveTest.setCardFourImage(str);
                }
            }
            List<String> cardFourRecord = (List<String>) upload.get("cardFourRecord");
            if (jiaoyan(cardFourRecord)) {
                for (String str : cardFourRecord) {
                    sentenceResolveTest.setCardFourRecord(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (jiaoyan(groupRecord)) {
                for (String str : groupRecord) {
                    sentenceResolveTest.setGroupRecord(str);
                }
            }
        }
        sentenceResolveTest.setCreateTime(new Date());
        sentenceResolveTest.setStates("1");
        int flag = sentenceResolveTestService.insert(sentenceResolveTest);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "添加成功");
        } else {
            result.put("code", "206");
            result.put("msg", "添加失败");
        }
        return result;
    }

    @RequestMapping(value = "/deleteSentenceResolveTest")
    @ResponseBody
    public Map deleteSentenceResolveTest(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        int flag = sentenceResolveTestService.delete(id);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "删除成功");
        } else {
            result.put("code", "206");
            result.put("msg", "删除失败");
        }
        return result;
    }

    @RequestMapping(value = "/updateSentenceResolveTest")
    @ResponseBody
    public Map updateSentenceResolveTest(HttpServletRequest request, @RequestParam(required = false, value = "cardOneChar") String cardOneChar,
                                         @RequestParam(required = false, value = "cardTwoChar") String cardTwoChar,
                                         @RequestParam(required = false, value = "cardThreeChar") String cardThreeChar,
                                         @RequestParam(required = false, value = "cardFourChar") String cardFourChar,
                                         @RequestParam(required = false, value = "groupChar") String groupChar,
                                         @RequestParam(required = false, value = "id") Integer id,
                                         @RequestParam(required = false, value = "cardOneTime") Integer cardOneTime,
                                         @RequestParam(required = false, value = "cardTwoTime") Integer cardTwoTime,
                                         @RequestParam(required = false, value = "cardThreeTime") Integer cardThreeTime,
                                         @RequestParam(required = false, value = "cardFourTime") Integer cardFourTime) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydSentenceResolveTest sentenceResolveTest = new XydSentenceResolveTest();
        sentenceResolveTest.setId(id);
        if (!IsObjectNullUtils.is(cardOneTime)){
            sentenceResolveTest.setCardOneTime(cardOneTime);
        }
        if (!IsObjectNullUtils.is(cardTwoTime)){
            sentenceResolveTest.setCardTwoTime(cardTwoTime);
        }
        if (!IsObjectNullUtils.is(cardThreeTime)){
            sentenceResolveTest.setCardThreeTime(cardThreeTime);
        }
        if (!IsObjectNullUtils.is(cardFourTime)){
            sentenceResolveTest.setCardFourTime(cardFourTime);
        }
        if (!IsObjectNullUtils.is(cardOneChar)) {
            sentenceResolveTest.setCardOneChar(cardOneChar);
        }
        if (!IsObjectNullUtils.is(cardTwoChar)) {
            sentenceResolveTest.setCardTwoChar(cardTwoChar);
        }
        if (!IsObjectNullUtils.is(cardThreeChar)) {
            sentenceResolveTest.setCardThreeChar(cardThreeChar);
        }
        if (!IsObjectNullUtils.is(cardFourChar)) {
            sentenceResolveTest.setCardFourChar(cardFourChar);
        }
        if (!IsObjectNullUtils.is(groupChar)) {
            sentenceResolveTest.setGroupChar(groupChar);
        }

        Map<String, Object> upload = FileUtils.sentenceReslove(request);
        if (upload.size() > 0) {
            List<String> startSlideshow = (List<String>) upload.get("startSlideshow");
            if (jiaoyan(startSlideshow)) {
                String str1 = "";
                for (String str : startSlideshow) {
                    str1 += str+",";
                }
                sentenceResolveTest.setStartSlideshow(str1);
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (jiaoyan(cardOneImage)) {
                for (String str : cardOneImage) {
                    sentenceResolveTest.setCardOneImage(str);
                }
            }
            List<String> cardOneRecord = (List<String>) upload.get("cardOneRecord");
            if (jiaoyan(cardOneRecord)) {
                for (String str : cardOneRecord) {
                    sentenceResolveTest.setCardOneRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (jiaoyan(cardTwoImage)) {
                for (String str : cardTwoImage) {
                    sentenceResolveTest.setCardTwoImage(str);
                }
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (jiaoyan(cardTwoRecord)) {
                for (String str : cardTwoRecord) {
                    sentenceResolveTest.setCardTwoRecord(str);
                }
            }
            List<String> cardThreeImage = (List<String>) upload.get("cardThreeImage");
            if (jiaoyan(cardThreeImage)) {
                String str1 = "";
                for (String str : cardThreeImage) {
                    str1 += str+",";
                }
                sentenceResolveTest.setCardThreeImage(str1);
            }
            List<String> cardThreeRecord = (List<String>) upload.get("cardThreeRecord");
            if (jiaoyan(cardThreeRecord)) {
                for (String str : cardThreeRecord) {
                    sentenceResolveTest.setCardThreeRecord(str);
                }
            }
            List<String> cardFourImage = (List<String>) upload.get("cardFourImage");
            if (jiaoyan(cardFourImage)) {
                for (String str : cardFourImage) {
                    sentenceResolveTest.setCardFourImage(str);
                }
            }
            List<String> cardFourRecord = (List<String>) upload.get("cardFourRecord");
            if (jiaoyan(cardFourRecord)) {
                for (String str : cardFourRecord) {
                    sentenceResolveTest.setCardFourRecord(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (jiaoyan(groupRecord)) {
                for (String str : groupRecord) {
                    sentenceResolveTest.setGroupRecord(str);
                }
            }
        }
        sentenceResolveTest.setUpdateTime(new Date());
        int flag = sentenceResolveTestService.update(sentenceResolveTest);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "修改成功");
        } else {
            result.put("code", "206");
            result.put("msg", "修改失败");
        }
        return result;
    }

    @RequestMapping(value = "/SentenceResolveTestDetail")
    @ResponseBody
    public Map SentenceResolveTestDetail(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydSentenceResolveTest sentenceResolveTest = sentenceResolveTestService.selectById(id);
        result.put("data", sentenceResolveTest);
        result.put("status", 200);
        result.put("msg", "查询成功");
        return result;
    }


    /**
     * 句子成组 训练
     *
     * @return dataTableReturnData
     */
    @RequestMapping(value = "/getSentencegroupTrainingList")
    @ResponseBody
    public DataTableReturnData getSentencegroupTrainingList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydSentenceGroupTraining> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        if (!IsObjectNullUtils.is(params.get("groupWord"))) {
            queryParam.fill("groupChar", "%"+params.get("groupWord")+"%");
        }

//        XydTrainingHelptime helptime1 = new XydTrainingHelptime();
//        helptime1.setTopicType(3);
//        helptime1.setStates("1");
//        List<XydTrainingHelptime> helptime = trainingHelpService.selectByEntityList(helptime1);

        int count = sentenceGroupTrainingService.count(queryParam);
        List<XydSentenceGroupTraining> list = sentenceGroupTrainingService.select(queryParam);
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("list", list);
//        result.put("helptime",helptime);
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    @RequestMapping(value = "/addSentenceGroupTraining")
    @ResponseBody
    public Map addSentencegroupTraining(HttpServletRequest request, @RequestParam(required = false, value = "cardOneChar") String cardOneChar,
                                        @RequestParam(required = false, value = "cardTwoChar") String cardTwoChar,
                                        @RequestParam(required = false, value = "groupChar") String groupChar) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(cardOneChar)) {
            result.put("code", "205");
            result.put("msg", "参数获取失败");
            return result;
        }
        XydSentenceGroupTraining sentenceGroupTraining = new XydSentenceGroupTraining();
        sentenceGroupTraining.setCardOneChar(cardOneChar);
        sentenceGroupTraining.setCardTwoChar(cardTwoChar);
        sentenceGroupTraining.setGroupChar(groupChar);
        Map<String, Object> upload = FileUtils.sentenceGroup(request);
        if (upload.size() > 0) {
            List<String> startSlideshow = (List<String>) upload.get("startSlideshow");
            if (jiaoyan(startSlideshow)) {
                String str1 = "";
                for (String str : startSlideshow) {
                    str1 += str+",";
                }
                sentenceGroupTraining.setStartSlideshow(str1);
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (jiaoyan(cardOneImage)) {
                for (String str : cardOneImage) {
                    sentenceGroupTraining.setCardOneImage(str);
                }
            }
            List<String> cardoneRecord = (List<String>) upload.get("cardOneRecord");
            if (jiaoyan(cardoneRecord)) {
                for (String str : cardoneRecord) {
                    sentenceGroupTraining.setCardOneRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (jiaoyan(cardTwoImage)) {
                String str1 = "";
                for (String str : cardTwoImage) {
                    str1 += str+",";
                }
                sentenceGroupTraining.setCardTwoImage(str1);
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (jiaoyan(cardTwoRecord)) {
                for (String str : cardTwoRecord) {
                    sentenceGroupTraining.setCardTwoRecord(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (jiaoyan(groupRecord)) {
                for (String str : groupRecord) {
                    sentenceGroupTraining.setGroupRecord(str);
                }
            }
        }
        sentenceGroupTraining.setCreateTime(new Date());
        sentenceGroupTraining.setStates("1");
        int flag = sentenceGroupTrainingService.insert(sentenceGroupTraining);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "添加成功");
        } else {
            result.put("code", "206");
            result.put("msg", "添加失败");
        }
        return result;
    }


    @RequestMapping(value = "/deleteSentenceGroupTraining")
    @ResponseBody
    public Map deleteSentencegroupTraining(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        int flag = sentenceGroupTrainingService.delete(id);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "删除成功");
        } else {
            result.put("code", "206");
            result.put("msg", "删除失败");
        }
        return result;
    }

    @RequestMapping(value = "/updateSentenceGroupTraining")
    @ResponseBody
    public Map updateSentencegroupTraining(HttpServletRequest request, @RequestParam(required = false, value = "cardOneChar") String cardOneChar,
                                           @RequestParam(required = false, value = "cardTwoChar") String cardTwoChar,
                                           @RequestParam(required = false, value = "groupChar") String groupChar,
                                           @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydSentenceGroupTraining sentenceGroupTraining = new XydSentenceGroupTraining();
        sentenceGroupTraining.setId(id);
        if (!IsObjectNullUtils.is(cardOneChar)) {
            sentenceGroupTraining.setCardOneChar(cardOneChar);
        }
        if (!IsObjectNullUtils.is(cardOneChar)) {
            sentenceGroupTraining.setCardTwoChar(cardTwoChar);
        }
        if (!IsObjectNullUtils.is(cardOneChar)) {
            sentenceGroupTraining.setGroupChar(groupChar);
        }
        Map<String, Object> upload = FileUtils.sentenceGroup(request);
        if (upload.size() > 0) {
            List<String> startSlideshow = (List<String>) upload.get("startSlideshow");
            if (jiaoyan(startSlideshow)) {
                String str1 = "";
                for (String str : startSlideshow) {
                    str1 += str+",";
                }
                sentenceGroupTraining.setStartSlideshow(str1);
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (jiaoyan(cardOneImage)) {
                for (String str : cardOneImage) {
                    sentenceGroupTraining.setCardOneImage(str);
                }
            }
            List<String> cardoneRecord = (List<String>) upload.get("cardOneRecord");
            if (jiaoyan(cardoneRecord)) {
                for (String str : cardoneRecord) {
                    sentenceGroupTraining.setCardOneRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (jiaoyan(cardTwoImage)) {
                String str1 = "";
                for (String str : cardTwoImage) {
                    str1 += str+",";
                }
                sentenceGroupTraining.setCardTwoImage(str1);
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (jiaoyan(cardTwoRecord)) {
                for (String str : cardTwoRecord) {
                    sentenceGroupTraining.setCardTwoRecord(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (jiaoyan(groupRecord)) {
                for (String str : groupRecord) {
                    sentenceGroupTraining.setGroupRecord(str);
                }
            }
        }
        sentenceGroupTraining.setUpdateTime(new Date());
        int flag = sentenceGroupTrainingService.update(sentenceGroupTraining);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "修改成功");
        } else {
            result.put("code", "206");
            result.put("msg", "修改失败");
        }
        return result;
    }

    @RequestMapping(value = "/sentencegroupTrainingDetail")
    @ResponseBody
    public Map sentencegroupTrainingDetail(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydSentenceGroupTraining sentenceGroupTraining = sentenceGroupTrainingService.selectById(id);
        result.put("data", sentenceGroupTraining);
        result.put("status", 200);
        result.put("msg", "查询成功");
        return result;
    }

    /**
     * 句子成组 测试
     *
     * @return dataTableReturnData
     */
    @RequestMapping(value = "/getSentencegroupTestList")
    @ResponseBody
    public DataTableReturnData getSentenceGroupTestList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        DataTableReturnData<XydSentenceGroupTest> dataTableReturnData = new DataTableReturnData<>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);

        if (!IsObjectNullUtils.is(params.get("groupWord"))) {
            queryParam.fill("groupChar", "%"+params.get("groupWord")+"%");
        }
        int count = sentenceGroupTestService.count(queryParam);
        List<XydSentenceGroupTest> list = sentenceGroupTestService.select(queryParam);
        dataTableReturnData.setData(list);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    @RequestMapping(value = "/addSentenceGroupTest")
    @ResponseBody
    public Map addSentencegroupTest(HttpServletRequest request, @RequestParam(value = "cardOneChar", required = false) String cardOneChar,
                                    @RequestParam(value = "cardTwoChar", required = false) String cardTwoChar,
                                    @RequestParam(value = "cardOneTime", required = false) Integer cardOneTime,
                                    @RequestParam(value = "cardTwoTime", required = false) Integer cardTwoTime,
                                    @RequestParam(value = "groupChar", required = false) String groupChar) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(cardOneChar) || IsObjectNullUtils.is(cardTwoChar) || IsObjectNullUtils.is(cardOneTime)
                || IsObjectNullUtils.is(cardTwoTime) || IsObjectNullUtils.is(groupChar)) {
            result.put("code", "205");
            result.put("msg", "参数获取失败");
            return result;
        }
        XydSentenceGroupTest sentenceGroupTest = new XydSentenceGroupTest();
        sentenceGroupTest.setCardTwoChar(cardTwoChar);
        sentenceGroupTest.setCardOneChar(cardOneChar);
        sentenceGroupTest.setCardOneTime(cardOneTime);
        sentenceGroupTest.setCardTwoTime(cardTwoTime);
        sentenceGroupTest.setGroupChar(groupChar);
        Map<String, Object> upload = FileUtils.sentenceGroup(request);
        if (upload.size() > 0) {
            List<String> startSlideshow = (List<String>) upload.get("startSlideshow");
            if (jiaoyan(startSlideshow)) {
                String str1 = "";
                for (String str : startSlideshow) {
                    str1 += str+",";
                }
                sentenceGroupTest.setStartSlideshow(str1);
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (jiaoyan(cardOneImage)) {
                for (String str : cardOneImage) {
                    sentenceGroupTest.setCardOneImage(str);
                }
            }
            List<String> cardoneRecord = (List<String>) upload.get("cardOneRecord");
            if (jiaoyan(cardoneRecord)) {
                for (String str : cardoneRecord) {
                    sentenceGroupTest.setCardOneRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (jiaoyan(cardTwoImage)) {
                for (String str : cardTwoImage) {
                    sentenceGroupTest.setCardTwoImage(str);
                }
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (jiaoyan(cardTwoRecord)) {
                for (String str : cardTwoRecord) {
                    sentenceGroupTest.setCardTwoRecord(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (jiaoyan(groupRecord)) {
                for (String str : groupRecord) {
                    sentenceGroupTest.setGroupRecord(str);
                }
            }
        }
        sentenceGroupTest.setCreateTime(new Date());
        sentenceGroupTest.setStates("1");
        int flag = sentenceGroupTestService.insert(sentenceGroupTest);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "添加成功");
        } else {
            result.put("code", "206");
            result.put("msg", "添加失败");
        }
        return result;
    }

    @RequestMapping(value = "/deleteSentencegroupTest")
    @ResponseBody
    public Map deleteSentencegroupTest(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        int flag = sentenceGroupTestService.delete(id);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "删除成功");
        } else {
            result.put("code", "206");
            result.put("msg", "删除失败");
        }
        return result;
    }

    @RequestMapping(value = "/updateSentenceGroupTest")
    @ResponseBody
    public Map updateSentencegroupTest(HttpServletRequest request, @RequestParam(value = "cardOneChar", required = false) String cardOneChar,
                                       @RequestParam(value = "cardTwoChar", required = false) String cardTwoChar,
                                       @RequestParam(value = "cardOneTime", required = false) Integer cardOneTime,
                                       @RequestParam(value = "cardTwoTime", required = false) Integer cardTwoTime,
                                       @RequestParam(value = "groupChar", required = false) String groupChar,
                                       @RequestParam(value = "id", required = false) Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydSentenceGroupTest sentenceGroupTest = new XydSentenceGroupTest();
        sentenceGroupTest.setId(id);
        if (!IsObjectNullUtils.is(cardOneChar)) {
            sentenceGroupTest.setCardOneChar(cardOneChar);
        }
        if (!IsObjectNullUtils.is(cardTwoChar)) {
            sentenceGroupTest.setCardTwoChar(cardTwoChar);
        }
        if (!IsObjectNullUtils.is(cardOneTime)) {
            sentenceGroupTest.setCardOneTime(cardOneTime);
        }
        if (!IsObjectNullUtils.is(cardTwoTime)) {
            sentenceGroupTest.setCardTwoTime(cardTwoTime);
        }
        if (!IsObjectNullUtils.is(groupChar)) {
            sentenceGroupTest.setGroupChar(groupChar);
        }
        Map<String, Object> upload = FileUtils.sentenceGroup(request);
        if (upload.size() > 0) {
            List<String> startSlideshow = (List<String>) upload.get("startSlideshow");
            if (jiaoyan(startSlideshow)) {
                String str1 = "";
                for (String str : startSlideshow) {
                    str1 += str+",";
                }
                sentenceGroupTest.setStartSlideshow(str1);
            }
            List<String> cardOneImage = (List<String>) upload.get("cardOneImage");
            if (jiaoyan(cardOneImage)) {
                for (String str : cardOneImage) {
                    sentenceGroupTest.setCardOneImage(str);
                }
            }
            List<String> cardoneRecord = (List<String>) upload.get("cardOneRecord");
            if (jiaoyan(cardoneRecord)) {
                for (String str : cardoneRecord) {
                    sentenceGroupTest.setCardOneRecord(str);
                }
            }
            List<String> cardTwoImage = (List<String>) upload.get("cardTwoImage");
            if (jiaoyan(cardTwoImage)) {
                for (String str : cardTwoImage) {
                    sentenceGroupTest.setCardTwoImage(str);
                }
            }
            List<String> cardTwoRecord = (List<String>) upload.get("cardTwoRecord");
            if (jiaoyan(cardTwoRecord)) {
                for (String str : cardTwoRecord) {
                    sentenceGroupTest.setCardTwoRecord(str);
                }
            }
            List<String> groupRecord = (List<String>) upload.get("groupRecord");
            if (jiaoyan(groupRecord)) {
                for (String str : groupRecord) {
                    sentenceGroupTest.setGroupRecord(str);
                }
            }
        }
        sentenceGroupTest.setUpdateTime(new Date());
        int flag = sentenceGroupTestService.update(sentenceGroupTest);
        if (flag != 0) {
            result.put("code", "200");
            result.put("msg", "修改成功");
        } else {
            result.put("code", "206");
            result.put("msg", "修改失败");
        }
        return result;
    }

    @RequestMapping(value = "/sentencegroupTestDetail")
    @ResponseBody
    public Map sentencegroupTestDetail(HttpServletRequest request, @RequestParam(required = false, value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (IsObjectNullUtils.is(id)) {
            result.put("code", "205");
            result.put("msg", "id 获取失败");
            return result;
        }
        XydSentenceGroupTest sentenceGroupTest = sentenceGroupTestService.selectById(id);
        result.put("data", sentenceGroupTest);
        result.put("status", 200);
        result.put("msg", "查询成功");
        return result;
    }

}
