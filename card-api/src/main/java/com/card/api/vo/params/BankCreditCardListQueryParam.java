package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 信用卡列表搜索
 */
@ApiModel(value = "信用卡列表搜索入参", discriminator = "BankCreditCardListQueryParam", subTypes = {BankCreditCardListQueryParam.class})
public class BankCreditCardListQueryParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "银行Id")
    private Integer bankId;
    @ApiModelProperty(value = "信用卡名称")
    private String creditCardName;

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }
}
