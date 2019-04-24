package com.xfkj.common.web;


import com.xfkj.common.utils.IsObjectNullUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by Administrator on 2018/3/14 0014.
 */
public class BaseController {

    /**
     * 清空session
     * @param request
     */
    public void deleteSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (!IsObjectNullUtils.is(session)){
            session.invalidate();
        }
    }

    /**
     * 验证web端登录
     * @param request
     * @return
     */
    public boolean checkWebUserLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object userId=session.getAttribute("userId");
        if(userId!=null&& (Integer)userId!=0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 计算 页码数(只针对dataTables用)
     * @param start
     * @param length
     * @return
     */
    public int getPageNum(String start, String length){
        if(start==null||length==null){
            return 0;
        }
        if (start.equals("0")){
            return 1;
        }
        return (Integer.valueOf(start)/ Integer.valueOf(length))+1;
    }

    /**
     * 处理每页展示的  条数（只针对dataTables用）
     * @param length
     * @return
     */
    public int getPageSize(String length){
        if(!IsObjectNullUtils.is(length)){
            return Integer.valueOf(length);
        }
        return 0;
    }

    public int getDraw(String draw){
        if(!IsObjectNullUtils.is(draw)){
            return Integer.valueOf(draw);
        }
        return 0;
    }
    /**
     * 解决跨域问题
     * @param response
     * @return
     */
    public HttpServletResponse setHttpServletResponse(
            HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return response;
    }

    /**
     * 解决跨域问题（重载方法）
     * @param response
     * @return
     */
    public HttpServletResponse setHttpServletResponse(
            HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return response;
    }


   /* *//**
     * base64编码(处理表情)
     * @param text
     * @return
     * @throws Exception
     *//*
    public String bianMaBase64Encoder(String text) throws Exception{
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte = text.getBytes("UTF-8");
        return encoder.encodeToString(textByte);

    }

    *//**
     * base64解码（处理表情）
     * @param text
     * @return
     * @throws Exception
     *//*
    public String jieMaBase64Decoder(String text) throws Exception{
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(text),"UTF-8");
    }*/

}
