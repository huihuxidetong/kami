package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "微信获取sessionId返回", discriminator = "WxSessionV1Response", subTypes = {WxSessionResponse.class})
public class WxSessionResponse extends Response {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务器生成token")
    private String businessKey;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
}
