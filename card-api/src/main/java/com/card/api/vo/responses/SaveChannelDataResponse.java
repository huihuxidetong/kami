package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "保存渠道点击返回", discriminator = "SaveChannelDataResponse", subTypes = {SaveChannelDataResponse.class})
public class SaveChannelDataResponse extends Response{

}
