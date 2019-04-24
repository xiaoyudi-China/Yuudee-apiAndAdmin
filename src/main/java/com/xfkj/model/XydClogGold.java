package com.xfkj.model;

import java.util.Date;

public class XydClogGold {
    private Integer id;

    private Integer userid;

    private Integer gold;

    private String clogSub;

    private Date createTime;

    private Date updateTime;

    private String states;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public String getClogSub() {
        return clogSub;
    }

    public void setClogSub(String clogSub) {
        this.clogSub = clogSub == null ? null : clogSub.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states == null ? null : states.trim();
    }
}