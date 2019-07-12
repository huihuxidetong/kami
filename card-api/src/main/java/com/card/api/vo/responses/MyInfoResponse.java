package com.card.api.vo.responses;

import com.card.api.vo.vo.ActivityCreditCardVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "我的信息返回", discriminator = "MyInfoResponse", subTypes = {MyInfoResponse.class})
public class MyInfoResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户头像")
    private String userPortrait;
    @ApiModelProperty(value = "用户名")
    private String nickName;
    @ApiModelProperty(value = "用户积分")
    private Integer userIntegral;
    @ApiModelProperty(value = "用户已选择标签,逗号分隔 userChooseLable=a,b,c")
    private String userChooseLable;
    @ApiModelProperty(value = "用户绑定信用卡图片")
    private Integer userCreditCardLCount;
    @ApiModelProperty(value = "是否有未读消息")
    private Integer isHasNoReadComment;
    @ApiModelProperty(value = "爱领卡图片url")
    private String ailingkaCodeUrl;

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }

    public String getUserChooseLable() {
        return userChooseLable;
    }

    public void setUserChooseLable(String userChooseLable) {
        this.userChooseLable = userChooseLable;
    }

    public Integer getUserCreditCardLCount() {
        return userCreditCardLCount;
    }

    public void setUserCreditCardLCount(Integer userCreditCardLCount) {
        this.userCreditCardLCount = userCreditCardLCount;
    }

    public Integer getIsHasNoReadComment() {
        return isHasNoReadComment;
    }

    public void setIsHasNoReadComment(Integer isHasNoReadComment) {
        this.isHasNoReadComment = isHasNoReadComment;
    }

    public String getAilingkaCodeUrl() {
        return ailingkaCodeUrl;
    }

    public void setAilingkaCodeUrl(String ailingkaCodeUrl) {
        this.ailingkaCodeUrl = ailingkaCodeUrl;
    }
}
