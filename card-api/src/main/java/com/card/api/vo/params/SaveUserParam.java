package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息
 */
@ApiModel(value = "用户信息", discriminator = "SaveUserParam", subTypes = {SaveUserParam.class})
public class SaveUserParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务key")
    private String businessKey;
    @ApiModelProperty(value = "微信头像")
    private String userPortrait;
    @ApiModelProperty(value = "微信昵称")
    private String nickName;
    @ApiModelProperty(value = "渠道标识" ,required = false)
    private String channelCode;
    @ApiModelProperty(value = "手机品牌")
    private String mobileBrand;
    @ApiModelProperty(value = "手机型号")
    private String mobileModel;
    @ApiModelProperty(value = "手机系统")
    private String mobileSystem;
    @ApiModelProperty(value = "像素宽")
    private String mobileDprWide;
    @ApiModelProperty(value = "像素高")
    private String mobileDprHigh;
    @ApiModelProperty(value = "屏幕宽")
    private String screenWide;
    @ApiModelProperty(value = "屏幕高")
    private String screenHigh;
    @ApiModelProperty(value = "状态栏高度")
    private String statusBarHigh;
    @ApiModelProperty(value = "微信语言")
    private String wecharLanguage;
    @ApiModelProperty(value = "微信版本")
    private String wecharVersion;
    @ApiModelProperty(value = "客户端平台")
    private String clientPlatform;
    @ApiModelProperty(value = "字体大小")
    private String fontSize;
    @ApiModelProperty(value = "客户端基础版本")
    private String clientPlatformVersion;
    @ApiModelProperty(value = "性能等级")
    private String performanceLeval;
    @ApiModelProperty(value = "可使用窗口高度")
    private String windowUseHigh;
    @ApiModelProperty(value = "可使用窗口宽度")
    private String windowUseWidth;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
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

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
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

    public String getPerformanceLeval() {
        return performanceLeval;
    }

    public void setPerformanceLeval(String performanceLeval) {
        this.performanceLeval = performanceLeval;
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
