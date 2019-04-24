package com.xfkj.manage;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xfkj.common.config.Global;
import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.emun.PCDIMustType;
import com.xfkj.common.emun.PCDIMustWordEnum;
import com.xfkj.common.emun.PCDIOptionalType;
import com.xfkj.common.model_custom.DataTableReturnData;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.web.BaseController;
import com.xfkj.mapper.XydAnswerRecordMapper;
import com.xfkj.model.*;
import com.xfkj.model.model_extends.XyydAnswerRecordExtends;
import com.xfkj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by mai xiaogang on 2018/10/17.
 * pcdi问卷操作
 */
@Controller
@RequestMapping(value = "/manage/roll")
public class QuestionController extends BaseController{
    @Autowired
    private XydPcidMustService xydPcidMustService;
    @Autowired
    private XydPcidMustVocabularyService pcidMustVocabularyService;
    @Autowired
    private XydPcdiTypeService xydPcdiTypeService;
    @Autowired
    private XydPcidOptionalService xydPcidOptionalService;
    @Autowired
    private XydAnswerRecordService xydAnswerRecordService;
    @Autowired
    private XydAbcQuestionnaireService abcQuestionnaireService;
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    private XydResultScaleService xydResultScaleService;
    @Autowired
    Global global;

    /**
     * 跳转到pcdi必做问卷列表页
     */
    @RequestMapping(value = "/toPcdiList")
    public String toPcdiList(Model model){
        model.addAttribute("typeList", PCDIMustType.getEnumDescList());
        return "/demo/questionnaire/pcdiMustList";
    }

    /**
     * 跳转到pcdi必做问卷详情页、添加页
     */
    @RequestMapping(value = "/toPcdiAddPage")
    public String toPcdiInfo(Model model, @RequestParam(value = "ids", required = false)String ids){
        if (!IsObjectNullUtils.is(ids)){
            String [] idArr = ids.split(",");
            if (!IsObjectNullUtils.is(idArr) && idArr.length > 1){
                if ("1".equals(idArr[1])){
                    XydPcidMustVocabulary xydPcidMustVocabulary = pcidMustVocabularyService.selectByPrimaryKey(Integer.parseInt(idArr[0]));
                    if (!IsObjectNullUtils.is(xydPcidMustVocabulary)){
                        XydPcidType xydPcidType = xydPcdiTypeService.selectByPrimaryKey(xydPcidMustVocabulary.getPcdiTypeId());
                        model.addAttribute("pcidType", xydPcidType);
                        String [] ansers = xydPcidMustVocabulary.getTopicResultOne().split("\\|");
                        model.addAttribute("answers", ansers);
                    }
                    model.addAttribute("pcdi", xydPcidMustVocabulary);
                }else {
                     XydPcidMust xydPcidMust = xydPcidMustService.selectByPrimaryKey(Integer.parseInt(idArr[0]));
                    if (!IsObjectNullUtils.is(xydPcidMust)){
                        XydPcidType xydPcidType = xydPcdiTypeService.selectByPrimaryKey(xydPcidMust.getPcdiTypeId());
                        model.addAttribute("pcidType", xydPcidType);
                        if (!IsObjectNullUtils.is(xydPcidMust.getTopicResultOne())){
                            String [] ansers = xydPcidMust.getTopicResultOne().split("\\|");
                            model.addAttribute("answers", ansers);
                        }
                        if (!IsObjectNullUtils.is(xydPcidMust.getChildOptions())){
                            String [] ChildOptions = xydPcidMust.getChildOptions().split(",");
                            model.addAttribute("ChildOptions", ChildOptions);
                        }


                    }

                    model.addAttribute("pcdi", xydPcidMust);
                }
                model.addAttribute("ids", idArr[1]);
            }
        }
        //获取pcdi必填类型列表
        model.addAttribute("typeList", PCDIMustType.getEnumDescList());
        //获取词汇类型列表
        model.addAttribute("vocabularyTypeList", PCDIMustWordEnum.getEnumDescList());
        return "/demo/questionnaire/pcdiMustInfo";
    }


    /**
     * pcdi必做问卷修改或添加
     */
    @RequestMapping(value = "/addPcdi/Must", method = RequestMethod.POST)
    @ResponseBody
    public Map addPcdiMust(HttpServletResponse response, @RequestParam(value = "id", required = false)Integer id,
                           @RequestParam(value = "nameEnum")Integer nameEnum,
                           @RequestParam(value = "vabType", required = false)String vabType,
                           @RequestParam(value = "sort", required = false)Integer sort,
                           @RequestParam(value = "topicTitle")String topicTitle,
                           @RequestParam(value = "describes")String describes,
                           @RequestParam(value = "topicResult")String topicResult,
                           @RequestParam(value = "isOtherOptions", required = false)String isOtherOptions,
                           @RequestParam(value = "childOptions", required = false)String childOptions,
                           @RequestParam(value = "score")String score){

        Map<String, Object> result = new HashMap<>();
        XydPcidType xydPcidType = new XydPcidType();
        xydPcidType.setIsOptional("1");
        xydPcidType.setNameEnum(nameEnum);
        xydPcidType.setStates("1");
        List<XydPcidType> list = xydPcdiTypeService.selectByList(xydPcidType);
        if (!IsObjectNullUtils.is(list) && list.size() > 0){
            xydPcidType = list.get(0);
        }else {
            xydPcidType.setCreateTime(new Date());
            xydPcidType.setName(PCDIMustType.getEnum(nameEnum).getDesc());
            xydPcidType.setSort(nameEnum);
            if (xydPcdiTypeService.insertSelective(xydPcidType) < 0){
                result.put("code", ResultStant.RESULT_CODE_ERROR);
                result.put("msg", "添加失败！");
                result.put("data", "");
                return result;
            }
        }
        if (!IsObjectNullUtils.is(id)){
            //更新
            if (nameEnum == 5){  //更新pcdi词汇
                XydPcidMustVocabulary xydPcidMustVocabulary = pcidMustVocabularyService.selectByPrimaryKey(id);
                if (!IsObjectNullUtils.is(xydPcidMustVocabulary)){
                    XydPcidMustVocabulary pcidMustVocabulary = new XydPcidMustVocabulary();
                    pcidMustVocabulary.setPcdiTypeId(xydPcidType.getId());
                    pcidMustVocabulary.setId(xydPcidMustVocabulary.getId());
                    pcidMustVocabulary.setType(vabType);
                    pcidMustVocabulary.setSort(sort);
                    pcidMustVocabulary.setIsScore(score);
                    pcidMustVocabulary.setTopicTitle(topicTitle);
                    pcidMustVocabulary.setUpdateTime(new Date());
                    pcidMustVocabulary.setTopicResultOne(topicResult);
                    if (pcidMustVocabularyService.updateByPrimaryKeySelective(pcidMustVocabulary) > 0){
                        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                        result.put("msg", "更新成功！");
                        result.put("data", "");
                        return result;
                    }
                }
            }else { //更新pcdi其他
                XydPcidMust xydPcidMust = xydPcidMustService.selectByPrimaryKey(id);
                if (!IsObjectNullUtils.is(xydPcidMust)){
                    XydPcidMust pcidMust = new XydPcidMust();
                    pcidMust.setPcdiTypeId(xydPcidType.getId());
                    pcidMust.setId(xydPcidMust.getId());
                    pcidMust.setDescribes(describes);
                    pcidMust.setTopicTitle(topicTitle);
                    pcidMust.setSort(sort);
                    pcidMust.setTopicResultOne(topicResult);
                    pcidMust.setIsOtherOptions(isOtherOptions == null ? "2" : "1");
                    pcidMust.setChildOptions(isOtherOptions == null ? "" : isOtherOptions);
                    pcidMust.setIsScore(score);
                    pcidMust.setUpdateTime(new Date());
                    if (xydPcidMustService.updateByPrimaryKeySelective(pcidMust) > 0){
                        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                        result.put("msg", "更新成功！");
                        result.put("data", "");
                        return result;
                    }
                }
            }
            result.put("code", ResultStant.RESULT_CODE_ERROR);
            result.put("msg", "更新失败！");
            result.put("data", "");
            return result;
        }else {
            if (nameEnum == 5){  //添加pcdi词汇
                XydPcidMustVocabulary pcidMustVocabulary = new XydPcidMustVocabulary();
                pcidMustVocabulary.setPcdiTypeId(xydPcidType.getId());
                pcidMustVocabulary.setType(vabType);
                pcidMustVocabulary.setIsScore(score);
                pcidMustVocabulary.setSort(sort);
                pcidMustVocabulary.setTopicTitle(topicTitle);
                pcidMustVocabulary.setCreateTime(new Date());
                pcidMustVocabulary.setStates("1");
                pcidMustVocabulary.setTopicResultOne(topicResult);
                if (pcidMustVocabularyService.insertSelective(pcidMustVocabulary) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "添加成功！");
                    result.put("data", "");
                    return result;
                }
            }else {
                //添加
                XydPcidMust pcidMust = new XydPcidMust();
                pcidMust.setPcdiTypeId(xydPcidType.getId());
                pcidMust.setDescribes(describes);
                pcidMust.setTopicTitle(topicTitle);
                pcidMust.setSort(sort);
                pcidMust.setTopicResultOne(topicResult);
                pcidMust.setIsOtherOptions(isOtherOptions == null ? "2" : "1");
                pcidMust.setChildOptions(isOtherOptions == null ? "" : isOtherOptions);
                pcidMust.setIsScore(score);
                pcidMust.setCreateTime(new Date());
                pcidMust.setStates("1");
                if (xydPcidMustService.insertSelective(pcidMust) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "添加成功！");
                    return result;
                }
            }
            result.put("code", ResultStant.RESULT_CODE_ERROR);
            result.put("data", "");
            result.put("msg", "添加失败！");
            return result;
        }
    }


    /**
     * pcdi必做题列表
     */
    @ResponseBody
    @RequestMapping(value = "/getpcdiMustList.ajax")
    public DataTableReturnData getIntroduceList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydPcidMust> dataTableReturnData = new DataTableReturnData();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("topictitle"))) {
            queryParam.fill("topictitle",  "%"+params.get("topictitle")+"%");
        }
        if (!IsObjectNullUtils.is(params.get("type"))) {
            queryParam.fill("nameenum",  params.get("type"));
        }
        queryParam.fill("states",  "1");
        int count = xydPcidMustService.selectByParamCount(queryParam);
        List<XydPcidMust> xydPcidMustList = xydPcidMustService.selectByParamList(queryParam);
        dataTableReturnData.setData(xydPcidMustList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }


    /**
     * 删除pcdi必做问卷题
     */
    @ResponseBody
    @RequestMapping(value = "/toPcdidelete")
    public Map toPcdidelete( @RequestParam(value = "ids")String ids){
        Map<String, Object> resultMap = new HashMap();
        if (!IsObjectNullUtils.is(ids)){
            String [] idArr = ids.split(",");
            if (!IsObjectNullUtils.is(idArr) && idArr.length > 1){
                if ("1".equals(idArr[1])){
                    XydPcidMustVocabulary xydPcidMustVocabulary = pcidMustVocabularyService.selectByPrimaryKey(Integer.parseInt(idArr[0]));
                    if (!IsObjectNullUtils.is(xydPcidMustVocabulary)){
                        XydPcidMustVocabulary deleteObject = new XydPcidMustVocabulary();
                        deleteObject.setId(xydPcidMustVocabulary.getId());
                        deleteObject.setStates("2");
                        if (pcidMustVocabularyService.updateByPrimaryKeySelective(deleteObject) > 0){
                            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                            resultMap.put("msg", "删除成功！");
                        }else {
                            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                            resultMap.put("msg", "删除失败！");
                        }
                    }else {
                        resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                        resultMap.put("msg", "无法获取该题信息！");
                    }
                }else {
                    XydPcidMust xydPcidMust = xydPcidMustService.selectByPrimaryKey(Integer.parseInt(idArr[0]));
                    if (!IsObjectNullUtils.is(xydPcidMust)){
                        XydPcidMust deleteObject = new XydPcidMust();
                        deleteObject.setId(xydPcidMust.getId());
                        deleteObject.setStates("2");
                        if (xydPcidMustService.updateByPrimaryKeySelective(deleteObject) > 0){
                            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                            resultMap.put("msg", "删除成功！");

                        }else {
                            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                            resultMap.put("msg", "删除失败！");
                        }
                    }else {
                        resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                        resultMap.put("msg", "无法获取该题信息！");
                    }
                }
                return resultMap;
             }else {
                resultMap.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                resultMap.put("msg", "系统异常，请稍后重试！");
                return resultMap;
            }
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_UNUSUAL);
            resultMap.put("msg", "无法获取相关信息，请刷新重试！");
            return resultMap;
        }
    }

    /**
     * 跳转到pcdi选做题列表页
     * @param model
     * @return
     */
    @RequestMapping(value = "/toPcdiSelectList")
    public String toPcdiSelectList(Model model){
        model.addAttribute("typeList", PCDIOptionalType.getEnumDescList());
        return "/demo/questionnaire/pcdiSelectList";
    }

    /**
     * pcdi选做题列表
     */
    @ResponseBody
    @RequestMapping(value = "/getpcdiSelectList.ajax")
    public DataTableReturnData getpcdiSelectList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydPcidOptional> dataTableReturnData = new DataTableReturnData();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("topictitle"))) {
            queryParam.fill("topictitle",  "%"+params.get("topictitle")+"%");
        }
        if (!IsObjectNullUtils.is(params.get("type"))) {
            queryParam.fill("nameenum",  params.get("type"));
        }
        queryParam.fill("states",  "1");
        //1 必做 2 选做
        queryParam.fill("isoptional",  "2");
        int count = xydPcidOptionalService.selectByParamCount(queryParam);
        List<XydPcidOptional> xydPcidOptionalList = xydPcidOptionalService.selectByParamList(queryParam);
        dataTableReturnData.setData(xydPcidOptionalList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     * pcdi选做题删除
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/toPcdiSelectDelete")
    public Map PcdiSelectDelete(Model model, @RequestParam(value = "id")Integer id){
        Map<String, Object> result = new HashMap<>();
        if (IsObjectNullUtils.is(id)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "无法获取该题的id");
            return result;
        }
        XydPcidOptional xydPcidOptional = xydPcidOptionalService.selectByPrimaryKey(id);
        if (!IsObjectNullUtils.is(xydPcidOptional)){
            XydPcidOptional optionalUpdate = new XydPcidOptional();
            optionalUpdate.setId(xydPcidOptional.getId());
            optionalUpdate.setStates("2");
            if (xydPcidOptionalService.updateByPrimaryKeySelective(optionalUpdate) > 0){
                result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                result.put("msg", "删除成功！");
                return result;
            }
        }
        result.put("code", ResultStant.RESULT_CODE_ERROR);
        result.put("msg", "操作失败");
        return result;
    }

    /**
     * pcdi选做题添加操作
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/PcdiSelectAdd")
    public Map toPcdiSelectAdd(Model model, @RequestParam(value = "type")Integer type,
                               @RequestParam(value = "name")String name,
                               @RequestParam(value = "id", required = false)Integer id){
        Map<String, Object> result = new HashMap<>();
        PCDIOptionalType optionalType = PCDIOptionalType.getEnum(type);
        List<XydPcidType> list = new ArrayList<>();
        if (!IsObjectNullUtils.is(optionalType)){
            XydPcidType xydPcidType = new XydPcidType();
            xydPcidType.setIsOptional("2");
            xydPcidType.setStates("1");
            xydPcidType.setNameEnum(type);
            list = xydPcdiTypeService.selectByList(xydPcidType);
            if (IsObjectNullUtils.is(list) || list.size() < 1){
                xydPcidType.setName(optionalType.getDesc());
                xydPcidType.setCreateTime(new Date());
                xydPcidType.setSort(type);
               if ( xydPcdiTypeService.insertSelective(xydPcidType) < 1){
                   result.put("code", ResultStant.RESULT_CODE_LOSE);
                   result.put("msg", "该类型添加失败！");
                   return result;
               }
               list.add(xydPcidType);
            }
        }else {
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "请选择有效的类型！");
            return result;
        }
        //判断是否已存在
        XydPcidOptional addObject = new XydPcidOptional();
        addObject.setTopicTitle(name);
        addObject.setPcdiTypeId(list.get(0).getId());
        addObject.setStates("1");
        List<XydPcidOptional> optionalList = xydPcidOptionalService.selectList(addObject);
        if (!IsObjectNullUtils.is(optionalList) && optionalList.size() >0){
            result.put("code", ResultStant.RESULT_CODE_ERROR);
            result.put("msg", name+"词已存在，不能重复添加！");
            return result;
        }
        if (!IsObjectNullUtils.is(id)){
            XydPcidOptional xydPcidOptional = xydPcidOptionalService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydPcidOptional)){
                xydPcidOptional.setTopicTitle(name);
                xydPcidOptional.setPcdiTypeId(list.get(0).getId());
                xydPcidOptional.setUpdateTime(new Date());
                if (xydPcidOptionalService.updateByPrimaryKeySelective(xydPcidOptional) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "更新成功！");
                    return result;
                }
            }else {
                result.put("code", ResultStant.RESULT_CODE_ERROR);
                result.put("msg", "操作异常，请刷新重试！");
                return result;
            }
        }else {
            XydPcidOptional xydPcidOptional = new XydPcidOptional();
            xydPcidOptional.setTopicTitle(name);
            xydPcidOptional.setPcdiTypeId(list.get(0).getId());
            xydPcidOptional.setUpdateTime(new Date());
            xydPcidOptional.setCreateTime(new Date());
            xydPcidOptional.setStates("1");
            xydPcidOptional.setTopicResultOne("不会说");
            xydPcidOptional.setTopicResultTwo("会说");
            if (xydPcidOptionalService.insertSelective(xydPcidOptional) > 0){
                result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                result.put("msg", "添加成功！");
                return result;
            }
        }
        result.put("code", ResultStant.RESULT_CODE_ERROR);
        result.put("msg", "操作失败！");
        return result;
    }

    /**
     * 跳转到abc问卷列表
     * @return
     */
    @RequestMapping(value = "/toAbcListPage")
    public String toAbcPage(){
        return "/demo/questionnaire/abcList";
    }


    /**
     *abc问卷列表
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAbcList.ajax")
    public DataTableReturnData getAbcList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydAbcQuestionnaire> dataTableReturnData = new DataTableReturnData();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("topictitle"))) {
            queryParam.fill("topictitle",  "%"+params.get("topictitle")+"%");
        }
        queryParam.fill("states",  "1");
        int count = abcQuestionnaireService.selectByParamCount(queryParam);
        List<XydAbcQuestionnaire> xydAbcQuestionnaireList = abcQuestionnaireService.selectByParamList(queryParam);
        dataTableReturnData.setData(xydAbcQuestionnaireList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     * 删除abc问卷题
     * @return
     */
    @RequestMapping(value = "/toAbcDelete")
    @ResponseBody
    public Map toAbcDelete(@RequestParam(value = "id")Integer id){
        Map<String, Object> result = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            XydAbcQuestionnaire xydAbcQuestionnaire = abcQuestionnaireService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydAbcQuestionnaire)){
                XydAbcQuestionnaire abc = new XydAbcQuestionnaire();
                abc.setStates("2");
                abc.setId(xydAbcQuestionnaire.getId());
                if (abcQuestionnaireService.updateByPrimaryKeySelective(abc) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "成功删除！");
                    return result;
                }
            }
        }
        result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
        result.put("msg", "删除失败！");
        return result;
    }
    /**
     * 添加abc问卷题
     * @return
     */
    @RequestMapping(value = "/toAbcAdd")
    @ResponseBody
    public Map toAbcAdd(@RequestParam(value = "id",required = false)Integer id,
                           @RequestParam(value = "name")String name,
                           @RequestParam(value = "sort")Integer sort){
        Map<String, Object> result = new HashMap<>();
        if (IsObjectNullUtils.is(name.trim())){
            result.put("code", ResultStant.RESULT_CODE_ERROR);
            result.put("msg", "题目不能为空!");
            return result;
        }
        if (sort == null){
            result.put("code", ResultStant.RESULT_CODE_ERROR);
            result.put("msg", "题号不能为空!");
            return result;
        }
        if (!IsObjectNullUtils.is(id)){
            XydAbcQuestionnaire questionnaire = abcQuestionnaireService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(questionnaire)){
                XydAbcQuestionnaire sortAbcQuestionnaire = new XydAbcQuestionnaire();
                sortAbcQuestionnaire.setStates("1");
                sortAbcQuestionnaire.setSort(sort);
                List<XydAbcQuestionnaire> listSort = abcQuestionnaireService.selectByEntityList(sortAbcQuestionnaire);
                if (!IsObjectNullUtils.is(listSort) && listSort.size() > 0 && listSort.get(0).getId() != questionnaire.getId()){
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", "序号已存在，不能重复添加!");
                    return result;
                }
                XydAbcQuestionnaire xydAbcQuestionnaire = new XydAbcQuestionnaire();
                xydAbcQuestionnaire.setTopicTitle(name);
                xydAbcQuestionnaire.setStates("1");
                List<XydAbcQuestionnaire> list = abcQuestionnaireService.selectByEntityList(xydAbcQuestionnaire);
                if (!IsObjectNullUtils.is(list) && list.size() > 0 && list.get(0).getId() != questionnaire.getId()){
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", "题目已存在，不能重复添加!");
                    return result;
                }
                questionnaire.setTopicTitle(name);
                questionnaire.setSort(sort);
                questionnaire.setUpdateTime(new Date());
                if (abcQuestionnaireService.updateByPrimaryKeySelective(questionnaire) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "更新成功!");
                    return result;
                }
            }
        }else {
            XydAbcQuestionnaire sortAbcQuestionnaire = new XydAbcQuestionnaire();
            sortAbcQuestionnaire.setStates("1");
            sortAbcQuestionnaire.setSort(sort);
            List<XydAbcQuestionnaire> listSort = abcQuestionnaireService.selectByEntityList(sortAbcQuestionnaire);
            if (!IsObjectNullUtils.is(listSort) && listSort.size() > 0){
                result.put("code", ResultStant.RESULT_CODE_ERROR);
                result.put("msg", "序号已存在，不能重复添加!");
                return result;
            }
            XydAbcQuestionnaire xydAbcQuestionnaire = new XydAbcQuestionnaire();
            xydAbcQuestionnaire.setTopicTitle(name);
            xydAbcQuestionnaire.setStates("1");
            List<XydAbcQuestionnaire> list = abcQuestionnaireService.selectByEntityList(xydAbcQuestionnaire);
            if (!IsObjectNullUtils.is(list) && list.size() > 0 ){
                result.put("code", ResultStant.RESULT_CODE_ERROR);
                result.put("msg", "题目已存在，不能重复添加!");
                return result;
            }
            xydAbcQuestionnaire.setSort(sort);
            xydAbcQuestionnaire.setCreateTime(new Date());
            if (abcQuestionnaireService.insertSelective(xydAbcQuestionnaire) > 0){
                result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                result.put("msg", "添加成功!");
                return result;
            }
        }
        result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
        result.put("msg", "操作失败!");
        return result;
    }

    /**
     * 查看问卷记录
     */
    @RequestMapping(value = "/topcdiAndabc/resultPage")
    public String toResultPage(){
        return "/demo/questionnaire/pcdiAndabcResultList";
    }


    /**
     *问卷结果列表
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getresultList.ajax")
    public DataTableReturnData getresultList(@RequestParam Map<String, String> params){
        DataTableReturnData<XyydAnswerRecordExtends> dataTableReturnData = new DataTableReturnData();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("name"))) {
            queryParam.fill("name",  "%"+params.get("name")+"%");
        }
        if (!IsObjectNullUtils.is(params.get("sex"))) {
            queryParam.fill("sex",  params.get("sex"));
        }
        if (!IsObjectNullUtils.is(params.get("type"))) {
            queryParam.fill("type",  params.get("type"));
        }
        if (!IsObjectNullUtils.is(params.get("phone"))) {
            queryParam.fill("phone",  params.get("phone"));
        }
        if (!IsObjectNullUtils.is(params.get("birthdate"))) {
            queryParam.fill("birthdate",  params.get("birthdate"));
        }
        if (!IsObjectNullUtils.is(params.get("valid"))) {
            queryParam.fill("valid",  params.get("valid"));
        }
        int count = xydAnswerRecordService.selectByParamCount(queryParam);
        List<XyydAnswerRecordExtends> extendsList = xydAnswerRecordService.selectByParamList(queryParam);
        dataTableReturnData.setData(extendsList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    @RequestMapping("/topcdiAndabc/resultInfoPage")
    public String resultInfoPage(@RequestParam(value = "id")Integer id, Model model){
        model.addAttribute("id", id);
        return "/demo/questionnaire/resultDetail";
    }



    /**
     * 查看问卷答题记录
     */
    @RequestMapping(value = "/topcdiAndabc/resultInfo")
    public String toResultPage(Model model, HttpServletResponse response, @RequestParam(value = "id", required = false)Integer id){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        Map<String, Object> result = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            XydAnswerRecord xydAnswerRecord = xydAnswerRecordService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydAnswerRecord)){
                XydParents xydParents = xydParentsService.selectByPrimaryKey(xydAnswerRecord.getParentsId());
                if ("1".equals(xydAnswerRecord.getType())){
                    //获取所有pcdi必做问卷类型
                    XydPcidType xydPcidType = new XydPcidType();
                    xydPcidType.setStates("1");
                    xydPcidType.setIsOptional("1");
                    List<XydPcidType> typeList = xydPcdiTypeService.selectByTypeSortList(xydPcidType);
                    //封装需要的数据
                    List<Map<String, Object>> dataList = new ArrayList<>();
                    for (XydPcidType pcidType : typeList) {
                        if (IsObjectNullUtils.is(pcidType)){
                            continue;
                        }
                        Map<String, Object> dataMap = new HashMap<>();
                        List list = xydPcidMustService.selectByTypeAndOutLis(pcidType, xydAnswerRecord, xydParents);
                        if (IsObjectNullUtils.is(list) || list.size() <1)
                            continue;
                        dataMap.put("pcidType", pcidType);
                        dataMap.put("pcidList", list);
                        dataList.add(dataMap);
                    }
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "pcid必做");
                    Map<String, Object> resultDate = new HashMap<>();
                    resultDate.put("dataList", dataList);
                    resultDate.put("type", "1");
                    model.addAttribute("data", resultDate);
                }

                if ("2".equals(xydAnswerRecord.getType())){
                    Map<String, Object> data = new HashMap<>();
                    XydPcidType xydPcidType = new XydPcidType();
                    xydPcidType.setStates("1");
                    xydPcidType.setIsOptional("2");
                    //获取所有pcdi选做问卷类型
                    List<XydPcidType> typeList = xydPcdiTypeService.selectByTypeSortList(xydPcidType);
                    //封装每种类型的数据
                    List<Map<String, Object>> dataList = new ArrayList<>();
                    for (XydPcidType pcidType : typeList) {
                        Map<String, Object> dataMap = new HashMap<>();
                        if (!IsObjectNullUtils.is(pcidType)){
                            XydPcidOptional xydPcidOptional = new XydPcidOptional();
                            xydPcidOptional.setStates("1");
                            xydPcidOptional.setPcdiTypeId(pcidType.getId());
                            List<XydPcidOptional> list = xydPcidOptionalService.selectByTypeAndOutLis(pcidType, xydAnswerRecord, xydParents);
                            if (IsObjectNullUtils.is(list) || list.size() <1)
                                continue;
                            dataMap.put("pcidType", pcidType);
                            dataMap.put("pcidList", list);
                            dataList.add(dataMap);
                        }
                    }
                    data.put("optional", dataList);

                    //获取所有pcdi必做问卷类型
                    XydPcidType pcidType1 = new XydPcidType();
                    pcidType1.setStates("1");
                    pcidType1.setIsOptional("1");
                    List<XydPcidType> list1 = xydPcdiTypeService.selectByTypeSortList(pcidType1);
                    //封装需要的数据
                    List<Map<String, Object>> list2 = new ArrayList<>();
                    for (XydPcidType pcidType : list1) {
                        if (IsObjectNullUtils.is(pcidType)){
                            continue;
                        }
                        Map<String, Object> dataMap = new HashMap<>();
                        List list = xydPcidMustService.selectByTypeAndOutLis(pcidType, xydAnswerRecord, xydParents);
                        if (IsObjectNullUtils.is(list) || list.size() <1)
                            continue;
                        dataMap.put("pcidType", pcidType);
                        dataMap.put("pcidList", list);
                        list2.add(dataMap);
                    }
                    data.put("must", list2);
                    Map<String, Object> resultDate = new HashMap<>();
                    resultDate.put("dataList", data);
                    resultDate.put("type", "2");
                    model.addAttribute("data", resultDate);
                }

                if ("3".equals(xydAnswerRecord.getType())){
                    //获取所有Abc问卷类型
                   List<XydAbcQuestionnaire> list = abcQuestionnaireService.selectByListAndResult(xydAnswerRecord.getId());
                    Map<String, Object> resultDate = new HashMap<>();
                    resultDate.put("dataList", list);
                    resultDate.put("type", "3");
                    model.addAttribute("data", resultDate);
                }
            }
        }
        return "/demo/questionnaire/resultDetail";
    }

    /**
     * 查看问卷记录文案
     */
    @RequestMapping(value = "/topcdiAndabc/adviceInfo")
    public String adviceInfo(Model model, @RequestParam(value = "resultId", required = false)Integer resultId){
        if (!IsObjectNullUtils.is(resultId)){
            XydAnswerRecord xydAnswerRecord = xydAnswerRecordService.selectByPrimaryKey(resultId);
            model.addAttribute("advice", xydAnswerRecord);
        }
        return "/demo/questionnaire/adviceView";
    }

    /**
     * 更新问卷文案建议
     */
    @ResponseBody
    @RequestMapping(value = "/topcdiAndabc/adviceupdate")
    public Map adviceupdate(Model model, @RequestParam(value = "id", required = false)Integer id,
                               @RequestParam(value = "topicTitle", required = false)String topicTitle,
                               @RequestParam(value = "sentenceTitle", required = false)String sentenceTitle,
                               @RequestParam(value = "vocabulary", required = false)String vocabulary){
        Map<String, Object> result = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            XydAnswerRecord xydAnswerRecord = xydAnswerRecordService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydAnswerRecord)){
                XydAnswerRecord answerRecord = new XydAnswerRecord();
                answerRecord.setId(xydAnswerRecord.getId());
                if ("3".equals(xydAnswerRecord.getType())){
                    if (IsObjectNullUtils.is(topicTitle)){
                        result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                        result.put("msg", "建议不能为空！");
                        return result;
                    }
                    answerRecord.setTitle(topicTitle);
                }else {
                    if (IsObjectNullUtils.is(vocabulary) || IsObjectNullUtils.is(sentenceTitle)){
                        result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                        result.put("msg", "建议不能为空！");
                        return result;
                    }
                    answerRecord.setTitle(sentenceTitle);
                    answerRecord.setVocabulary(vocabulary);
                }
                if (xydAnswerRecordService.updateByPrimaryKeySelective(answerRecord) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "更新成功！");
                }else {
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", "更新失败！");
                }
                return result;
            }
        }
        result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
        result.put("msg", "无法获取问卷报告信息！");
        return result;
    }

    /**
     * 跳转文案建议列表设置
     */
    @RequestMapping(value = "/toWordListPage")
    public String toWordListPage(){
        return "/demo/questionnaire/wordList";
    }

    /**
     * 文案建议列表设置
     */
    @RequestMapping(value = "/toWordList.ajax")
    @ResponseBody
    public DataTableReturnData toWordList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydResultScale> dataTableReturnData = new DataTableReturnData();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("type"))) {
            queryParam.fill("type",  params.get("type"));
        }
        if (!IsObjectNullUtils.is(params.get("valid"))) {
            queryParam.fill("valid",  params.get("valid"));
        }
        queryParam.fill("states",  "1");
        int count = xydResultScaleService.selectByParamCount(queryParam);
        List<XydResultScale> xydResultScaleList = xydResultScaleService.selectByParamList(queryParam);
        dataTableReturnData.setData(xydResultScaleList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }
    /**
     * 跳转文案建议详情页
     */
    @RequestMapping(value = "/toWordInfoPage")
    public String toWordInfoPage(@RequestParam(value = "id",required = false)Integer id, Model model){
        if (!IsObjectNullUtils.is(id)){
            XydResultScale xydResultScale = xydResultScaleService.selectByPrimaryKey(id);
            model.addAttribute("word", xydResultScale);
        }
        model.addAttribute("defaultAgeMax", global.getDefaultAgeMax());
        model.addAttribute("defaultAgeMin", global.getDefaultAgeMin());

        return "/demo/questionnaire/wordInfo";
    }

    /**
     * 删除文案建议
     */
    @RequestMapping(value = "/wordDelete")
    @ResponseBody
    public Map wordDelete(@RequestParam(value = "id")Integer id){
        Map<String, Object> result = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            XydResultScale xydResultScale = xydResultScaleService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydResultScale)){
                XydResultScale resultScale = new XydResultScale();
                resultScale.setId(xydResultScale.getId());
                resultScale.setStates("2");
                if (xydResultScaleService.updateByPrimaryKeySelective(resultScale) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "删除成功！");
                    result.put("data","");
                    return result;
                }else {
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", "删除失败！");
                    result.put("data","");
                    return result;
                }
            }
        }
        result.put("code", ResultStant.RESULT_CODE_SERVICE);
        result.put("msg", "系统异常！");
        result.put("data","");
        return result;
    }

    /**
     * 添加、修改文案建议
     */
    @RequestMapping(value = "/wordAdd", method = RequestMethod.POST)
    @ResponseBody
    public Map wordAdd(@RequestParam(value = "id",required = false)Integer id,
                       @RequestParam(value = "type")String type,
                       @RequestParam(value = "isOptional")String isOptional,
                       @RequestParam(value = "topicType", required = false)String topicType,
                       @RequestParam(value = "more", required = false)String more,
                       @RequestParam(value = "state")Integer state,
                       @RequestParam(value = "end")Integer end,
                       @RequestParam(value = "content")String content,
                       @RequestParam(value = "sex", required = false)String sex){
        Map<String, Object> result = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            //修改操作
            XydResultScale xydResultScale = xydResultScaleService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydResultScale)){
                XydResultScale resultScale = new XydResultScale();
                resultScale.setId(xydResultScale.getId());
                resultScale.setType(type);
                if (!IsObjectNullUtils.is(type) && type.equals("1")){
                    resultScale.setIsOptional(isOptional);
                    resultScale.setTopicType(topicType);
                }
                if (!IsObjectNullUtils.is(more))
                    resultScale.setMore(more);
                resultScale.setState(state);
                resultScale.setEnd(end);
                resultScale.setContent(content);
                if (!IsObjectNullUtils.is(sex))
                    resultScale.setSex(sex);
                resultScale.setUpdateTime(new Date());
                if (xydResultScaleService.updateByPrimaryKeySelective(resultScale) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "更新成功！");
                    result.put("data","");
                    return result;
                }else {
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", "更新失败！");
                    result.put("data","");
                    return result;
                }
            }
        }else {
            //添加操作
            XydResultScale resultScale = new XydResultScale();
            resultScale.setType(type);
            if (!IsObjectNullUtils.is(type) && type.equals("1")){
                resultScale.setIsOptional(isOptional);
                resultScale.setTopicType(topicType);
            }
            if (!IsObjectNullUtils.is(more))
                resultScale.setMore(more);
            resultScale.setState(state);
            resultScale.setEnd(end);
            resultScale.setContent(content);
            if (!IsObjectNullUtils.is(sex))
                resultScale.setSex(sex);
            resultScale.setCreateTime(new Date());
            resultScale.setStates("1");
            if (xydResultScaleService.insertSelective(resultScale) > 0){
                result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                result.put("msg", "添加成功！");
                result.put("data","");
                return result;
            }else {
                result.put("code", ResultStant.RESULT_CODE_ERROR);
                result.put("msg", "添加失败！");
                result.put("data","");
                return result;
            }
        }
        result.put("code", ResultStant.RESULT_CODE_SERVICE);
        result.put("msg", "系统异常！");
        result.put("data","");
        return result;
    }


}
