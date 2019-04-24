package com.xfkj.common.emun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/21.
 * pcdi 问卷必做等级分类
 */
public enum PCDIMustType {
    //必做部分
    MUST_NAME_A(1, "小孩怎么使用"),
    MUST_NAME_B(2, "句子和语句"),
    MUST_NAME_C(3, "组合句子"),
    MUST_NAME_D(4, "复杂性"),
    MUST_NAME_E(5, "词汇掌握前测"),
    ;

    private String desc;
    private int value;

    private PCDIMustType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    public static PCDIMustType getEnum(int type) {
        PCDIMustType[] status = PCDIMustType.values();
        for(int i = 0; i < status.length; i++) {
            if(status[i].getValue() == type) {
                return status[i];
            }
        }

        return null;
    }

    public static List<Map<String, Object>> getEnumDescList() {
        List<Map<String, Object>> list = new ArrayList<>();
        PCDIMustType[] status = PCDIMustType.values();
        for(int i = 0; i < status.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("value",  status[i].getValue());
            map.put("desc", status[i].getDesc());
            list.add(map);
        }
        return list;
    }
}
