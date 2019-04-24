package com.xfkj.model;

import java.util.*;

public class XydNounTest {
    private Integer id;

    private String cardColorImage;

    private String cardColorChar;

    private String cardColorRecord;

    private Integer fristAssistTime;

    private String cardWireImage;

    private String cardWireChar;

    private String cardWireRecord;

    private Integer secondAssistTime;

    private String groupImage;

    private String groupChar;

    private String groupRecord;

    private Date createTime;

    private Date updateTime;

    private String states;

    //冗余字段
    private ArrayList<Object> list;

    public ArrayList<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardColorImage() {
        return cardColorImage;
    }

    public void setCardColorImage(String cardColorImage) {
        this.cardColorImage = cardColorImage == null ? null : cardColorImage.trim();
    }

    public String getCardColorChar() {
        return cardColorChar;
    }

    public void setCardColorChar(String cardColorChar) {
        this.cardColorChar = cardColorChar == null ? null : cardColorChar.trim();
    }

    public String getCardColorRecord() {
        return cardColorRecord;
    }

    public void setCardColorRecord(String cardColorRecord) {
        this.cardColorRecord = cardColorRecord == null ? null : cardColorRecord.trim();
    }

    public Integer getFristAssistTime() {
        return fristAssistTime;
    }

    public void setFristAssistTime(Integer fristAssistTime) {
        this.fristAssistTime = fristAssistTime;
    }

    public String getCardWireImage() {
        return cardWireImage;
    }

    public void setCardWireImage(String cardWireImage) {
        this.cardWireImage = cardWireImage == null ? null : cardWireImage.trim();
    }

    public String getCardWireChar() {
        return cardWireChar;
    }

    public void setCardWireChar(String cardWireChar) {
        this.cardWireChar = cardWireChar == null ? null : cardWireChar.trim();
    }

    public String getCardWireRecord() {
        return cardWireRecord;
    }

    public void setCardWireRecord(String cardWireRecord) {
        this.cardWireRecord = cardWireRecord == null ? null : cardWireRecord.trim();
    }

    public Integer getSecondAssistTime() {
        return secondAssistTime;
    }

    public void setSecondAssistTime(Integer secondAssistTime) {
        this.secondAssistTime = secondAssistTime;
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