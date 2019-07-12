package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "信用卡详情", discriminator = "CreditCardDetailParam", subTypes = {CreditCardDetailParam.class})
public class CreditCardDetailParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务key")
    private String businessKey;
    @ApiModelProperty(value = "信用卡Id")
    private Integer id;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
