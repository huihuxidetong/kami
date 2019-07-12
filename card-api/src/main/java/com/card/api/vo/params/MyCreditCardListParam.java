package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 我的信用卡列表
 */
@ApiModel(value = "我的信用卡列表", discriminator = "MyCreditCardListParam", subTypes = {MyCreditCardListParam.class})
public class MyCreditCardListParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务key")
    private String businessKey;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

}
