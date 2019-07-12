package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "保存用户信息返回", discriminator = "SaveUserResponse", subTypes = {SaveUserResponse.class})
public class SaveUserResponse extends Response{

    private static final long serialVersionUID = 1L;
}
