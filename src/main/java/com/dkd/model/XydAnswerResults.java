package com.dkd.model;

import java.util.Date;
import java.util.List;

public class XydAnswerResults {
    private Integer id;

    private Integer answerId;

    private Integer topicId;

    private Integer topicType;

    private String isOtherOptions;

    private String answer;

    private String answerOne;

    private String answerTwo;

    private String answerThree;

    private String states;

    private Date createTime;

    private Integer sign;

    private Date updateTime;

    private List answerList;

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public List getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List answerList) {
        this.answerList = answerList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getIsOtherOptions() {
        return isOtherOptions;
    }

    public void setIsOtherOptions(String isOtherOptions) {
        this.isOtherOptions = isOtherOptions == null ? null : isOtherOptions.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne == null ? null : answerOne.trim();
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo == null ? null : answerTwo.trim();
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree == null ? null : answerThree.trim();
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