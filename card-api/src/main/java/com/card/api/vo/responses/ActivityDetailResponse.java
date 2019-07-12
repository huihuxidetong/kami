package com.card.api.vo.responses;

import com.card.api.vo.vo.ActivityCreditCardVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "活动详情信息返回", discriminator = "ActivityDetailResponse", subTypes = {ActivityDetailResponse.class})
public class ActivityDetailResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动id")
    private Integer id;
    @ApiModelProperty(value = "活动图片")
    private List<String> activityUmageUrl;
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    @ApiModelProperty(value = "活动开始时间")
    private Date activityTimeStart;
    @ApiModelProperty(value = "活动结束时间")
    private Date activityTimeEnd;
    @ApiModelProperty(value = "活动地点")
    private String activityPosition;
    @ApiModelProperty(value = "活动须知 ;分隔")
    private String describe;
    @ApiModelProperty(value = "活动描述")
    private String notic;
    @ApiModelProperty(value = "是否需要报名 1=是 0=否")
    private Integer isHasSignUp;
    @ApiModelProperty(value = "相关信用卡")
    private List<ActivityCreditCardVo> activityCreditCardVoList;
    @ApiModelProperty(value = "是否关注 1=是 0=否")
    private Integer userConcernStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getActivityUmageUrl() {
        return activityUmageUrl;
    }

    public void setActivityUmageUrl(List<String> activityUmageUrl) {
        this.activityUmageUrl = activityUmageUrl;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getActivityTimeStart() {
        return activityTimeStart;
    }

    public void setActivityTimeStart(Date activityTimeStart) {
        this.activityTimeStart = activityTimeStart;
    }

    public Date getActivityTimeEnd() {
        return activityTimeEnd;
    }

    public void setActivityTimeEnd(Date activityTimeEnd) {
        this.activityTimeEnd = activityTimeEnd;
    }

    public String getActivityPosition() {
        return activityPosition;
    }

    public void setActivityPosition(String activityPosition) {
        this.activityPosition = activityPosition;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getNotic() {
        return notic;
    }

    public void setNotic(String notic) {
        this.notic = notic;
    }

    public Integer getIsHasSignUp() {
        return isHasSignUp;
    }

    public void setIsHasSignUp(Integer isHasSignUp) {
        this.isHasSignUp = isHasSignUp;
    }

    public List<ActivityCreditCardVo> getActivityCreditCardVoList() {
        return activityCreditCardVoList;
    }

    public void setActivityCreditCardVoList(List<ActivityCreditCardVo> activityCreditCardVoList) {
        this.activityCreditCardVoList = activityCreditCardVoList;
    }

    public Integer getUserConcernStatus() {
        return userConcernStatus;
    }

    public void setUserConcernStatus(Integer userConcernStatus) {
        this.userConcernStatus = userConcernStatus;
    }
}
