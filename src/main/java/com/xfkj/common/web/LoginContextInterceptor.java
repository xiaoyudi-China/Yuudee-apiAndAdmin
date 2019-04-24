package com.xfkj.common.web;

import com.xfkj.common.utils.IPUtil;
import com.xfkj.model.XydManageAccount;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by mai xiaogang on 2018/11/6.
 */
public class LoginContextInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {


        // 获取请求的URL
        String url = request.getRequestURI();
        System.out.println("------LoginInterceptor-----登录IP:"+ IPUtil.getIpAddr(request)+"--请求url:"+url);
        // URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        // 注意：一些静态文件不能拦截，否则会死循环，知道内存耗尽
        if (url.indexOf("login") >= 0 || url.indexOf("toLogin") >= 0) {
            return true;
        }
        // 获取Session
        HttpSession session = request.getSession();
        XydManageAccount xydManageAccount = (XydManageAccount) session.getAttribute("user");
        if (xydManageAccount != null) {
            //权限处理
            return true;
        }
        // 不符合条件的，跳转到登录界面
        response.sendRedirect(request.getContextPath()+"/manage/account/toLogin");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
