package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "添加自定义标签返回", discriminator = "AddCustomLableResponse", subTypes = {AddCustomLableResponse.class})
public class AddCustomLableResponse extends Response{

    private static final long serialVersionUID = 1L;


}
