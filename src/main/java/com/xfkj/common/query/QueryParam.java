package com.xfkj.common.query;









import com.xfkj.common.emun.BaseEnum;

import java.util.Map;


/**
 * Author ： LILEI ENGLISH SUNNY
 * CreateTime: 2017-04-28
 * Title  :总的参数Map抽象接口设计
 */
public interface QueryParam extends Map<String, Object> {
    /**
     * 新增查询参数
     *
     * @param key   参数名
     * @param value 参数值
     *              日期型要用 QueryDateType
     * @return
     */
    QueryParam fill(BaseEnum key, Object value);
    QueryParam fill(String key, Object value);

}
