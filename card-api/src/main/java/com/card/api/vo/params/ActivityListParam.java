package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "活动列表入参", discriminator = "ActivityListParam", subTypes = {ActivityListParam.class})
public class ActivityListParam {

    private static final long serialVersionUID = 1L;


}
