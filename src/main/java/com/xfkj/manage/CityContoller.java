package com.xfkj.manage;

import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.model_custom.DataTableReturnData;
import com.xfkj.common.query.GenericQueryParam;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.web.BaseController;
import com.xfkj.model.XydArea;
import com.xfkj.model.XydSystemImages;
import com.xfkj.service.XydAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2019/3/8.
 */
@Controller
@RequestMapping(value = "/manage/city")
public class CityContoller extends BaseController{

    @Autowired
    private XydAreaService areaService;


    /**
     * city列表
     */
    @RequestMapping(value = "/toCityList")
    public String toStrartPageInfo(Model model) {

        return "/demo/system/cityList";
    }

    /**
     * city详情
     */
    @RequestMapping(value = "/toCityInfo")
    public String toStrartPageInfo(@RequestParam(value = "areaid", required = false) Integer areaid, Model model) {
        if (!IsObjectNullUtils.is(areaid)) {
            XydArea xydArea = areaService.selectByPrimaryKey(areaid);
            if (!IsObjectNullUtils.is(xydArea) && xydArea.getLevel() > 1){
                XydArea area = new XydArea();
                area.setLevel((byte) (xydArea.getLevel()-1));
                List<XydArea> list = areaService.selectByList(area);
                model.addAttribute("parentList", list);
            }
            model.addAttribute("xydArea", xydArea);
        }
        return "/demo/system/cityInfo";
    }

    /**
     * 启动页图列表
     */
    @ResponseBody
    @RequestMapping(value = "/getCityList.ajax")
    public DataTableReturnData getStartPageList(@RequestParam Map<String, String> params) {
        DataTableReturnData<XydArea> dataTableReturnData = new DataTableReturnData<XydArea>();
        int pageNum = getPageNum(params.get("start"), params.get("length"));
        int pageSize = getPageSize(params.get("length"));

        GenericQueryParam queryParam = new GenericQueryParam(pageNum, pageSize);
        if (!IsObjectNullUtils.is(params.get("areaName"))) {
            queryParam.fill("areaname", "%"+params.get("areaName")+"%");
        }
        int count = areaService.count(queryParam);
        List<XydArea> xydAreaList = areaService.select(queryParam);
        dataTableReturnData.setData(xydAreaList);
        dataTableReturnData.setDraw(getDraw(params.get("draw")));
        dataTableReturnData.setMsg("查询成功");
        dataTableReturnData.setStatus(ResultStant.RESULT_CODE_SUCCESS);
        dataTableReturnData.setRecordsFiltered(count);
        dataTableReturnData.setRecordsTotal(count);
        return dataTableReturnData;
    }

    /**
     * city删除
     */
    @RequestMapping(value = "/deleteCity")
    @ResponseBody
    public Map cityDelete(@RequestParam(value = "areaid") Integer areaid) {
        Map<String, Object> resultMap = new HashMap();
        if (!IsObjectNullUtils.is(areaid)) {
            XydArea xydArea = areaService.selectByPrimaryKey(areaid);
            if (IsObjectNullUtils.is(xydArea)){
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("msg", "操作异常，请刷新重试！");
                return resultMap;
            }
            if (xydArea.getLevel() == 1){
                XydArea xydArea1 = new XydArea();
                xydArea1.setParentid(xydArea.getAreaid());
                List<XydArea> list1 = areaService.selectByList(xydArea1);
                if (!IsObjectNullUtils.is(list1) && list1.size() > 0){
                    for (XydArea area : list1) {
                        //删除自生加子地区
                        if (area.getAreaid() != null){
                            areaService.deleteChilCity(area.getAreaid());
                        }
                    }
                }
                areaService.deleteChilCity(xydArea.getAreaid());
            }

            if (xydArea.getLevel() == 2){
                areaService.deleteChilCity(xydArea.getAreaid());
            }
            if (areaService.deleteByPrimaryKey(xydArea.getAreaid()) > 0){
                resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                resultMap.put("msg", "删除成功！");
                return resultMap;
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                resultMap.put("msg", "操作异常，请刷新重试！");
                return resultMap;
            }
        }
        return resultMap;
    }

    /**
     * city新增或修改
     */
    @RequestMapping(value = "/addCity")
    @ResponseBody
    public Map cityUpdateOrAdd(@RequestParam(value = "areaid", required = false) Integer areaid,
                               @RequestParam(value = "level") Byte level,
                               @RequestParam(value = "areaname") String areaname,
                               @RequestParam(value = "parentid") Integer parentid,
                               @RequestParam(value = "areaCode", required = false)String areaCode
                               ) {
        Map<String, Object> resultMap = new HashMap();
        XydArea xydArea = new XydArea();
        xydArea.setLevel(level);
        xydArea.setAreaname(areaname);
        xydArea.setParentid(parentid);
        List<XydArea> list = areaService.selectByList(xydArea);
        if (!IsObjectNullUtils.is(list) && list.size() > 0 && !list.get(0).getAreaid().equals(areaid)){
            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
            resultMap.put("msg", "该地区已存在，不能重复添加！");
            return resultMap;
        }
        int flag = 0;
        if (!IsObjectNullUtils.is(areaid)) { //修改
            XydArea area = areaService.selectByPrimaryKey(areaid);
            if (IsObjectNullUtils.is(area)){
                resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                resultMap.put("msg", "操作异常，请刷新重试！");
                return resultMap;
            }
            xydArea.setAreaid(area.getAreaid());
            xydArea.setUpdateTime(new Date());
            if (!IsObjectNullUtils.is(areaCode))
                xydArea.setAreacode(areaCode);
            flag = areaService.updateByPrimaryKeySelective(xydArea);

        }else { //新增
            xydArea.setCreateTime(new Date());
            xydArea.setAreacode("0");
            flag = areaService.insertSelective(xydArea);
        }
        if (flag > 0){
            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
            resultMap.put("msg", "操作成功");
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
            resultMap.put("msg", "操作失败，请稍后重试！");
        }
        return resultMap;
    }



}
