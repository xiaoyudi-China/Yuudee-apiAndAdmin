package com.xfkj.common.utils;

import com.xfkj.model.XydParents;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * 生成Token的工具类
 * Created by King on 2018/6/1.
 */
public class TokenProccessor {

    private RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private TokenProccessor(){};
    private static final TokenProccessor instance = new TokenProccessor();

    public static TokenProccessor getInstance() {
        return instance;
    }

    /**
     * 生成Token
     * @return
     */
    public String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            String sb=encoder.encode(md5);
            sb = sb.replaceAll("\\+","");
            System.out.println(sb);
            return sb;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户key获取对应的信息
     */
    public Object getValueByKeyTheRedis(String args){
        Object object=null;
        if(!IsObjectNullUtils.is(args)){
            object = redisTemplate.boundValueOps(args).get();
            RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
        }
        return object;
    }

    /**
     * 保存用户信息以及token，存储到redis中。key为用户Token
     */
    public boolean saveUserInfoByTokenToRedis(Object userInfo){
        boolean flag = false;
        if(!IsObjectNullUtils.is(userInfo)) {
            if(userInfo instanceof XydParents){
                XydParents xydParents=(XydParents)userInfo;
                if (!IsObjectNullUtils.is(xydParents.getId()) && !IsObjectNullUtils.is(xydParents.getToken())){
                    redisTemplate.boundValueOps(xydParents.getToken()).set(xydParents);
                    //设置过期时间
                    redisTemplate.expire(xydParents.getToken(),30,TimeUnit.DAYS);
                    flag=true;
                }
            }
            RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
        }
        return flag;
    }

    /**
     * 保存以 键-值 存在的内容到redis
     */
    public boolean saveKeyValueToRedis(String keyArgs, String valueArgs){
        boolean flag=false;
        try {
            if (!IsObjectNullUtils.is(keyArgs)){
                redisTemplate.boundValueOps(keyArgs).set(valueArgs);
                redisTemplate.expire(keyArgs,5,TimeUnit.MINUTES);
                RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return flag;
        }
    }

    /**
     * 根据数字代码获取用户身份英文代码
     */
    public String getUserTypeTheEnglishByCode(Integer code){
        if(null == code){
            return "";
        }
        String userType="";
        try {
            switch (code){
                case 0:
                    userType="XydParents";
                    break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return userType;
        }
    }

    /**
     * 根据用户身份英文代码获取数字代码
     */
    public Integer getUserTypeTheCodeByEnglish(String code){
        if(null == code || "".equals(code.trim())){
            return null;
        }
        Integer userType=null;
        try {
            switch (code){
                case "XydParents":
                    userType=0;
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return userType;
        }
    }

    /**
     * 根据Object对象获取用户身份英文代码
     */
    public String getUserTypeTheEnglishByObject(Object object){
        if(null == object){
            return "";
        }
        String userType="";
        try {
            if(object instanceof XydParents){
                userType="XydParents";
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return userType;
        }
    }

    /**
     * 根据Object对象获取用户身份数字代码
     */
    public Integer getUserTypeTheNumberByObject(Object object){
        if(null == object){
            return null;
        }
        Integer userType=null;
        try {
            if(object instanceof XydParents){
                userType=0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return userType;
        }
    }


}
