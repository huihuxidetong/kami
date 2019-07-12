package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "用户取消关注返回", discriminator = "UpdateUserConcernBasicResponse", subTypes = {UpdateUserConcernBasicResponse.class})
public class UpdateUserConcernBasicResponse extends Response{

    private static final long serialVersionUID = 1L;

}
