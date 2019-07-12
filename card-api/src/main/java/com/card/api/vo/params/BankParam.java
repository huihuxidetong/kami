package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "银行信息入参", discriminator = "BankParam", subTypes = {BankParam.class})
public class BankParam {

    private static final long serialVersionUID = 1L;


}
