package com.card.api.vo.responses;

import com.card.api.vo.vo.ActivityCreditCardVo;
import com.card.api.vo.vo.BankVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "查询银行信息返回", discriminator = "BankResponse", subTypes = {BankResponse.class})
public class BankResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "银行信息")
    private List<BankVo> bankVoList;

    public List<BankVo> getBankVoList() {
        return bankVoList;
    }

    public void setBankVoList(List<BankVo> bankVoList) {
        this.bankVoList = bankVoList;
    }
}
