package com.xfkj.common.emun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/9/21.
 * 儿童医学诊断类型
 */
public enum MedicalTypeEnum {
    XYD_MEDICAL_AUTI(0, "自闭症"),
    XYD_MEDICAL_DELAY(1, "语言发育迟缓（其他正常）"),
    XYD_MEDICAL_NO_AUTI(2, "单纯性智力低下（无自闭症）"),
    XYD_MEDICAL_REST(3, "其他诊断"),
    XYD_MEDICAL_NORMAL(4, "正常"),
    ;

    private String desc;
    private int value;

    private MedicalTypeEnum(int value, String desc) {
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

    public static MedicalTypeEnum getEnum(int type) {
        MedicalTypeEnum[] status = MedicalTypeEnum.values();
        for(int i = 0; i < status.length; i++) {
            if(status[i].getValue() == type) {
                return status[i];
            }
        }
        return null;
    }

    public static List<Map<String, Object>> getEnumDescList() {
        List<Map<String, Object>> list = new ArrayList<>();
        MedicalTypeEnum[] status = MedicalTypeEnum.values();
        for(int i = 0; i < status.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("value",  status[i].getValue());
            map.put("desc", status[i].getDesc());
            list.add(map);
        }
        return list;
    }
}
