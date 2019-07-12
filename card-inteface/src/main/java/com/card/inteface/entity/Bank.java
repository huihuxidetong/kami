package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;  

/**
 * @notes:银行实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class Bank implements Serializable {

	private static final long serialVersionUID = 201809261303390L;
	
    private Integer id;
    
    private String bankName;
    
    private String bankLogo;
    
    private String bankContactsName;
    
    private String bankContactsTel;
    
    private String bankContactsNotic;
    
    private Integer createUserId;
    
    private Date createTime;
    
    private Integer updateUserId;
    
    private Date updateTime;
    
    private Integer status;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }
    
    public String getBankContactsName() {
        return bankContactsName;
    }

    public void setBankContactsName(String bankContactsName) {
        this.bankContactsName = bankContactsName;
    }
    
    public String getBankContactsTel() {
        return bankContactsTel;
    }

    public void setBankContactsTel(String bankContactsTel) {
        this.bankContactsTel = bankContactsTel;
    }
    
    public String getBankContactsNotic() {
        return bankContactsNotic;
    }

    public void setBankContactsNotic(String bankContactsNotic) {
        this.bankContactsNotic = bankContactsNotic;
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
    
}