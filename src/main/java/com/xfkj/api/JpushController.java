package com.xfkj.api;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.xfkj.common.constant.ResultStant;
import com.xfkj.common.utils.IsObjectNullUtils;
import com.xfkj.common.utils.JpushClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by King on 2018/11/19.
 * 极光推送
 */
@Controller
@RequestMapping("/app/jpush")
public class JpushController {

    /**
     * 自定义消息推送
     * 备注：推送方式不为空时，推送的值也不能为空；推送方式为空时，推送值不做要求
     * @param type 推送方式：1、“tag”标签推送，2、“alias”别名推送
     * @param value 推送的标签或别名值
     * @param alert 推送的内容
     * @return
     */
    @RequestMapping(value = "/pushMsg")
    @ResponseBody
    public Map pushMsg(HttpServletRequest request, @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "value", required = false)String value,
                               @RequestParam(value = "alert", required = false)String alert) {
        Map<String, Object> result = new HashMap<String, Object>();
        if(!IsObjectNullUtils.is(type)){
            if(IsObjectNullUtils.is(alert)){
                result.put("code", ResultStant.RESULT_CODE_NULL);
                result.put("msg","推送方式不为空时，推送的值也不能为空");
                return result;
            }
        }
        JPushClient jPushClient=new JPushClient("fca18d9c783adcd59c76c188","ee9177f68df4a5eac387b555");
        PushPayload pushPayload=  JpushClientUtil.push_Android("tagkefu");
        try{
            PushResult pushResult=jPushClient.sendPush(pushPayload);
        }catch(Exception e){
            result.put("code", ResultStant.RESULT_CODE_SERVICE);
            result.put("msg","系统繁忙，请稍后重试！");
            e.printStackTrace();
        }
        result.put("code", ResultStant.RESULT_CODE_SUCCESS);
        result.put("msg","推送成功");
        return result;
    }
}
