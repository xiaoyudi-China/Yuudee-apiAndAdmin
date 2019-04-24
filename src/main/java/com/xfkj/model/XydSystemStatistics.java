package com.xfkj.model;

import java.math.BigDecimal;
import java.util.Date;

public class XydSystemStatistics {
    private Integer id;

    private Integer userId;

    private Integer learningTime;

    private String module;

    private String childModule;

    private Integer passNumber;

    private String states;

    private Date createTime;

    private Date updateTime;

    private Integer player;

    private BigDecimal rate;

    private Integer count;

    private String isPass;

    private BigDecimal rate1;

    private Integer again;

    public Integer getAgain() {
        return again;
    }

    public void setAgain(Integer again) {
        this.again = again;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public BigDecimal getRate1() {
        return rate1;
    }

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

    public void setRate1(BigDecimal rate1) {
        this.rate1 = rate1;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getChildModule() {
        return childModule;
    }

    public void setChildModule(String childModule) {
        this.childModule = childModule;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLearningTime() {
        return learningTime;
    }

    public void setLearningTime(Integer learningTime) {
        this.learningTime = learningTime;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public Integer getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(Integer passNumber) {
        this.passNumber = passNumber;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states == null ? null : states.trim();
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

    @Override
    public String toString() {
        return "XydSystemStatistics{" +
                "id=" + id +
                ", userId=" + userId +
                ", learningTime=" + learningTime +
                ", module='" + module + '\'' +
                ", childModule='" + childModule + '\'' +
                ", passNumber=" + passNumber +
                ", states='" + states + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", player=" + player +
                ", rate=" + rate +
                ", count=" + count +
                ", isPass='" + isPass + '\'' +
                ", rate1=" + rate1 +
                ", again=" + again +
                '}';
    }

}