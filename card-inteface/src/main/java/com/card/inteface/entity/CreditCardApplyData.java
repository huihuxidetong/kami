package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;  

/**
 * @notes:信用卡数据实体类
 *
 * @author zzh
 *
 * 2018-10-12 11:20:54		Email:azhangzhihengi@163.com
 */
public class CreditCardApplyData implements Serializable {

	private static final long serialVersionUID = 201810121120540L;
	
    private Integer id;
    
    private Integer creditCardId;
    
    private Integer userId;
    
    private Date createTime;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
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