package com.xfkj.common.constant;

/**
 * Created by mai xiaogang on 2018/9/21.
 */
public class ResultStant {
    /**
     *ajax请求状态
     */
    public static final int RESULT_CODE_SUCCESS = 200;      //ajax请求成功

    public static final int RESULT_CODE_ERROR = 201;      //ajax请求失败

    public static final int RESULT_CODE_LOSE = 202;      //ajax请求无法获取相关信息

    public static final int RESULT_CODE_UNUSUAL = 203;      //ajax异常请求

    public static final int RESULT_CODE_LOGIN = 209;      //未登录或无法获取登录信息，重新登录

    public static final int RESULT_CODE_NULL = 204;      //没有获取到想要的资源

    public static final int RESULT_CODE_SERVICE = 500;      //服务器内部错误
}
