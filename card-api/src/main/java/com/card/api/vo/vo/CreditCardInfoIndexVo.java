package com.card.api.vo.vo;

import io.swagger.annotations.ApiModelProperty;

public class CreditCardInfoIndexVo {

    @ApiModelProperty(value = "信用卡id")
    private Integer id;
    @ApiModelProperty(value = "信用卡logo")
    private String cardLogo;
    @ApiModelProperty(value = "信用卡银行id")
    private Integer bankId;
    @ApiModelProperty(value = "信用卡银行名称")
    private String bankName;
    @ApiModelProperty(value = "信用卡名称")
    private String cardName;
    @ApiModelProperty(value = "信用卡查看人数")
    private Integer cardLookNumber;
    @ApiModelProperty(value = "信用卡特色")
    private String cardFeature;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardLogo() {
        return cardLogo;
    }

    public void setCardLogo(String cardLogo) {
        this.cardLogo = cardLogo;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
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
