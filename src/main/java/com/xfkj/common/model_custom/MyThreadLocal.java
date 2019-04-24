package com.xfkj.common.model_custom;

/**
 * Created by King on 2018/10/15.
 */
public class MyThreadLocal {
    private static final ThreadLocal<Template> userThreadLocal = new ThreadLocal<Template>();
    public static void set(Template tem){
        userThreadLocal.set(tem);
    }
    public static void unset(){
        userThreadLocal.remove();
    }
    public static Template get(){
        return userThreadLocal.get();
    }
}
