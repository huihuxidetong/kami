package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;  

/**
 * @notes:用户实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class User implements Serializable {

	private static final long serialVersionUID = 201809261303390L;
	
    private Integer id;
    
    private String openId;
    
    private Integer userIntegral;
    
    private String userPortrait;
    
    private String userChooseLable;
    
    private String userCustomLable;
    
    private Date createTime;
    
    private Integer updateUserId;
    
    private Date updateTime;

    private String nickName;

    private String channelCode;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public Integer getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }
    
    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }
    
    public String getUserChooseLable() {
        return userChooseLable;
    }

    public void setUserChooseLable(String userChooseLable) {
        this.userChooseLable = userChooseLable;
    }
    
    public String getUserCustomLable() {
        return userCustomLable;
    }

    public void setUserCustomLable(String userCustomLable) {
        this.userCustomLable = userCustomLable;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
}