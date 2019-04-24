package com.xfkj.common.utils;

import cn.jpush.api.JPushClient;

import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by King on 2018/10/25.
 */
public class JpushClientUtil {

    //两个参数分别填写你申请的masterSecret和appKey
    private static JPushClient jPushClient=new JPushClient("fca18d9c783adcd59c76c188","ee9177f68df4a5eac387b555");

    /**
     * 通知推送
     * 备注：推送方式不为空时，推送的值也不能为空；推送方式为空时，推送值不做要求
     * @param type 推送方式：1、“tag”标签推送，2、“alias”别名推送
     * @param value 推送的标签或别名值
     * @param alert 推送的内容
     */
    public static void pushNotice(String type, String value, String alert){
        PushPayload.Builder builder= PushPayload.newBuilder();
        builder.setPlatform(Platform.all());//设置接受的平台，all为所有平台，包括安卓、ios、和微软的
        //设置如果用户不在线、离线消息保存的时间
        Options options=Options.sendno();
        options.setTimeToLive(60*60*24L);    //设置为86400为保存一天，如果不设置默认也是保存一天
        builder.setOptions(options);
        //设置推送方式
        if(type.equals("alias")){
            builder.setAudience(Audience.alias(value));//根据别名推送
        }else if(type.equals("tag")){
            builder.setAudience(Audience.tag(value));//根据标签推送
        }else{
            builder.setAudience(Audience.all());//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
        }
        //设置为采用通知的方式发送消息
        builder.setNotification(Notification.alert(alert));
        Options.Builder builder1 = Options.newBuilder();
        builder1.setApnsProduction(false);
        builder.setOptions(builder1.build());
        PushPayload pushPayload=builder.build();
        try{
            //进行推送，实际推送就在这一步
            PushResult pushResult=jPushClient.sendPush(pushPayload);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 自定义消息推送     -->暂时没有调通
     * 备注：推送方式不为空时，推送的值也不能为空；推送方式为空时，推送值不做要求
     * @param type 推送方式：1、“tag”标签推送，2、“alias”别名推送
     * @param value 推送的标签或别名值
     * @param alert 推送的内容
     */
    public static void pushMsg(String type, String value,String alert){
        PushPayload.Builder builder= PushPayload.newBuilder();
        builder.setPlatform(Platform.all());//设置接受的平台
        if(type.equals("alias")){
            builder.setAudience(Audience.alias(value));//别名推送
        }else if(type.equals("tag")){
            builder.setAudience(Audience.tag(value));//标签推送
        }else{
            builder.setAudience(Audience.all());//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
        }
        Message.Builder newBuilder=Message.newBuilder();
        newBuilder.setMsgContent(alert);//消息内容
        Message message=newBuilder.build();
        builder.setMessage(Message.content(alert));
        Options.Builder builder1 = Options.newBuilder();
        builder1.setApnsProduction(false);
        builder.setOptions(builder1.build());
        PushPayload pushPayload=builder.build();
        try{
            PushResult pushResult=jPushClient.sendPush(pushPayload);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //发送自定义消息 -->暂时没有调通
    public static PushPayload push_Android(String message) {
        Map<String,String> map = new HashMap<>();
        map.put("测试", "测试1");
        Message.Builder msg = Message.newBuilder();
        msg.setTitle("title");
        msg.setMsgContent("content");
        msg.setContentType("type:1");
        msg.addExtra("type", 1);
//        Message.newBuilder();
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("40"))
                .setMessage(msg.build())
                .setOptions((Options.newBuilder().setApnsProduction(false)).build())
                .build();
    }


    public static void main(String[] args){
        //给标签为kefu的用户进行消息推送
        PushPayload pushPayload=  JpushClientUtil.push_Android("tagkefu");
        try{
            PushResult pushResult=jPushClient.sendPush(pushPayload);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
