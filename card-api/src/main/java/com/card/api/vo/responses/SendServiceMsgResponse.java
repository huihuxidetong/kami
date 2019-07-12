package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "发送给客服消息返回", discriminator = "SendServiceMsgResponse", subTypes = {SendServiceMsgResponse.class})
public class SendServiceMsgResponse extends Response{

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
