package com.card.inteface.vo;

public class ActivityCreditCardVo {

    private Integer activityId;
    private String id;
    private String bankName;
    private String cardName;
    private String cardLogo;
    private Integer cardLookNumber;
    private String cardFeature;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardLogo() {
        return cardLogo;
    }

    public void setCardLogo(String cardLogo) {
        this.cardLogo = cardLogo;
    }

    public String getCardFeature() {
        return cardFeature;
    }

    public void setCardFeature(String cardFeature) {
        this.cardFeature = cardFeature;
    }

    public Integer getCardLookNumber() {
        return cardLookNumber;
    }

    public void setCardLookNumber(Integer cardLookNumber) {
        this.cardLookNumber = cardLookNumber;
    }
}
