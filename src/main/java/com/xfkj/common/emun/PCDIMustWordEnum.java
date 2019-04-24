package com.xfkj.common.emun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/21.
 * pcdi 问卷必做等级分类
 */
public enum PCDIMustWordEnum {
    //必做部分
    MUST_VERB_A(1, "动词"),
    MUST_NOUN_B(2, "名词"),
    MUST_NAME_C(3, "形容词"),
    MUST_ADJ_D(4, "无"),
    ;

    private String desc;
    private int value;

    private PCDIMustWordEnum(int value, String desc) {
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

    public static PCDIMustWordEnum getEnum(int type) {
        PCDIMustWordEnum[] status = PCDIMustWordEnum.values();
        for(int i = 0; i < status.length; i++) {
            if(status[i].getValue() == type) {
                return status[i];
            }
        }

        return null;
    }

    public static List<Map<String, Object>> getEnumDescList() {
        List<Map<String, Object>> list = new ArrayList<>();
        PCDIMustWordEnum[] status = PCDIMustWordEnum.values();
        for(int i = 0; i < status.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("value",  status[i].getValue());
            map.put("desc", status[i].getDesc());
            list.add(map);
        }
        return list;
    }
}
