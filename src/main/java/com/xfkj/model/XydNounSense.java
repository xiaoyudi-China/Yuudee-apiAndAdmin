package com.xfkj.model;

import com.xfkj.common.model_custom.NounSenseCustom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XydNounSense {
    private Integer id;

    private String cardAdjectiveImage;

    private String cardAdjectiveChar;

    private String cardAdjectiveRecord;

    private Integer fristAssistTime;

    private String cardNounImage;

    private String cardNounChar;

    private String cardNounRecord;

    private Integer secondAssistTime;

    private String groupImage;

    private String groupChar;

    private String groupRecord;

    private String disturbType;

    private String idioType;

    private Date createTime;

    private Date updateTime;

    private String states;

    //冗余字段
    private ArrayList<NounSenseCustom> list;

    public ArrayList<NounSenseCustom> getList() {
        return list;
    }

    public void setList(ArrayList<NounSenseCustom> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardAdjectiveImage() {
        return cardAdjectiveImage;
    }

    public void setCardAdjectiveImage(String cardAdjectiveImage) {
        this.cardAdjectiveImage = cardAdjectiveImage == null ? null : cardAdjectiveImage.trim();
    }

    public String getCardAdjectiveChar() {
        return cardAdjectiveChar;
    }

    public void setCardAdjectiveChar(String cardAdjectiveChar) {
        this.cardAdjectiveChar = cardAdjectiveChar == null ? null : cardAdjectiveChar.trim();
    }

    public String getCardAdjectiveRecord() {
        return cardAdjectiveRecord;
    }

    public void setCardAdjectiveRecord(String cardAdjectiveRecord) {
        this.cardAdjectiveRecord = cardAdjectiveRecord == null ? null : cardAdjectiveRecord.trim();
    }

    public Integer getFristAssistTime() {
        return fristAssistTime;
    }

    public void setFristAssistTime(Integer fristAssistTime) {
        this.fristAssistTime = fristAssistTime;
    }

    public String getCardNounImage() {
        return cardNounImage;
    }

    public void setCardNounImage(String cardNounImage) {
        this.cardNounImage = cardNounImage == null ? null : cardNounImage.trim();
    }

    public String getCardNounChar() {
        return cardNounChar;
    }

    public void setCardNounChar(String cardNounChar) {
        this.cardNounChar = cardNounChar == null ? null : cardNounChar.trim();
    }

    public String getCardNounRecord() {
        return cardNounRecord;
    }

    public void setCardNounRecord(String cardNounRecord) {
        this.cardNounRecord = cardNounRecord == null ? null : cardNounRecord.trim();
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

    public String getDisturbType() {
        return disturbType;
    }

    public void setDisturbType(String disturbType) {
        this.disturbType = disturbType == null ? null : disturbType.trim();
    }

    public String getIdioType() {
        return idioType;
    }

    public void setIdioType(String idioType) {
        this.idioType = idioType == null ? null : idioType.trim();
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