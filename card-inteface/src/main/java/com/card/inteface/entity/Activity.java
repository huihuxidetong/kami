package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;  

/**
 * @notes:活动实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class Activity implements Serializable {

	private static final long serialVersionUID = 201809261303391L;
	
    private Integer id;
    
    private String bankId;
    
    private String bankCreditCardId;
    
    private String activityName;
    
    private Date activityTimeStart;
    
    private Date activityTimeEnd;
    
    private String activityPosition;
    
    private Integer activityType;
    
    private Integer isHasSignUp;
    
    private String describe;
    
    private String activityCustomLable;
    
    private String notic;
    
    private Integer createUserId;
    
    private Date createTime;
    
    private Integer updateUserId;
    
    private Date updateTime;
    
    private Integer activityLookNumber;

    private Integer status;
    

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
    
    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }
    
    public Integer getIsHasSignUp() {
        return isHasSignUp;
    }

    public void setIsHasSignUp(Integer isHasSignUp) {
        this.isHasSignUp = isHasSignUp;
    }
    
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
    public String getActivityCustomLable() {
        return activityCustomLable;
    }

    public void setActivityCustomLable(String activityCustomLable) {
        this.activityCustomLable = activityCustomLable;
    }
    
    public String getNotic() {
        return notic;
    }

    public void setNotic(String notic) {
        this.notic = notic;
    }
    
    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
    
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Integer getActivityLookNumber() {
        return activityLookNumber;
    }

    public void setActivityLookNumber(Integer activityLookNumber) {
        this.activityLookNumber = activityLookNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}