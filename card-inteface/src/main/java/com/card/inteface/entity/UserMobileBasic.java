package com.card.inteface.entity;

import java.io.Serializable;

/**
 * @notes:用户手机基本信息实体类
 *
 * @author zzh
 *
 * 2018-10-18 11:02:04		Email:azhangzhihengi@163.com
 */
public class UserMobileBasic implements Serializable {

	private static final long serialVersionUID = 201810181102042L;
	
    private Integer id;
    
    private Integer userId;
    
    private String mobileBrand;
    
    private String mobileModel;
    
    private String mobileSystem;
    
    private String mobileDprWide;
    
    private String mobileDprHigh;
    
    private String screenWide;
    
    private String screenHigh;
    
    private String windowUseHigh;
    
    private String windowUseWidth;
    
    private String statusBarHigh;
    
    private String wecharLanguage;
    
    private String wecharVersion;
    
    private String clientPlatform;
    
    private String fontSize;
    
    private String clientPlatformVersion;
    
    private String performanceLeval;
    

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
    
}