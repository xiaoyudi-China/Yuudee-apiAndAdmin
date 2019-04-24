package com.xfkj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class XydPcidMustVocabulary implements Serializable{
    private static final long serialVersionUID = -7530230937566192436L;
    private Integer id;

    private Integer pcdiTypeId;

    private String type;

    private Integer sort;

    private String topicTitle;

    private String topicResultOne;

    private String topicResultTwo;

    private String states;

    private Date createTime;

    private Date updateTime;

    private String isScore;

    //冗余字段
    private String answer;

    private String sign;

    private String answerOne;

    private String answerTwo;

    private String answerThree;

    private List answers;

    private List topicResult;

    public String getIsScore() {
        return isScore;
    }

    public void setIsScore(String isScore) {
        this.isScore = isScore;
    }

    public List getTopicResult() {
        return topicResult;
    }

    public void setTopicResult(List topicResult) {
        this.topicResult = topicResult;
    }

    public List getAnswers() {
        return answers;
    }

    public void setAnswers(List answers) {
        this.answers = answers;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPcdiTypeId() {
        return pcdiTypeId;
    }

    public void setPcdiTypeId(Integer pcdiTypeId) {
        this.pcdiTypeId = pcdiTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle == null ? null : topicTitle.trim();
    }

    public String getTopicResultOne() {
        return topicResultOne;
    }

    public void setTopicResultOne(String topicResultOne) {
        this.topicResultOne = topicResultOne == null ? null : topicResultOne.trim();
    }

    public String getTopicResultTwo() {
        return topicResultTwo;
    }

    public void setTopicResultTwo(String topicResultTwo) {
        this.topicResultTwo = topicResultTwo == null ? null : topicResultTwo.trim();
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