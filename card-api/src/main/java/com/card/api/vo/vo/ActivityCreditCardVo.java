package com.card.api.vo.vo;

import io.swagger.annotations.ApiModelProperty;

public class ActivityCreditCardVo {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "信用卡id")
    private String id;
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    @ApiModelProperty(value = "信用卡名称")
    private String cardName;
    @ApiModelProperty(value = "信用卡logo")
    private String cardLogo;
    @ApiModelProperty(value = "查看人数")
    private Integer cardLookNumber;
    @ApiModelProperty(value = "特色")
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
