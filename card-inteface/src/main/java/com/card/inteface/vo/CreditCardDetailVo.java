package com.card.inteface.vo;

public class CreditCardDetailVo {

    private Integer id;
    private String cardLogo;
    private Integer bankId;
    private String bankName;
    private String cardName;
    private String cardFeature;
    private String cardLabel;
    private String cardCurrency;
    private String cardHairpinOrgani;
    private String freeInterestRule;
    private String integralRule;
    private String applyCondition;
    private String firstBrushCeremony;
    private String cardPrivilege;
    private String oprProcess;
    private String cardLeval;
    private Integer userConcernStatus;
    private String openCreditCardUrl;
    private String cardChooseLabel;
    private String creditCharacters;
    private String freeInterestPeriod;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardFeature() {
        return cardFeature;
    }

    public void setCardFeature(String cardFeature) {
        this.cardFeature = cardFeature;
    }

    public String getCardLabel() {
        cardLabel = cardChooseLabel;
        return cardLabel;
    }

    public void setCardLabel(String cardLabel) {
        this.cardLabel = cardLabel;
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

    public String getFreeInterestRule() {
        return freeInterestRule;
    }

    public void setFreeInterestRule(String freeInterestRule) {
        this.freeInterestRule = freeInterestRule;
    }

    public String getIntegralRule() {
        return integralRule;
    }

    public void setIntegralRule(String integralRule) {
        this.integralRule = integralRule;
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

    public String getCardLeval() {
        return cardLeval;
    }

    public void setCardLeval(String cardLeval) {
        this.cardLeval = cardLeval;
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

    public String getOpenCreditCardUrl() {
        return openCreditCardUrl;
    }

    public void setOpenCreditCardUrl(String openCreditCardUrl) {
        this.openCreditCardUrl = openCreditCardUrl;
    }

    public String getCardChooseLabel() {
        return cardChooseLabel;
    }

    public void setCardChooseLabel(String cardChooseLabel) {
        this.cardChooseLabel = cardChooseLabel;
    }

    public String getCreditCharacters() {
        return creditCharacters;
    }

    public void setCreditCharacters(String creditCharacters) {
        this.creditCharacters = creditCharacters;
    }

    public String getFreeInterestPeriod() {
        return freeInterestPeriod;
    }

    public void setFreeInterestPeriod(String freeInterestPeriod) {
        this.freeInterestPeriod = freeInterestPeriod;
    }
}
