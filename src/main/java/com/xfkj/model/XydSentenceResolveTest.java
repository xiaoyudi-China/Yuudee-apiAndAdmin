package com.xfkj.model;

import com.xfkj.common.model_custom.SentenceResolveTestCustom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XydSentenceResolveTest {
    private Integer id;

    private String startSlideshow;

    private String cardOneImage;

    private String cardOneChar;

    private String cardOneRecord;

    private Integer cardOneTime;

    private String cardTwoImage;

    private String cardTwoChar;

    private String cardTwoRecord;

    private Integer cardTwoTime;

    private String cardThreeImage;

    private String cardThreeChar;

    private String cardThreeRecord;

    private Integer cardThreeTime;

    private String cardFourImage;

    private String cardFourChar;

    private String cardFourRecord;

    private Integer cardFourTime;

    private String groupChar;

    private String groupRecord;

    private Date createTime;

    private Date updateTime;

    private String states;

    //冗余字段
    private List<SentenceResolveTestCustom> list;

    private ArrayList<String> startSlideshowList;

    private ArrayList<String> cardThreeImageList;

    public ArrayList<String> getStartSlideshowList() {
        return startSlideshowList;
    }

    public void setStartSlideshowList(ArrayList<String> startSlideshowList) {
        this.startSlideshowList = startSlideshowList;
    }

    public ArrayList<String> getCardThreeImageList() {
        return cardThreeImageList;
    }

    public void setCardThreeImageList(ArrayList<String> cardThreeImageList) {
        this.cardThreeImageList = cardThreeImageList;
    }

    public List<SentenceResolveTestCustom> getList() {
        return list;
    }

    public void setList(List<SentenceResolveTestCustom> list) {
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

    public Integer getCardOneTime() {
        return cardOneTime;
    }

    public void setCardOneTime(Integer cardOneTime) {
        this.cardOneTime = cardOneTime;
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

    public Integer getCardTwoTime() {
        return cardTwoTime;
    }

    public void setCardTwoTime(Integer cardTwoTime) {
        this.cardTwoTime = cardTwoTime;
    }

    public String getCardThreeImage() {
        return cardThreeImage;
    }

    public void setCardThreeImage(String cardThreeImage) {
        this.cardThreeImage = cardThreeImage == null ? null : cardThreeImage.trim();
    }

    public String getCardThreeChar() {
        return cardThreeChar;
    }

    public void setCardThreeChar(String cardThreeChar) {
        this.cardThreeChar = cardThreeChar == null ? null : cardThreeChar.trim();
    }

    public String getCardThreeRecord() {
        return cardThreeRecord;
    }

    public void setCardThreeRecord(String cardThreeRecord) {
        this.cardThreeRecord = cardThreeRecord == null ? null : cardThreeRecord.trim();
    }

    public Integer getCardThreeTime() {
        return cardThreeTime;
    }

    public void setCardThreeTime(Integer cardThreeTime) {
        this.cardThreeTime = cardThreeTime;
    }

    public String getCardFourImage() {
        return cardFourImage;
    }

    public void setCardFourImage(String cardFourImage) {
        this.cardFourImage = cardFourImage == null ? null : cardFourImage.trim();
    }

    public String getCardFourChar() {
        return cardFourChar;
    }

    public void setCardFourChar(String cardFourChar) {
        this.cardFourChar = cardFourChar == null ? null : cardFourChar.trim();
    }

    public String getCardFourRecord() {
        return cardFourRecord;
    }

    public void setCardFourRecord(String cardFourRecord) {
        this.cardFourRecord = cardFourRecord == null ? null : cardFourRecord.trim();
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

    public Integer getCardFourTime() {
        return cardFourTime;
    }

    public void setCardFourTime(Integer cardFourTime) {
        this.cardFourTime = cardFourTime;
    }
}