package com.xfkj.common.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置拦截器，阻止普通用户和游客进入管理员界面，阻止游客进行个人信息有关操作
     * 如果要拦截多个则可后面 addPathPatterns("/manage/**", "/admin/");
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginContextInterceptor())
                .addPathPatterns("/manage/**");
    }

}
