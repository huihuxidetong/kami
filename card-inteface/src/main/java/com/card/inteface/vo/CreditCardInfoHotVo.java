package com.card.inteface.vo;

public class CreditCardInfoHotVo {

    private Integer id;
    private String cardLogo;
    private Integer bankId;
    private String bankName;
    private String bankLogo;
    private String cardName;
    private Integer cardLookNumber;
    private String cardFeature;
    private Integer bankCreditCardId;
    private Integer creditCardApplyNumber;

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

    public Integer getCardLookNumber() {
        return cardLookNumber;
    }

    public void setCardLookNumber(Integer cardLookNumber) {
        this.cardLookNumber = cardLookNumber;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public Integer getBankCreditCardId() {
        return bankCreditCardId;
    }

    public void setBankCreditCardId(Integer bankCreditCardId) {
        this.bankCreditCardId = bankCreditCardId;
    }

    public Integer getCreditCardApplyNumber() {
        return creditCardApplyNumber;
    }

    public void setCreditCardApplyNumber(Integer creditCardApplyNumber) {
        this.creditCardApplyNumber = creditCardApplyNumber;
    }
}
