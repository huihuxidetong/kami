package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 删除自定义标签入参
 */
@ApiModel(value = "删除自定义标签入参", discriminator = "DelUserChonnseLableParam", subTypes = {DelUserChonnseLableParam.class})
public class DelUserChonnseLableParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务key")
    private String businessKey;
    @ApiModelProperty(value = "自定义标签")
    private String customLable;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getCustomLable() {
        return customLable;
    }

    public void setCustomLable(String customLable) {
        this.customLable = customLable;
    }
}
