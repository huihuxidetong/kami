package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "参与活动入参", discriminator = "JoinActivityParam", subTypes = {JoinActivityParam.class})
public class JoinActivityParam {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "业务key")
    private String businessKey;
    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "参与热")
    private String joinUserName;
    @ApiModelProperty(value = "参与人电话")
    private String joinUserMobile;
    @ApiModelProperty(value = "参与人电话")
    private String joinUserCreditCard;

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

    public String getJoinUserName() {
        return joinUserName;
    }

    public void setJoinUserName(String joinUserName) {
        this.joinUserName = joinUserName;
    }

    public String getJoinUserMobile() {
        return joinUserMobile;
    }

    public void setJoinUserMobile(String joinUserMobile) {
        this.joinUserMobile = joinUserMobile;
    }

    public String getJoinUserCreditCard() {
        return joinUserCreditCard;
    }

    public void setJoinUserCreditCard(String joinUserCreditCard) {
        this.joinUserCreditCard = joinUserCreditCard;
    }
}
