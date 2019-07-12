package com.card.inteface.vo;

import java.util.Date;

public class ActivityDetailVo {

    private Integer id;
    private String activityName;
    private Date activityTimeStart;
    private Date activityTimeEnd;
    private String activityPosition;
    private String describe;
    private String notic;
    private Integer isHasSignUp;
    private Integer userConcernStatus;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserConcernStatus() {
        if(null == userConcernStatus){
            userConcernStatus = 0;
        }
        return userConcernStatus;
    }

    public void setUserConcernStatus(Integer userConcernStatus) {
        this.userConcernStatus = userConcernStatus;
    }
}
