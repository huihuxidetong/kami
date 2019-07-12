package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "获取openId返回", discriminator = "GetOpenIdResponse", subTypes = {GetOpenIdResponse.class})
public class GetOpenIdResponse extends Response{

    private static final long serialVersionUID = 1L;

    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
