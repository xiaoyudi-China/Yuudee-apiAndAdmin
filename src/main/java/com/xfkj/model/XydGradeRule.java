package com.xfkj.model;

import java.math.BigDecimal;
import java.util.Date;

public class XydGradeRule {
    private Integer id;

    private String type;

    private String isOptional;

    private Integer topicType;

    private BigDecimal answer;

    private BigDecimal answerOne;

    private BigDecimal answerTwo;

    private BigDecimal answerThree;

    private BigDecimal answerFour;

    private BigDecimal answerFive;

    private BigDecimal answerSix;

    private String states;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(String isOptional) {
        this.isOptional = isOptional == null ? null : isOptional.trim();
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public BigDecimal getAnswer() {
        return answer;
    }

    public void setAnswer(BigDecimal answer) {
        this.answer = answer;
    }

    public BigDecimal getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(BigDecimal answerOne) {
        this.answerOne = answerOne;
    }

    public BigDecimal getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(BigDecimal answerTwo) {
        this.answerTwo = answerTwo;
    }

    public BigDecimal getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(BigDecimal answerThree) {
        this.answerThree = answerThree;
    }

    public BigDecimal getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(BigDecimal answerFour) {
        this.answerFour = answerFour;
    }

    public BigDecimal getAnswerFive() {
        return answerFive;
    }

    public void setAnswerFive(BigDecimal answerFive) {
        this.answerFive = answerFive;
    }

    public BigDecimal getAnswerSix() {
        return answerSix;
    }

    public void setAnswerSix(BigDecimal answerSix) {
        this.answerSix = answerSix;
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
}