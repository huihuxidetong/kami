package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:活动申请实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class ActivityApply implements Serializable {

	private static final long serialVersionUID = 201809261303390L;
	
    private Integer id;
    
    private Integer activityId;
    
    private String joinUserName;
    
    private String joinUserMobile;
    
    private String joinUserCreditCard;
    
    private Integer createUserId;
    
    private String createUserName;

    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
    
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}