package com.xfkj.model;

import com.xfkj.common.model_custom.VerbTestCustom;

import java.util.ArrayList;
import java.util.Date;

public class XydVerbTest {
    private Integer id;

    private String startSlideshow;

    private String endSlideshow;

    private String verbType;

    private String verbImage;

    private String verbChar;

    private String verbRecord;

    private String cardImage;

    private String cardChar;

    private String cardRecord;

    private String groupImage;

    private String groupChar;

    private String groupRecord;

    private Date createTime;

    private Date updateTime;

    private String states;

    private Integer cardOneTime;

    private Integer cardTwoTime;

    //冗余字段

    private ArrayList<VerbTestCustom> list;

    public Integer getCardOneTime() {
        return cardOneTime;
    }

    public void setCardOneTime(Integer cardOneTime) {
        this.cardOneTime = cardOneTime;
    }

    public Integer getCardTwoTime() {
        return cardTwoTime;
    }

    public void setCardTwoTime(Integer cardTwoTime) {
        this.cardTwoTime = cardTwoTime;
    }

    public ArrayList<VerbTestCustom> getList() {
        return list;
    }

    public void setList(ArrayList<VerbTestCustom> list) {
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

    public String getEndSlideshow() {
        return endSlideshow;
    }

    public void setEndSlideshow(String endSlideshow) {
        this.endSlideshow = endSlideshow == null ? null : endSlideshow.trim();
    }

    public String getVerbType() {
        return verbType;
    }

    public void setVerbType(String verbType) {
        this.verbType = verbType == null ? null : verbType.trim();
    }

    public String getVerbImage() {
        return verbImage;
    }

    public void setVerbImage(String verbImage) {
        this.verbImage = verbImage == null ? null : verbImage.trim();
    }

    public String getVerbChar() {
        return verbChar;
    }

    public void setVerbChar(String verbChar) {
        this.verbChar = verbChar == null ? null : verbChar.trim();
    }

    public String getVerbRecord() {
        return verbRecord;
    }

    public void setVerbRecord(String verbRecord) {
        this.verbRecord = verbRecord == null ? null : verbRecord.trim();
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage == null ? null : cardImage.trim();
    }

    public String getCardChar() {
        return cardChar;
    }

    public void setCardChar(String cardChar) {
        this.cardChar = cardChar == null ? null : cardChar.trim();
    }

    public String getCardRecord() {
        return cardRecord;
    }

    public void setCardRecord(String cardRecord) {
        this.cardRecord = cardRecord == null ? null : cardRecord.trim();
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage == null ? null : groupImage.trim();
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