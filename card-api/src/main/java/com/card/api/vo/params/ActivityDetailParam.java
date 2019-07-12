package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

@ApiModel(value = "活动详情入参", discriminator = "ActivityListParam", subTypes = {ActivityDetailParam.class})
public class ActivityDetailParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务key")
    private String businessKey;
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
