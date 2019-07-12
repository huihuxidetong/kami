package com.card.api.vo.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:活动实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class ActivityIndexVo implements Serializable {

	private static final long serialVersionUID = 201809261303391L;

	@ApiModelProperty(value = "活动id")
    private Integer id;
    @ApiModelProperty(value = "活动银行id")
    private String bankId;
    @ApiModelProperty(value = "活动信用卡id")
    private String bankCreditCardId;
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    @ApiModelProperty(value = "活动开始时间")
    private Date activityTimeStart;
    @ApiModelProperty(value = "活动结束时间")
    private Date activityTimeEnd;
    @ApiModelProperty(value = "活动地点")
    private String activityPosition;
    @ApiModelProperty(value = "活动图片")
    private String activityImageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankCreditCardId() {
        return bankCreditCardId;
    }

    public void setBankCreditCardId(String bankCreditCardId) {
        this.bankCreditCardId = bankCreditCardId;
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

    public String getActivityImageUrl() {
        return activityImageUrl;
    }

    public void setActivityImageUrl(String activityImageUrl) {
        this.activityImageUrl = activityImageUrl;
    }
}