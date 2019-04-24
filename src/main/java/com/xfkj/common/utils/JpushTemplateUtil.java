package com.xfkj.common.utils;

import com.xfkj.common.config.Global;
import com.xfkj.common.model_custom.MyThreadLocal;
import com.xfkj.common.model_custom.Template;
import com.xfkj.model.XydChild;
import com.xfkj.model.XydParents;
import com.xfkj.model.XydPhoneQcellcore;
import com.xfkj.model.XydSystemRemind;
import com.xfkj.service.XydChildService;
import com.xfkj.service.XydParentsService;
import com.xfkj.service.XydPhoneQcellcoreService;
import com.xfkj.service.XydSystemRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by King on 2018/11/22.
 * 三个月定时推送消息提醒
 */
public class JpushTemplateUtil implements Runnable {

    @Autowired
    Global global;
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    private XydSystemRemindService xydSystemRemindService;
    @Autowired
    private XydPhoneQcellcoreService xydPhoneQcellcoreService;
    @Autowired
    private XydChildService xydChildService;


    private static Template tem = new Template();

    @RequestMapping(value = "/mxga")
    @ResponseBody
    public Object sendTemplate( @RequestParam(value = "threeMonthTimeLong") Long threeMonthTimeLong,
                                @RequestParam(value = "code") String code,
                                @RequestParam(value = "phonePrefix") String phonePrefix,
                                @RequestParam(value = "phoneNumber") String phoneNumber,
                                @RequestParam(value = "id") Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            tem.setCode(code);
            tem.setPhone(phoneNumber);
            tem.setPrefix(phonePrefix);
            tem.setRemindId(id);
            tem.setMillisecond(threeMonthTimeLong);
            new Thread(this).start();
            result.put("status", 200);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "500");
            result.put("msg", "系统繁忙，请稍后重试！");
            return result;
        }
    }

    @Override
    public void run() {
        Template template = new Template();
        template.setMillisecond(tem.getMillisecond());
        template.setCode(tem.getCode());
        template.setPhone(tem.getPhone());
        template.setPrefix(tem.getPrefix());
        template.setRemindId(tem.getRemindId());
        MyThreadLocal.set(template);
        //所有的数据都在 template 直接用就行
        template = MyThreadLocal.get();
        try {
            Thread.sleep(template.getMillisecond());
            //↓↓↓↓↓↓↓ 逻辑代码 ↓↓↓↓↓↓↓ sleep之后发送短信 调用发送短信接口
            XydSystemRemind systemRemind = xydSystemRemindService.selectByPrimaryKey(template.getRemindId());
            if (!IsObjectNullUtils.is(systemRemind)){
                XydParents xydParents = xydParentsService.selectByPrimaryKey(systemRemind.getParentsId());
                if (!IsObjectNullUtils.is(xydParents)){
                    XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                    if (!IsObjectNullUtils.is(xydChild)){
                        //-----------   调用极光推送  -----------
                        String type = "alias";
                        String value = xydParents.getId() + "";
                        String alert = "雨滴2  "+ xydChild.getName() +"小朋友已经在雨滴2上学习了3个月了，赶紧评估一下他的学习效果吧！";
                        JpushClientUtil.pushNotice(type,value,alert);
                        System.out.println("alias:"+value+"推送内容:"+alert);
                    }
                }

            }
            Thread.sleep(24*60*60*1000L);
            for (int i = 0; i < 2; i++){
                XydSystemRemind xydSystemRemind = xydSystemRemindService.selectByPrimaryKey(template.getRemindId());
                if (IsObjectNullUtils.is(xydSystemRemind) || "2".equals(xydSystemRemind.getIsRemind())){
                    break;
                }else {
                    XydParents parents = xydParentsService.selectByPrimaryKey(systemRemind.getParentsId());
                    if (!IsObjectNullUtils.is(parents)){
                        XydChild child = xydChildService.selectByPrimaryKey(parents.getChildId());
                        if (!IsObjectNullUtils.is(child)){
                            //-----------   调用极光推送  -----------
                            String type = "alias";
                            String value = parents.getId() + "";
                            String alert = "雨滴2  "+ child.getName() +"小朋友已经在雨滴2上学习了3个月了，赶紧评估一下他的学习效果吧！";
                            JpushClientUtil.pushNotice(type,value,alert);
                            System.out.println("alias:"+value+"推送内容:"+alert);
                        }
                    }
                    Thread.sleep(24*60*60*1000L);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            MyThreadLocal.unset();
        }
        MyThreadLocal.unset();
    }


    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public XydParentsService getXydParentsService() {
        return xydParentsService;
    }

    public void setXydParentsService(XydParentsService xydParentsService) {
        this.xydParentsService = xydParentsService;
    }

    public XydSystemRemindService getXydSystemRemindService() {
        return xydSystemRemindService;
    }

    public void setXydSystemRemindService(XydSystemRemindService xydSystemRemindService) {
        this.xydSystemRemindService = xydSystemRemindService;
    }

    public XydPhoneQcellcoreService getXydPhoneQcellcoreService() {
        return xydPhoneQcellcoreService;
    }

    public void setXydPhoneQcellcoreService(XydPhoneQcellcoreService xydPhoneQcellcoreService) {
        this.xydPhoneQcellcoreService = xydPhoneQcellcoreService;
    }

    public XydChildService getXydChildService() {
        return xydChildService;
    }

    public void setXydChildService(XydChildService xydChildService) {
        this.xydChildService = xydChildService;
    }
}
