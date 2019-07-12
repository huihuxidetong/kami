package com.card.inteface.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:用户机基本信息实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class UserBasicVo implements Serializable {

	private static final long serialVersionUID = 201809261303391L;
	
    private Integer id;
    
    private Integer userId;
    
    private String mobileBrand;
    
    private String mobileModel;
    
    private String mobileSystem;
    
    private String mobileDprWide;
    
    private String mobileDprHigh;
    
    private String screenWide;
    
    private String screenHigh;
    
    private String statusBarHigh;
    
    private String wecharLanguage;
    
    private String wecharVersion;
    
    private String clientPlatform;
    
    private String fontSize;
    
    private String clientPlatformVersion;
    
    private String performanceLeval;

    private String openId;

    private String userPortrait;

    private Date createTime;

    private Date updateTime;

    private String nickName;

    private String channelCode;

    private String windowUseHigh;
    private String windowUseWidth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(String mobileBrand) {
        this.mobileBrand = mobileBrand;
    }
    
    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }
    
    public String getMobileSystem() {
        return mobileSystem;
    }

    public void setMobileSystem(String mobileSystem) {
        this.mobileSystem = mobileSystem;
    }
    
    public String getMobileDprWide() {
        return mobileDprWide;
    }

    public void setMobileDprWide(String mobileDprWide) {
        this.mobileDprWide = mobileDprWide;
    }
    
    public String getMobileDprHigh() {
        return mobileDprHigh;
    }

    public void setMobileDprHigh(String mobileDprHigh) {
        this.mobileDprHigh = mobileDprHigh;
    }
    
    public String getScreenWide() {
        return screenWide;
    }

    public void setScreenWide(String screenWide) {
        this.screenWide = screenWide;
    }
    
    public String getScreenHigh() {
        return screenHigh;
    }

    public void setScreenHigh(String screenHigh) {
        this.screenHigh = screenHigh;
    }
    
    public String getStatusBarHigh() {
        return statusBarHigh;
    }

    public void setStatusBarHigh(String statusBarHigh) {
        this.statusBarHigh = statusBarHigh;
    }
    
    public String getWecharLanguage() {
        return wecharLanguage;
    }

    public void setWecharLanguage(String wecharLanguage) {
        this.wecharLanguage = wecharLanguage;
    }
    
    public String getWecharVersion() {
        return wecharVersion;
    }

    public void setWecharVersion(String wecharVersion) {
        this.wecharVersion = wecharVersion;
    }
    
    public String getClientPlatform() {
        return clientPlatform;
    }

    public void setClientPlatform(String clientPlatform) {
        this.clientPlatform = clientPlatform;
    }
    
    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }
    
    public String getClientPlatformVersion() {
        return clientPlatformVersion;
    }

    public void setClientPlatformVersion(String clientPlatformVersion) {
        this.clientPlatformVersion = clientPlatformVersion;
    }
    
    public String getPerformanceLeval() {
        return performanceLeval;
    }

    public void setPerformanceLeval(String performanceLeval) {
        this.performanceLeval = performanceLeval;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getWindowUseHigh() {
        return windowUseHigh;
    }

    public void setWindowUseHigh(String windowUseHigh) {
        this.windowUseHigh = windowUseHigh;
    }

    public String getWindowUseWidth() {
        return windowUseWidth;
    }

    public void setWindowUseWidth(String windowUseWidth) {
        this.windowUseWidth = windowUseWidth;
    }
}