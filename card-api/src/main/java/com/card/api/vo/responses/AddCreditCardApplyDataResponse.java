package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "信用卡申请返回", discriminator = "AddCreditCardApplyDataResponse", subTypes = {AddCreditCardApplyDataResponse.class})
public class AddCreditCardApplyDataResponse extends Response{

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
