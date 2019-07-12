package com.card.inteface.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:活动实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class ActivityVo implements Serializable {

	private static final long serialVersionUID = 201809261303391L;

    private Integer id;

    private String bankId;

    private String bankCreditCardId;

    private String activityName;
    
    private Date activityTimeStart;
    
    private Date activityTimeEnd;
    
    private String activityPosition;

    private String activityImageUrl;

    private Integer activityType;

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

    public String getActivityImageUrl() {
        return activityImageUrl;
    }

    public void setActivityImageUrl(String activityImageUrl) {
        this.activityImageUrl = activityImageUrl;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
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
}