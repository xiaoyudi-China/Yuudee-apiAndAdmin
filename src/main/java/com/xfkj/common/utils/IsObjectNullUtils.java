package com.xfkj.common.utils;

import java.util.*;


public class IsObjectNullUtils {
    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean is(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).isEmpty();
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!is(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static Object isZero(Object obj) {
        if (obj instanceof Map) {
            if (is(obj)) {
                return new HashMap<>();
            }
            return obj;
        }
        if (obj instanceof List) {
            if (is(obj)) {
                return new ArrayList<>();
            }
            return obj;
        }
        if (null == obj) {
            return "";
        }
        return obj;
    }

    /**
     * @param map  数据源    数据源为空返回 true
     * @param obj  需要判断的参数名
     * @param flag 参数是否可为“null”或“0” false : 可为空   true:不可为空
     * @return
     */
    public static boolean checkMapParam(Map map, Object obj, boolean flag) {
        if(null==obj){
            return true;
        }
        if(is(map)){
            return true;
        }
        if (map.containsKey(obj)) {
            if(flag){
                return (("".equals(map.get(obj)))||("0".equals(map.get(obj)))||(0== Integer.parseInt(map.get(obj).toString())));
            }else {
                return false;
            }
        } else {
            return true;
        }
    }


}
