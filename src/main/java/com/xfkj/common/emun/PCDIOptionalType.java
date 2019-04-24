package com.xfkj.common.emun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/10/8.
 */
public enum PCDIOptionalType {
    //pcdi选做部分
    OPTIONAL_NAME_A(1, "象声和感叹词"),
    OPTIONAL_NAME_B(2, "人名"),
    OPTIONAL_NAME_C(3, "游戏和常做的事"),
    OPTIONAL_NAME_D(4, "动词"),
    OPTIONAL_NAME_E(5, "吃和喝的"),
    OPTIONAL_NAME_F(6, "身体的部分"),
    OPTIONAL_NAME_G(7, "动物（真的或玩具的均可）"),
    OPTIONAL_NAME_H(8, "形容词和副词"),
    OPTIONAL_NAME_I(9, "家里的小东西"),
    OPTIONAL_NAME_J(10, "玩具和娱乐用品"),
    OPTIONAL_NAME_K(11, "衣服"),
    OPTIONAL_NAME_L(12, "家具、屋子"),
    OPTIONAL_NAME_M(13, "外面的东西"),
    OPTIONAL_NAME_N(14, "车（真的或玩具的均可）"),
    OPTIONAL_NAME_O(15, "外面的地方"),
    OPTIONAL_NAME_P(16, "方向词"),
    OPTIONAL_NAME_Q(17, "数量词"),
    OPTIONAL_NAME_R(18, "代词"),
    OPTIONAL_NAME_S(19, "量词"),
    OPTIONAL_NAME_T(20, "疑问句"),
    OPTIONAL_NAME_U(21, "居委虚词"),
    OPTIONAL_NAME_V(22, "时间词"),
    OPTIONAL_NAME_W(23, "助词"),
    OPTIONAL_NAME_X(24, "连词"),
    ;
    private String desc;
    private int value;

    private PCDIOptionalType(int value, String desc) {
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

    public static PCDIOptionalType getEnum(int type) {
        PCDIOptionalType[] status = PCDIOptionalType.values();
        for(int i = 0; i < status.length; i++) {
            if(status[i].getValue() == type) {
                return status[i];
            }
        }

        return null;
    }

    public static List<Map<String, Object>> getEnumDescList() {
        List<Map<String, Object>> list = new ArrayList<>();
        PCDIOptionalType[] status = PCDIOptionalType.values();
        for(int i = 0; i < status.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("value",  status[i].getValue());
            map.put("desc", status[i].getDesc());
            list.add(map);
        }
        return list;
    }
}
