package com.dkd.api;

import com.dkd.common.config.Global;
import com.dkd.common.utils.AliyunSendUtil;
import com.dkd.common.utils.TemplateUtils;
import com.dkd.model.XydPhoneQcellcore;
import com.dkd.service.XydChildService;
import com.dkd.service.XydParentsService;
import com.dkd.service.XydPhoneQcellcoreService;
import com.dkd.service.XydSystemRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/11/21.
 * 测试用
 */
@Controller
@RequestMapping(value = "/app")
public class TestApp {
    @Autowired
    private XydParentsService xydParentsService;
    @Autowired
    private XydPhoneQcellcoreService xydPhoneQcellcoreService;
    @Autowired
    private XydChildService xydChildService;
    @Autowired
    private XydSystemRemindService xydSystemRemindService;
    @Autowired
    Global global;
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "/mxg")
    @ResponseBody
    public Map getMap(@RequestParam(value = "threeMonthTimeLong") Long threeMonthTimeLong,
                      @RequestParam(value = "code") String code,
                      @RequestParam(value = "phonePrefix") String phonePrefix,
                      @RequestParam(value = "phoneNumber") String phoneNumber,
                      @RequestParam(value = "id")Integer id){
        TemplateUtils templateUtils = new TemplateUtils();
        templateUtils.setXydParentsService(xydParentsService);
        templateUtils.setXydChildService(xydChildService);
        templateUtils.setXydPhoneQcellcoreService(xydPhoneQcellcoreService);
        templateUtils.setXydSystemRemindService(xydSystemRemindService);
        templateUtils.setGlobal(global);
        Map<String, Object> result = new HashMap();
        templateUtils.sendTemplate(5*1000L, code, phonePrefix, phoneNumber, 97);
        result.put("data", "11");
        return result;

    }
    @RequestMapping(value = "/code")
    @ResponseBody
    public Map testCode(){
        Map<String, Object> result = new HashMap<>();

        XydPhoneQcellcore xydPhoneQcellcore = new XydPhoneQcellcore();
        xydPhoneQcellcore.setName("美国");
        xydPhoneQcellcore.setPhonePrefix("1");
        Map map = AliyunSendUtil.sendCountryText("17795591253", xydPhoneQcellcore, global.getWnounMSM());
        return map;

    }
}
