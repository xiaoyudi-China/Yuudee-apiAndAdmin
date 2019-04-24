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
import com.xfkj.service.impl.XydSystemRemindServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.rmi.CORBA.Util;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by King on 2018/10/15.
 * 三个月定时发送短信提醒
 */
@Controller
@RequestMapping(value = "/app")
public class TemplateUtils implements Runnable {

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
            System.out.println("用户："+template.getPhone() +" 定时任务开始++++");
            Thread.sleep(template.getMillisecond());
            //↓↓↓↓↓↓↓ 逻辑代码 ↓↓↓↓↓↓↓ sleep之后发送短信 调用发送短信接口
            if (IsObjectNullUtils.is(xydSystemRemindService)){
                System.out.println("时间到，为空！");
            }
            XydSystemRemind systemRemind = xydSystemRemindService.selectByPrimaryKey(template.getRemindId());
            System.out.println("用户："+template.getPhone() +" 定时任务结束，开始逻辑处理++++");
            if (!IsObjectNullUtils.is(systemRemind) && "2".equals(systemRemind.getIsRemind())){
                XydParents xydParents = xydParentsService.selectByPrimaryKey(systemRemind.getParentsId());
                if (!IsObjectNullUtils.is(xydParents)){
                    XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(xydParents.getQcellcoreId());
                    XydChild xydChild = xydChildService.selectByPrimaryKey(xydParents.getChildId());
                    if (!IsObjectNullUtils.is(xydChild) && !IsObjectNullUtils.is(xydPhoneQcellcore)){
                        XydSystemRemind xydSystemRemind = new XydSystemRemind();
                        xydSystemRemind.setIsRemind("1");
                        xydSystemRemind.setStates("1");
                        xydSystemRemind.setId(systemRemind.getId());
                        if (xydSystemRemindService.updateByPrimaryKeySelective(xydSystemRemind) > 0){
                            //发送短信
                            System.out.println("用户："+xydParents.getPhoneNumber() +" ，发送短信++++");
                            if ("86".equals(xydPhoneQcellcore.getPhonePrefix())){
                                AliyunSendUtil.sendRemind(xydParents.getPhoneNumber(), xydPhoneQcellcore.getPhonePrefix(), xydChild.getName(), global.getRemindMSM());
                            }else {
                                AliyunSendUtil.sendCountryRemind(xydParents.getPhoneNumber(), xydPhoneQcellcore, xydChild.getName(), global.getWremindMSM());
                            }
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
                            XydPhoneQcellcore xydPhoneQcellcore = xydPhoneQcellcoreService.selectByPrimaryKey(parents.getQcellcoreId());
                            XydChild child = xydChildService.selectByPrimaryKey(parents.getChildId());
                            if (!IsObjectNullUtils.is(child) && !IsObjectNullUtils.is(xydPhoneQcellcore)){
                                System.out.println("用户："+xydParents.getPhoneNumber() +" ，发送短信++++");
                                if ("86".equals(xydPhoneQcellcore.getPhonePrefix())){
                                    AliyunSendUtil.sendRemind(parents.getPhoneNumber(), xydPhoneQcellcore.getPhonePrefix(), child.getName(), global.getRemindMSM());
                                }else {
                                    AliyunSendUtil.sendCountryRemind(parents.getPhoneNumber(), xydPhoneQcellcore, child.getName(), global.getWremindMSM());
                                }
                            }
                        }
                        Thread.sleep(24*60*60*1000L);
                    }
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
