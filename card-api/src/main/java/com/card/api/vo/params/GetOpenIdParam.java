package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户获取openId", discriminator = "GetOpenIdParam", subTypes = {GetOpenIdParam.class})
public class GetOpenIdParam {

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
