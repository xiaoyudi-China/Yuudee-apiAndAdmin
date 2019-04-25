package com.dkd.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.dkd.common.constant.ResultStant;
import com.dkd.model.XydPhoneQcellcore;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maixiaogang on 2018/7/20.
 */
public class  AliyunSendUtil {
    private RedisTemplate redisTemplate;
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIEwm2LXa64cdB";
    static final String accessKeySecret = "6VW6F1l7GcZiYvrWWbnNzxdiEwiDyW";
    static final String bankUrl = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=";
    static final String getBankUrl = "&cardBinCheck=true";


    public static Map sendCode(String phone, RedisTemplate redisTemplate, String sendType, String MSMCode){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (IsObjectNullUtils.is(phone)) {
                result.put("code", ResultStant.RESULT_CODE_LOSE);
                result.put("msg", "phone 获取失败");
                result.put("data", "");
                return result;
            }
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("雨滴2");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(MSMCode); //国内
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            String oldCode = (String) TokenProccessor.getInstance().getValueByKeyTheRedis(sendType+phone);
            if (IsObjectNullUtils.is(oldCode)){
                //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
                 oldCode = (int) ((Math.random() * 9 + 1) * 100000)+"";

            }
            String code = "{\"code\":\"";
            code = code + oldCode + "\"}";
            request.setTemplateParam(code);
            System.out.println("phone："+phone+"code"+code);
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            System.out.println("sendSmsResponse："+sendSmsResponse+"getMessage:"+sendSmsResponse.getMessage());
            if(!sendSmsResponse.getMessage().equals("OK")){
                if (sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", " 您已超限，请您1小时后重试!");
                    return result;
                }
                result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                result.put("msg", "发送失败，请稍后重试！");
                return result;
            }
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            TokenProccessor.getInstance().saveKeyValueToRedis(sendType+phone,  oldCode);
            result.put("code", ResultStant.RESULT_CODE_SUCCESS);
            result.put("msg", "发送成功");
            result.put("data", "");
            System.out.println("result:"+result);
            return result;
        } catch (Exception e) {
            result.put("code", ResultStant.RESULT_CODE_SERVICE);
            result.put("msg", "系统繁忙，请稍后重试！");
            result.put("data", "");
            return result;
        }
    }


    public static Map sendCountryCode(String phone, RedisTemplate redisTemplate, XydPhoneQcellcore phoneQcellcore, String MSMCode){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (IsObjectNullUtils.is(phone)) {
                result.put("code", ResultStant.RESULT_CODE_LOSE);
                result.put("msg", "phone 获取失败");
                result.put("data", "");
                return result;
            }
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            QueryInterSmsIsoInfoRequest request1 = new QueryInterSmsIsoInfoRequest();
            request1.setCountryName(phoneQcellcore.getName());
            String countryCode = "";
            //hint 此处可能会抛出异常，注意catch
            QueryInterSmsIsoInfoResponse queryInterSmsIsoInfoResponse = acsClient.getAcsResponse(request1);
            if(queryInterSmsIsoInfoResponse.getCode() != null && queryInterSmsIsoInfoResponse.getCode().equals("OK")) {
                List<QueryInterSmsIsoInfoResponse.IsoSupportDTO> isoSupportDTOs = queryInterSmsIsoInfoResponse.getIsoSupportDTOs();
                for(QueryInterSmsIsoInfoResponse.IsoSupportDTO isoSupportDTO : isoSupportDTOs) {
                    if (isoSupportDTO.getIsoCode().equals(phoneQcellcore.getPhonePrefix())){
                        System.out.println("获取的发送国家Code:"+isoSupportDTO.getCountryCode());
                        countryCode = isoSupportDTO.getCountryCode();
                    }
                }
            }


            //组装请求对象-具体描述见控制台-文档部分内容
            SendInterSmsRequest request = new SendInterSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:对应国家的国家编号,国家编号的获取详见国际短信国家编码获取API接口
            request.setCountryCode(countryCode);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("雨滴2");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(MSMCode);
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            String oldCode = (String) TokenProccessor.getInstance().getValueByKeyTheRedis(phoneQcellcore.getPhonePrefix()+phone);
            if (IsObjectNullUtils.is(oldCode)){
                //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
                oldCode = (int) ((Math.random() * 9 + 1) * 100000)+"";

            }
            String code = "{\"code\":\"";
            code = code + oldCode + "\"}";
            request.setTemplateParam(code);
            System.out.println("phone："+phone+"code"+code);
            SendInterSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            System.out.println("sendSmsResponse："+sendSmsResponse+"getMessage:"+sendSmsResponse.getMessage());
            if(!sendSmsResponse.getMessage().equals("OK")){
                if (sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", " 您已超限，请您1小时后重试!");
                    return result;
                }
                result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                result.put("msg", "发送失败，请稍后重试！");
                return result;
            }
            TokenProccessor.getInstance().setRedisTemplate(redisTemplate);
            TokenProccessor.getInstance().saveKeyValueToRedis(phoneQcellcore.getPhonePrefix()+phone,  oldCode);
            result.put("code", ResultStant.RESULT_CODE_SUCCESS);
            result.put("msg", "发送成功");
            result.put("data", "");
            System.out.println("result:"+result);
            return result;
        } catch (Exception e) {
            result.put("code", ResultStant.RESULT_CODE_SERVICE);
            result.put("msg", "系统繁忙，请稍后重试！");
            result.put("data", "");
            return result;
        }
    }

    //发送文本内容
    public static Map sendText(String phone, String sendType, String MSMCode){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (IsObjectNullUtils.is(phone)) {
                result.put("code", ResultStant.RESULT_CODE_LOSE);
                result.put("msg", "phone 获取失败");
                return result;
            }
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("雨滴2");
            //必填:短信模板-可在短信控制台中找到
            if (sendType.equals("86")){
                request.setTemplateCode(MSMCode); //国内
            }else {
                request.setTemplateCode(MSMCode); //国际
            }
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(!sendSmsResponse.getMessage().equals("OK")){
                if (sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", " 您已超限，请您1小时后重试!");
                    return result;
                }
                result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                result.put("msg", "发送失败，请稍后重试！");
                return result;
            }
            result.put("code", ResultStant.RESULT_CODE_SUCCESS);
            result.put("msg", "发送成功");
            return result;
        } catch (Exception e) {
            result.put("code", ResultStant.RESULT_CODE_SERVICE);
            result.put("msg", "系统繁忙，请稍后重试！");
            return result;
        }
    }

    //国际发送文本内容
    public static Map sendCountryText(String phone, XydPhoneQcellcore phoneQcellcore, String MSMCode){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (IsObjectNullUtils.is(phone)) {
                result.put("code", ResultStant.RESULT_CODE_LOSE);
                result.put("msg", "phone 获取失败");
                return result;
            }
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            QueryInterSmsIsoInfoRequest request1 = new QueryInterSmsIsoInfoRequest();
            request1.setCountryName(phoneQcellcore.getName());
            String countryCode = "";
            //hint 此处可能会抛出异常，注意catch
            QueryInterSmsIsoInfoResponse queryInterSmsIsoInfoResponse = acsClient.getAcsResponse(request1);
            if(queryInterSmsIsoInfoResponse.getCode() != null && queryInterSmsIsoInfoResponse.getCode().equals("OK")) {
                List<QueryInterSmsIsoInfoResponse.IsoSupportDTO> isoSupportDTOs = queryInterSmsIsoInfoResponse.getIsoSupportDTOs();
                for(QueryInterSmsIsoInfoResponse.IsoSupportDTO isoSupportDTO : isoSupportDTOs) {
                    if (isoSupportDTO.getIsoCode().equals(phoneQcellcore.getPhonePrefix())){
                        System.out.println("获取的发送国家Code:"+isoSupportDTO.getCountryCode());
                        countryCode = isoSupportDTO.getCountryCode();
                    }
                }
            }


            //组装请求对象-具体描述见控制台-文档部分内容
            SendInterSmsRequest request = new SendInterSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:对应国家的国家编号,国家编号的获取详见国际短信国家编码获取API接口
            request.setCountryCode(countryCode);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("雨滴2");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(MSMCode); //国内
            SendInterSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(!sendSmsResponse.getMessage().equals("OK")){
                if (sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", " 您已超限，请您1小时后重试!");
                    return result;
                }
                result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                result.put("msg", "发送失败，请稍后重试！");
                return result;
            }
            result.put("code", ResultStant.RESULT_CODE_SUCCESS);
            result.put("msg", "发送成功");
            return result;
        } catch (Exception e) {
            result.put("code", ResultStant.RESULT_CODE_SERVICE);
            result.put("msg", "系统繁忙，请稍后重试！");
            return result;
        }
    }

    //发送提醒短信
    public static Map sendRemind(String phone, String sendType,String name, String MSMCode){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (IsObjectNullUtils.is(phone)) {
                result.put("code", ResultStant.RESULT_CODE_LOSE);
                result.put("msg", "phone 获取失败");
                return result;
            }
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("雨滴2");
            //必填:短信模板-可在短信控制台中找到
            if (sendType.equals("86")){
                request.setTemplateCode(MSMCode); //国内
            }else {
                request.setTemplateCode(MSMCode); //国际
            }
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            String nameStr = "{\"name\":\"";
            nameStr = nameStr + name + "\"}";
            request.setTemplateParam(nameStr);
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(!sendSmsResponse.getMessage().equals("OK")){
                if (sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", " 您已超限，请您1小时后重试!");
                    return result;
                }
                result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                result.put("msg", "发送失败，请稍后重试！");
                return result;
            }
            result.put("code", ResultStant.RESULT_CODE_SUCCESS);
            result.put("msg", "发送成功");
            return result;
        } catch (Exception e) {
            result.put("code", ResultStant.RESULT_CODE_SERVICE);
            result.put("msg", "系统繁忙，请稍后重试！");
            return result;
        }
    }

    //发送国际提醒短信
    public static Map sendCountryRemind(String phone,  XydPhoneQcellcore phoneQcellcore,String name, String MSMCode){

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (IsObjectNullUtils.is(phone)) {
                result.put("code", ResultStant.RESULT_CODE_LOSE);
                result.put("msg", "phone 获取失败");
                return result;
            }
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            QueryInterSmsIsoInfoRequest request1 = new QueryInterSmsIsoInfoRequest();
            request1.setCountryName(phoneQcellcore.getName());
            String countryCode = "";
            //hint 此处可能会抛出异常，注意catch
            QueryInterSmsIsoInfoResponse queryInterSmsIsoInfoResponse = acsClient.getAcsResponse(request1);
            if(queryInterSmsIsoInfoResponse.getCode() != null && queryInterSmsIsoInfoResponse.getCode().equals("OK")) {
                List<QueryInterSmsIsoInfoResponse.IsoSupportDTO> isoSupportDTOs = queryInterSmsIsoInfoResponse.getIsoSupportDTOs();
                for(QueryInterSmsIsoInfoResponse.IsoSupportDTO isoSupportDTO : isoSupportDTOs) {
                    if (isoSupportDTO.getIsoCode().equals(phoneQcellcore.getPhonePrefix())){
                        System.out.println("获取的发送国家Code:"+isoSupportDTO.getCountryCode());
                        countryCode = isoSupportDTO.getCountryCode();
                    }
                }
            }


            //组装请求对象-具体描述见控制台-文档部分内容
            SendInterSmsRequest request = new SendInterSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:对应国家的国家编号,国家编号的获取详见国际短信国家编码获取API接口
            request.setCountryCode(countryCode);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("雨滴2");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(MSMCode); //国内
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            String nameStr = "{\"name\":\"";
            nameStr = nameStr + name + "\"}";
            request.setTemplateParam(nameStr);
            SendInterSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(!sendSmsResponse.getMessage().equals("OK")){
                if (sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
                    result.put("code", ResultStant.RESULT_CODE_ERROR);
                    result.put("msg", " 您已超限，请您1小时后重试!");
                    return result;
                }
                result.put("code", ResultStant.RESULT_CODE_UNUSUAL);
                result.put("msg", "发送失败，请稍后重试！");
                return result;
            }
            result.put("code", ResultStant.RESULT_CODE_SUCCESS);
            result.put("msg", "发送成功");
            return result;
        } catch (Exception e) {
            result.put("code", ResultStant.RESULT_CODE_SERVICE);
            result.put("msg", "系统繁忙，请稍后重试！");
            return result;
        }
    }
}
