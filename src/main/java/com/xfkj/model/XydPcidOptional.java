package com.xfkj.model;

import java.util.Date;
import java.util.List;

public class XydPcidOptional {
    private Integer id;

    private Integer pcdiTypeId;

    private String topicTitle;

    private String topicResultOne;

    private String topicResultTwo;

    private String states;

    private Date createTime;

    private Date updateTime;

    //冗余字段、 答案
    private String answer;


    private List answers;

    private List topicResult;


    //冗余字段、所属类型标识以及名称
    private String name;

    private String nameEnum;

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

    public String getNameEnum() {
        return nameEnum;
    }

    public void setNameEnum(String nameEnum) {
        this.nameEnum = nameEnum;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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