package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户取消关注", discriminator = "UpdateUserConcernBasicParam", subTypes = {UpdateUserConcernBasicParam.class})
public class UpdateUserConcernBasicParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务key")
    private String businessKey;
    @ApiModelProperty(value = "关注类型 1=信用卡 2=活动")
    private Integer concernType;
    @ApiModelProperty(value = "关注id")
    private Integer concernId;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Integer getConcernType() {
        return concernType;
    }

    public void setConcernType(Integer concernType) {
        this.concernType = concernType;
    }

    public Integer getConcernId() {
        return concernId;
    }

    public void setConcernId(Integer concernId) {
        this.concernId = concernId;
    }
}
