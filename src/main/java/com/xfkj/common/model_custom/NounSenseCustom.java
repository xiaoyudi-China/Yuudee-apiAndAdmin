package com.xfkj.common.model_custom;

/**
 * Created by King on 2018/10/8.
 */
public class NounSenseCustom {

    //是否正确 0否 1正确
    private String IsSuccess;

    //形容词还是名词
    private String IsAdj;

    private String cardAdjectiveImage;

    private String cardAdjectiveChar;

    private String cardAdjectiveRecord;

    private Integer assistTime;

    public String getIsAdj() {
        return IsAdj;
    }

    public void setIsAdj(String isAdj) {
        IsAdj = isAdj;
    }

    public String getIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        IsSuccess = isSuccess;
    }

    public String getCardAdjectiveImage() {
        return cardAdjectiveImage;
    }

    public void setCardAdjectiveImage(String cardAdjectiveImage) {
        this.cardAdjectiveImage = cardAdjectiveImage;
    }

    public String getCardAdjectiveChar() {
        return cardAdjectiveChar;
    }

    public void setCardAdjectiveChar(String cardAdjectiveChar) {
        this.cardAdjectiveChar = cardAdjectiveChar;
    }

    public String getCardAdjectiveRecord() {
        return cardAdjectiveRecord;
    }

    public void setCardAdjectiveRecord(String cardAdjectiveRecord) {
        this.cardAdjectiveRecord = cardAdjectiveRecord;
    }

    public Integer getAssistTime() {
        return assistTime;
    }

    public void setAssistTime(Integer assistTime) {
        this.assistTime = assistTime;
    }
}