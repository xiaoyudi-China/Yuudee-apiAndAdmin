package com.xfkj.common.emun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/21.
 * 儿童语言类型
 */
public enum TrainingTypeEnum {

    XYD_MEDICAL_DELAY(1, "ABA"),
    XYD_MEDICAL_NO_AUTI(2, "其他训练疗法"),
    XYD_MEDICAL_AUTI(3, "无训练"),
    ;

    private String desc;
    private int value;

    private TrainingTypeEnum(int value, String desc) {
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

    public static TrainingTypeEnum getEnum(int type) {
        TrainingTypeEnum[] status = TrainingTypeEnum.values();
        for(int i = 0; i < status.length; i++) {
            if(status[i].getValue() == type) {
                return status[i];
            }
        }

        return null;
    }

    public static List<Map<String, Object>> getEnumDescList() {
        List<Map<String, Object>> list = new ArrayList<>();
        TrainingTypeEnum[] status = TrainingTypeEnum.values();
        for(int i = 0; i < status.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("value",  status[i].getValue());
            map.put("desc", status[i].getDesc());
            list.add(map);
        }
        return list;
    }
}
