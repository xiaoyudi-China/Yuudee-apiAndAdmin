package com.xfkj.model;

import java.util.Date;

public class XydResultAnalyze {
    private Integer id;

    private String isOptional;

    private Integer monthAge;

    private String sex;

    private Integer stateScore;

    private Integer endScore;

    private Integer statePercent;

    private Integer endPercent;

    private Date createTime;

    private Date updateTime;

    private String states;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(String isOptional) {
        this.isOptional = isOptional == null ? null : isOptional.trim();
    }

    public Integer getMonthAge() {
        return monthAge;
    }

    public void setMonthAge(Integer monthAge) {
        this.monthAge = monthAge;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getStateScore() {
        return stateScore;
    }

    public void setStateScore(Integer stateScore) {
        this.stateScore = stateScore;
    }

    public Integer getEndScore() {
        return endScore;
    }

    public void setEndScore(Integer endScore) {
        this.endScore = endScore;
    }

    public Integer getStatePercent() {
        return statePercent;
    }

    public void setStatePercent(Integer statePercent) {
        this.statePercent = statePercent;
    }

    public Integer getEndPercent() {
        return endPercent;
    }

    public void setEndPercent(Integer endPercent) {
        this.endPercent = endPercent;
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