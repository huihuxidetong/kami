package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "参与活动返回", discriminator = "JoinActivityResponse", subTypes = {JoinActivityResponse.class})
public class JoinActivityResponse extends Response{

    private static final long serialVersionUID = 1L;

}
