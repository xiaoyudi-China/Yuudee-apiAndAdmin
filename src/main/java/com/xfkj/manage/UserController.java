package com.xfkj.manage;

import com.xfkj.common.config.Global;
import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.emun.EducationTypeEnum;
import com.xfkj.common.emun.LanguageTypeEnum;
import com.xfkj.common.emun.MedicalTypeEnum;
import com.xfkj.common.emun.TrainingTypeEnum;
import com.xfkj.common.model_custom.DataTableReturnData;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.DateUtil;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.utils.MyMD5Util;
import com.xfkj.common.web.BaseController;
import com.xfkj.model.*;
import com.xfkj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Created by mai xiaogang on 2018/9/18.
 * 管理后台的类路径统一用manage开头
 * 用户管理
 */
@Controller
@RequestMapping("/manage/user")
public class UserController extends BaseController{
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    private XydChildService xydChildService;
    @Autowired
    private XydPhoneQcellcoreService xydPhoneQcellcoreService;
    @Autowired
    private XydSystemRemindService xydSystemRemindService;
    @Autowired
    private XydAreaService xydAreaService;

    @Autowired Global global;

    /**
     * 跳转到家长列表页
     * @return
     */
    @RequestMapping(value = "/toParentsPage")
    public String toParentsPage(Model model){
        List<Map<String, Object>> resultList = new ArrayList<>();
        XydPhoneQcellcore xydPhoneQcellcore = new XydPhoneQcellcore();
        xydPhoneQcellcore.setStates("1");
        List<XydPhoneQcellcore> typeList = xydPhoneQcellcoreService.selectByTypeList();
        if (!IsObjectNullUtils.is(typeList) && typeList.size() > 0){
            //获取热门列表
            Map<String, Object> maplast = new HashMap<>();
            xydPhoneQcellcore = typeList.get(typeList.size()-1);
            xydPhoneQcellcore.setStates("1");
            maplast.put("title", xydPhoneQcellcore.getCityType());
            maplast.put("list", xydPhoneQcellcoreService.selectByEntityList(xydPhoneQcellcore));
            resultList.add(maplast);
            for (int i = 0; i < typeList.size()-1; i++) {
                Map<String, Object> map = new HashMap<>();
                xydPhoneQcellcore = typeList.get(i);
                xydPhoneQcellcore.setStates("1");
                map.put("title", xydPhoneQcellcore.getCityType());
                map.put("list", xydPhoneQcellcoreService.selectByEntityList(xydPhoneQcellcore));
                resultList.add(map);
            }
        }
        model.addAttribute("cityList", resultList);
        //获取家长手机号城市
        return "/demo/user/parentsList";
    }
    /**
     * 跳转到孩子列表页
     * @return
     */
    @RequestMapping(value = "/toChildPage")
    public String toChildPage(Model model){
        XydArea xydArea = new XydArea();
        xydArea.setLevel((byte)1);
        List<XydArea> areaList = xydAreaService.selectByList(xydArea);
        model.addAttribute("countiyList", areaList);
        return "/demo/user/childList";
    }
    /**
     *家长信息列表
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getParentsList.ajax")
    public DataTableReturnData getParentsList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydParents> dataTableReturnData = new DataTableReturnData();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("phone"))) {
            queryParam.fill("phonenumber", params.get("phone"));
        }
        if (!IsObjectNullUtils.is(params.get("qcellcoreId"))) {
            queryParam.fill("qcellcoreid", params.get("qcellcoreId"));
        }
        if (!IsObjectNullUtils.is(params.get("nickname"))) {
            queryParam.fill("nickname", "%"+params.get("nickname")+"%");
        }
        queryParam.fill("status",  "1");
        int count = xydParentsService.selectByParamCount(queryParam);
        List<XydParents> xydParentsList = xydParentsService.selectByParamList(queryParam);
        dataTableReturnData.setData(xydParentsList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     * 删除家长用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/parentsDelete", method = RequestMethod.POST)
    @ResponseBody
    public Map parentsDelete(@RequestParam(value = "id")Integer id){
        Map<String, Object> result = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            XydParents xydParents = xydParentsService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydParents)){
                XydParents parents = new XydParents();
                parents.setId(xydParents.getId());
                parents.setUpdateTime(new Date());
                parents.setStatus("2");
                if (xydParentsService.updateByPrimaryKeySelective(parents) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "操作成功！");
                }else {
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", "操作失败！");
                }
                return result;
            }
        }
        result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
        result.put("msg", "操作失败！");
        return result;
    }

    /**
     * 重置用户密码
     * @param id
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Map updatePassword(@RequestParam(value = "id")Integer id){
        Map<String, Object> result = new HashMap<>();
        if (!IsObjectNullUtils.is(id)){
            XydParents xydParents = xydParentsService.selectByPrimaryKey(id);
            if (!IsObjectNullUtils.is(xydParents)){
                XydParents parents = new XydParents();
                parents.setId(xydParents.getId());
                parents.setUpdateTime(new Date());
                try {
                    parents.setPassword(MyMD5Util.getEncryptedPwd(global.getXydPassword()));
                    //发送一条短信
                } catch (Exception e) {
                    result.put("code", ResultStant.RESULT_CODE_SERVICE);
                    result.put("msg", "系统异常！");
                    e.printStackTrace();
                    return result;
                }
                if (xydParentsService.updateByPrimaryKeySelective(parents) > 0){
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "重置成功！");
                }else {
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", "重置失败！");
                }
                return result;
            }
        }
        result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
        result.put("msg", "无法获取相关信息！");
        return result;
    }

    /**
     * 跳转到家长添加页
     * @param model
     * @return
     */
    @RequestMapping(value = "/toParentsAdd")
    public String toParentsAdd(Model model){
        XydPhoneQcellcore xydPhoneQcellcore = new XydPhoneQcellcore();
        xydPhoneQcellcore.setStates("1");
        List<XydPhoneQcellcore> list = xydPhoneQcellcoreService.selectByEntityList(xydPhoneQcellcore);
        model.addAttribute("cityList", list);
        return "/demo/user/parentsInfo";
    }
    /**
     * 添加用户
     * @param qcellcoreId
     * @return
     */
    @RequestMapping(value = "/parentsAdd", method = RequestMethod.POST)
    @ResponseBody
    public Map parentsAdd(@RequestParam(value = "phone")String phone,
                          @RequestParam(value = "qcellcoreId")Integer qcellcoreId,
                          @RequestParam(value = "passwrod")String password
                          ){
        Map<String, Object> result = new HashMap<>();
        if (IsObjectNullUtils.is(password) || IsObjectNullUtils.is(qcellcoreId) || IsObjectNullUtils.is(phone)){
            result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
            result.put("msg", "无法获取相关信息！");
            return result;
        }
        XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(qcellcoreId);
        if (IsObjectNullUtils.is(xydPhoneQcellcore)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "无法获取相关信息，请稍后重试！");
            return result;
        }
        //根据手机号查询是否已注册
        XydParents xydParents = new XydParents();
        xydParents.setPhoneNumber(phone);
        xydParents.setStatus("1");
        List<XydParents> xydParentsList = xydParentsService.selectByEntityList(xydParents);
        if (!IsObjectNullUtils.is(xydParentsList) && xydParentsList.size() > 0){
            result.put("msg", "该手机号已注册！");
            result.put("code", ResultStant.RESULT_CODE_ERROR);
            return result;
        }
        //添加逻辑
        if (xydParentsService.manageAddParents(phone, password, qcellcoreId) > 0){
            result.put("msg", "添加成功！");
            result.put("code", ResultStant.RESULT_CODE_SUCCESS);
            return result;
        }
        result.put("msg", "添加失败!");
        result.put("code", ResultStant.RESULT_CODE_ERROR);
        return result;
    }


    /**
     *儿童信息列表
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getChildList.ajax")
    public DataTableReturnData getChildList(@RequestParam Map<String, String> params){
        DataTableReturnData<XydChild> dataTableReturnData = new DataTableReturnData();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize=getPageSize(params.get("length"));
        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("name"))) {
            queryParam.fill("name", "%"+params.get("name")+"%");
        }
        if (!IsObjectNullUtils.is(params.get("countiy"))) {
            XydArea xydArea = xydAreaService.selectByPrimaryKey(Integer.parseInt(params.get("countiy")));
            if (!IsObjectNullUtils.is(xydArea)){
                queryParam.fill("countiy", xydArea.getAreaname());
            }
        }
        if (!IsObjectNullUtils.is(params.get("province"))) {
            XydArea xydArea = xydAreaService.selectByPrimaryKey(Integer.parseInt(params.get("province")));
            if (!IsObjectNullUtils.is(xydArea)){
                queryParam.fill("province", xydArea.getAreaname());
            }

        }
        if (!IsObjectNullUtils.is(params.get("city"))) {
            XydArea xydArea = xydAreaService.selectByPrimaryKey(Integer.parseInt(params.get("city")));
            if (!IsObjectNullUtils.is(xydArea)){
                queryParam.fill("city", xydArea.getAreaname());
            }
        }
        if (!IsObjectNullUtils.is(params.get("birthdate"))) {
            queryParam.fill("birthdate", params.get("birthdate"));
        }
        if (!IsObjectNullUtils.is(params.get("sex"))) {
            queryParam.fill("sex", params.get("sex"));
        }
        if (!IsObjectNullUtils.is(params.get("phone"))) {
            queryParam.fill("phonenumber", params.get("phone"));
        }
        queryParam.fill("status",  "1");
        int count = xydChildService.selectByParamCount(queryParam);
        List<XydChild> xydChildList = xydChildService.selectByParamList(queryParam);
        dataTableReturnData.setData(xydChildList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     *删除儿童个人信息
     * @return
     */
    @RequestMapping(value = "/childDelete", method = RequestMethod.POST)
    @ResponseBody
    public Map childDelete(@RequestParam(value = "id")Integer id){
        Map<String, Object> result = new HashMap<>();
        if (IsObjectNullUtils.is(id)){
            result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
            result.put("msg", "系统异常，请稍后重试");
            return result;
        }
        XydChild xydChild = xydChildService.selectByPrimaryKey(id);
        if (IsObjectNullUtils.is(xydChild)){
            result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
            result.put("msg", "无法获取相关信息！");
            return result;
        }
        XydChild child = new XydChild();
        child.setId(xydChild.getId());
        child.setStatus("2");
        if (xydChildService.updateByPrimaryKeySelective(child) < 1){
            result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
            result.put("msg", "操作失败！");
            return result;
        }
        XydSystemRemind xydSystemRemind = new XydSystemRemind();
        xydSystemRemind.setUserId(xydChild.getId());
        xydSystemRemind.setParentsId(xydChild.getParentsId());
        List<XydSystemRemind> list = xydSystemRemindService.selectList(xydSystemRemind);
        if (!IsObjectNullUtils.is(list) && list.size() >0){
            for (XydSystemRemind systemRemind : list) {
                xydSystemRemind.setId(systemRemind.getId());
                xydSystemRemind.setStates("1");
                xydSystemRemind.setIsRemind("1");
                xydSystemRemind.setUpdateTime(new Date());
                xydSystemRemindService.updateByPrimaryKeySelective(xydSystemRemind);
            }
        }
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg", "删除成功！");
        return result;
    }

    /**
     * 孩子信息编辑页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/toChildInfo")
    public String toChildInfo(Model model,@RequestParam(value = "id")Integer id){
        if (!IsObjectNullUtils.is(id)){
            XydChild xydChild = xydChildService.selectByPrimaryKey(id);
            model.addAttribute("child", xydChild);
        }
        model.addAttribute("lanageList", LanguageTypeEnum.getEnumDescList());
        model.addAttribute("trainingList", TrainingTypeEnum.getEnumDescList());
        model.addAttribute("EducationList", EducationTypeEnum.getEnumDescList());
        model.addAttribute("medicalList", MedicalTypeEnum.getEnumDescList());
        XydArea xydArea = new XydArea();
        xydArea.setLevel((byte)1);
        List<XydArea> areaList = xydAreaService.selectByList(xydArea);
        model.addAttribute("countiyList", areaList);
        return "/demo/user/childInfo";
    }

    /**
     * 孩子信息更新
     * @param xydChild
     * @return
     */
    @RequestMapping(value = "/childUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map childUpdate(XydChild xydChild){
        Map<String, Object> result = new HashMap<>();
        if (!IsObjectNullUtils.is(xydChild)){
           xydChild.setUpdateTime(new Date());

           XydArea Countiy = xydAreaService.selectByPrimaryKey(Integer.parseInt(xydChild.getCountiy()));
           if (!IsObjectNullUtils.is(Countiy)){
                xydChild.setCountiy(Countiy.getAreaname());
            }else {
                xydChild.setCountiy(null);
           }
            XydArea Province = xydAreaService.selectByPrimaryKey(Integer.parseInt(xydChild.getProvince()));
            if (!IsObjectNullUtils.is(Province)){
                xydChild.setProvince(Province.getAreaname());
            }else {
                xydChild.setProvince(null);
            }
            XydArea City = xydAreaService.selectByPrimaryKey(Integer.parseInt(xydChild.getCity()));
            if (!IsObjectNullUtils.is(City)){
                xydChild.setCity(City.getAreaname());
            }else {
                xydChild.setCity(null);
            }
           xydChild.setBirthdate(DateUtil.parseDate(xydChild.getBirthTime()));
           if (xydChildService.updateByPrimaryKeySelective(xydChild) > 0){
               result.put("code", ResultStant.RESULT_CODE_SUCCESS);
               result.put("msg", "更新成功！");
               return result;
           }
        }
        result.put("code", ResultStant.RESULT_CODE_ERROR);
        result.put("msg", "更新失败！");
        return result;
    }

}
