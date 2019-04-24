package com.xfkj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class XydPcidMust implements Serializable{
    private static final long serialVersionUID = 2968284364094554956L;
    private Integer id;

    private Integer pcdiTypeId;

    private String describes;

    private String topicTitle;

    private Integer sort;

    private String topicResultOne;

    private String topicResultTwo;

    private String topicResultThree;

    private String topicResultFour;

    private String topicResultFive;

    private String topicResultSix;

    private String isOtherOptions;

    private String childOptions;

    private String isScore;

    private String states;

    private Date createTime;

    private Date updateTime;

    //冗余字段
    private String answer;

    private String answerOne;

    private String answerTwo;

    private String answerThree;

    //冗余字段 后台用
    private String name;

    private String result;

    private List answers;

    private List topicResult;

    public List getAnswers() {
        return answers;
    }

    public void setAnswers(List answers) {
        this.answers = answers;
    }

    public List getTopicResult() {
        return topicResult;
    }

    public void setTopicResult(List topicResult) {
        this.topicResult = topicResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle == null ? null : topicTitle.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getTopicResultThree() {
        return topicResultThree;
    }

    public void setTopicResultThree(String topicResultThree) {
        this.topicResultThree = topicResultThree == null ? null : topicResultThree.trim();
    }

    public String getTopicResultFour() {
        return topicResultFour;
    }

    public void setTopicResultFour(String topicResultFour) {
        this.topicResultFour = topicResultFour == null ? null : topicResultFour.trim();
    }

    public String getTopicResultFive() {
        return topicResultFive;
    }

    public void setTopicResultFive(String topicResultFive) {
        this.topicResultFive = topicResultFive == null ? null : topicResultFive.trim();
    }

    public String getTopicResultSix() {
        return topicResultSix;
    }

    public void setTopicResultSix(String topicResultSix) {
        this.topicResultSix = topicResultSix == null ? null : topicResultSix.trim();
    }

    public String getIsOtherOptions() {
        return isOtherOptions;
    }

    public void setIsOtherOptions(String isOtherOptions) {
        this.isOtherOptions = isOtherOptions == null ? null : isOtherOptions.trim();
    }

    public String getChildOptions() {
        return childOptions;
    }

    public void setChildOptions(String childOptions) {
        this.childOptions = childOptions == null ? null : childOptions.trim();
    }

    public String getIsScore() {
        return isScore;
    }

    public void setIsScore(String isScore) {
        this.isScore = isScore == null ? null : isScore.trim();
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