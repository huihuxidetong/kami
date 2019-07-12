package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 添加我的信用卡
 */
@ApiModel(value = "添加我的信用卡", discriminator = "AddMyCreditCardParam", subTypes = {AddMyCreditCardParam.class})
public class AddMyCreditCardParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务key")
    private String businessKey;
    @ApiModelProperty(value = "持卡人姓名")
    private String creditCardHolder;
    @ApiModelProperty(value = "卡号")
    private String creditCardNumber;
    @ApiModelProperty(value = "发卡行id")
    private Integer cardIssuingBank;
    @ApiModelProperty(value = "信用卡id")
    private Integer cardIssuingBankType;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getCreditCardHolder() {
        return creditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        this.creditCardHolder = creditCardHolder;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Integer getCardIssuingBank() {
        return cardIssuingBank;
    }

    public void setCardIssuingBank(Integer cardIssuingBank) {
        this.cardIssuingBank = cardIssuingBank;
    }

    public Integer getCardIssuingBankType() {
        return cardIssuingBankType;
    }

    public void setCardIssuingBankType(Integer cardIssuingBankType) {
        this.cardIssuingBankType = cardIssuingBankType;
    }
}
