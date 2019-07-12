package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "用户关注返回", discriminator = "AddUserConcernBasicResponse", subTypes = {AddUserConcernBasicResponse.class})
public class AddUserConcernBasicResponse extends Response{

    private static final long serialVersionUID = 1L;

}
