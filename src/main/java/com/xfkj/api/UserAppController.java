package com.xfkj.api;

import com.xfkj.common.config.Global;
import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.emun.LanguageTypeEnum;
import com.xfkj.common.emun.MedicalTypeEnum;
import com.xfkj.common.utils.*;
import com.xfkj.model.*;
import com.xfkj.service.*;
import org.hamcrest.core.Is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/18.
 * app家长孩子基本信息操作
 */

@RestController
@RequestMapping(value = "/app/user")
public class UserAppController {
    @Autowired
    private XydChildService xydChildService;
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    private XydPhoneQcellcoreService xydPhoneQcellcoreService;
    @Autowired
    private XydSystemRemindService xydSystemRemindService;
    @Autowired
    private XydAreaService xydAreaService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    Global global;
    @Autowired
    private RedisService redisService ;

    @RequestMapping(value = "testRedis")
    public String testResd(@RequestParam(value = "mxg")String mxg){
        return (String) redisService.get("mxg");
    }

    /**
     * 判断该手机号是否已注册
     * @param phone
     * @param districeId
     * @return
     */
    @RequestMapping(value = "/phoneIsRegister")
    public Map<String, Object> phoneIsRegister(@RequestParam(value = "phone")String phone, @RequestParam(required = false, value = "districeId")Integer districeId){
        Map<String, Object> result = new HashMap<>();
        if (IsObjectNullUtils.is(phone)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "手机号不能为空！");
            result.put("data", "");
            return result;
        }

        if (!IsObjectNullUtils.is(districeId)){
            XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(districeId);
            if (IsObjectNullUtils.is(xydPhoneQcellcore)){
                result.put("code", ResultStant.RESULT_CODE_LOSE);
                result.put("msg", "无法获取相关信息，请稍后重试！");
                result.put("data", "");
                return result;
            }
            if (!PhoneCheckUtil.phoneTypeCheck(phone, xydPhoneQcellcore.getPhonePrefix())){
                result.put("code", ResultStant.RESULT_CODE_LOSE);
                result.put("msg", "手机号码格式不正确！");
                result.put("data", "");
                return result;
            }
        }

        //根据手机号查询是否已注册
        XydParents xydParents = new XydParents();
        xydParents.setPhoneNumber(phone);
        List<XydParents> xydParentsList = xydParentsService.selectByEntityList(xydParents);
        Map<String, Object> resultMap = new HashMap<>();
        if (!IsObjectNullUtils.is(xydParentsList) && xydParentsList.size() > 0){
            result.put("msg", "该手机号已注册！");
            XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(xydParentsList.get(0).getQcellcoreId());
            resultMap.put("qcellcore", xydPhoneQcellcore);
            resultMap.put("isRegister", true);
            result.put("data", resultMap);
        }else {
            result.put("msg", "该手机号还没有注册！");
            resultMap.put("isRegister", false);
            result.put("data", resultMap);
        }
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        return result;
    }

    /**
     * 注册发送验证码
     * @param phone
     * @param districeId
     * @return
     */
    @RequestMapping(value = "/registerSendCode")
    public Map<String, Object> registerSendCode(@RequestParam(value = "phone")String phone, @RequestParam(value = "districeId")Integer districeId){
        Map<String, Object> result = new HashMap<>();
        if (IsObjectNullUtils.is(phone)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "手机号不能为空！");
            result.put("data", "");
            return result;
        }
        if (IsObjectNullUtils.is(districeId)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "请选择手机号所属地区！");
            result.put("data", "");
            return result;
        }
        XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(districeId);
        if (IsObjectNullUtils.is(xydPhoneQcellcore)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "无法获取相关信息，请稍后重试！");
            result.put("data", "");
            return result;
        }
        if (!PhoneCheckUtil.phoneTypeCheck(phone, xydPhoneQcellcore.getPhonePrefix())){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "手机号码格式不正确！");
            result.put("data", "");
            return result;
        }
        //更具手机号进行发送验证码
        if ("86".equals(xydPhoneQcellcore.getPhonePrefix())){
            result = AliyunSendUtil.sendCode(phone, redisTemplate, xydPhoneQcellcore.getPhonePrefix(), global.getRegisterMSM());
        }else {
            result = AliyunSendUtil.sendCountryCode(phone, redisTemplate, xydPhoneQcellcore, global.getWregisterMSM());
        }
        return result;
    }

    /**
     * 家长注册、手机验证码验证
     * @param phone
     * @param districeId
     * @return
     */
    @RequestMapping(value = "/registerCodeverify")
    public Map<String, Object> registerCodeverify(@RequestParam(value = "phone")String phone, @RequestParam(value = "districeId")Integer districeId,
                                                  @RequestParam(value = "code")String code){
        Map<String, Object> result = new HashMap<>();
        if (IsObjectNullUtils.is(phone)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "手机号不能为空！");
            result.put("data", "");
            return result;
        }

        if (IsObjectNullUtils.is(districeId) && IsObjectNullUtils.is(code)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "无法获取相关信息");
            result.put("data", "");
            return result;
        }
        XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(districeId);
        if (IsObjectNullUtils.is(xydPhoneQcellcore)){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "无法获取相关信息，请稍后重试！");
            result.put("data", "");
            return result;
        }
        if (!PhoneCheckUtil.phoneTypeCheck(phone, xydPhoneQcellcore.getPhonePrefix())){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "手机号码格式不正确！");
            result.put("data", "");
            return result;
        }
        //获取短信code
        TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
        String valueByKeyTheRedis = (String) TokenProccessor.getInstance().getValueByKeyTheRedis(xydPhoneQcellcore.getPhonePrefix()+phone);
        if (!IsObjectNullUtils.is(valueByKeyTheRedis)) {
            if (!IsObjectNullUtils.is(code)) {
                if (!valueByKeyTheRedis.equals(code)) {
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", "验证码错误");
                    result.put("data", "");
                    return result;
                }
            } else {
                result.put("code", ResultStant.RESULT_CODE_LOSE);
                result.put("msg", "验证码为空");
                result.put("data", "");
                return result;
            }
        }else{
            result.put("code", ResultStant.RESULT_CODE_ERROR);
            result.put("msg", "验证码错误");
            result.put("data", "");
            return result;
        }
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg", "验证通过");
        result.put("data", "");
        redisService.remove(xydPhoneQcellcore.getPhonePrefix()+phone);
        return result;
    }

    /**
     * 家长注册
     * @param request
     * @param password
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> userRegister(HttpServletRequest request, @RequestParam(required = false, value = "phone")String phone,
                                            @RequestParam(value = "qcellcoreId", required = false)Integer qcellcoreId,
                                            @RequestParam(value = "password")String password){
        Map<String, Object> result = new HashMap<>();
        if (!IsObjectNullUtils.is(password) && !IsObjectNullUtils.is(phone) && !IsObjectNullUtils.is(qcellcoreId)){
            try {
                XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(qcellcoreId);
                if (IsObjectNullUtils.is(xydPhoneQcellcore)){
                    result.put("code", ResultStant.RESULT_CODE_LOSE);
                    result.put("msg", "无法获取手机号归属地信息！");
                    result.put("data", "");
                    return result;
                }
                if (!PhoneCheckUtil.phoneTypeCheck(phone, xydPhoneQcellcore.getPhonePrefix())){
                    result.put("code", ResultStant.RESULT_CODE_LOSE);
                    result.put("msg", "手机号码格式不正确！");
                    result.put("data", "");
                    return result;
                }
                XydParents Isparents = new XydParents();
                Isparents.setPhoneNumber(phone);
                Isparents.setStatus("1");
                List<XydParents> list = xydParentsService.selectByEntityList(Isparents);
                if (!IsObjectNullUtils.is(list) && list.size()>0){
                    result.put("code", ResultStant.RESULT_CODE_LOSE);
                    result.put("data", "");
                    result.put("msg", "该手机已注册，请直接登录使用吧！");
                    return result;
                }
                XydParents xydParents = new XydParents();
                xydParents.setPhoneNumber(phone);
                xydParents.setPassword(MyMD5Util.getEncryptedPwd(password));
                xydParents.setQcellcoreId(xydPhoneQcellcore.getId());
                xydParents.setStatus("1");
                xydParents.setFeel("0");
                xydParents.setCreateTime(new Date());
                if ( xydParentsService.insertSelective(xydParents) > 0){
                    //添加儿童个人信息提醒记录
                    XydSystemRemind xydSystemRemind = new XydSystemRemind();
                    xydSystemRemind.setStates("1");
                    xydSystemRemind.setIsRemind("1");
                    xydSystemRemind.setCreateTime(new Date());
                    xydSystemRemind.setUpdateTime(new Date());
                    xydSystemRemind.setParentsId(xydParents.getId());
                    xydSystemRemind.setType("2");
                    xydSystemRemind.setTitle("完善儿童个人信息提醒");
                    xydSystemRemindService.insertSelective(xydSystemRemind);
                    //添加pcdi问卷提醒
                    xydSystemRemind.setType("1");
                    xydSystemRemind.setTitle("pcid问卷提醒");
                    xydSystemRemindService.insertSelective(xydSystemRemind);
                    //添加abc问卷提醒
                    xydSystemRemind.setType("3");
                    xydSystemRemind.setTitle("abc问卷提醒");
                    xydSystemRemindService.insertSelective(xydSystemRemind);
                    if ("86".equals(xydPhoneQcellcore.getPhonePrefix())){
                        AliyunSendUtil.sendText(phone, xydPhoneQcellcore.getPhonePrefix(), global.getRegisterWinMSM());
                    }else {
                        AliyunSendUtil.sendCountryText(phone, xydPhoneQcellcore, global.getRegisterWinMSM());
                    }
                    TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
                    String token=TokenProccessor.getInstance().makeToken();
                    System.out.println("注册并登录成功："+token);
                    xydParents.setToken(token);
                    XydParents parents = new XydParents();
                    parents.setId(xydParents.getId());
                    parents.setToken(token);
                    xydParentsService.updateByPrimaryKeySelective(parents);
                    TokenProccessor.getInstance().saveUserInfoByTokenToRedis(xydParents);
                    result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    result.put("msg", "注册成功");
                    result.put("data", xydParents);
                    return result;
                }
            } catch (Exception e) {
                result.put("code", ResultStant.RESULT_CODE_SERVICE);
                result.put("msg", "系统繁忙，请稍后重试！");
                result.put("data", "");
                e.printStackTrace();
                return result;
            }
        }
        result.put("code", ResultStant.RESULT_CODE_ERROR);
        result.put("msg", "注册失败，请稍后重试！");
        result.put("data", "");
        return result;
    }


    /**
     * 普通登录
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "/generalLogin", method = RequestMethod.POST)
    public Map<String, Object> generalLogin(@RequestParam(value = "phone")String phone,
                                            @RequestParam(value = "password")String password,
                                            @RequestParam(value = "qcellcoreId", required = false)Integer qcellcoreId
                                            ){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        XydParents xydParents = null;
        if (IsObjectNullUtils.is(phone)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "手机号码输入的不能为空！");
            resultMap.put("data", "");
            return resultMap;
        }
        try{
            if(!IsObjectNullUtils.is(phone) && !IsObjectNullUtils.is(password)){
                xydParents=new XydParents();
                xydParents.setPhoneNumber(phone);
                xydParents.setStatus("1");
                List<XydParents> xydParentsList = xydParentsService.selectByEntityList(xydParents);
                if(!IsObjectNullUtils.is(xydParentsList) && xydParentsList.size() > 0 ){
                  if ( MyMD5Util.validPassword(password,xydParentsList.get(0).getPassword())){
                      xydParents = xydParentsList.get(0);
                      //删除之前的token
                      if (!IsObjectNullUtils.is(xydParents.getToken())){
                          redisService.remove(xydParents.getToken());
                      }
                      TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
                      String token=TokenProccessor.getInstance().makeToken();
                      System.out.println("登录成功："+token);
                      xydParents.setToken(token);
                      XydParents parents = new XydParents();
                      parents.setId(xydParents.getId());
                      parents.setToken(token);
                      xydParentsService.updateByPrimaryKeySelective(parents);
                      TokenProccessor.getInstance().saveUserInfoByTokenToRedis(xydParents);
                      Map<String, Object> map = new HashMap<>();
                      XydSystemRemind xydSystemRemind = new XydSystemRemind();
                      xydSystemRemind.setIsRemind("1");
                      xydSystemRemind.setParentsId(xydParents.getId());
                      //判断是否需要完善儿童个人信息提醒
                      xydSystemRemind.setType("2");
                      List<XydSystemRemind> list = xydSystemRemindService.selectList(xydSystemRemind);
                      if (!IsObjectNullUtils.is(list) && list.size() > 0){
                          map.put("IsRemind", "1"); //未完善儿童个人信息
                      }else {
                          map.put("IsRemind", "2");//已完善儿童个人信息
                      }
                      // 0男 1女
                      map.put("chilSex", "0");
                      map.put("chilName", null);
                      map.put("chilPhoto", null);
                      // 0男 1女
                      if (!IsObjectNullUtils.is(xydParents.getChildId())){
                          XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                          if (!IsObjectNullUtils.is(xydChild)){
                              map.put("chilSex", xydChild.getSex() == null ? "0" : xydChild.getSex());
                              map.put("chilName", xydChild.getName());
                              map.put("chilPhoto", xydChild.getPhoto());
                          }
                      }
                      xydParents.setPassword(null);
                      map.put("parents", xydParents);
                      resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                      resultMap.put("msg", "登录成功！");
                      resultMap.put("data", map);
                  }else {
                      resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                      resultMap.put("msg", "密码不正确，请重新输入！");
                      resultMap.put("data", "");
                  }
                }else{
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "对不起，您还没有注册！");
                    resultMap.put("data", "");
                }
            }else{
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("msg", "用户名和密码不一致，请重新输入！");
                resultMap.put("data", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("code", ResultStant.RESULT_CODE_UNUSUAL);
            resultMap.put("data", "");
            resultMap.put("msg", "登录异常，请稍后重试！");
        }finally {
            return resultMap;
        }
    }

    /**
     * 快捷登录发送验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/shortcutLoginSend", method = RequestMethod.GET)
    public Map<String, Object> shortcutLoginSendCode(@RequestParam(value = "phone")String phone,
                                                     @RequestParam(value = "qcellcoreId", required = false)Integer qcellcoreId){
        Map<String, Object> resultMap = new HashMap<>();

        if (IsObjectNullUtils.is(phone)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "手机号不能为空！");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(qcellcoreId)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息！");
            resultMap.put("data", "");
            return resultMap;
        }
         XydPhoneQcellcore xydPhoneQcellcore1 = xydPhoneQcellcoreService.selectByPrimaryKey(qcellcoreId);
        if (IsObjectNullUtils.is(xydPhoneQcellcore1)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息，请稍后重试！");
            resultMap.put("data", "");
            return resultMap;
        }
        if (!PhoneCheckUtil.phoneTypeCheck(phone, xydPhoneQcellcore1.getPhonePrefix())){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "手机号码格式不正确！");
            resultMap.put("data", "");
            return resultMap;
        }
        XydParents xydParents = new XydParents();
        xydParents.setPhoneNumber(phone);
        xydParents.setStatus("1");
        List<XydParents> xydParentsList = xydParentsService.selectByEntityList(xydParents);
        if (IsObjectNullUtils.is(xydParentsList) || xydParentsList.size() < 1){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "该手机号还没有注册，请先注册！");
            resultMap.put("data", "");
            return resultMap;
        }
        xydParents = xydParentsList.get(0);
        XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(xydParents.getQcellcoreId());
        if (IsObjectNullUtils.is(xydPhoneQcellcore)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取到相关信息！");
            resultMap.put("data", "");
            return resultMap;
        }
        //更具手机号进行发送验证码
        System.out.println("更具手机号进行发送验证码："+phone+"xydParents"+xydParents.getId());
        if ("86".equals(xydPhoneQcellcore.getPhonePrefix())){
            resultMap = AliyunSendUtil.sendCode(phone, redisTemplate, xydPhoneQcellcore.getPhonePrefix(), global.getLoginMSM());
        }else {
            resultMap = AliyunSendUtil.sendCountryCode(phone, redisTemplate, xydPhoneQcellcore, global.getWloginMSM());
        }
        return resultMap;
    }

    /**
     * 快捷登录 图片校验
     * @param request
     * @param imageCode
     * @return
     */
    @RequestMapping(value = "/verify/imageCode")
    public Map image(HttpServletRequest request, @RequestParam(value = "imageCode")String imageCode){
        Map<String, Object> resultMap = new HashMap<>();
        String codeImage = (String) request.getSession().getAttribute("verifyCode");
        if (!IsObjectNullUtils.is(codeImage) &&!IsObjectNullUtils.is(imageCode) && codeImage.toLowerCase().equals(imageCode.toLowerCase())){
            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
            resultMap.put("data", "");
            resultMap.put("msg", "验证通过！");
        }else {
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "输入的验证码不正确！");
            resultMap.put("data", "正确的验证码："+codeImage);
        }
        return resultMap;
    }

    /**
     * 快捷登录
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value = "/shortcutLogin", method = RequestMethod.POST)
    public Map<String, Object> shortcutLogin(HttpServletRequest request,@RequestParam(value = "phone")String phone,
                                             @RequestParam(value = "code", required = false)String code,
                                             @RequestParam(value = "qcellcoreId", required = false)Integer qcellcoreId){
        Map<String, Object> resultMap = new HashMap<>();
        if (IsObjectNullUtils.is(phone)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "您输入的信息不完整！");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(qcellcoreId)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息！");
            resultMap.put("data", "");
            return resultMap;
        }
        XydPhoneQcellcore xydPhoneQcellcore1 = xydPhoneQcellcoreService.selectByPrimaryKey(qcellcoreId);
        if (IsObjectNullUtils.is(xydPhoneQcellcore1)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息，请稍后重试！");
            resultMap.put("data", "");
            return resultMap;
        }
        if (!PhoneCheckUtil.phoneTypeCheck(phone, xydPhoneQcellcore1.getPhonePrefix())){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "手机号码格式不正确！");
            resultMap.put("data", "");
            return resultMap;
        }
        XydParents xydParents = new XydParents();
        xydParents.setPhoneNumber(phone);
        xydParents.setStatus("1");
        List<XydParents> xydParentsList = xydParentsService.selectByEntityList(xydParents);
        if (IsObjectNullUtils.is(xydParentsList) || xydParentsList.size() < 1){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("data", "");
            resultMap.put("msg", "无法获取到相关信息！");
            return resultMap;
        }
        xydParents = xydParentsList.get(0);
        XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(xydParents.getQcellcoreId());
        if (IsObjectNullUtils.is(xydPhoneQcellcore)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("data", "");
            resultMap.put("msg", "无法获取到相关信息！");
            return resultMap;
        }
        //删除之前的token
        if (!IsObjectNullUtils.is(xydParents.getToken())){
            redisService.remove(xydParents.getToken());
        }
        String token=TokenProccessor.getInstance().makeToken();
        System.out.println("登录成功："+token);
        xydParents.setToken(token);
        XydParents parents = new XydParents();
        parents.setId(xydParents.getId());
        parents.setToken(token);
        xydParentsService.updateByPrimaryKeySelective(parents);
        TokenProccessor.getInstance().saveUserInfoByTokenToRedis(xydParents);
        Map<String, Object> map = new HashMap<>();
        XydSystemRemind xydSystemRemind = new XydSystemRemind();
        xydSystemRemind.setStates("1");
        xydSystemRemind.setIsRemind("1");
        xydSystemRemind.setParentsId(xydParents.getId());
        //判断是否需要完善儿童个人信息提醒
        xydSystemRemind.setType("2");
        List<XydSystemRemind> list = xydSystemRemindService.selectList(xydSystemRemind);
        if (!IsObjectNullUtils.is(list) && list.size() > 0){
            map.put("IsRemind", "1"); //未完善儿童个人信息
        }else {
            map.put("IsRemind", "2");//已完善儿童个人信息
        }
        map.put("chilSex", "0");
        map.put("chilName", null);
        map.put("chilPhoto", null);
        // 0男 1女
        if (!IsObjectNullUtils.is(xydParents.getChildId())){
            XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
            if (!IsObjectNullUtils.is(xydChild)){
                map.put("chilSex", xydChild.getSex() == null ? "0" : xydChild.getSex());
                map.put("chilName", xydChild.getName());
                map.put("chilPhoto", xydChild.getPhoto());
            }
        }
        xydParents.setPassword(null);
        map.put("parents", xydParents);
        resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
        resultMap.put("msg", "登录成功！");
        resultMap.put("data", map);
        return resultMap;
    }

    /**
     * 发送验证码
     * @param phone
     * @param qcellcoreId
     * @param type 1 忘记密码  2 修改密码 3：更换手机号旧发送验证码 4:更换手机号新手机发送验证码
     * @return
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    public Map sendCode(@RequestParam(value = "phone")String phone,
                        @RequestParam(value = "qcellcoreId", required = false)Integer qcellcoreId,
                        @RequestParam(value = "type", required = false)String type){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if (IsObjectNullUtils.is(phone) && IsObjectNullUtils.is(type) && IsObjectNullUtils.is(qcellcoreId)){
            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
            resultMap.put("msg", "请输入相关信息！");
            resultMap.put("data", "");
            return resultMap;
        }
        XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(qcellcoreId);
        if (IsObjectNullUtils.is(xydPhoneQcellcore)){
            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
            resultMap.put("msg", "无法获取相关信息！");
            resultMap.put("data", "");
            return resultMap;
        }
        if (!PhoneCheckUtil.phoneTypeCheck(phone, xydPhoneQcellcore.getPhonePrefix())){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "手机号码格式不正确！");
            resultMap.put("data", "");
            return resultMap;
        }
        if ("1".equals(type)){
            //忘记密码验证码
            //更具手机号进行发送验证码
            if ("86".equals(xydPhoneQcellcore.getPhonePrefix())){
                resultMap = AliyunSendUtil.sendCode(phone, redisTemplate, xydPhoneQcellcore.getPhonePrefix(), global.getUpdatePasswordMSM());
            }else {
                resultMap = AliyunSendUtil.sendCountryCode(phone, redisTemplate, xydPhoneQcellcore, global.getWupdatePasswordMSM());
            }
            return resultMap;
        }
        if ("2".equals(type)){
            if ("86".equals(xydPhoneQcellcore.getPhonePrefix())){
                resultMap = AliyunSendUtil.sendCode(phone, redisTemplate, xydPhoneQcellcore.getPhonePrefix(), global.getUpdatePasswordMSM());
            }else {
                resultMap = AliyunSendUtil.sendCountryCode(phone, redisTemplate, xydPhoneQcellcore, global.getWupdatePasswordMSM());
            }
            return resultMap;
        }
        if ("3".equals(type)){
            //根据手机号查询是否已注册
            XydParents xydParents = new XydParents();
            xydParents.setPhoneNumber(phone);
            List<XydParents> xydParentsList = xydParentsService.selectByEntityList(xydParents);
            if (IsObjectNullUtils.is(xydParentsList) || xydParentsList.size() < 1){
                resultMap.put("msg", "已绑定手机号输入错误，请重新输入！");
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("data", "");
                return resultMap;
            }
            if ("86".equals(xydPhoneQcellcore.getPhonePrefix())){
                resultMap = AliyunSendUtil.sendCode(phone, redisTemplate, xydPhoneQcellcore.getPhonePrefix(), global.getOldPhoneMSM());
            }else {
                resultMap = AliyunSendUtil.sendCountryCode(phone, redisTemplate, xydPhoneQcellcore, global.getWoldPhoneMSM());
            }
            return resultMap;
        }
        if ("4".equals(type)){
            //根据手机号查询是否已注册
            XydParents xydParents = new XydParents();
            xydParents.setPhoneNumber(phone);
            List<XydParents> xydParentsList = xydParentsService.selectByEntityList(xydParents);
            if (!IsObjectNullUtils.is(xydParentsList) && xydParentsList.size() > 0){
                resultMap.put("msg", "该手机号已注册,不能重复绑定！");
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("data", "");
                return resultMap;
            }
            if ("86".equals(xydPhoneQcellcore.getPhonePrefix())){
                resultMap = AliyunSendUtil.sendCode(phone, redisTemplate, xydPhoneQcellcore.getPhonePrefix(), global.getNewPhoneMSM());

            }else {
                resultMap = AliyunSendUtil.sendCountryCode(phone, redisTemplate, xydPhoneQcellcore, global.getWnewPhoneMSM());
            }
            return resultMap;
        }
        resultMap.put("msg", "系统繁忙，请稍后重试！");
        resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
        resultMap.put("data", "");
        return resultMap;
    }

    /**
     * 忘记密码
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Map<String, Object> resetPassword(@RequestParam(value = "phone")String phone,
                                              @RequestParam(value = "password")String password){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if (IsObjectNullUtils.is(phone)|| IsObjectNullUtils.is(password)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "您输入的信息不完整！");
            resultMap.put("data", "");
            return resultMap;
        }
        try{
            XydParents xydParents = new XydParents();
             xydParents.setPhoneNumber(phone);
            List<XydParents> xydParentsList = xydParentsService.selectByEntityList(xydParents);
            if (!IsObjectNullUtils.is(xydParentsList) && xydParentsList.size() > 0){
                xydParents = xydParentsList.get(0);
                XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(xydParents.getQcellcoreId());
                if (IsObjectNullUtils.is(xydPhoneQcellcore)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                    resultMap.put("msg", "无法获取相关信息！");
                    resultMap.put("data", "");
                    return resultMap;
                }
                xydParents.setPassword(MyMD5Util.getEncryptedPwd(password));
                if (xydParentsService.updateByPrimaryKeySelective(xydParents) > 0){
                    resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                    resultMap.put("msg", "密码修改成功！");
                    resultMap.put("data", "");
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                    resultMap.put("msg", "密码修改失败！");
                    resultMap.put("data", "");
                }
            }else {
                resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
                resultMap.put("msg", "无法获取相关信息！");
                resultMap.put("data", "");
            }
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
        }finally {
            return resultMap;
        }
    }

    /**
     * 修改密码
     * @param token
     * @param oldPassword
      * @param newPassword
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Map<String, Object> updatePassword(@RequestParam(value = "token")String token,
                                              @RequestParam(value = "oldPassword")String oldPassword,
                                              @RequestParam(value = "newPassword")String newPassword){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if (IsObjectNullUtils.is(oldPassword) || IsObjectNullUtils.is(newPassword)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "您输入的信息不完整！");
            resultMap.put("data", "");
            return resultMap;
        }
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录！");
            resultMap.put("data", "");
            return resultMap;
        }
        try{
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents)object).getId());
                if (!IsObjectNullUtils.is(xydParents)){
                    if (MyMD5Util.validPassword(oldPassword, xydParents.getPassword())){
                        if (oldPassword.equals(newPassword)){
                            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                            resultMap.put("msg", "新密码不能和当前密码重复！");
                            resultMap.put("data", "");
                            return resultMap;
                        }
                        XydParents parents = new XydParents();
                        parents.setId(xydParents.getId());
                        parents.setPassword(MyMD5Util.getEncryptedPwd(newPassword));
                        if (xydParentsService.updateByPrimaryKeySelective(parents) > 0){
                            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                            resultMap.put("data", "");
                            resultMap.put("msg", "修改成功，请重新登录！");
                            return resultMap;
                        }else {
                            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                            resultMap.put("data", "");
                            resultMap.put("msg", "修改异常，请稍后重试！");
                            return resultMap;
                        }
                    }else {
                        resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                        resultMap.put("msg", "当前密码错误，请重新输入！");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                }
            }
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录！");
            resultMap.put("data", "");
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
        }finally {
            return resultMap;
        }
    }

    /**
     * 完善儿童个人信息
     */
    @RequestMapping(value = "/perfection/addChild", method = RequestMethod.POST)
    public Map<String, Object> addChild(@RequestParam(required = false, value = "token")String token,
                                        @RequestParam(required = false, value = "name")String name,
                                        @RequestParam(required = false, value = "sex")String sex,
                                        @DateTimeFormat(pattern="yyyy-MM-dd")Date birthdate,
                                        @RequestParam(required = false, value = "medical")String medical,
                                        @RequestParam(required = false, value = "medicalState")String medicalState,
                                        @RequestParam(required = false, value = "firstLanguage")String firstLanguage,
                                        @RequestParam(required = false, value = "firstRests")String firstRests,
                                        @RequestParam(required = false, value = "secondLanguage")String secondLanguage,
                                        @RequestParam(required = false, value = "secondRests")String secondRests,
                                        @RequestParam(required = false, value = "fatherCulture")String fatherCulture,
                                        @RequestParam(required = false, value = "motherCulture")String motherCulture,
                                        @RequestParam(required = false, value = "trainingMethod")String trainingMethod,
                                        @RequestParam(required = false, value = "trainingRests")String trainingRests,
                                        @RequestParam(required = false, value = "states")String states,
                                        @RequestParam(required = false, value = "countiyId")Integer countiyId,
                                        @RequestParam(required = false, value = "cityId")Integer cityId,
                                        @RequestParam(required = false, value = "provinceId")Integer provinceId
                                        ){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            if (IsObjectNullUtils.is(token)){
                resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                resultMap.put("msg", "无法获取用户信息，请重新登录！");
                resultMap.put("data", "");
                return resultMap;
            }
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = (XydParents)object;
                xydParents = xydParentsService.selectByPrimaryKey(xydParents.getId());
                if (IsObjectNullUtils.is(xydParents)){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                    resultMap.put("msg", "无法获取用户信息请重新登录！");
                    resultMap.put("data", "");
                    return resultMap;
                }
                XydChild xydChild = new XydChild();
                xydChild.setParentsId(xydParents.getId());
                //查询是否已添加儿童信息
                List<XydChild> xydChildList = xydChildService.selectByList(xydChild);
                if (!IsObjectNullUtils.is(xydChildList) && xydChildList.size() > 0){
                    if ("1".equals(xydChildList.get(0).getPerfection())){
                        resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                        resultMap.put("msg", "您已完善了儿童个人信息！");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                    xydChild.setId(xydChildList.get(0).getId());
                }
                //添加居住地
                XydArea xydAreaProvince = xydAreaService.selectByPrimaryKey(provinceId);
                if (!IsObjectNullUtils.is(xydAreaProvince)){
                    xydChild.setProvince(xydAreaProvince.getAreaname());
                }
                XydArea xydAreaCountiy = xydAreaService.selectByPrimaryKey(countiyId);
                if (!IsObjectNullUtils.is(xydAreaCountiy)){
                    xydChild.setCountiy(xydAreaCountiy.getAreaname());
                }
                XydArea xydAreaCity = xydAreaService.selectByPrimaryKey(cityId);
                if (!IsObjectNullUtils.is(xydAreaCity)){
                    xydChild.setCity(xydAreaCity.getAreaname());
                }
                if (!IsObjectNullUtils.is(name)){
                    xydChild.setName(name);
                }
                if (!IsObjectNullUtils.is(birthdate)){
                    xydChild.setBirthdate(birthdate);
                }
                if (!IsObjectNullUtils.is(sex)){
                    xydChild.setSex(sex);
                }
                //判断获取的医学诊断数据是否有效
                if (!IsObjectNullUtils.is(medical)){
                    MedicalTypeEnum medicalTypeEnum = MedicalTypeEnum.getEnum(Integer.parseInt(medical));
                    if (medicalTypeEnum != null ){
                        xydChild.setMedical(medical);
                    }
                }
                if (!IsObjectNullUtils.is(medicalState)){
                    xydChild.setMedicalState(medicalState);
                }
                //添加第一语言
                if (!IsObjectNullUtils.is(firstLanguage)){
                    LanguageTypeEnum languageTypeEnum = LanguageTypeEnum.getEnum(Integer.parseInt(firstLanguage));
                    if (languageTypeEnum != null ){
                        xydChild.setFirstLanguage(firstLanguage);
                    }
                }
                if (!IsObjectNullUtils.is(firstRests)){
                    xydChild.setFirstRests(firstRests);
                }
                //添加第二语言
                if (!IsObjectNullUtils.is(secondLanguage)){
                    LanguageTypeEnum languageTypeEnum = LanguageTypeEnum.getEnum(Integer.parseInt(secondLanguage));
                    if (languageTypeEnum != null ){
                        xydChild.setSecondLanguage(secondLanguage);
                    }
                }
                if (!IsObjectNullUtils.is(secondRests)){
                    xydChild.setSecondRests(secondRests);
                }
                if (!IsObjectNullUtils.is(fatherCulture)){
                    xydChild.setFatherCulture(fatherCulture);
                }
                if (!IsObjectNullUtils.is(motherCulture)){
                    xydChild.setMotherCulture(motherCulture);
                }
                if (!IsObjectNullUtils.is(trainingMethod)){
                    xydChild.setTrainingMethod(trainingMethod);
                }
                if (!IsObjectNullUtils.is(trainingRests)){
                    xydChild.setTrainingRests(trainingRests);
                }
                //是否完善儿童个人信息：0 否 1是',
                xydChild.setPerfection(states);
                xydChild.setCreateTime(new Date());
                //状态：1正常 2 删除
                xydChild.setStatus("1");
                xydChild.setParentsId(xydParents.getId());
                xydChild.setPhoneNumber(xydParents.getPhoneNumber());
                if (xydChild.getId() == null){
                    if (xydChildService.insertSelective(xydChild) > 0){
                        xydParents.setChildId(xydChild.getId());
                        xydParents.setUpdateTime(new Date());
                        if (xydParentsService.updateByPrimaryKeySelective(xydParents) > 0){
                            //完善修改提醒
                            if ("1".equals(states)){
                                    XydSystemRemind xydSystemRemind = new XydSystemRemind();
                                    xydSystemRemind.setParentsId(xydParents.getId());
                                    xydSystemRemind.setStates("1");
                                    xydSystemRemind.setIsRemind("1");
                                    xydSystemRemind.setType("2");
                                    List<XydSystemRemind> list = xydSystemRemindService.selectList(xydSystemRemind);
                                    if (!IsObjectNullUtils.is(list) && list.size() > 0){
                                        xydSystemRemind.setId(list.get(0).getId());
                                        xydSystemRemind.setUpdateTime(new Date());
                                        xydSystemRemind.setIsRemind("2");
                                        xydSystemRemind.setUserId(xydChild.getId());
                                        xydSystemRemindService.updateByPrimaryKeySelective(xydSystemRemind);
                                    }
                                }
                            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                            resultMap.put("msg", "添加成功！");
                            resultMap.put("data", xydChild);
                            return resultMap;
                        }else {
                            resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                            resultMap.put("msg", "添加失败！");
                            resultMap.put("data", "");
                            return resultMap;
                        }
                    }
                }else {
                    //修改儿童信息
                    if (xydChildService.updateByPrimaryKeySelective(xydChild) > 0){
                                //完善修改提醒
                                if ("1".equals(states)){
                                    XydSystemRemind xydSystemRemind = new XydSystemRemind();
                                    xydSystemRemind.setParentsId(xydParents.getId());
                                    xydSystemRemind.setStates("1");
                                    xydSystemRemind.setIsRemind("1");
                                    xydSystemRemind.setType("2");
                                    List<XydSystemRemind> list = xydSystemRemindService.selectList(xydSystemRemind);
                                    if (!IsObjectNullUtils.is(list) && list.size() > 0){
                                        xydSystemRemind.setId(list.get(0).getId());
                                        xydSystemRemind.setUpdateTime(new Date());
                                        xydSystemRemind.setIsRemind("2");
                                        xydSystemRemind.setUserId(xydChild.getId());
                                        xydSystemRemindService.updateByPrimaryKeySelective(xydSystemRemind);
                                    }
                                }
                            resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                            resultMap.put("msg", "修改成功！");
                            resultMap.put("data", xydChild);
                            return resultMap;
                    }
                }
            }
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取登录信息，请重新登录！");
            resultMap.put("data", "");
            return resultMap;
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
            return resultMap;
        }
    }

    /**
     * 修改儿童昵称
     * @param token
     * @param name
     * @return
     */
    @RequestMapping(value = "/updateChildInfo", method = RequestMethod.POST)
    public Map updateChildInfo(@RequestParam(value = "token")String token, @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "photo", required = false)String photo){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (IsObjectNullUtils.is(token)){
                resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                resultMap.put("msg", "无法获取用户信息，请重新登录！");
                resultMap.put("data", "");
                return resultMap;
            }
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents) object).getId());
                if (!IsObjectNullUtils.is(xydParents) && IsObjectNullUtils.is(xydParents.getChildId())){
                    resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                    resultMap.put("msg", "您还没有完善儿童个人信息！");
                    resultMap.put("data", "");
                    return resultMap;
                }
                if (!IsObjectNullUtils.is(xydParents)){
                    XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                    if (IsObjectNullUtils.is(xydChild)){
                        resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                        resultMap.put("msg", "无法获取到相关信息！");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                    XydChild updateChild = new XydChild();
                    if (!IsObjectNullUtils.is(name)){
                        updateChild.setName(name.trim());
                    }
                    if (!IsObjectNullUtils.is(photo)){
                        updateChild.setPhoto(photo);
                    }
                    updateChild.setId(xydChild.getId());
                    if (xydChildService.updateByPrimaryKeySelective(updateChild) > 0){
                        resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                        resultMap.put("msg", "更新成功！");
                        resultMap.put("data", xydChildService.selectByPrimaryKey(xydChild.getId()));
                        return resultMap;
                    }else {
                        resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                        resultMap.put("msg", "更新失败！");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                }else {
                    resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
                    resultMap.put("msg", "无法获取用户信息，请重新登录！");
                    resultMap.put("data", "");
                    return resultMap;
                }
            }
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录！");
            resultMap.put("data", "");
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "内部服务器错误！");
            resultMap.put("data", "");
            e.printStackTrace();
        }finally {
            return resultMap;
        }
    }

    /**
     * 用户修改手机号
     * @param token
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value = "/updatePhone", method = RequestMethod.POST)
    public Map updatePhone(@RequestParam(value = "token")String token, @RequestParam(value = "phone")String phone,
                           @RequestParam(value = "districeId")Integer districeId, @RequestParam(value = "code")String code){
        Map<String, Object> resultMap = new HashMap<>();
        if (IsObjectNullUtils.is(token)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录！");
            resultMap.put("data", "");
            return resultMap;
        }

        if (IsObjectNullUtils.is(code) || IsObjectNullUtils.is(phone) || IsObjectNullUtils.is(districeId)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息，请稍后重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(districeId);
        if (IsObjectNullUtils.is(xydPhoneQcellcore) || "2".equals(xydPhoneQcellcore.getStates())){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息，请稍后重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (!PhoneCheckUtil.phoneTypeCheck(phone, xydPhoneQcellcore.getPhonePrefix())){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "手机号码格式不正确！");
            resultMap.put("data", "");
            return resultMap;
        }
        //获取短信code
        TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
        String valueByKeyTheRedis = (String) TokenProccessor.getInstance().getValueByKeyTheRedis(xydPhoneQcellcore.getPhonePrefix()+phone);
        if (IsObjectNullUtils.is(valueByKeyTheRedis) || !code.equals(valueByKeyTheRedis)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "验证码错误!");
            resultMap.put("data", "");
            return resultMap;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents)object).getId());
                if (!IsObjectNullUtils.is(xydParents)){
                    //获取该孩子的信息进行更新
                    if (!IsObjectNullUtils.is(xydParents.getChildId())){
                        XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                        if (!IsObjectNullUtils.is(xydChild)){
                            xydChild.setPhoneNumber(phone);
                            if (xydChildService.updateByPrimaryKeySelective(xydChild) < 0){
                                resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                                resultMap.put("msg", "修改失败！");
                                resultMap.put("data", "");
                                return resultMap;
                            }
                        }
                    }
                    xydParents.setPhoneNumber(phone);
                    if (xydParentsService.updateByPrimaryKeySelective(xydParents) > 0){
                        resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
                        resultMap.put("msg", "更换成功！");
                        resultMap.put("data", "");
                        return resultMap;
                    }else {
                        resultMap.put("code", ResultStant.RESULT_CODE_ERROR);
                        resultMap.put("msg", "修改失败！");
                        resultMap.put("data", "");
                        return resultMap;
                    }
                }
            }
            resultMap.put("code", ResultStant.RESULT_CODE_LOGIN);
            resultMap.put("msg", "无法获取用户信息，请重新登录！");
            resultMap.put("data", "");
            return resultMap;
        }catch (Exception e){
            resultMap.put("code", ResultStant.RESULT_CODE_SERVICE);
            resultMap.put("msg", "系统繁忙，请稍后重试！");
            resultMap.put("data", "");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 修改手机号 旧手机号验证码验证
     * @param phone
     * @param code
     * @param districeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/efficacyCode", method = RequestMethod.POST)
    public Map updatePhone(@RequestParam(value = "phone")String phone, @RequestParam(value = "code")String code,
                           @RequestParam(value = "districeId")Integer districeId){
        Map<String, Object> resultMap = new HashMap<>();

        if (IsObjectNullUtils.is(code) || IsObjectNullUtils.is(phone) || IsObjectNullUtils.is(districeId)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息，请稍后重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(districeId);
        if (IsObjectNullUtils.is(xydPhoneQcellcore) || "2".equals(xydPhoneQcellcore.getStates())){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "无法获取相关信息，请稍后重试!");
            resultMap.put("data", "");
            return resultMap;
        }
        if (!PhoneCheckUtil.phoneTypeCheck(phone, xydPhoneQcellcore.getPhonePrefix())){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "手机号码格式不正确！");
            resultMap.put("data", "");
            return resultMap;
        }
        //获取短信code
        TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
        String valueByKeyTheRedis = (String) TokenProccessor.getInstance().getValueByKeyTheRedis(xydPhoneQcellcore.getPhonePrefix()+phone);
        if (IsObjectNullUtils.is(valueByKeyTheRedis) || !code.equals(valueByKeyTheRedis)){
            resultMap.put("code", ResultStant.RESULT_CODE_LOSE);
            resultMap.put("msg", "验证码错误!");
            resultMap.put("data", "");
            return resultMap;
        }
        redisTemplate.delete(xydPhoneQcellcore.getPhonePrefix()+phone);
        resultMap.put("code", ResultStant.RESULT_CODE_SUCCESS);
        resultMap.put("msg", "验证码正确");
        resultMap.put("data", "");
        return resultMap;
    }

    /**
     * 获取儿童个人信息
     * @param token
     * @return
     */
    @RequestMapping(value = "/getChilInfo")
    @ResponseBody
    public Map getChilInfo(@RequestParam(value = "token")String token){
        Map<String, Object> result = new HashMap<>();
        if (IsObjectNullUtils.is(token) ){
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "无法获取用户信息!");
            result.put("data", "");
            return result;
        }
        try {
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            Object object = TokenProccessor.getInstance().getValueByKeyTheRedis(token);
            if (!IsObjectNullUtils.is(object) && object instanceof XydParents) {
                XydParents xydParents = xydParentsService.selectByPrimaryKey(((XydParents)object).getId());
                if (!IsObjectNullUtils.is(xydParents)) {
                    Map<String, Object> data = new HashMap<>();
                    if (!IsObjectNullUtils.is(xydParents.getChildId())){
                        XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
                        result.put("msg", "");
                        data.put("xydChild", xydChild);
                        data.put("IsRemind", "2");
                        result.put("data", data);
                        return result;
                    }else {
                        result.put("code", ResultStant.RESULT_CODE_LOSE);
                        result.put("msg", "无法获取用户信息!");
                        data.put("IsRemind", "1");
                        result.put("data", data);
                        return result;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", ResultStant.RESULT_CODE_LOSE);
            result.put("msg", "系统繁忙，请稍后重试!");
            result.put("data", "");
            return result;
        }
        result.put("code", ResultStant.RESULT_CODE_LOSE);
        result.put("msg", "无法获取用户信息!");
        result.put("data", "");
        return result;
    }
}
