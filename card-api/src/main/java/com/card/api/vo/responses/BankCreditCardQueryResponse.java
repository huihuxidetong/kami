package com.card.api.vo.responses;

import com.card.api.vo.vo.CreditCardInfoQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "信用卡搜索返回", discriminator = "BankCreditCardQueryResponse", subTypes = {BankCreditCardQueryResponse.class})
public class BankCreditCardQueryResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "信用搜索返回")
    private List<CreditCardInfoQueryVo> creditCardInfoQueryVos;

    public List<CreditCardInfoQueryVo> getCreditCardInfoQueryVos() {
        return creditCardInfoQueryVos;
    }

    public void setCreditCardInfoQueryVos(List<CreditCardInfoQueryVo> creditCardInfoQueryVos) {
        this.creditCardInfoQueryVos = creditCardInfoQueryVos;
    }
}
