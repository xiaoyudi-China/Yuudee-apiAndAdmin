package com.xfkj.common.model_custom;

import com.xfkj.model.XydParents;
import com.xfkj.service.XydParentsService;

/**
 * Created by King on 2018/10/15.
 */
public class Template {
    //毫秒
    private long millisecond;
    //短信code
    private String code;
    //手机号前缀  如 +86
    private String prefix;
    //接收短信的手机号
    private String phone;
    //提醒表id
    private Integer remindId;

    public Integer getRemindId() {
        return remindId;
    }

    public void setRemindId(Integer remindId) {
        this.remindId = remindId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(long millisecond) {
        this.millisecond = millisecond;
    }
}
