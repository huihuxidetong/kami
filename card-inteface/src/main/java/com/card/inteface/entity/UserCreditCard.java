package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;  

/**
 * @notes:用户绑定信用卡实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class UserCreditCard implements Serializable {

	private static final long serialVersionUID = 201809261303390L;
	
    private Integer id;
    
    private Integer userId;
    
    private String creditCardHolder;
    
    private String creditCardNumber;
    
    private Integer cardIssuingBank;
    
    private Integer cardIssuingBankType;
    
    private Date createTime;
    

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
    
    public String getCreditCardHolder() {
        return creditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        this.creditCardHolder = creditCardHolder;
    }
    
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    
    public Integer getCardIssuingBank() {
        return cardIssuingBank;
    }

    public void setCardIssuingBank(Integer cardIssuingBank) {
        this.cardIssuingBank = cardIssuingBank;
    }
    
    public Integer getCardIssuingBankType() {
        return cardIssuingBankType;
    }

    public void setCardIssuingBankType(Integer cardIssuingBankType) {
        this.cardIssuingBankType = cardIssuingBankType;
    }
    
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}