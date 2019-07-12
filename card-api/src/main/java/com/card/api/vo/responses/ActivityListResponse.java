package com.card.api.vo.responses;

import com.card.api.vo.vo.ActivityIndexVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "回复评论信息返回", discriminator = "AddCommentReplyResponse", subTypes = {ActivityListResponse.class})
public class ActivityListResponse extends Response{

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "银行活动列表")
    public List<ActivityIndexVo> activityVoList4Bank;
    @ApiModelProperty(value = "平台活动列表")
    public List<ActivityIndexVo> activityVoList4Platform;

    public List<ActivityIndexVo> getActivityVoList4Bank() {
        return activityVoList4Bank;
    }

    public void setActivityVoList4Bank(List<ActivityIndexVo> activityVoList4Bank) {
        this.activityVoList4Bank = activityVoList4Bank;
    }

    public List<ActivityIndexVo> getActivityVoList4Platform() {
        return activityVoList4Platform;
    }

    public void setActivityVoList4Platform(List<ActivityIndexVo> activityVoList4Platform) {
        this.activityVoList4Platform = activityVoList4Platform;
    }
}
