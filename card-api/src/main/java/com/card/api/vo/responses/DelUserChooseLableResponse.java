package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "删除用户标签", discriminator = "DelUserChooseLableResponse", subTypes = {DelUserChooseLableResponse.class})
public class DelUserChooseLableResponse extends Response{

    private static final long serialVersionUID = 1L;


}
