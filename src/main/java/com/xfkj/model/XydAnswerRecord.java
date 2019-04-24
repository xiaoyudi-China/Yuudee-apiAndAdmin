package com.xfkj.model;

import java.math.BigDecimal;
import java.util.Date;

public class XydAnswerRecord {
    private Integer id;

    private Integer userId;

    private Integer parentsId;

    private String type;

    private BigDecimal score;

    private String isValid;

    private BigDecimal percent;

    private Integer count;

    private Integer adjectiveScore;

    private Integer nounScore;

    private Integer verbScore;

    private String sentence;

    private String vocabulary;

    private Long learningTime;

    private BigDecimal rateAll;

    private String anew;

    private String title;

    private String states;

    private Date createTime;

    private Date updateTime;

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Long getLearningTime() {
        return learningTime;
    }

    public void setLearningTime(Long learningTime) {
        this.learningTime = learningTime;
    }

    public BigDecimal getRateAll() {
        return rateAll;
    }

    public void setRateAll(BigDecimal rateAll) {
        this.rateAll = rateAll;
    }

    public String getAnew() {
        return anew;
    }

    public void setAnew(String anew) {
        this.anew = anew;
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

    public Integer getParentsId() {
        return parentsId;
    }

    public void setParentsId(Integer parentsId) {
        this.parentsId = parentsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAdjectiveScore() {
        return adjectiveScore;
    }

    public void setAdjectiveScore(Integer adjectiveScore) {
        this.adjectiveScore = adjectiveScore;
    }

    public Integer getNounScore() {
        return nounScore;
    }

    public void setNounScore(Integer nounScore) {
        this.nounScore = nounScore;
    }

    public Integer getVerbScore() {
        return verbScore;
    }

    public void setVerbScore(Integer verbScore) {
        this.verbScore = verbScore;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence == null ? null : sentence.trim();
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary == null ? null : vocabulary.trim();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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