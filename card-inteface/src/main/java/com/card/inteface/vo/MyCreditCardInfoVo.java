package com.card.inteface.vo;

import org.apache.commons.lang.StringUtils;

public class MyCreditCardInfoVo {

    private Integer id;
    private String cardLogo;
    private Integer bankId;
    private String bankName;
    private String cardName;
    private String bankLogo;
    private String creditCardNumber;
    private Integer userId;

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

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public String getCreditCardNumber() {
        if(StringUtils.isNotBlank(creditCardNumber)){
            String a = creditCardNumber.substring(creditCardNumber.length()-4,creditCardNumber.length()-3);
            String b = creditCardNumber.substring(creditCardNumber.length()-3,creditCardNumber.length()-2);
            String c = creditCardNumber.substring(creditCardNumber.length()-2,creditCardNumber.length()-1);
            String d = creditCardNumber.substring(creditCardNumber.length()-1,creditCardNumber.length());
            creditCardNumber = "****  ****  ****" + creditCardNumber.substring(creditCardNumber.length()-4,creditCardNumber.length());
        }
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static void main(String[] args) {
        String str = "6212261001025280634";
        String a = str.substring(str.length()-4,str.length()-3);
        String b = str.substring(str.length()-3,str.length()-2);
        String c = str.substring(str.length()-2,str.length()-1);
        String d = str.substring(str.length()-1,str.length());
        System.out.println("* * * *   * * * *   * * * * " + a + " " + b + " " + c + " " +d);
    }
}
