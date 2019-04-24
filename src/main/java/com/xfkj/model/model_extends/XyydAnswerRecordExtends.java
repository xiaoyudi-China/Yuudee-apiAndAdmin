package com.xfkj.model.model_extends;

import com.xfkj.model.XydAnswerRecord;

import java.util.Date;

/**
 * Created by mai xiaogang on 2018/10/22.
 */
public class XyydAnswerRecordExtends extends XydAnswerRecord {
    private String name;
    private String sex;
    private String phone;
    private Date birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
