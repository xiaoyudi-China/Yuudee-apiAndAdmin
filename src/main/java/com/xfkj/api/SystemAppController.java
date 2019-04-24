package com.xfkj.api;

import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.utils.*;
import com.xfkj.model.*;
import com.xfkj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.*;

/**
 * * app接口都写在api包下边
 * Created by mai xiaogang on 2018/9/26.
 * app公共的接口类
 */
@RestController
@RequestMapping(value = "/app/system")
public class SystemAppController {
    @Autowired
    private XydChildService xydChildService;
    @Autowired
    private XydPhoneQcellcoreService xydPhoneQcellcoreService;
    @Autowired
    private XydSystemSuggestService xydSystemSuggestService;
    @Autowired
    private XydSystemVersionsService xydSystemVersionsService;
    @Autowired
    private XydIntroduceService xydIntroduceService;
    @Autowired
    private XydSystemImagesService xydSystemImagesService;
    @Autowired
    private XydAreaService xydAreaService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private XydParentsService xydParentsService;


    /**
     * 上传图片
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/oss/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map fileUpload(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> result = new HashMap<>();
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg", "上传成功！");
        result.put("data", FileUtils.upload(request));
        return result;
    }

    /**
     * 删除图片
     * @return
     */
    @RequestMapping(value = "/oss/deleteFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map deleteFileUpload(@RequestParam(value = "image", required = false)String image){
        Map<String,Object> result = new HashMap<>();
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        FileUtils.deleteFile(image);
        result.put("msg", "删除成功！");
        result.put("data", "");
        return result;
    }


    /**
     * 获取手机号归属地数据列表
     * 如果后续数据增多查询太慢可使用缓存
     * @return
     */
    @RequestMapping(value = "/qcellcoreList")
    public Map getQcellcoreList(){
        Map<String, Object> resultMap = new HashMap<>();
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
        resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
        resultMap.put("data", resultList);
        resultMap.put("msg", "查询成功！");
        return resultMap;
    }

    /**
     * 产品介绍
     * @return
     */
    @RequestMapping(value = "/productInfo")
    public Map productInfo(@RequestParam(value = "type")String type){
        Map<String, Object> resultMap = new HashMap<>();
        XydIntroduce xydIntroduce = new XydIntroduce();
        xydIntroduce.setStates("1");
        xydIntroduce.setType(type);
        List<XydIntroduce> list = xydIntroduceService.selectList(xydIntroduce);
        if (!IsObjectNullUtils.is(list) && list.size() > 0){
            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
            resultMap.put("data", list.get(0));
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "获取不到想要的信息！");
        }
        return resultMap;
    }

    /**
     * 更多建议反馈和版本信息
     * @return
     */
    @RequestMapping(value = "/aboutUs")
    public Map getAboutUs(@RequestParam(value = "versionsNumber", required = false)String versionsNumber,
                          @RequestParam(value = "type", required = false)String type,
                          @RequestParam(value = "token", required = false)String token){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
        Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
        if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
            XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
            if (!IsObjectNullUtils.is(xydParents) && !IsObjectNullUtils.is(xydParents.getChildId())){
                XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                if (!IsObjectNullUtils.is(xydChild)){
                    result.put("name", xydChild.getName());
                    result.put("img", xydChild.getPhoto());
                    result.put("phone", xydParents.getPhoneNumber());
                }
            }
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("data", result);
            resultMap.put("msg", "无法获取到用户信息，");
            return resultMap;
        }
        XydSystemSuggest xydSystemSuggest = new XydSystemSuggest();
        XydSystemVersions xydSystemVersions = new XydSystemVersions();
        xydSystemVersions.setNumber(versionsNumber);
        xydSystemVersions.setType(type);
        List<XydSystemVersions> systemVersionsList = xydSystemVersionsService.selectList(xydSystemVersions);
        if (!IsObjectNullUtils.is(xydSystemVersions) && systemVersionsList.size() > 0){
            xydSystemVersions = systemVersionsList.get(0);
            //获取版本信息 以及对应的反馈信息
            xydSystemSuggest.setStates("1");
            xydSystemSuggest.setVersionsId(xydSystemVersions.getId());
            List<XydSystemSuggest> list = xydSystemSuggestService.selectList(xydSystemSuggest);
            if (!IsObjectNullUtils.is(list) && list.size() > 0){
                xydSystemSuggest = list.get(0);
            }
        }
        result.put("suggest", xydSystemSuggest);
        result.put("versions", xydSystemVersions);
        resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
        resultMap.put("data", result);
        resultMap.put("msg", "查询成功！");
        return resultMap;
    }

    /**
     * 获取图片信息
     * @param type :1 app启动页图片
     * @return
     */
    @RequestMapping(value = "/appStartImage")
    public Map getAppStartImage(@RequestParam(value = "type")String type){
        Map<String, Object> resultMap = new HashMap<>();

        XydSystemImages xydSystemImages = new XydSystemImages();
        xydSystemImages.setType(type);
        xydSystemImages.setStates("1");
        List<XydSystemImages> imagesList = xydSystemImagesService.selectList(xydSystemImages);
        if (!IsObjectNullUtils.is(imagesList) && imagesList.size() > 0){
            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
            resultMap.put("msg", "获取成功！");
            resultMap.put("data", imagesList.get(0));
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_NULL);
            resultMap.put("msg", "没有获取到您想要的资源！");
            resultMap.put("data", "");
        }
        return resultMap;
    }


    /**
     * 获取城市列表
     * level   1:国家country,2:省级别province,3:市级别city
     */
    @RequestMapping(value = "/getCityList")
    public Map getCityList(@RequestParam(value = "level", required = false)int level,
                           @RequestParam(value = "parentId", required = false)Integer parentId){
        Map<String, Object> result = new HashMap<>();
        XydArea xydArea = new XydArea();
        xydArea.setParentid(parentId);
        xydArea.setLevel((byte)level);
        List<XydArea> areaList = xydAreaService.selectByList(xydArea);
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg", "查询成功！");
        result.put("data",areaList);
        return result;
    }

    /**
     * 获取城市列表
     * level
     */
    @RequestMapping(value = "/getThisCityList")
    public Map getThisCityList(){
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        XydArea xydArea = new XydArea();
        //国家country
        xydArea.setLevel((byte)1);
        List<XydArea> countryList = xydAreaService.selectByList(xydArea);
        data.put("countryList", countryList);
        //province
        xydArea.setLevel((byte)2);
        List<XydArea> provinceList = xydAreaService.selectByList(xydArea);
        data.put("provinceList", provinceList);
        //city
        xydArea.setLevel((byte)3);
        List<XydArea> cityList = xydAreaService.selectByList(xydArea);
        data.put("cityList", cityList);
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg", "查询成功！");
        result.put("data", data);
        return result;
    }

    @RequestMapping(value = "/verify/phoneNumber")
    public Map verifyPhone(@RequestParam(value = "phone")String phone, @RequestParam(value = "qcellcoreId")Integer qcellcoreId){
        Map<String, Object> resultMap = new HashMap<>();
        if (IsObjectNullUtils.is(phone)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "手机号码输入的不能为空！");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(qcellcoreId)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "请选择手机号归属地！");
            resultMap.put("data", "");
            return resultMap;
        }

        XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(qcellcoreId);
        if (IsObjectNullUtils.is(xydPhoneQcellcore)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取到相关信息！");
            resultMap.put("data", "");
            return resultMap;
        }
        resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
        resultMap.put("msg", "已校验");
        resultMap.put("data", PhoneCheckUtil.phoneTypeCheck(phone, xydPhoneQcellcore.getPhonePrefix()) == false ? "0" : "1");
        return resultMap;
    }
}
