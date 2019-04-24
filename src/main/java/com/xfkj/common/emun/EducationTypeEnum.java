package com.xfkj.common.emun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/21.
 * 儿童语言类型
 */
public enum EducationTypeEnum {
    XYD_SCHOOL_PRIMARY(0, "小学-高中"),
    XYD_SCHOOL_UNIVERSITY(1, "大学"),
    XYD_SCHOOL_MASTER(2, "硕士研究生"),
    XYD_SCHOOL_DOCTOR(3, "博士或类似"),
    ;

    private String desc;
    private int value;

    private EducationTypeEnum(int value, String desc) {
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

    public static EducationTypeEnum getEnum(int type) {
        EducationTypeEnum[] status = EducationTypeEnum.values();
        for(int i = 0; i < status.length; i++) {
            if(status[i].getValue() == type) {
                return status[i];
            }
        }

        return null;
    }

    public static List<Map<String, Object>> getEnumDescList() {
        List<Map<String, Object>> list = new ArrayList<>();
        EducationTypeEnum[] status = EducationTypeEnum.values();
        for(int i = 0; i < status.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("value",  status[i].getValue());
            map.put("desc", status[i].getDesc());
            list.add(map);
        }
        return list;
    }
}
