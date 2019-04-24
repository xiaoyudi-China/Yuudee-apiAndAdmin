package com.xfkj.common.emun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/12/20.
 * 儿童学习模块
 */
public enum ModuleTypeEnum {
    XYD_VERB(1, "动词"),
    XYD_CONCISE(2, "简单句子"),
    XYD_NOUN(3, "名词"),
    XYD_SECTENCE_GROUP(4, "句子成组"),
    XYD_SECTENCE_RESOLVE(5, "句子分解"),
    ;

    private String desc;
    private int value;

    private ModuleTypeEnum(int value, String desc) {
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

    public static ModuleTypeEnum getEnum(int type) {
        ModuleTypeEnum[] status = ModuleTypeEnum.values();
        for(int i = 0; i < status.length; i++) {
            if(status[i].getValue() == type) {
                return status[i];
            }
        }
        return null;
    }

    public static List<Map<String, Object>> getEnumDescList() {
        List<Map<String, Object>> list = new ArrayList<>();
        ModuleTypeEnum[] status = ModuleTypeEnum.values();
        for(int i = 0; i < status.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("value",  status[i].getValue());
            map.put("desc", status[i].getDesc());
            list.add(map);
        }
        return list;
    }
}
