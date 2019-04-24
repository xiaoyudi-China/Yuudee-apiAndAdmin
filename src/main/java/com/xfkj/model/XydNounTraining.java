package com.xfkj.model;

import java.util.Date;

public class XydNounTraining {
    private Integer id;

    private String wireImage;

    private String wireChar;

    private String wireRecord;

    private String colorPenChar;

    private String colorPenRecord;

    private String groupImage;

    private String groupRecord;

    private Date createTime;

    private Date updateTime;

    private String states;

    private String groupWord;

    public String getGroupWord() {
        return groupWord;
    }

    public void setGroupWord(String groupWord) {
        this.groupWord = groupWord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWireImage() {
        return wireImage;
    }

    public void setWireImage(String wireImage) {
        this.wireImage = wireImage == null ? null : wireImage.trim();
    }

    public String getWireChar() {
        return wireChar;
    }

    public void setWireChar(String wireChar) {
        this.wireChar = wireChar == null ? null : wireChar.trim();
    }

    public String getWireRecord() {
        return wireRecord;
    }

    public void setWireRecord(String wireRecord) {
        this.wireRecord = wireRecord == null ? null : wireRecord.trim();
    }

    public String getColorPenChar() {
        return colorPenChar;
    }

    public void setColorPenChar(String colorPenChar) {
        this.colorPenChar = colorPenChar == null ? null : colorPenChar.trim();
    }

    public String getColorPenRecord() {
        return colorPenRecord;
    }

    public void setColorPenRecord(String colorPenRecord) {
        this.colorPenRecord = colorPenRecord == null ? null : colorPenRecord.trim();
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage == null ? null : groupImage.trim();
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