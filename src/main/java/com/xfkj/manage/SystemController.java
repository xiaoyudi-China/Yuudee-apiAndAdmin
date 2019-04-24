package com.xfkj.manage;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.model_custom.DataTableReturnData;
import com.xfkj.common.query.GenericQueryParam;

import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.web.BaseController;
import com.xfkj.model.*;
import com.xfkj.service.*;
import org.apache.commons.io.FileUtils;
import org.hamcrest.core.Is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * Created by mai xiaogang on 2018/10/16.
 * 系统配置
 */
@Controller
@RequestMapping("/manage/system")
public class SystemController extends BaseController {
    @Autowired
    private XydSystemImagesService xydSystemImagesService;
    @Autowired
    private XydIntroduceService xydIntroduceService;
    @Autowired
    private XydSystemVersionsService xydSystemVersionsService;
    @Autowired
    private XydAreaService xydAreaService;
    @Autowired
    private XydSystemSuggestService xydSystemSuggestService;

    /**
     * 上传图片
     */

    @RequestMapping(value = "/oss/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map fileUpload(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg", "上传成功！");
        result.put("data",  com.xfkj.common.utils.FileUtils.upload(request));
        return result;
    }

    /**
     * 启动页设置
     */
    @RequestMapping(value = "/toStrartPage")
    public String toStartPage() {
        return "/demo/system/startupPageList";
    }

    /**
     * 启动页详情
     */
    @RequestMapping(value = "/toStrartPageInfo")
    public String toStrartPageInfo(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (!IsObjectNullUtils.is(id)) {
            XydSystemImages xydSystemImages = xydSystemImagesService.selectByPrimaryKey(id);
            model.addAttribute("systemImage", xydSystemImages);
        }
        return "/demo/system/startupPageInfo";
    }

    /**
     * 启动页图列表
     */
    @ResponseBody
    @RequestMapping(value = "/getStartPageList.ajax")
    public DataTableReturnData getStartPageList(@RequestParam Map<String, String> params) {
        DataTableReturnData<XydSystemImages> dataTableReturnData = new DataTableReturnData<XydSystemImages>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));

        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("type"))) {
            queryParam.fill("Type", params.get("type"));
        }
        queryParam.fill("states", "1");
        int count = xydSystemImagesService.count(queryParam);
        List<XydSystemImages> xydSystemImagesList = xydSystemImagesService.select(queryParam);
        dataTableReturnData.setData(xydSystemImagesList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     * 启动页删除
     */
    @RequestMapping(value = "/strartPageDelete")
    @ResponseBody
    public Map strartPageDelete(@RequestParam(value = "id") Integer id) {
        Map<String, Object> resultMap = new HashMap<>();
        if (!IsObjectNullUtils.is(id)) {
            XydSystemImages xydSystemImages = xydSystemImagesService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydSystemImages)) {
                XydSystemImages systemImages = new XydSystemImages();
                systemImages.setStates("2");
                systemImages.setId(xydSystemImages.getId());
                if (xydSystemImagesService.updateByPrimaryKeySelective(systemImages) > 0) {
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "删除成功！");
                    return resultMap;
                } else {
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "操作失败！");
                    return resultMap;
                }
            }
        }
        resultMap.put("code", ResultStant.RESULT_CODE_NULL);
        resultMap.put("msg", "系统异常，请稍后重试！");
        return resultMap;
    }

    /**
     * 启动页添加
     */
    @RequestMapping(value = "/strartPageAdd", method = RequestMethod.POST)
    @ResponseBody
    public Map strartPageAdd(@RequestParam(value = "id", required = false) Integer id,
                             @RequestParam(value = "title") String title,
                             @RequestParam(value = "image") String image) {
        Map<String, Object> resultMap = new HashMap<>();
        if (IsObjectNullUtils.is(image) || IsObjectNullUtils.is(title)) {
            resultMap.put("code", ResultStant.RESULT_CODE_UNUSUAL);
            resultMap.put("msg", "请输入必要的信息！");
            return resultMap;
        }
        if (!IsObjectNullUtils.is(id)) {
            XydSystemImages xydSystemImages = xydSystemImagesService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydSystemImages)) {
                XydSystemImages systemImages = new XydSystemImages();
                systemImages.setId(xydSystemImages.getId());
                systemImages.setUpdateTime(new Date());
                systemImages.setTitle(title);
                systemImages.setImage(image);
                if (xydSystemImagesService.updateByPrimaryKeySelective(systemImages) > 0) {
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "更新成功！");
                    return resultMap;
                } else {
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "操作失败！");
                    return resultMap;
                }
            }
        } else {
            XydSystemImages systemImages = new XydSystemImages();
            systemImages.setCreateTime(new Date());
            systemImages.setTitle(title);
            systemImages.setImage(image);
            systemImages.setStates("1");
            systemImages.setType("1");
            if (xydSystemImagesService.insertSelective(systemImages) > 0) {
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("msg", "新增成功！");
                return resultMap;
            } else {
                resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                resultMap.put("msg", "添加失败！");
                return resultMap;
            }
        }
        resultMap.put("code", ResultStant.RESULT_CODE_NULL);
        resultMap.put("msg", "系统异常，请稍后重试！");
        return resultMap;
    }


    /**
     * 跳转到产品介绍列表
     */
    @RequestMapping(value = "/toIntroduceList")
    public String toIntroduceList() {
        return "/demo/system/introduceList";
    }

    /**
     * 跳转到产品编辑、添加页
     */
    @RequestMapping(value = "/toIntroduceInfo")
    public String toIntroduceInfo(Model model, @RequestParam(value = "id", required = false) Integer id) {
        XydSystemVersions xydSystemVersions = new XydSystemVersions();
        xydSystemVersions.setStates("1");
        if (!IsObjectNullUtils.is(id)) {
            XydIntroduce xydIntroduce = xydIntroduceService.selectByPrimaryKey(id);
            model.addAttribute("introduce", xydIntroduce);
        }
       /* List<XydSystemVersions> list = xydSystemVersionsService.selectList(xydSystemVersions);
        model.addAttribute("versionList", list);*/
        return "/demo/system/introduceInfo";
    }


    /**
     * 编辑器图片上传实现
     *
     * @param file
     * @param CKEditorFuncNum
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/ckeditorUpload")
    //名字upload是固定的，有兴趣，可以打开浏览器查看元素验证
    public String ckeditorUpload(HttpServletRequest request, @RequestParam("upload") MultipartFile file, String CKEditorFuncNum) throws Exception {
        // 注意路径格式，一般为项目路径下的一个文件夹里边，项目发布到linux服务器上又得改了
        String imageFilePath = "c://xydImages";
        // 获取文件名
       Map<String, Object> map = com.xfkj.common.utils.FileUtils.uploadfile(file);
        String path = "";
        ArrayList<String> list = (ArrayList<String>) map.get("images");
        if (!IsObjectNullUtils.is(list) && list.size() > 0){
             path = list.get(0);
        }
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" +path
                + "','')");
        sb.append("</script>");
        return sb.toString();
    }


    /**
     * 产品介绍列表
     */
    @ResponseBody
    @RequestMapping(value = "/getIntroduceList.ajax")
    public DataTableReturnData getIntroduceList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydIntroduce> dataTableReturnData = new DataTableReturnData();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("type"))) {
            queryParam.fill("Type",  params.get("type"));
        }
        queryParam.fill("states",  "1");
        int count = xydIntroduceService.selectByParamCount(queryParam);
        List<XydIntroduce> xydIntroduceList = xydIntroduceService.selectByParamList(queryParam);
        dataTableReturnData.setData(xydIntroduceList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }


    /**
     * 介绍添加、更新
     */
    @ResponseBody
    @RequestMapping(value = "/toIntroduceAdd", method = RequestMethod.POST)
    public Map toIntroduceAdd(@RequestParam(value = "id", required = false)Integer id,
                                 @RequestParam(value = "type", required = false)String type,
                                 @RequestParam(value = "title", required = false)String title,
                                 @RequestParam(value = "content", required = false)String content,
                                 @RequestParam(value = "versionId", required = false)Integer versionId){
        Map<String, Object> resultMap = new HashMap();
        if (IsObjectNullUtils.is(title) || IsObjectNullUtils.is(content) || IsObjectNullUtils.is(type)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "请输入必要的信息！");
            resultMap.put("data", "");
            return resultMap;
        }
       /* XydSystemVersions xydSystemVersions = xydSystemVersionsService.selectByPrimaryKey(versionId);
        if (IsObjectNullUtils.is(xydSystemVersions) || "2".equals(xydSystemVersions.getStates())){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息！");
            resultMap.put("data", "");
            return resultMap;
        }*/
        if (!IsObjectNullUtils.is(id)){
            //更新操作
            XydIntroduce xydIntroduce = xydIntroduceService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydIntroduce)){
                xydIntroduce.setType(type);
                xydIntroduce.setContent(content);
                xydIntroduce.setUpdateTime(new Date());
                xydIntroduce.setTitle(title);
                xydIntroduce.setStates("1");
                if (xydIntroduceService.updateByPrimaryKeySelective(xydIntroduce) > 0){
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "修改成功！");
                    resultMap.put("data", "");
                    return resultMap;
                }
            }
        }else {
            //添加操作
            XydIntroduce xydIntroduce = new XydIntroduce();
            xydIntroduce.setType(type);
            xydIntroduce.setStates("1");
            List<XydIntroduce> list = xydIntroduceService.selectList(xydIntroduce);
            if (!IsObjectNullUtils.is(list) && list.size() > 0){
                resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                resultMap.put("msg", "该类型已存在，不能重复添加！");
                resultMap.put("data", "");
                return resultMap;
            }
            xydIntroduce.setContent(content);
            xydIntroduce.setCreateTime(new Date());
            xydIntroduce.setTitle(title);
            xydIntroduce.setStates("1");
            if (xydIntroduceService.insertSelective(xydIntroduce) > 0){
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("data", "");
                resultMap.put("msg", "添加成功！");
                return resultMap;
            }
        }
        resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
        resultMap.put("msg", "操作失败！");
        resultMap.put("data", "");
        return resultMap;
    }

    /**
     * 删除产品介绍
     */
    @ResponseBody
    @RequestMapping(value = "/Introducedelete")
    public Map Introducedelete(Model model, @RequestParam(value = "id", required = false)Integer id){
        Map<String, Object> resultMap = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            XydIntroduce xydIntroduce = xydIntroduceService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydIntroduce) && "1".equals(xydIntroduce.getStates())){
                XydIntroduce updatexyd = new XydIntroduce();
                updatexyd.setId(xydIntroduce.getId());
                updatexyd.setStates("2");
                if (xydIntroduceService.updateByPrimaryKeySelective(updatexyd) > 0){
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "操作成功！");
                    resultMap.put("data", "");
                    return resultMap;
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "操作失败！");
                    resultMap.put("data", "");
                    return resultMap;
                }
            }
        }
        resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
        resultMap.put("msg", "无法获取相关信息！");
        resultMap.put("data", "");
        return resultMap;
    }


    /**
     * 获取城市列表
     * level   1:国家country,2:省级别province,3:市级别city
     */
    @RequestMapping(value = "/getCityList")
    @ResponseBody
    public Map getCityList(@RequestParam(value = "level", required = false)int level,
                           @RequestParam(value = "parentId", required = false)Integer parentId){
        Map<String, Object> result = new HashMap<>();
        XydArea xydArea = new XydArea();
        if (!IsObjectNullUtils.is(parentId)){
            xydArea.setParentid(parentId);
        }
        if (!IsObjectNullUtils.is(level)){
            xydArea.setLevel((byte)level);
        }
        List<XydArea> areaList = xydAreaService.selectByList(xydArea);
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg", "查询成功！");
        result.put("data",areaList);
        return result;
    }

    /**
     * 跳转到版本列表页
     */
    @RequestMapping(value = "/toVersionPage")
    public String toVersionPage(){
        return "/demo/system/versionList";
    }

    /**
     * 启动页图列表
     */
    @ResponseBody
    @RequestMapping(value = "/getVersionList.ajax")
    public DataTableReturnData getVersionList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydSystemVersions> dataTableReturnData = new DataTableReturnData<XydSystemVersions>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));

        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("type"))) {
            queryParam.fill("Type",  params.get("type"));
        }
        queryParam.fill("states",  "1");
        int count = xydSystemVersionsService.selectByParamCount(queryParam);
        List<XydSystemVersions> xydSystemVersionsList = xydSystemVersionsService.selectByParamList(queryParam);
        dataTableReturnData.setData(xydSystemVersionsList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     * 版本删除
     */
    @RequestMapping(value = "/versionDelete")
    @ResponseBody
    public Map versionDelete(@RequestParam(value = "id")Integer id){
        Map<String, Object>  resultMap = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            XydSystemVersions xydSystemVersions = xydSystemVersionsService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydSystemVersions)){
                XydSystemVersions systemVersions = new XydSystemVersions();
                systemVersions.setId(xydSystemVersions.getId());
                systemVersions.setStates("2");
                if (xydSystemVersionsService.updateByPrimaryKeySelective(systemVersions) > 0){
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "删除成功！");
                    resultMap.put("data", "");
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "删除失败！");
                    resultMap.put("data", "");
                }
                return resultMap;
            }
        }
        resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
        resultMap.put("msg", "无法获取相关信息，请稍后重试！");
        resultMap.put("data", "");
        return resultMap;
    }

    /**
     * 跳转到详情页
     */
    @RequestMapping(value = "/toAddVersionPage")
    public String versionPageAdd(@RequestParam(value = "id", required = false)Integer id, Model model){
        if (!IsObjectNullUtils.is(id)){
            XydSystemVersions xydSystemVersions = xydSystemVersionsService.selectByPrimaryKey(id);
            model.addAttribute("version", xydSystemVersions);
        }
        return "/demo/system/versionInfo";
    }

    /**
     * 添加或修改
     */
    @RequestMapping(value = "/versionAdd", method = RequestMethod.POST)
    @ResponseBody
    public Map versionAdd(@RequestParam(value = "id", required = false)Integer id,
                          @RequestParam(value = "number", required = false)String number,
                          @RequestParam(value = "type", required = false)String type,
                          @RequestParam(value = "title", required = false)String title,
                          @RequestParam(value = "download", required = false)String download
                          ){
        Map<String, Object> resultMap = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            XydSystemVersions versions = new XydSystemVersions();
            versions.setNumber(number);
            versions.setType(type);
            versions.setStates("1");
            List<XydSystemVersions> list = xydSystemVersionsService.selectList(versions);
            if (!IsObjectNullUtils.is(list) && list.size() > 0){
                if (list.get(0).getId() != id){
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "该版本已存在，不能重复添加！");
                    resultMap.put("data", "");
                    return resultMap;
                }
            }
            XydSystemVersions xydSystemVersions = xydSystemVersionsService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydSystemVersions)){
                XydSystemVersions systemVersions = new XydSystemVersions();
                systemVersions.setId(xydSystemVersions.getId());
                systemVersions.setNumber(number);
                systemVersions.setType(type);
                systemVersions.setTitle(title);
                systemVersions.setDownload(download);
                if (xydSystemVersionsService.updateByPrimaryKeySelective(systemVersions) > 0){
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "修改成功！");
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "修改失败！");
                }
                resultMap.put("data", "");
                return resultMap;
            }
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "无法获取相关信息！");
            resultMap.put("data", "");
            return resultMap;
        }else {
            XydSystemVersions versions = new XydSystemVersions();
            versions.setNumber(number);
            versions.setStates("1");
            versions.setType(type);
            List<XydSystemVersions> list = xydSystemVersionsService.selectList(versions);
            if (!IsObjectNullUtils.is(list) && list.size() > 0){
                resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                resultMap.put("msg", "该版本已存在，不能重复添加！");
                resultMap.put("data", "");
                return resultMap;
            }
            XydSystemVersions systemVersions = new XydSystemVersions();
            systemVersions.setNumber(number);
            systemVersions.setType(type);
            systemVersions.setTitle(title);
            systemVersions.setDownload(download);
            systemVersions.setStates("1");
            systemVersions.setCreateTime(new Date());
            if (xydSystemVersionsService.insertSelective(systemVersions) > 0){
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("msg", "添加成功！");
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                resultMap.put("msg", "添加失败！");
            }
            resultMap.put("data", "");
            return resultMap;
        }
    }

    /**
     * 跳转到建议反馈列表页
     */
    @RequestMapping(value = "/toSuggestPage")
    public String toSuggestPage( Model model){
        return "/demo/system/suggestList";
    }


    /**
     * 建议反馈列表
     */
    @ResponseBody
    @RequestMapping(value = "/getSuggestList.ajax")
    public DataTableReturnData getSuggestList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydSystemSuggest> dataTableReturnData = new DataTableReturnData<XydSystemSuggest>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));

        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("type"))) {
            queryParam.fill("Type",  params.get("type"));
        }
        queryParam.fill("states",  "1");
        int count = xydSystemSuggestService.selectByParamCount(queryParam);
        List<XydSystemSuggest> xydSystemSuggests = xydSystemSuggestService.selectByParamList(queryParam);
        dataTableReturnData.setData(xydSystemSuggests);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     * 跳转到建议反馈添加页
     */
    @RequestMapping(value = "/toSuggestAdd")
    public String toSuggestAdd( Model model, @RequestParam(value = "id", required = false)Integer id){
        if (!IsObjectNullUtils.is(id)){
            XydSystemSuggest xydSystemSuggest = xydSystemSuggestService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydSystemSuggest)){
                XydSystemVersions xydSystemVersions = xydSystemVersionsService.selectByPrimaryKey(xydSystemSuggest.getVersionsId());
                xydSystemSuggest.setType(xydSystemVersions.getType());
            }
            model.addAttribute("suggest", xydSystemSuggest);
        }
        XydSystemVersions xydSystemVersions = new XydSystemVersions();
        xydSystemVersions.setStates("1");
        xydSystemVersions.setType("1");
        List<XydSystemVersions> list1 = xydSystemVersionsService.selectList(xydSystemVersions);
        xydSystemVersions.setType("2");
        List<XydSystemVersions> list2 = xydSystemVersionsService.selectList(xydSystemVersions);
        model.addAttribute("versionList1", list1);
        model.addAttribute("versionList2", list2);
        return "/demo/system/suggestAdd";
    }

    /**
     * 跳建议反馈删除
     */
    @RequestMapping(value = "/uggestsDelete")
    @ResponseBody
    public Map toSuggestAdd(@RequestParam(value = "id", required = false)Integer id){
        Map<String, Object> result = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            XydSystemSuggest xydSystemSuggest = xydSystemSuggestService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydSystemSuggest)){
                XydSystemSuggest systemSuggest = new XydSystemSuggest();
                systemSuggest.setStates("2");
                systemSuggest.setId(xydSystemSuggest.getId());
                if (xydSystemSuggestService.updateByPrimaryKeySelective(systemSuggest) > 0){
                    result.put("msg", "删除成功！");
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    return result;
                }
            }
        }
        result.put("msg", "无法获取用户信息！");
        result.put("code", ResultStant.RESULT_CODE_LOSE);
        return result;

    }


    /**
     * 跳建议反馈添加
     */
    @RequestMapping(value = "/insertSuggest")
    @ResponseBody
    public Map insertSuggest(@RequestParam(value = "id", required = false)Integer id,
                             @RequestParam(value = "phone", required = false)String phone,
                             @RequestParam(value = "qqun", required = false)String qqun,
                             @RequestParam(value = "network", required = false)String network,
                             @RequestParam(value = "weixin", required = false)String weixin,
                                 @RequestParam(value = "mail", required = false)String mail,
                             @RequestParam(value = "versionsId", required = false)Integer versionsId){
        Map<String, Object> result = new HashMap<>();
        XydSystemSuggest xydSystemSuggest = new XydSystemSuggest();
        if (!IsObjectNullUtils.is(id)){
            XydSystemSuggest systemSuggest = xydSystemSuggestService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(systemSuggest)){
                xydSystemSuggest.setId(systemSuggest.getId());
                xydSystemSuggest.setPhone(phone);
                xydSystemSuggest.setQqun(qqun);
                xydSystemSuggest.setNetwork(network);
                xydSystemSuggest.setWeixin(weixin);
                xydSystemSuggest.setMail(mail);
                xydSystemSuggest.setVersionsId(versionsId);
                xydSystemSuggest.setUpdateTime(new Date());
                if (xydSystemSuggestService.updateByPrimaryKeySelective(xydSystemSuggest) > 0){
                    result.put("msg", "修改成功！");
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    return result;
                }
            }
            result.put("msg", "修改失败！");
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            return result;
        }else {
            xydSystemSuggest.setPhone(phone);
            xydSystemSuggest.setQqun(qqun);
            xydSystemSuggest.setNetwork(network);
            xydSystemSuggest.setWeixin(weixin);
            xydSystemSuggest.setVersionsId(versionsId);
            xydSystemSuggest.setStates("1");
            xydSystemSuggest.setMail(mail);
            xydSystemSuggest.setCreateTime(new Date());
            if (xydSystemSuggestService.insert(xydSystemSuggest) > 0){
                result.put("msg", "添加成功！");
                result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                return result;
            }
        }
        result.put("msg", "添加失败！");
        result.put("code", ResultStant.RESULT_CODE_LOSE);
        return result;
    }

}
