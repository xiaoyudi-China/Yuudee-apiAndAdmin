package com.xfkj.model;

import com.xfkj.common.model_custom.SentenceGroupTrainingCustom;

import java.util.ArrayList;
import java.util.Date;

public class XydSentenceGroupTraining {
    private Integer id;

    private String startSlideshow;

    private String cardOneImage;

    private String cardOneChar;

    private String cardOneRecord;

    private String cardTwoImage;

    private String cardTwoChar;

    private String cardTwoRecord;

    private String groupChar;

    private String groupRecord;

    private Date createTime;

    private Date updateTime;

    private String states;

    //冗余字段

    private ArrayList<SentenceGroupTrainingCustom> list;

    public ArrayList<SentenceGroupTrainingCustom> getList() {
        return list;
    }

    public void setList(ArrayList<SentenceGroupTrainingCustom> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartSlideshow() {
        return startSlideshow;
    }

    public void setStartSlideshow(String startSlideshow) {
        this.startSlideshow = startSlideshow == null ? null : startSlideshow.trim();
    }

    public String getCardOneImage() {
        return cardOneImage;
    }

    public void setCardOneImage(String cardOneImage) {
        this.cardOneImage = cardOneImage == null ? null : cardOneImage.trim();
    }

    public String getCardOneChar() {
        return cardOneChar;
    }

    public void setCardOneChar(String cardOneChar) {
        this.cardOneChar = cardOneChar == null ? null : cardOneChar.trim();
    }

    public String getCardOneRecord() {
        return cardOneRecord;
    }

    public void setCardOneRecord(String cardOneRecord) {
        this.cardOneRecord = cardOneRecord == null ? null : cardOneRecord.trim();
    }

    public String getCardTwoImage() {
        return cardTwoImage;
    }

    public void setCardTwoImage(String cardTwoImage) {
        this.cardTwoImage = cardTwoImage == null ? null : cardTwoImage.trim();
    }

    public String getCardTwoChar() {
        return cardTwoChar;
    }

    public void setCardTwoChar(String cardTwoChar) {
        this.cardTwoChar = cardTwoChar == null ? null : cardTwoChar.trim();
    }

    public String getCardTwoRecord() {
        return cardTwoRecord;
    }

    public void setCardTwoRecord(String cardTwoRecord) {
        this.cardTwoRecord = cardTwoRecord == null ? null : cardTwoRecord.trim();
    }

    public String getGroupChar() {
        return groupChar;
    }

    public void setGroupChar(String groupChar) {
        this.groupChar = groupChar == null ? null : groupChar.trim();
    }

    public String getGroupRecord() {
        return groupRecord;
    }

    public void setGroupRecord(String groupRecord) {
        this.groupRecord = groupRecord == null ? null : groupRecord.trim();
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