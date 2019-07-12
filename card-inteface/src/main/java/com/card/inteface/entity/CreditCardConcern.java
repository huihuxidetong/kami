package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;  

/**
 * @notes:信用卡关注实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class CreditCardConcern implements Serializable {

	private static final long serialVersionUID = 201809261303391L;
	
    private Integer id;
    
    private Integer bankCreditCardId;
    
    private Integer userId;
    
    private Date createTime;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getBankCreditCardId() {
        return bankCreditCardId;
    }

    public void setBankCreditCardId(Integer bankCreditCardId) {
        this.bankCreditCardId = bankCreditCardId;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}