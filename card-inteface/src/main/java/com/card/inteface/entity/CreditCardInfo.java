package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;  

/**
 * @notes:信用卡信息实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class CreditCardInfo implements Serializable {

	private static final long serialVersionUID = 201809261303391L;
	
    private Integer id;
    
    private String cardLogo;
    
    private Integer bankId;
    
    private Integer bankCreditCardId;
    
    private String cardFeature;
    
    private String cardLeval;
    
    private String cardCurrency;
    
    private String cardHairpinOrgani;
    
    private String freeInterestPeriod;
    
    private String freeInterestRule;
    
    private String applyCondition;
    
    private String firstBrushCeremony;
    
    private String cardLabel;
    
    private String cardPrivilege;
    
    private String oprProcess;
    
    private Integer createUserId;
    
    private Date createTime;
    
    private Integer updateUserId;
    
    private Date updateTime;
    
    private Integer status;
    
    private Integer cardLookNumber;
    
    private Integer isHot;

    private String openCreditCardUrl;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCardLogo() {
        return cardLogo;
    }

    public void setCardLogo(String cardLogo) {
        this.cardLogo = cardLogo;
    }
    
    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }
    
    public Integer getBankCreditCardId() {
        return bankCreditCardId;
    }

    public void setBankCreditCardId(Integer bankCreditCardId) {
        this.bankCreditCardId = bankCreditCardId;
    }
    
    public String getCardFeature() {
        return cardFeature;
    }

    public void setCardFeature(String cardFeature) {
        this.cardFeature = cardFeature;
    }
    
    public String getCardLeval() {
        return cardLeval;
    }

    public void setCardLeval(String cardLeval) {
        this.cardLeval = cardLeval;
    }
    
    public String getCardCurrency() {
        return cardCurrency;
    }

    public void setCardCurrency(String cardCurrency) {
        this.cardCurrency = cardCurrency;
    }
    
    public String getCardHairpinOrgani() {
        return cardHairpinOrgani;
    }

    public void setCardHairpinOrgani(String cardHairpinOrgani) {
        this.cardHairpinOrgani = cardHairpinOrgani;
    }
    
    public String getFreeInterestPeriod() {
        return freeInterestPeriod;
    }

    public void setFreeInterestPeriod(String freeInterestPeriod) {
        this.freeInterestPeriod = freeInterestPeriod;
    }
    
    public String getFreeInterestRule() {
        return freeInterestRule;
    }

    public void setFreeInterestRule(String freeInterestRule) {
        this.freeInterestRule = freeInterestRule;
    }
    
    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition;
    }
    
    public String getFirstBrushCeremony() {
        return firstBrushCeremony;
    }

    public void setFirstBrushCeremony(String firstBrushCeremony) {
        this.firstBrushCeremony = firstBrushCeremony;
    }
    
    public String getCardLabel() {
        return cardLabel;
    }

    public void setCardLabel(String cardLabel) {
        this.cardLabel = cardLabel;
    }
    
    public String getCardPrivilege() {
        return cardPrivilege;
    }

    public void setCardPrivilege(String cardPrivilege) {
        this.cardPrivilege = cardPrivilege;
    }
    
    public String getOprProcess() {
        return oprProcess;
    }

    public void setOprProcess(String oprProcess) {
        this.oprProcess = oprProcess;
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
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getCardLookNumber() {
        return cardLookNumber;
    }

    public void setCardLookNumber(Integer cardLookNumber) {
        this.cardLookNumber = cardLookNumber;
    }
    
    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getOpenCreditCardUrl() {
        return openCreditCardUrl;
    }

    public void setOpenCreditCardUrl(String openCreditCardUrl) {
        this.openCreditCardUrl = openCreditCardUrl;
    }
}