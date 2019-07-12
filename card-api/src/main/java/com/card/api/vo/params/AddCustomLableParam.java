package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 添加自定义标签入参
 */
@ApiModel(value = "添加自定义标签入参", discriminator = "AddCustomLableParam", subTypes = {AddCustomLableParam.class})
public class AddCustomLableParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务key")
    private String businessKey;
    @ApiModelProperty(value = "自定义标签")
    private String customLable;
    @ApiModelProperty(value = "是否是新增标签 1=是 0 =否")
    private Integer isNewLable;

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

    public Integer getIsNewLable() {
        return isNewLable;
    }

    public void setIsNewLable(Integer isNewLable) {
        this.isNewLable = isNewLable;
    }
}
