package com.card.inteface.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes相关活动
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class CreditDetailActivityVo implements Serializable {

	private static final long serialVersionUID = 201809261303391L;

	private Integer activityId;
    private String activityName;
    private Date activityTimeEnd;
    private Date activityTimeStart;
    private String activityPosition;
    private String activityImage;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getActivityTimeEnd() {
        return activityTimeEnd;
    }

    public void setActivityTimeEnd(Date activityTimeEnd) {
        this.activityTimeEnd = activityTimeEnd;
    }

    public Date getActivityTimeStart() {
        return activityTimeStart;
    }

    public void setActivityTimeStart(Date activityTimeStart) {
        this.activityTimeStart = activityTimeStart;
    }

    public String getActivityPosition() {
        return activityPosition;
    }

    public void setActivityPosition(String activityPosition) {
        this.activityPosition = activityPosition;
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }
}