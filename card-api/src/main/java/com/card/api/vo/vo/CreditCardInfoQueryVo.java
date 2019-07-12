package com.card.api.vo.vo;

import io.swagger.annotations.ApiModelProperty;

public class CreditCardInfoQueryVo {

    @ApiModelProperty(value = "信用卡id")
    private Integer id;
    @ApiModelProperty(value = "信用卡名称")
    private String cardName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
